package com.dotum.desafio.controller;

import com.dotum.desafio.dtos.ContaDTORequest;
import com.dotum.desafio.entity.Conta;
import com.dotum.desafio.enums.TipoConta;
import com.dotum.desafio.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService service;
    public ContaController(ContaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarConta(@RequestBody @Valid ContaDTORequest dto){
        return service.cadastrarNovaConta(dto);
    }

    @GetMapping
    public List<Conta> buscarTodasContas(){
        return service.buscarTodasContas();
    }

    @GetMapping("/total/{tipoConta}")
    public Double obterTotalPorTipo(@PathVariable TipoConta tipoConta){
        return service.calcularTotalEmAbertoPorTipo(tipoConta);
    }

    @GetMapping("/saldo")
    public Double obterSaldoGeral(){
        return service.calcularSaldoFinanceiro();
    }

}
