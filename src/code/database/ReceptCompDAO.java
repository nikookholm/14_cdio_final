package code.database;

import java.util.List;

public interface ReceptCompDAO {
	ReceptCompDTO getReceptComp(int receptId, int ingredientId) throws DALException;
	List<ReceptCompDTO> getReceptCompList(int receptId) throws DALException;
	List<ReceptCompDTO> getReceptCompList() throws DALException;
	void createReceptComp(ReceptCompDTO receptcomponent) throws DALException;
	void updateReceptComp(ReceptCompDTO receptcomponent) throws DALException;
}
