package co.bancolombia.aplicacionbanco.model;

import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class CuentaBasica extends Cuenta{

    @Override
    public Cuenta depositoCajero(BigDecimal monto){
        BigDecimal costo = new BigDecimal(2.00);
        if (getSaldo().compareTo(costo)<0)
            throw new IllegalArgumentException("Saldo insuficiente para realizar el deposito");
        setSaldo(getSaldo().subtract(costo));
        setSaldo(getSaldo().add(monto));
        return this;
    }

    @Override
    public void retiroCajero(BigDecimal monto){
        BigDecimal costo = new BigDecimal(1.0);
        monto.add(costo);
        if (getSaldo().compareTo(monto)<0)
            throw new IllegalArgumentException("Saldo insuficiente para realizar el retiro");
        setSaldo(getSaldo().subtract(monto));

    }



}
