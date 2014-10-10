/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165;

import cz.muni.fi.pa165.legomanager.CategoryDao;
import cz.muni.fi.pa165.legomanager.LegoPieceDao;
import cz.muni.fi.pa165.legomanager.entity.LegoPiece;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;


/**
 *
 * @author Martin Laštovička
 */
public class LegoPieceTest extends BaseTest{
        @Autowired
        LegoPieceDao legoPieceDao;
    
        @PersistenceUnit
	public EntityManagerFactory emf;	 
	
	@Before
	public void setup(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		LegoPiece legoPiece1 = new LegoPiece();
                LegoPiece legoPiece2 = new LegoPiece();
                LegoPiece legoPiece3 = new LegoPiece();
                
                em.persist(legoPiece1);
                em.persist(legoPiece2);
                em.persist(legoPiece3);
		
		em.getTransaction().commit();
		em.close();
	}
        
        @Test
	public void getAllLegoPiecesTest() {
		EntityManager em = emf.createEntityManager();
		List<LegoPiece> legoPieces = em.createQuery("SELECT l FROM LegoPiece l",LegoPiece.class).getResultList();
		em.close();
		
		Assert.assertEquals(legoPieces.size(), 3);
	}
        
        @Test
	public void findLegoPieceByIdTest() {
		EntityManager em = emf.createEntityManager();
                long id = 1;
		LegoPiece legoPiece = em.createQuery("SELECT l FROM LegoPiece l where id =  :pieceId",LegoPiece.class)
                        .setParameter("pieceId", id)
                        .getSingleResult();
		em.close();
		
		//Assert.assertEquals(legoPiece.getColor(), "Red");
	}
        
        @Test
	public void updateLegoPieceTest() {
		EntityManager em = emf.createEntityManager();
                LegoPiece legoPieceTest = new LegoPiece();
		List<LegoPiece> legoPieces = em.createQuery("SELECT LegoPiece FROM LegoPiece",LegoPiece.class).getResultList();
		em.close();
		
		Assert.assertEquals(legoPieces.size(), 3);
	}
}
