/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.legomanager;

import cz.muni.fi.pa165.legomanager.entity.Category;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martin Laštovička
 */
@Repository
public class CategoryDaoImpl implements CategoryDao{
    
    List<Category> categories;
    
    @PersistenceContext
    EntityManager entityManager;

    @Override    
    public List<Category> getAllCategories() {
        return entityManager.createNamedQuery("SELECT c FROM Category C", Category.class).getResultList();
    }

    @Override 
    @Transactional
    public void updateCategory(Category category) {
        entityManager.merge(category);
        entityManager.flush();
    }

    @Override   
    @Transactional
    public void deleteCategory(Category category) throws IllegalArgumentException {
        if (findCategoryById(category.getId()) == null) throw new IllegalArgumentException();
        
        Category cat = entityManager.merge(category);
        entityManager.remove(cat);
        
        entityManager.flush();
    }

    @Override 
    @Transactional
    public void addCategory(Category category) {
        entityManager.persist(category);
        entityManager.flush();
    }

    @Override    
    public Category findCategoryById(Long id){
        return entityManager.find(Category.class, id);
    }
    
}
