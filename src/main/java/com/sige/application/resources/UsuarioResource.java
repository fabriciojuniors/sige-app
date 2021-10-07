package com.sige.application.resources;

import com.sige.application.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.sige.application.controller.*;

import javax.websocket.server.PathParam;
import java.util.logging.Logger;

@RestController()
@RequestMapping("/usuario")
public class UsuarioResource {

    @Autowired
    UsuarioController controller;

    @PostMapping
    public Usuario save(@RequestBody Usuario u){
        return controller.save(u);
    }

    @GetMapping(path = "/{nivel}")
    public Page<Usuario> getByNivel(@PathVariable String nivel, @PathParam("pagina") int pagina){
        return controller.getByNivel(nivel, pagina);
    }

    @GetMapping(path = "/busca/{id}")
    public Usuario getById(@PathVariable int id){
        return controller.getById(id) ;
    }

    @PostMapping(path = "/autenticar")
    public Usuario autenticar(@RequestBody Usuario u){
        return controller.autenticar(u);
    }

}
