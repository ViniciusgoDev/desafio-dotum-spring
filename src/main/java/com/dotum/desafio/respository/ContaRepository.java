package com.dotum.desafio.respository;

import com.dotum.desafio.entity.Conta;
import com.dotum.desafio.enums.TipoConta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    @Query("SELECT SUM(c.valor) FROM Conta c WHERE c.isPago = false AND c.tipoConta = :tipoConta")
    Double calcularTotalEmAbertoPorTipo(@Param("tipoConta") TipoConta tipoConta);

}
