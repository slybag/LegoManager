/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.muni.fi.pa165.legomanager;

import cz.muni.fi.pa165.legomanager.entity.Category;
import java.util.List;

/**
 *
 * @author Martin Laštovička
 */
public interface CategoryDao {
    /**
     * Finds all categories
     * @return list of all categories
     * @throws IllegalArgumentException
     * @throws LegoDaoException
     */
    public List<Category> getAllCategories() throws IllegalArgumentException,LegoDaoException;
    
    /**
     * Update category
     * @param category to be updated
     * @throws IllegalArgumentException
     * @throws LegoDaoException
     */
    public void updateCategory(Category category) throws IllegalArgumentException,LegoDaoException;
    
    /**
     * Remove category
     * @param category to be deleted
     * @throws IllegalArgumentException
     * @throws LegoDaoException
     */
    public void deleteCategory(Category category) throws IllegalArgumentException,LegoDaoException;
    
    /**
     * Add new category
     * @param category to be added   
     * @throws IllegalArgumentException
     * @throws LegoDaoException 
     */
    public void addCategory(Category category) throws IllegalArgumentException,LegoDaoException;
    
    /**
     * Find category by id
     * @param id of wanted category
     * @return category with specified id
     * @throws IllegalArgumentException
     * @throws LegoDaoException 
     */
    public Category findCategoryById (Long id) throws IllegalArgumentException,LegoDaoException;
}
