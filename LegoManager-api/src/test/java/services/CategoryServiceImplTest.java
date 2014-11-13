/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import cz.muni.fi.pa165.legomanager.dao.CategoryDao;
import cz.muni.fi.pa165.legomanager.dao.LegoDaoException;
import cz.muni.fi.pa165.legomanager.entity.Category;
import cz.muni.fi.pa165.legomanager.services.impl.CategoryServiceImpl;
import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoSetTO;
import java.util.ArrayList;
import org.dozer.DozerBeanMapper;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Michal
 */
public class CategoryServiceImplTest extends BaseServiceTest {
    @Autowired
    @InjectMocks
    private CategoryServiceImpl categoryService;
    
    @Mock
    private CategoryDao categoryDao;
    
    @Autowired
    private DozerBeanMapper mapper;
    
    private CategoryTO category;
        
    @Before    
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        category = createCategory("Ponny", "Ponicci, konicci a podobne haveti");
        category.setId(1L);
    }
    
    @Test
    public void getCategoryTest() {
        try {
            when(categoryDao.findCategoryById(category.getId()))
                    .thenReturn(mapper.map(category, Category.class));
            CategoryTO cat = categoryService.getCategory(category.getId());
                verify(categoryDao).findCategoryById(category.getId());
            
            assertDeepEquals(category, cat);
            
        } catch (Exception ex) {
            fail("Exception thrown: " + ex.getMessage());
        }
    }
    
    @Test
    public void getCategoryTestException() {
        try {
            doThrow(LegoDaoException.class).when(categoryDao).findCategoryById(category.getId());

            categoryService.getCategory(category.getId());
            fail("No exception thrown.");
        } catch (DataAccessException ex) {
            // OK
        } catch (Exception ex) {
            fail("Bad exception throwed: " + ex);
        }
    }
    
    @Test
    public void addCategoryTest() {
        try {
            categoryService.createCategory(category);
            verify(categoryDao).addCategory(mapper.map(category, Category.class));
        } catch (Exception ex) {
            fail("Exception thrown: " + ex.getMessage());
        }
    }
    
    @Test
    public void addCategoryTestException() {
        try {
            doThrow(LegoDaoException.class).when(categoryDao).addCategory(mapper.map(category, Category.class));
            
            categoryService.createCategory(category);
            fail("No exception thrown.");
        } catch (DataAccessException ex) {
            // OK
        } catch (Exception ex) {
            fail("Bad exception throwed: " + ex);
        }
    }
    
    @Test
    public void updateCategoryTest() {
        try {
            categoryService.updateCategory(category);
            verify(categoryDao).updateCategory(mapper.map(category, Category.class));
        } catch (Exception ex) {
            fail("Exception thrown: " + ex.getMessage());
        }
    }
    
    @Test
    public void updateCategoryTestException() {
        try {
            doThrow(LegoDaoException.class).when(categoryDao)
                    .updateCategory(mapper.map(category, Category.class));

            categoryService.updateCategory(category);
            fail("No exception thrown.");
        } catch (DataAccessException ex) {
            // OK
        } catch (Exception ex) {
            fail("Bad exception throwed: " + ex);
        }
    }
    
    @Test
    public void removeCategoryTest() {
        try {
            categoryService.deleteCategory(category);
            verify(categoryDao).deleteCategory(mapper.map(category, Category.class));
        } catch (Exception ex) {
            fail("Exception thrown: " + ex.getMessage());
        }
    }
    
    @Test
    public void removeCategoryTestException() {
        try {
            doThrow(LegoDaoException.class).when(categoryDao)
                    .deleteCategory(mapper.map(category, Category.class));

            categoryService.deleteCategory(category);
            fail("No exception thrown.");
        } catch (DataAccessException ex) {
            // OK
        } catch (Exception ex) {
            fail("Bad exception throwed: " + ex);
        }
    }
    
    @Test
    public void getAllCategoriesTest() {
        try {
            categoryService.getAllCategories();
            verify(categoryDao).getAllCategories();
        } catch (Exception ex) {
            fail("Exception thrown: " + ex.getMessage());
        }
    }
    
    @Test
    public void getAllCategoriesTestException() {
        try {
            doThrow(LegoDaoException.class).when(categoryDao).getAllCategories();

            categoryService.getAllCategories();
            fail("No exception thrown.");
        } catch (DataAccessException ex) {
            // OK
        } catch (Exception ex) {
            fail("Bad exception throwed: " + ex);
        }
    }
    
    private CategoryTO createCategory(String name, String description) {
        CategoryTO catTO = new CategoryTO();
        catTO.setName(name);
        catTO.setDescription(description);
        catTO.setLegoKits(new ArrayList<LegoKitTO>());
        catTO.setLegoSets(new ArrayList<LegoSetTO>());
        
        return catTO;
    }
    
    private void assertDeepEquals(CategoryTO cat1, CategoryTO cat2) {
        assertEquals(cat1.getId(), cat2.getId());
        assertEquals(cat1.getName(), cat2.getName());
        assertEquals(cat1.getDescription(), cat2.getDescription());
        assertEquals(cat1.getLegoKits(), cat2.getLegoKits());
        assertEquals(cat1.getLegoSets(), cat2.getLegoSets());
    }
}
