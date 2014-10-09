/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Petr Konecny
 * 
 * Base class that loads Spring configuration for tests, for dependency injection
 * 
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/resources/testApplicationContext.xml"})
public abstract class AbstractTest {
    
}
