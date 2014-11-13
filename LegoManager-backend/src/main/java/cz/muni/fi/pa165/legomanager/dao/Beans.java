/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

/**
 *
 * @author Petr
 */

@Configuration
@ComponentScan
public class Beans {
    
    @Bean
    PersistenceExceptionTranslationPostProcessor getProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }    
}
