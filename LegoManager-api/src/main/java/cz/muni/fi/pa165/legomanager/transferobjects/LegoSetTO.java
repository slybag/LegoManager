/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.transferobjects;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Michal Rais
 */
public class LegoSetTO {
    private Long id;
    private BigDecimal price;
    private Set<CategoryTO> categories;
    private List<LegoKitTO> legoKits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<CategoryTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryTO> categories) {
        this.categories = categories;
    }

    public List<LegoKitTO> getLegoKits() {
        return legoKits;
    }

    public void setLegoKits(List<LegoKitTO> legoKits) {
        this.legoKits = legoKits;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final LegoSetTO other = (LegoSetTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }        

public class LegoSetTO implements Serializable {
    
    
    private Long id;
    
    private List<LegoKitTO> legoKits;
    
    private BigDecimal price;
    
    private Set<CategoryTO> categories;

    private String name;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LegoKitTO> getLegoKits() {
        return legoKits;
    }

    public void setLegoKits(List<LegoKitTO> legoKits) {
        this.legoKits = legoKits;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<CategoryTO> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryTO> categories) {
        this.categories = categories;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final LegoSetTO other = (LegoSetTO) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
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
}
