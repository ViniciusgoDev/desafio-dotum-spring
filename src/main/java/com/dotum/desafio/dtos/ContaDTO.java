package com.dotum.desafio.dtos;

import com.dotum.desafio.enums.TipoConta;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ContaDTO(

        @NotBlank(message = "Descrição é obrigatória")
        String descricao,

        @NotNull(message = "Valor é obrigatório")
        @Positive(message = "Valor deve ser positivo")
        Double valor,

        @NotNull(message = "Data é obrigatória")
        @FutureOrPresent(message = "Data não pode estar no passado")
        LocalDate dataVencimemento,

        @NotNull(message = "Tipo da conta é obrigatório")
        TipoConta tipoConta){}


