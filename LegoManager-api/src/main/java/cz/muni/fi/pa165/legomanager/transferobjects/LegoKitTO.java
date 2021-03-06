package cz.muni.fi.pa165.legomanager.transferobjects;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

/**
 *
 * @author Petr Konecny
 */
@XmlRootElement
public class LegoKitTO {

    @XmlElement
    private Long id;
    @XmlElement
    private BigDecimal price;
    @XmlElement
    private Integer ageRestriction;
    @XmlElement
    private String name;
    @XmlElement
    @XmlInverseReference(mappedBy = "legoKits")
    private Set<CategoryTO> categories;
    @XmlElement
    @XmlInverseReference(mappedBy = "legoKits")
    private List<LegoSetTO> legoSets;
    @XmlElement
    @XmlInverseReference(mappedBy = "legoKits")
    private List<LegoPieceTO> legoPieces;

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

    public Integer getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(Integer ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    /**
     * @return the categories
     */
    public Set<CategoryTO> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(Set<CategoryTO> categories) {
        this.categories = categories;
    }

    /**
     * @return the legoSets
     */
    public List<LegoSetTO> getLegoSets() {
        return legoSets;
    }

    /**
     * @param legoSets the legoSets to set
     */
    public void setLegoSets(List<LegoSetTO> legoSets) {
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
        final LegoKitTO other = (LegoKitTO) obj;
        return Objects.equals(this.id, other.id);
    }

    /**
     * @return the legoPieces
     */
    public List<LegoPieceTO> getLegoPieces() {
        return legoPieces;
    }

    /**
     * @param legoPieces the legoPieces to set
     */
    public void setLegoPieces(List<LegoPieceTO> legoPieces) {
        this.legoPieces = legoPieces;
    }

    @Override
    public String toString() {
        return "LegoKitTO{" + "id=" + id + ", price=" + price + ", ageRestriction=" + ageRestriction + ", name=" + name + '}';
    }
}
