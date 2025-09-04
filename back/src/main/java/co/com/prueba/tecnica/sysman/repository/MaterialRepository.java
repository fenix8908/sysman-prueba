package co.com.prueba.tecnica.sysman.repository;

import co.com.prueba.tecnica.sysman.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    @Query("""
            select m from Material m
            where(:tipo is null or lower(m.tipo)=lower(:tipo))
            and(:desde is null or m.fechaCompra >= :desde)
            and(:hasta is null or m.fechaCompra <= :hasta)
            """ )
    List<Material> buscarPorTipoYFecha(@Param("tipo") String tipo,
                                       @Param("desde") LocalDate desde,
                                       @Param("hasta") LocalDate hasta);

    @Query("select m from Material m where m.ciudad.id = :ciudadId")
    List<Material> buscarPorCiudad(@Param("ciudadId") Long ciudadId);
}
