package com.sige.application.resources;

import com.sige.application.controller.ParceiroController;
import com.sige.application.model.Parceiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping(path = "/parceiro")
public class ParceiroResource {

    @Autowired
    ParceiroController controller;

    @PostMapping
    public Parceiro save(@Valid @RequestBody Parceiro parceiro){
        return controller.save(parceiro);
    }

    @GetMapping
    public Page<Parceiro> getAll(@PathParam("pagina") int pagina){
        return controller.getAll(pagina);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id){
        controller.delete(id);
    }

}
