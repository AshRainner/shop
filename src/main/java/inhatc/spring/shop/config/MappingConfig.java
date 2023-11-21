package inhatc.spring.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class MappingConfig {

    @Bean
    public MappingConfig modelMapper(){
        return new MappingConfig();
    }
}
