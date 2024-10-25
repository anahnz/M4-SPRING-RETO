package co.bancolombia.aplicacionbanco.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import co.bancolombia.aplicacionbanco.model.Cuenta;
import co.bancolombia.aplicacionbanco.model.DTO.ConsultarCuentaDTO;
import co.bancolombia.aplicacionbanco.model.Transaccion;
import co.bancolombia.aplicacionbanco.repository.CuentaRepository;
import co.bancolombia.aplicacionbanco.repository.TransaccionRepository;
import org.springframework.stereotype.Service;

@Service
public class cuentaService {

    private final CuentaRepository cuentaRepository;
    private final TransaccionRepository transaccionRepository;
    LocalDateTime fechaHora = LocalDateTime.now();

    public cuentaService(CuentaRepository cuentaRepository, TransaccionRepository transaccionRepository) {
        this.cuentaRepository = cuentaRepository;
        this.transaccionRepository = transaccionRepository;

    }

    public Cuenta consultarCuenta(ConsultarCuentaDTO idCuenta) {
        idCuenta.getIdCuenta();
        Cuenta infoCuenta = cuentaRepository.findById(idCuenta.getIdCuenta()).orElseThrow(() ->
                new NoSuchElementException("Cuenta no encontrada"));
        Cuenta datos = infoCuenta;
        return datos;
    }



    public List<Transaccion> consultaHistoricoTransacciones(ConsultarCuentaDTO idCuenta){
        Transaccion transacciones = new Transaccion();
        return  transaccionRepository.  findTransactions(idCuenta.getIdCuenta());

    }


}












