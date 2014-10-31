/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.services.impl;

import cz.muni.fi.pa165.legomanager.CategoryDao;
import cz.muni.fi.pa165.legomanager.entity.Category;
import cz.muni.fi.pa165.legomanager.services.CategoryService;
import cz.muni.fi.pa165.legomanager.transferobjects.CategoryTO;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martin Laštovička
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
    
    @Autowired
    CategoryDao categoryDao;
    
    @Autowired
    DozerBeanMapper mapper;
    
    @Override
    public List<CategoryTO> getAllCategories() {
        List<Category> categories = categoryDao.getAllCategories();
        List<CategoryTO> categoriesTOs = new ArrayList<>();
        for(Category cat : categories){
            categoriesTOs.add(mapper.map(cat, CategoryTO.class));
        }
        return categoriesTOs;
    }

    @Override
    public void updateCategory(CategoryTO category) {
        categoryDao.updateCategory(mapper.map(category, Category.class));
    }

    @Override
    public void deleteCategory(CategoryTO category) {
        categoryDao.deleteCategory(mapper.map(category, Category.class));
    }

    @Override
    public void createCategory(CategoryTO category) {
        categoryDao.addCategory(mapper.map(category, Category.class));
    }

    @Override
    public CategoryTO getCategory(Long id) {
        return mapper.map(categoryDao.findCategoryById(id), CategoryTO.class);
    }

    
}
