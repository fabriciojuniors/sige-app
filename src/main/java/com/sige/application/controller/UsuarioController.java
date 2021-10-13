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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;


@Service
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    EnderecoRepository enderecoRepository;

    public Usuario save(Usuario u){

        if(!repository.findByEmail(u.getEmail()).isEmpty()){
            throw new CampoException("usuario.email", "E-mail informado já cadastrado.", "O e-mail informado já está cadastrado, informe outro e-mail ou redefina sua senha.", ExceptionOperacao.C);
        }
        if(!repository.findByCpf(u.getCpf()).isEmpty()){
            throw new CampoException("usuario.cpf", "CPF informado já cadastrado.", "", ExceptionOperacao.C);
        }

        Endereco endereco = enderecoRepository.save(u.getEndereco());
        u.setEndereco(endereco);
        return repository.save(u);
    }

    public Page<Usuario> getByNivel(String n, int pagina){
        Nivel nivel = null;
        Pageable page = PageRequest.of(pagina-1,5,Sort.by(Sort.Order.by("id")));
        if(n.equals("T")){
            return repository.findAll(page);
        }
        try{
            nivel = Nivel.valueOf(n.toUpperCase());
        }catch (Exception ex){
            throw new CampoException("usuario.nivel", "Nível inválido", "Verifique o nível informado e tente novamente", ExceptionOperacao.L);
        }

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
        Usuario usuario = null;
        try{
            usuario = repository.findById((long) id).get();
        }catch (Exception e){
            Logger.getLogger("DELETE USUARIO: Não encontrado usuario com ID " + id);
        }
        return usuario;
    }

    public void delete(int id){
        Usuario usuario = getById(id);

        if(usuario == null){
            throw new CampoException("usuario.id", "Não foi possível excluir o usuário.", "Não encontrado usuário com ID " + id + " para exclusão.", ExceptionOperacao.D);
        }else{
            repository.delete(usuario);
        }
    }

}
