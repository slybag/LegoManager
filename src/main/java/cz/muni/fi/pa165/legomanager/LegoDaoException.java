/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager;

import javax.persistence.PersistenceException;

/**
 *
 * @author Petr
 * 
 */
public class LegoDaoException extends PersistenceException {
    
    public LegoDaoException() {
    }

    public LegoDaoException(String msg) {
        super(msg);
    }
    
    public LegoDaoException(Throwable cause) {
        super(cause);
    }
    
    public LegoDaoException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

