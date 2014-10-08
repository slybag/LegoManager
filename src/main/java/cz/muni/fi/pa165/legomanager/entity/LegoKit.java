package cz.muni.fi.pa165.legomanager.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Petr Konecny
 */
@Entity
public class LegoKit implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private BigDecimal price;
    
    private int ageRestriction;
    
    private String name;
    
    @ManyToMany(mappedBy="Category")
    private Set<Category> categories;
    
    @ManyToMany(mappedBy="LegoSet")
    private List<LegoSet> legoSets;

    @ManyToMany(mappedBy="LegoPiece")
    private List<LegoPiece> legoPieces;
    
    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
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
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final LegoKit other = (LegoKit) obj;
        return this.id == other.id;
    }   

    /**
     * @return the legoPieces
     */
    public List<LegoPiece> getLegoPieces() {
        return legoPieces;
    }

    /**
     * @param legoPieces the legoPieces to set
     */
    public void setLegoPieces(List<LegoPiece> legoPieces) {
        this.legoPieces = legoPieces;
    }
    
}
