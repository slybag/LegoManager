/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.dao.CategoryDao;
import cz.muni.fi.pa165.legomanager.dao.impl.CategoryDaoImpl;
import cz.muni.fi.pa165.legomanager.entity.Category;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Michal
 */
public class CategoryDaoImplTest extends BaseDaoTest {

    @Autowired
    CategoryDao categoryDao;
    
    @PersistenceContext           
    EntityManager entityManager;
                      
    private String descriptionBoys = "This category contains kits suitable for boys";
    private String descriptionGirls = "This category contains kits suitable for girls";
       
    @Test
    public void createCategory() {
        //everything right
        Category category1 = createCategory("Barbie", descriptionGirls);
        try {
            categoryDao.addCategory(category1);
        } catch (Exception e) {
            fail("Exception thrown" + e.getMessage());
        }
                
        Category categoryFromDb = categoryDao.findCategoryById(category1.getId());        
        assertDeepEquals(categoryFromDb, category1);        
        
        //with null
        try {
          categoryDao.addCategory(null);
          fail ("No exception thrown");
        } catch (Exception ex) {            
        }
        
        //with null params
        Category category3 = createCategory(null, null);
        try {
            categoryDao.addCategory(category3);
            fail ("No exception thrown");
        } catch (Exception ex) {
        }                
    }
    
    @Test
    public void updateCategory() {
        Category category1 = createCategory("Star Wars", descriptionBoys);
        categoryDao.addCategory(category1);
        
        //OK
        try {
            categoryDao.updateCategory(category1);
        } catch (Exception ex) {
            fail("No exception");
        }
        
        //with null
        try {
            categoryDao.updateCategory(null);
            fail ("No exception");
        } catch (Exception ex) {
        }                        
    }
    
    @Test
    public void removeCategory() {
        Category category1 = createCategory("Star Trek", descriptionBoys);
        categoryDao.addCategory(category1);
        Category catToRemove = categoryDao.findCategoryById(category1.getId());
        
        //OK
        try {
            categoryDao.deleteCategory(catToRemove);
        } catch (Exception ex) {
            fail ("Exception thrown: " + ex.getMessage());
        }
        
        //with null
        try {
            categoryDao.deleteCategory(null);
            fail ("No exception thrown");
        } catch (Exception ex) {
        }
        
        //not in DB
        Category category2 = createCategory("Ponnies", descriptionGirls);
        categoryDao.addCategory(category2);
        
        Category catToRemove2 = categoryDao.findCategoryById(category2.getId());
        categoryDao.deleteCategory(catToRemove2);
        
        try {
            categoryDao.deleteCategory(catToRemove2);
            fail ("No exception thrown");
        } catch (Exception ex) {
        }
    }
    
    @Test
    public void getCategory() {
        Category category1 = createCategory("Houses", descriptionGirls);
        categoryDao.addCategory(category1);
        
        //OK
        try {
            categoryDao.findCategoryById(category1.getId());
        } catch (Exception ex) {
            fail ("Exception thrown");
        }
        
        Category category2 = categoryDao.findCategoryById(category1.getId());
        assertDeepEquals(category1, category2);
        
        //with null
        try {
            categoryDao.findCategoryById(null);
            fail ("No exception thrown");
        } catch (Exception ex) {
        }
        
        //Not in DB
        Category category3 = createCategory("Star ships", descriptionBoys);
        categoryDao.addCategory(category3);
        categoryDao.deleteCategory(category3);
        
        try {            
            categoryDao.findCategoryById(category3.getId());
            fail ("No exception thrown");
        } catch (Exception ex) {
        }        
    }
    
    @Test 
    public void getAllCategories() {
        Category category1 = createCategory("Star Wars", descriptionBoys);
        Category category2 = createCategory("Star Trek", descriptionBoys);
        Category category3 = createCategory("Firefly", descriptionBoys);
        
        categoryDao.addCategory(category1);
        categoryDao.addCategory(category3);
        categoryDao.addCategory(category2);
        
        //OK
        List<Category> categoryList1;
        try {
            categoryList1 = categoryDao.getAllCategories();
        } catch (Exception ex) {
            fail ("Exception thrown");
        }                
    }
    
                
    public static Category createCategory(String name, String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        
        return category;
    }
    
    private void assertDeepEquals(Category cat1, Category cat2) {
        assertEquals(cat1.getId(), cat2.getId());
        assertEquals(cat1.getName(), cat2.getName());
        assertEquals(cat1.getDescription(), cat2.getDescription());
    }
}
