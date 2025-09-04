package co.com.prueba.tecnica.sysman.repository;

import co.com.prueba.tecnica.sysman.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {


    List<Material> findByTipoAndFechaCompra( String tipo,LocalDate fechaCompra);

    @Query("SELECT m FROM Material m WHERE m.ciudad.id = :ciudadId")
    List<Material> buscarPorCiudad(@Param("ciudadId") Long ciudadId);
}
