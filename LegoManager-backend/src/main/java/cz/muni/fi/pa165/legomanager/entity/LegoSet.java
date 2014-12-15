package cz.muni.fi.pa165.legomanager.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Michal Rais
 */
@Entity
@Table(name="LegoSet")
public class LegoSet implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @ManyToMany
    private List<LegoKit> legoKits;
    
    private BigDecimal price;
    
    @ManyToMany(mappedBy="legoSets")
    private Set<Category> categories;

    private String name;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LegoKit> getLegoKits() {
        return legoKits;
    }

    public void setLegoKits(List<LegoKit> legoKits) {
        this.legoKits = legoKits;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
        final LegoSet other = (LegoSet) obj;
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
