package co.com.prueba.tecnica.sysman.repository;

import co.com.prueba.tecnica.sysman.entity.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

}
