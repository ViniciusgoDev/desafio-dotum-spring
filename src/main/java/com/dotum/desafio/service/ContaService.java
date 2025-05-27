package com.dotum.desafio.service;

import com.dotum.desafio.dtos.ContaDTORequest;

import com.dotum.desafio.entity.Conta;
import com.dotum.desafio.enums.TipoConta;
import com.dotum.desafio.respository.ContaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ContaService {

    private final ContaRepository repository;
    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<?> cadastrarNovaConta(ContaDTORequest dto) {

        try {
            log.info("Iniciando o cadastro de uma nova conta: ");
            Conta conta = new Conta(dto);
            conta.setIsPago(false);
            repository.save(conta);
            log.info("Conta cadastrada com sucesso : " + conta );
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
                log.info("Erro ao cadastrar conta =" + e.getMessage());
                return ResponseEntity.badRequest().build();
        }
    }


    public List<Conta> buscarTodasContas(){
        log.info("Buscando todas as contas cadastradas.");
        return repository.findAll();
    }


    public Double calcularTotalEmAbertoPorTipo(TipoConta tipoConta){
        log.info("Calculando total em aberto para o tipo de conta: " + tipoConta);
        return repository.calcularTotalEmAbertoPorTipo(tipoConta);
    }


    public Double calcularSaldoFinanceiro() {
        log.info("Calculando saldo financeiro geral.");
        return calcularTotalEmAbertoPorTipo(TipoConta.RECEBER) - calcularTotalEmAbertoPorTipo(TipoConta.PAGAR);

    }
}
