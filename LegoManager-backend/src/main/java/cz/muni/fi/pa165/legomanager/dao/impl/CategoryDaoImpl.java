/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.legomanager.dao.impl;

import cz.muni.fi.pa165.legomanager.dao.CategoryDao;
import cz.muni.fi.pa165.legomanager.entity.Category;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.springframework.stereotype.Repository;
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
    public void updateCategory(Category category) {
        if (!isValidCategory(category)) throw new IllegalArgumentException("Not a valid category");
        Category cat = entityManager.find(Category.class, category.getId());
        if (cat == null) {
            throw new PersistenceException("Category not found in database");
        } else {
            entityManager.merge(category);
            entityManager.flush();
        }
        
    }

    @Override   
    public void deleteCategory(Category category) {
        if (!isValidCategory(category)){
            throw new IllegalArgumentException("Not valid category");
        }
        Category cat = entityManager.find(Category.class, category.getId());
        if (cat == null) {
            throw new PersistenceException("Category not found in database");
        } 
        
        entityManager.remove(cat);
        entityManager.flush();
        
    }

    @Override 
    public void addCategory(Category category) {
        if (!isValidCategory(category)){
            throw new IllegalArgumentException("Not valid category");
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
        return categoryToFind;
    }
    
    public boolean isValidCategory(Category category){
        if (category == null ||
                category.getLegoKits() == null ||
                category.getLegoSets()== null ||
                category.getName() == null ) return false;        
        return true;
        
    
    }
}
