package com.sige.application.resources;

import com.sige.application.controller.ParceiroController;
import com.sige.application.model.Parceiro;
import com.sige.application.repository.ParceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(path = "/parceiro")
public class ParceiroResource {

    @Autowired
    ParceiroController controller;

    @Autowired
    ParceiroRepository repository;

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

    @GetMapping("/all")
    public List<Parceiro> getAll2(){
        return repository.findAll();
    }

}
