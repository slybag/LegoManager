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

/**
 *
 * @author Martin Laštovička
 */
public class CategoryDaoImpl implements CategoryDao{
    
    List<Category> categories;
    
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Category> getAllCategories() {
        return entityManager.createNamedQuery("SELECT c FROM Category C", Category.class).getResultList();
    }

    @Override
    public void updateCategory(Category category) {
        entityManager.merge(category);
    }

    @Override
    public void deleteCategory(Category category) {
        entityManager.remove(category);
    }

    @Override
    public void addCategory(Category category) {
        entityManager.persist(category);
    }

    @Override
    public Category findCategoryById(Long id){
        return entityManager.find(Category.class, id);
    }
    
}
