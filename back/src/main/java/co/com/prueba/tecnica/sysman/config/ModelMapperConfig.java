package co.com.prueba.tecnica.sysman.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configuración opcional
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT) // Estrategia estricta
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true) // No mapear valores nulos
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        return modelMapper;
    }
}
