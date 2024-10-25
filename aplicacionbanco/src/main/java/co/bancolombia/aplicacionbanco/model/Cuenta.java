package co.bancolombia.aplicacionbanco.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Table(name="cuenta_bancaria")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
public abstract class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cuenta_id")
    private Long cuentaId;
    private BigDecimal saldo;
    @Column(unique = true, nullable = false)
    private String numCuenta;
    @OneToMany(mappedBy = "cuenta")
    private List<Transaccion> transacciones;
    public Cuenta(){
            transacciones = new ArrayList<>();
    }

    public Cuenta(Long cuentaId, BigDecimal saldo, String numCuenta, List<Transaccion> transacciones) {
        this.cuentaId = cuentaId;
        this.saldo = saldo;
        this.numCuenta = numCuenta;
        this.transacciones = transacciones;
    }

    public Cuenta depositoSucursal(BigDecimal monto){
        this.setSaldo(getSaldo().add(monto));
        return this;
    }
    abstract public Cuenta depositoCajero(BigDecimal monto);
    public  Cuenta depositoOtraCuenta(BigDecimal monto){
        BigDecimal costo = new BigDecimal(2.00);
        if (getSaldo().compareTo(costo)<0)
            throw new IllegalArgumentException("Saldo insuficiente para realizar el deposito");
        setSaldo(getSaldo().subtract(costo));
        setSaldo(getSaldo().add(monto));
        return this;

    }
    public Cuenta compraFisico(BigDecimal monto){
        if (getSaldo().compareTo(monto)<0)
            throw new IllegalArgumentException("Saldo insuficiente para realizar la compra");
        setSaldo(getSaldo().subtract(monto));
        return this;
    }
    public Cuenta compraWeb(BigDecimal monto){
        BigDecimal seguro = new BigDecimal(5.00);
        monto.add(seguro);
        if (getSaldo().compareTo(monto)<0)
            throw new IllegalArgumentException("Saldo insuficiente para realizar la compra");
        setSaldo(getSaldo().subtract(monto));
        return this;
    }
    abstract void retiroCajero(BigDecimal monto);

    public Long getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    @Override
    public String toString() {
        return "id de Cuenta :" + cuentaId +
                "\n" +
                "numero de cuenta :" + numCuenta +
                "\n" +
                "saldo actual: " + saldo ;
    }
}

