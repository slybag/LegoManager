/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.services.impl;

import cz.muni.fi.pa165.legomanager.dao.CategoryDao;
import cz.muni.fi.pa165.legomanager.dao.LegoDaoException;
import cz.muni.fi.pa165.legomanager.entity.Category;
import cz.muni.fi.pa165.legomanager.services.CategoryService;
import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martin Laštovička
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    DozerBeanMapper mapper;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public void setMapper(DozerBeanMapper mapper) {
        this.mapper = mapper;
    }
    
    
    
    @Override
    @Transactional
    public List<CategoryTO> getAllCategories() {
        List<CategoryTO> categoriesTOs;
        try {
            List<Category> categories = categoryDao.getAllCategories();
            categoriesTOs = new ArrayList<>();
            for (Category cat : categories) {
                categoriesTOs.add(mapper.map(cat, CategoryTO.class));
            }
        } catch (IllegalArgumentException | LegoDaoException | MappingException ex) {
            throw new RecoverableDataAccessException("Error while retrieving data", ex);
        }
        return categoriesTOs;
    }

    @Override
    @Transactional
    public void updateCategory(CategoryTO category) {
        try{
            categoryDao.updateCategory(mapper.map(category, Category.class));
        }catch(IllegalArgumentException | LegoDaoException | MappingException ex){
            throw new RecoverableDataAccessException("Error while updating category", ex);
        }
        
    }

    @Override
    @Transactional
    public void deleteCategory(CategoryTO category) {
        try{
            categoryDao.deleteCategory(mapper.map(category, Category.class));
        }catch(IllegalArgumentException | LegoDaoException | MappingException ex){
            throw new RecoverableDataAccessException("Error while deleting category", ex);
        }
        
    }

    @Override
    @Transactional
    public void createCategory(CategoryTO category) {
        try{
            categoryDao.addCategory(mapper.map(category, Category.class));
        }catch(IllegalArgumentException | LegoDaoException | MappingException ex){
            throw new RecoverableDataAccessException("Error while creating category", ex);
        }
        
    }

    @Override
    @Transactional
    public CategoryTO getCategory(Long id) {
        CategoryTO category;
        try{
            category = mapper.map(categoryDao.findCategoryById(id), CategoryTO.class);
        }catch(IllegalArgumentException | LegoDaoException | MappingException ex){
            throw new RecoverableDataAccessException("Error while retrieving category", ex);
        }
        return category;
    }

}
