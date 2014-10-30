/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Petr Konecny
 * 
 * Base class that loads Spring configuration for tests, for dependency injection
 * It does rollback after each test method
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/testApplicationContext.xml")
@Transactional 
public abstract class BaseTest {
    
}
