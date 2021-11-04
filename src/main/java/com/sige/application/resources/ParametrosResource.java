package com.sige.application.resources;

import com.sige.application.model.Parametros;
import com.sige.application.repository.ParametrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/parametros")
public class ParametrosResource {

    @Autowired
    ParametrosRepository repository;

    @PostMapping
    public Parametros save(@Valid @RequestBody Parametros parametros){
        return repository.save(parametros);
    }

    @GetMapping("/{id}")
    public Parametros save(@PathVariable int id){
        return repository.findById((long) id).get();
    }

}
