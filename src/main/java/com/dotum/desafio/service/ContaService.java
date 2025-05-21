package com.dotum.desafio.service;

import com.dotum.desafio.dtos.ContaDTO;

import com.dotum.desafio.entity.Conta;
import com.dotum.desafio.enums.TipoConta;
import com.dotum.desafio.respository.ContaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class ContaService {

    private final ContaRepository reepository;
    public ContaService(ContaRepository reepository) {
        this.reepository = reepository;
    }

    public ResponseEntity<?> cadastrarNovaConta(ContaDTO dto) {

        try {
            Conta conta = new Conta(dto);
            conta.setIsPago(false);
            reepository.save(conta);
            log.info("Nova conta cadastrada: " + conta );
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
                log.info("Erro ao cadastrar conta =" + e.getMessage());
                return ResponseEntity.badRequest().build();
        }
    }

    public List<Conta> listarTodas(){
        return reepository.findAll();
    }

    public Double obterValorTotalEmAberto(TipoConta tipoConta){
        List<Conta> contas = listarTodas();
        return  contas.stream().filter(conta -> !conta.getIsPago() && conta.getTipoConta() == tipoConta)
                .mapToDouble(Conta::getValor)
                .sum();
    }
    public Double resumoDeContas() {
        return obterValorTotalEmAberto(TipoConta.RECEBER) - obterValorTotalEmAberto(TipoConta.PAGAR);

    }
}
