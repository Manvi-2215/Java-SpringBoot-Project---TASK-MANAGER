package org.example.TaskManager.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    //model mapper helps converts itself dto to entity and vice a versa just by writing one line
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
