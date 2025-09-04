package co.com.prueba.tecnica.sysman.service;

import co.com.prueba.tecnica.sysman.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialesService {

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialesService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }


}
