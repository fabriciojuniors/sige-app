package com.sige.application.resources;

import com.sige.application.controller.ParceiroController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/parceiro")
public class ParceiroResource {

    @Autowired
    ParceiroController controller;

}
