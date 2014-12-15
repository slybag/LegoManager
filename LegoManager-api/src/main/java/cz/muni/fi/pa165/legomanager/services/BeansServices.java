package cz.muni.fi.pa165.legomanager.services;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Petr
 */
@Configuration
@ComponentScan
public class BeansServices {
    
    @Bean
    DozerBeanMapper mapper(){
        return new DozerBeanMapper();
    }
    
}
