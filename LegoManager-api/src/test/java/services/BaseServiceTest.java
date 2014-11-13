/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import cz.muni.fi.pa165.legomanager.dao.CategoryDao;
import cz.muni.fi.pa165.legomanager.dao.LegoKitDao;
import cz.muni.fi.pa165.legomanager.dao.LegoPieceDao;
import cz.muni.fi.pa165.legomanager.dao.LegoSetDao;
import org.dozer.DozerBeanMapper;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 *
 * @author Petr Konecny
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public abstract class BaseServiceTest {
    
    @Configuration
    @ComponentScan("cz.muni.fi.pa165.legomanager.services")
    static class ServiceTestContext {
    
        // This will declare the unused bean and inject with null overriding @Autowired.
        // it is here, because if it was not, default dao would be loaded and with it whole
        // persistence context
        
        @Bean
        public LegoSetDao getLegoSetDao(){
            return null;
        }

        @Bean
        public CategoryDao getCategoryDao(){
            return null;
        }

        @Bean
        public LegoKitDao getLegoKitDao(){
            return null;
        }

        @Bean
        public LegoPieceDao getLegoPieceDao(){
            return null;
        }

    }
}
