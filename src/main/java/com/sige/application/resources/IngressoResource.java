package com.sige.application.resources;

import com.sige.application.enums.ExceptionOperacao;
import com.sige.application.exception.CampoException;
import com.sige.application.model.Ingresso;
import com.sige.application.model.Parametros;
import com.sige.application.model.Usuario;
import com.sige.application.repository.IngressoRepository;
import com.sige.application.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/ingresso")
public class IngressoResource {

    @Autowired
    IngressoRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping
    public Ingresso save(@Valid @RequestBody Ingresso ingresso){
        return repository.save(ingresso);
    }

    @GetMapping("/{id}")
    public Ingresso getById(@PathVariable int id){
        return repository.findById((long) id).get();
    }

    @GetMapping("/usuario/{userId}")
    public List<Ingresso> getByUser(@PathVariable int id){
        Usuario usuario = usuarioRepository.findById((long) id).get();

        if(usuario == null){
            throw new CampoException("Usuario", "Não encontrado usuário para o id informado", "Não encontrado usuário para o id informado", ExceptionOperacao.L);
        }

        return repository.getByUsuario(usuario);
    }
}
