package com.sige.application.controller;

import com.sige.application.repository.ParceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParceiroController {

    @Autowired
    ParceiroRepository repository;

}
