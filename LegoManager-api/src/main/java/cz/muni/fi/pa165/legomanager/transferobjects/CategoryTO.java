/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.transferobjects;

import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

/**
 *
 * @author Martin Laštovička
 */

@XmlRootElement
public class CategoryTO {
    private Long id;
    private String name;
    private String description;
    
    @XmlElement
    @XmlInverseReference(mappedBy="categories")
    private List<LegoKitTO> legoKits;
    
    @XmlElement
    @XmlInverseReference(mappedBy="categories")
    private List<LegoSetTO> legoSets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LegoKitTO> getLegoKits() {
        return legoKits;
    }

    public void setLegoKits(List<LegoKitTO> legoKits) {
        this.legoKits = legoKits;
    }

    public List<LegoSetTO> getLegoSets() {
        return legoSets;
    }

    public void setLegoSets(List<LegoSetTO> legoSets) {
        this.legoSets = legoSets;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final CategoryTO other = (CategoryTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CategoryTO{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
    }
       
}
