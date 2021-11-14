package com.sige.application.resources;

import com.sige.application.enums.ExceptionOperacao;
import com.sige.application.enums.FormaPagamento;
import com.sige.application.enums.StatusCarrinho;
import com.sige.application.enums.StatusPagamento;
import com.sige.application.exception.CampoException;
import com.sige.application.model.*;
import com.sige.application.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoResource {

    @Autowired
    CarrinhoRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ItemCarrinhoRepository itemCarrinhoRepository;

    @Autowired
    ParametrosRepository parametrosRepository;

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    IngressoRepository ingressoRepository;

    @Autowired
    CartaoRepository cartaoRepository;

    @GetMapping(value = "/{id}")
    public Carrinho getByUsuario(@PathVariable int id){
        Usuario usuario = usuarioRepository.findById((long) id).get();

        if(usuario == null){
            throw new CampoException("Usuario", "Não encontrado usuário para o id informado", "Não encontrado usuário para o id informado", ExceptionOperacao.L);
        }

        Carrinho carrinho = repository.getByUsuario(usuario, StatusCarrinho.A);

        if(carrinho == null){
            carrinho = new Carrinho(0, usuario, null ,0, null, null, StatusPagamento.S, StatusCarrinho.A);
            carrinho = repository.save(carrinho);
        }

        return carrinho;
    }

    @PostMapping()
    public Carrinho save(@Valid @RequestBody Carrinho carrinho){
        if(!carrinho.getItemCarrinhos().isEmpty()){
            Parametros parametros = parametrosRepository.findById(1L).get();
            Logger.getLogger("PARAMETROS").info("Percentual permitido: " + parametros.getPercentualCapacidade());
            List<ItemCarrinho> itens = carrinho.getItemCarrinhos();
            Map<Evento, Integer> eventos = new HashMap<Evento, Integer>();

            //Quantidade de ingressos por evento no carrinho
            itens.forEach(itemCarrinho -> {
                if(eventos.containsKey(itemCarrinho.getIngresso().getEvento())){
                    Integer qtd = eventos.get(itemCarrinho.getIngresso().getEvento());
                    qtd += 1;
                    eventos.replace(itemCarrinho.getIngresso().getEvento(), qtd);
                }else {
                    eventos.put(itemCarrinho.getIngresso().getEvento(), 1);
                }
            });

            //Valida a quantidade de ingressos e se a compra poderá prosseguir
            eventos.forEach((evento, qtd) -> {
                Logger.getLogger(evento.getNome()).info(qtd+"");
                if(qtd > parametros.getQtdPessoa()){
                    throw new CampoException("quantidade", "A quantidade máxima de ingressos por pessoa é " + parametros.getQtdPessoa(), "A quantidade máxima de ingressos por pessoa é " + parametros.getQtdPessoa(), ExceptionOperacao.C);
                }

                Integer permitidaLocal = evento.getLocal().getCapacidade() * parametros.getPercentualCapacidade() / 100;
                long vendido = eventoRepository.getIngressosVendidos(evento);
                long previsao = vendido + qtd;
                long permitidaVenda = permitidaLocal - vendido;

                Logger.getLogger("EVENTO").info("Permitida local: " + permitidaLocal);
                Logger.getLogger("EVENTO").info("Vendido: " + vendido);
                Logger.getLogger("EVENTO").info("Previsão: " + previsao);
                Logger.getLogger("EVENTO").info("Permitida venda: " + permitidaVenda);

                if(vendido >= permitidaLocal  || previsao > permitidaLocal){
                    if(permitidaLocal > vendido){
                        throw new CampoException("local.capacidade", "Para este evento os ingressos disponíveis são " + permitidaVenda, "Para este evento os ingressos disponíveis são " + permitidaVenda, ExceptionOperacao.C);
                    }else{
                        throw new CampoException("local.capacidade", "Para este evento já foram vendidos todos os ingressos.", "Para este evento já foram vendidos todos os ingressos.", ExceptionOperacao.C);
                    }
                }
            });

            double valorTotal = 0;
            for(ItemCarrinho itemCarrinho : carrinho.getItemCarrinhos()){
                Ingresso ingresso = ingressoRepository.save(itemCarrinho.getIngresso());
                itemCarrinho.setIngresso(ingresso);
                itemCarrinho = itemCarrinhoRepository.save(itemCarrinho);
                if(!itemCarrinho.getIngresso().getEvento().isGeraCertificado()){
                    valorTotal += itemCarrinho.getIngresso().getEvento().getValorIngresso();
                }
            }

            carrinho.setValorTotal(valorTotal);
        }

        return repository.save(carrinho);
    }

    @PostMapping(path = "/finalizar")
    public Carrinho finalizar(@RequestBody Carrinho carrinho){
        LocalDate hoje = LocalDate.now();
        if(carrinho.getFormaPagamento().equals(FormaPagamento.PIX)){
            carrinho.setStatusPagamento(StatusPagamento.P);
        }else if(carrinho.getFormaPagamento().equals(FormaPagamento.GRATIS)){
            carrinho.setStatusPagamento(StatusPagamento.S);
        }else{
            Cartao cartao = carrinho.getCartao();

            if(cartao.getAnoVencimento() < hoje.getYear()){
                throw new CampoException("cartao.anoVencimento", "O ano de vencimento do cartão não pde ser inferior a " + hoje.getYear(), "O ano de vencimento do cartão não pde ser inferior a " + hoje.getYear(), ExceptionOperacao.C);
            }
            if(cartao.getAnoVencimento() == hoje.getYear() && cartao.getMesVencimento() < hoje.getMonthValue()){
                throw new CampoException("cartao.anoVencimento", "O cartão já está vencido", "O cartão já está vencido", ExceptionOperacao.C);
            }

            cartao = cartaoRepository.save(cartao);
            carrinho.setCartao(cartao);
            carrinho.setStatusPagamento(StatusPagamento.A);
        }

        List<ItemCarrinho> itens = carrinho.getItemCarrinhos();
        Map<Evento, Integer> eventos = new HashMap<Evento, Integer>();
        //Quantidade de ingressos por evento no carrinho
        itens.forEach(itemCarrinho -> {
            if(eventos.containsKey(itemCarrinho.getIngresso().getEvento())){
                Integer qtd = eventos.get(itemCarrinho.getIngresso().getEvento());
                qtd += 1;
                eventos.replace(itemCarrinho.getIngresso().getEvento(), qtd);
            }else {
                eventos.put(itemCarrinho.getIngresso().getEvento(), 1);
            }
        });

        eventos.forEach((evento, qtd) -> {
            if(qtd > 1){
                int invalidos = 0;
                int invalidosUsuario = 0;
                int contadorAux = 0;
                List<ItemCarrinho> itensCarrinho = carrinho.getItemCarrinhos();
                for(ItemCarrinho itemCarrinho : itensCarrinho){
                    if(itemCarrinho.getIngresso().getEvento().equals(evento)){
                        if(itemCarrinho.getIngresso().getCpf().equals("") || itemCarrinho.getIngresso().getNome().equals("")){
                            invalidos++;
                        }
                        if(itemCarrinho.getIngresso().getCpf().equals(carrinho.getUsuario().getCpf()) || itemCarrinho.getIngresso().getNome().equals(carrinho.getUsuario().getNome())){
                            invalidosUsuario++;
                        }
                    }
                }
                if(invalidos > 0 || invalidosUsuario > 1){
                    throw new CampoException("ingresso", "Existem ingressos sem nome ou CPF", "Existem ingressos sem nome ou CPF", ExceptionOperacao.C);
                }
            }
        });
        List<Ingresso> ingressos = new LinkedList<>();
        carrinho.getItemCarrinhos().forEach(itemCarrinho -> {
            ingressos.add(itemCarrinho.getIngresso());
        });
        ingressoRepository.saveAll(ingressos);

        carrinho.setStatusCarrinho(StatusCarrinho.F);

        return repository.save(carrinho);
    }

    @DeleteMapping(path = "/{usuario}/{item}")
    public Carrinho removerItem(@PathVariable long usuario, @PathVariable long item){
        Usuario usuarioObj = usuarioRepository.findById(usuario).get();
        Carrinho carrinhoOld = repository.getByUsuario(usuarioObj, StatusCarrinho.A);

        carrinhoOld.getItemCarrinhos().removeAll(carrinhoOld.getItemCarrinhos().stream().filter(itemCarrinho -> itemCarrinho.getId() == item).collect(Collectors.toList()));

        return repository.save(carrinhoOld);
    }


}
