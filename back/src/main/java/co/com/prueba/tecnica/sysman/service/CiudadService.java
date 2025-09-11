package co.com.prueba.tecnica.sysman.service;

import co.com.prueba.tecnica.sysman.entity.Ciudad;
import co.com.prueba.tecnica.sysman.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadService {

    private  final CiudadRepository ciudadRepository;

    @Autowired
    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    public List<Ciudad> obtenerTodasLasCiudades() {
        return ciudadRepository.findAll();
    }

    public Ciudad obtenerCiudadPorId(Long ciudadId) {
        return ciudadRepository.findById(ciudadId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Ciudad con ID " + ciudadId + " no encontrada"));
    }
}
