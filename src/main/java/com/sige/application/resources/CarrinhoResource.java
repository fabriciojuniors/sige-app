package com.sige.application.resources;

import com.sige.application.enums.ExceptionOperacao;
import com.sige.application.enums.StatusCarrinho;
import com.sige.application.enums.StatusPagamento;
import com.sige.application.exception.CampoException;
import com.sige.application.model.Carrinho;
import com.sige.application.model.Cartao;
import com.sige.application.model.ItemCarrinho;
import com.sige.application.model.Usuario;
import com.sige.application.repository.CarrinhoRepository;
import com.sige.application.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoResource {

    @Autowired
    CarrinhoRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

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
            double valorTotal = 0;
            for(ItemCarrinho itemCarrinho : carrinho.getItemCarrinhos()){
                valorTotal += itemCarrinho.getIngresso().getEvento().getValorIngresso();
            }

            carrinho.setValorTotal(valorTotal);
        }

        return repository.save(carrinho);
    }


}
