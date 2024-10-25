package co.bancolombia.aplicacionbanco.model;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class CuentaPremium extends Cuenta {



    @Override
    public Cuenta depositoCajero(BigDecimal monto){
        setSaldo(getSaldo().add(monto));
        return this;
    }

    @Override
    public void retiroCajero(BigDecimal monto){
        if (getSaldo().compareTo(monto)<0)
            throw new IllegalArgumentException("Saldo insuficiente para realizar el retiro");
        setSaldo(getSaldo().subtract(monto));

    }
}
