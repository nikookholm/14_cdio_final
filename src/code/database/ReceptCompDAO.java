package code.database;

import java.util.ArrayList;
import java.util.List;

public interface ReceptCompDAO {
	ReceptCompDTO getReceptComp(int receptId, int ingredientId) throws DALException;
	ArrayList<ReceptCompDTO> getReceptCompList(int receptId) throws DALException;
	ArrayList<ReceptCompDTO> getReceptCompList() throws DALException;
	void createReceptComp(ReceptCompDTO receptcomponent) throws DALException;
	void updateReceptComp(ReceptCompDTO receptcomponent) throws DALException;
}
