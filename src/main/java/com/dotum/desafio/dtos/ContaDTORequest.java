package com.dotum.desafio.dtos;

import com.dotum.desafio.enums.TipoConta;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ContaDTORequest(

        @NotBlank(message = "Descrição é obrigatória")
        String descricao,

        @NotNull(message = "Valor é obrigatório")
        @Positive(message = "Valor deve ser positivo")
        Double valor,

        @NotNull(message = "Data é obrigatória")
        @FutureOrPresent(message = "Data não pode estar no passado")
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dataVencimento,

        @NotNull(message = "Tipo da conta é obrigatório")
        TipoConta tipoConta){}


