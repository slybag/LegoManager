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
        return entityManager.createQuery("SELECT c FROM Category c", Category.class).getResultList();
    }

    @Override 
    @Transactional
    public void updateCategory(Category category) {
        if (category == null){
            throw new IllegalArgumentException("Category is null");
        }
        if (category.getName() == null) {
            throw new IllegalArgumentException("Category name is null");
        }
        Category cat = entityManager.find(Category.class, category.getId());
        if (cat == null) {
            throw new LegoDaoException("Category not found in database");
        } else {
            entityManager.merge(category);
            entityManager.flush();
        }
        
    }

    @Override   
    @Transactional
    public void deleteCategory(Category category) throws IllegalArgumentException {
        if (category == null){
            throw new IllegalArgumentException("Category is null");
        }
        if (category.getId() == null) {
            throw new IllegalArgumentException("Category Id is null");
        }
        Category cat = entityManager.find(Category.class, category.getId());
        if (cat == null) {
            throw new LegoDaoException("Category not found in database");
        } else {
            entityManager.remove(cat);
            entityManager.flush();
        }
    }

    @Override 
    @Transactional
    public void addCategory(Category category) {
        if (category == null){
            throw new IllegalArgumentException("Category is null");
        }
        if (category.getName() == null) {
            throw new IllegalArgumentException("Category name is null");
        }
        entityManager.persist(category);
        entityManager.flush();
    }

    @Override    
    public Category findCategoryById(Long id){
        if (id == null){
            throw new IllegalArgumentException("Id is null");
        }
        
        Category categoryToFind = entityManager.find(Category.class, id);
        if (categoryToFind == null) throw new IllegalArgumentException();
        
        return categoryToFind;
    }
}
