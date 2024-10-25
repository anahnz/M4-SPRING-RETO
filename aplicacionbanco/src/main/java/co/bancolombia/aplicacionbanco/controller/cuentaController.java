package co.bancolombia.aplicacionbanco.controller;

import co.bancolombia.aplicacionbanco.model.Cuenta;
import co.bancolombia.aplicacionbanco.model.DTO.ConsultarCuentaDTO;
import co.bancolombia.aplicacionbanco.model.Transaccion;
import co.bancolombia.aplicacionbanco.repository.TransaccionRepository;
import co.bancolombia.aplicacionbanco.service.cuentaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/Cuenta")
public class cuentaController {

    private  final cuentaService cuentaservice;
    private final TransaccionRepository transacciones;

    public cuentaController(cuentaService cuentaservice, TransaccionRepository transacciones) {
        this.cuentaservice = cuentaservice;
        this.transacciones = transacciones;
    }

    @GetMapping("/saldo/")
    public String saldo(@Valid @RequestBody ConsultarCuentaDTO consultaCuenta) {
        Cuenta datosCuenta = cuentaservice.consultarCuenta(consultaCuenta);
        return datosCuenta.toString();
    }

    @GetMapping("/transacciones")
    public List<Transaccion> listaTrasacciones(@Valid @RequestBody ConsultarCuentaDTO consultaCuenta){
        return cuentaservice.consultaHistoricoTransacciones(consultaCuenta);
    }



}




