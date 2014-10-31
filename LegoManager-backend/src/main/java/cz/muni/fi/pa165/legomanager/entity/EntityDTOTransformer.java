/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.entity;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoKitTO;
import cz.muni.fi.pa165.legomanager.transferobjects.LegoPieceTO;
import java.util.List;

/**
 *
 * @author Tomas
 */
public class EntityDTOTransformer {
    
    public static LegoPieceTO legoPieceConvert(LegoPiece legoPiece){
        if(legoPiece == null){
            return null;
        }else{
            LegoPieceTO piece = new LegoPieceTO();
            piece.setId(legoPiece.getId());
            piece.setColor(legoPiece.getColor());
            piece.setLegoKits(legoKitListConvert(legoPiece.getLegoKits()));
            return piece;
        }
    }
    
    public static LegoPiece legoPieceTOConvert(LegoPieceTO legoPiece){
        if(legoPiece == null){
            return null;
        }else{
            LegoPiece piece = new LegoPiece();            
            piece.setId(legoPiece.getId());
            piece.setColor(legoPiece.getColor());
            piece.setLegoKits(legoKitListTOConvert(legoPiece.getLegoKits()));
            return piece;
        }
    }
    
    public static List<LegoKitTO> legoKitListConvert(List<LegoKit> legoKits){
        return null;
    }
    
    public static List<LegoKit> legoKitListTOConvert(List<LegoKitTO> legoKits){
        return null;
    }
}