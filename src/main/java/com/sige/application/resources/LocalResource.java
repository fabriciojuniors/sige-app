package com.sige.application.resources;

import com.sige.application.controller.LocalController;
import com.sige.application.model.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RequestMapping("/local")
@RestController()
public class LocalResource {

    @Autowired
    LocalController controller;

    @PostMapping
    public Local save(@Valid @RequestBody Local local){
        return controller.save(local);
    }

    @GetMapping
    public Page<Local> getAll(@PathParam("pagina") int pagina){
        return controller.getAll(pagina);
    }

}
