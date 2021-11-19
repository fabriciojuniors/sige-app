package com.sige.application.resources;

import com.sige.application.controller.EventoController;
import com.sige.application.model.Evento;
import com.sige.application.model.Local;
import com.sige.application.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/evento")
public class EventoResource {

    @Autowired
    EventoController controller;

    @Autowired
    EventoRepository repository;

    @PostMapping
    public Evento save(@Valid @RequestBody Evento evento){
        return controller.save(evento);
    }

    @GetMapping
    public Page<Evento> getAll(@PathParam("pagina") int pagina){
        return controller.getAll(pagina);
    }

    @GetMapping(value = "/mobile")
    public Page<Evento> getAllMobile(@PathParam("pagina") int pagina){
        return controller.getAllMobile(pagina);
    }

    @GetMapping(value = "/mobile/{nome}")
    public Page<Evento> getAllMobileFiltro(@PathVariable String nome,  @PathParam("pagina") int pagina){
        return controller.getAllMobile(pagina, nome);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        controller.delete(id);
    }

    @GetMapping(value = "/all")
    public List<Evento> get(){
        return repository.findAll().stream().filter(evento -> evento.getData().isAfter(LocalDate.now())).collect(Collectors.toList());
    }

}
