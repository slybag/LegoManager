package cz.muni.fi.pa165.legomanager.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Martin Laštovička
 */
@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column
    private String description;
    
    @ManyToMany
    private List<LegoKit> legoKits;
    
    @ManyToMany
    private List<LegoSet> legoSets;

    /**
     * 
     * @return Id 
     */
    public Long getId() {
        return id;
    }
    
    /**
     * 
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * 
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param id id to be set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @param name name to be set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 
     * @param description description to be set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return lego kits
     */
    public List<LegoKit> getLegoKits() {
        return legoKits;
    }

    /**
     * 
     * @param legoKits lego kits to be set
     */
    public void setLegoKits(List<LegoKit> legoKits) {
        this.legoKits = legoKits;
    }

    /**
     * 
     * @return lego sets
     */
    public List<LegoSet> getLegoSets() {
        return legoSets;
    }

    /**
     * 
     * @param legoSets lego sets to be set
     */
    public void setLegoSets(List<LegoSet> legoSets) {
        this.legoSets = legoSets;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.id);
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
        final Category other = (Category) obj;
        return Objects.equals(this.id, other.id);
    }

}
