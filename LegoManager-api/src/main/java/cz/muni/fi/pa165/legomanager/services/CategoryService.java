package cz.muni.fi.pa165.legomanager.services;

import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import java.util.List;

/**
 *
 * @author Martin Laštovička
 */
public interface CategoryService {
        
     /**
     * Finds all categories
     * @return list of all categories
     */
    public List<CategoryTO> getAllCategories() ;
    
    /**
     * Update category
     * @param category
     */
    public void updateCategory(CategoryTO category);
    
    /**
     * Removes category
     * @param category to be deleted
     * @throws IllegalArgumentException
     */     
    public void deleteCategory (CategoryTO category);
    
    /**
     * Add new category
     * @param category to be added   
     */
    public void createCategory (CategoryTO category);
    
    /**
     * Find category by id
     * @param id of wanted category
     * @return category with specified id
     */
    public CategoryTO getCategory (Long id);
}
