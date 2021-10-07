package com.sige.application.controller;

import com.sige.application.enums.ExceptionOperacao;
import com.sige.application.enums.Nivel;
import com.sige.application.exception.CampoException;
import com.sige.application.exception.LoginException;
import com.sige.application.model.Endereco;
import com.sige.application.model.Usuario;
import com.sige.application.repository.EnderecoRepository;
import com.sige.application.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    EnderecoRepository enderecoRepository;

    public Usuario save(Usuario u){
        if(u.getId() == 0L || u.getId() == null){
            if(!repository.findByEmail(u.getEmail()).isEmpty()){
                throw new CampoException("usuario.email", "E-mail informado já cadastrado.", "O e-mail informado já está cadastrado, informe outro e-mail ou redefina sua senha.", ExceptionOperacao.C);
            }
            if(!repository.findByCpf(u.getCpf()).isEmpty()){
                throw new CampoException("usuario.cpf", "CPF informado já cadastrado.", "", ExceptionOperacao.C);
            }
        }

        if(u.getEndereco().getId() == 0L || u.getEndereco().getId() == null){
            Endereco endereco = enderecoRepository.save(u.getEndereco());
            u.setEndereco(endereco);
        }
        return repository.save(u);
    }

    public Page<Usuario> getByNivel(String n, int pagina){
        Nivel nivel = null;
        try{
            nivel = Nivel.valueOf(n.toUpperCase());
        }catch (Exception ex){
            throw new CampoException("usuario.nivel", "Nível inválido", "Verifique o nível informado e tente novamente", ExceptionOperacao.L);
        }

        Pageable page = PageRequest.of(pagina-1,20);

        return repository.findByNivel(nivel, page);
    }

    public Usuario autenticar(Usuario u){
        Usuario usuario = repository.autenticar(u.getEmail(), u.getSenha());

        if(null == usuario){
            throw new LoginException();
        }

        return usuario;
    }

    public Usuario getById(int id){
        Usuario usuario = repository.findById((long) id).get();
        return usuario ;
    }

}
