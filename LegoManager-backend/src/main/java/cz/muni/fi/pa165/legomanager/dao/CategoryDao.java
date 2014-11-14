/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.legomanager.dao;

import cz.muni.fi.pa165.legomanager.entity.Category;
import cz.muni.fi.pa165.legomanager.entity.LegoKit;
import cz.muni.fi.pa165.legomanager.entity.LegoSet;
import java.util.List;

/**
 *
 * @author Martin Laštovička
 */
public interface CategoryDao {
    /**
     * Finds all categories
     * @return list of all categories
     */
    public List<Category> getAllCategories() ;
    
    /**
     * Update category
     * @param category to be updated
     */
    public void updateCategory(Category category) ;
    
    /**
     * Remove category
     * @param category to be deleted
     */
    public void deleteCategory(Category category) ;
    
    /**
     * Add new category
     * @param category to be added   
     */
    public void addCategory(Category category) ;
    
    /**
     * Find category by id
     * @param id of wanted category
     * @return category with specified id
     */
    public Category findCategoryById (Long id) ;   
}
