/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Petr Konecny
 */
public class LegoKit {
    
    
    private long id;
    private BigDecimal price;
    private int ageRestriction;
    private Set<Category> categories;
    private List<LegoSet> legoSets;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the ageRestriction
     */
    public int getAgeRestriction() {
        return ageRestriction;
    }

    /**
     * @param ageRestriction the ageRestriction to set
     */
    public void setAgeRestriction(int ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    /**
     * @return the categories
     */
    public Set<Category> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    /**
     * @return the legoSets
     */
    public List<LegoSet> getLegoSets() {
        return legoSets;
    }

    /**
     * @param legoSets the legoSets to set
     */
    public void setLegoSets(List<LegoSet> legoSets) {
        this.legoSets = legoSets;
    }
    
    
    
}
