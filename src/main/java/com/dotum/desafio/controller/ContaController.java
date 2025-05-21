package com.dotum.desafio.controller;

import com.dotum.desafio.dtos.ContaDTO;
import com.dotum.desafio.entity.Conta;
import com.dotum.desafio.enums.TipoConta;
import com.dotum.desafio.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("conta")
public class ContaController {

    private final ContaService service;
    public ContaController(ContaService service) {
        this.service = service;
    }

    @PostMapping("cadastrar")
    public ResponseEntity<?> cadastrarConta(@RequestBody @Valid ContaDTO dto){
        return service.cadastrarNovaConta(dto);
    }

    @GetMapping("listarTodas")
    public List<Conta> listarTodas(){
        return service.listarTodas();
    }

    @GetMapping("obterTotal/{tipoConta}")
    public Double obterValorTotal(@PathVariable TipoConta tipoConta){
        return service.obterValorTotalEmAberto(tipoConta);
    }

    @GetMapping("obterSaldoGeral")
    public Double resumoFinanceiro(){
        return service.resumoDeContas();
    }

}
