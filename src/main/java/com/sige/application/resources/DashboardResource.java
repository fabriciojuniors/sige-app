package com.sige.application.resources;

import com.sige.application.enums.StatusPagamento;
import com.sige.application.repository.CarrinhoRepository;
import com.sige.application.repository.EventoRepository;
import com.sige.application.repository.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/dashboard")
public class DashboardResource {

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    IngressoRepository ingressoRepository;

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @GetMapping
    public Map<String, Object> get(){
        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("ativos", eventoRepository.eventosAtivos(LocalDate.now()));
        dashboard.put("ingressos_vendidos", ingressoRepository.ingressosVendidos(LocalDate.now()));
        dashboard.put("recebimento", carrinhoRepository.valorTotalByStatusPagamento(StatusPagamento.A));
        dashboard.put("aguardando_aprovacao", carrinhoRepository.valorTotalByStatusPagamento(StatusPagamento.P));
        return dashboard;
    }

}
