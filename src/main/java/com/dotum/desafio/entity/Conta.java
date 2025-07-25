package com.dotum.desafio.entity;

import com.dotum.desafio.dtos.ContaDTORequest;
import com.dotum.desafio.enums.TipoConta;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "tb_Contas")
@AllArgsConstructor
@NoArgsConstructor
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valor;
    private String descricao;
    private LocalDate dataVencimento;
    private Boolean isPago;
    private TipoConta tipoConta;



    public Conta(@Valid ContaDTORequest dto) {
        this.dataVencimento = dto.dataVencimento();
        this.descricao = dto.descricao();
        this.tipoConta = dto.tipoConta();
        this.valor = dto.valor();
    }
}
