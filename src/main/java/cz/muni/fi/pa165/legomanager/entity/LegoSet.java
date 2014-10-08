/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.entity;

import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Michal Rais
 */
public class LegoSet {
    @Id
    @GeneratedValue
    private long id;
    
    @ManyToMany
    private List<LegoKit> legoKits;
    
    @Column
    private int price;
    
    @ManyToMany
    private Set<Category> categories;

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<LegoKit> getLegoKits() {
        return legoKits;
    }

    public void setLegoKits(List<LegoKit> legoKits) {
        this.legoKits = legoKits;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LegoSet other = (LegoSet) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }        
}
