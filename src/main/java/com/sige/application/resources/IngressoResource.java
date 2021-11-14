package com.sige.application.resources;

import com.sige.application.enums.ExceptionOperacao;
import com.sige.application.enums.StatusCarrinho;
import com.sige.application.enums.StatusIngresso;
import com.sige.application.exception.CampoException;
import com.sige.application.model.*;
import com.sige.application.repository.CarrinhoRepository;
import com.sige.application.repository.CartaoRepository;
import com.sige.application.repository.IngressoRepository;
import com.sige.application.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/ingresso")
public class IngressoResource {

    @Autowired
    IngressoRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @PostMapping
    public Ingresso save(@Valid @RequestBody Ingresso ingresso){
        return repository.save(ingresso);
    }

    @GetMapping("/{id}")
    public Ingresso getById(@PathVariable int id){
        return repository.findById((long) id).get();
    }

    @GetMapping("/usuario/{id}")
    public List<Ingresso> getByUser(@PathVariable int id){
        Usuario usuario = usuarioRepository.findById((long) id).get();

        if(usuario == null){
            throw new CampoException("Usuario", "Não encontrado usuário para o id informado", "Não encontrado usuário para o id informado", ExceptionOperacao.L);
        }

        List<Carrinho> carrinhos = carrinhoRepository.getByUsuarioList(usuario, StatusCarrinho.F);
        List<Ingresso> ingressos = new LinkedList<>();
        for(Carrinho carrinho : carrinhos){
            for(ItemCarrinho itemCarrinho : carrinho.getItemCarrinhos()){
                if(itemCarrinho.getIngresso().getStatusIngresso().equals(StatusIngresso.EMITIDO)){
                    ingressos.add(itemCarrinho.getIngresso());
                }
            }
        }

        return ingressos;
    }

    @PostMapping("/autorizar/{id}")
    public Ingresso autorizar(@PathVariable int id){
        Ingresso ingresso = repository.findById((long) id).get();

        ingresso.setStatusIngresso(StatusIngresso.AUTORIZADO);
        return  repository.save(ingresso);

    }
}
