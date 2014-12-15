package cz.muni.fi.pa165.legomanager.transferobjects;

import cz.muni.fi.pa165.legomanager.support.Color.PieceColor;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

/**
 *
 * @author Tomas
 */

@XmlRootElement
public class LegoPieceTO {
    
    @XmlElement
    private Long id;
    
    @XmlElement
    private PieceColor color;
    
    @XmlElement
    @XmlInverseReference(mappedBy="legoPieces")
    private List<LegoKitTO> legoKits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
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
        final LegoPieceTO other = (LegoPieceTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LegoPieceTO{" + "id=" + id + ", color=" + color + '}';
    }
    
}
