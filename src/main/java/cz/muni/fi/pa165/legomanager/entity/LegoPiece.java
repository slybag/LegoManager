package cz.muni.fi.pa165.legomanager.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author 
 */
@Entity
public class LegoPiece implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private PetColor color;
	
    public enum PetColor{
	BLACK,WHITE,RED,BLUE,YELLOW,PURPLE,GREEN,VIOLET,GREY
    }
    
    @ManyToMany
    private List<LegoKit> legoKits;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetColor getColor() {
        return color;
    }

    public void setColor(PetColor color) {
        this.color = color;
    }

    public List<LegoKit> getLegoKits() {
        return legoKits;
    }

    public void setLegoKits(List<LegoKit> legoKits) {
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
        final LegoPiece other = (LegoPiece) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
