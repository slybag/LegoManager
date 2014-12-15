package cz.muni.fi.pa165.legomanager.services;

import cz.muni.fi.pa165.legomanager.transferobjects.LegoSetTO;
import java.util.List;

/**
 *
 * @author Michal Rais
 */
public interface LegoSetService {
    public void createLegoSet(LegoSetTO legoSet);
    public void updateLegoSet(LegoSetTO legoSet);
    public void removeLegoSet(LegoSetTO legoSet);
    public LegoSetTO getLegoSet(Long id);
    public List<LegoSetTO> getAllLegoSets();
}
