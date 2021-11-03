package com.sige.application.resources;

import com.sige.application.controller.LocalController;
import com.sige.application.model.Local;
import com.sige.application.repository.LocalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RequestMapping("/local")
@RestController()
public class LocalResource {

    @Autowired
    LocalController controller;

    @Autowired
    LocalRepository repository;

    @PostMapping
    public Local save(@Valid @RequestBody Local local){
        return controller.save(local);
    }

    @GetMapping
    public Page<Local> getAll(@PathParam("pagina") int pagina){
        return controller.getAll(pagina);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        controller.delete(id);
    }

    @GetMapping("/all")
    public List<Local> getAll2(){
        return repository.findAll();
    }

    @GetMapping(path = "/busca/{id}")
    public Local getById(@PathVariable int id){
        return repository.findById((long) id).get();
    }

}
