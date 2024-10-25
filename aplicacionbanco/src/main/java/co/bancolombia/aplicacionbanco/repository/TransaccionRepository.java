package co.bancolombia.aplicacionbanco.repository;

import co.bancolombia.aplicacionbanco.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    @Query("select p from Transaccion p join fetch p.cuenta where p.cuenta.cuentaid =?1 ")
    List<Transaccion> findTransactions(Long cuentaId);
}
