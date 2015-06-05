package code.database;

import java.util.List;

public interface ReceptCompDAO {
	ReceptCompDTO getReceptComp(int receptId, int raavareId) throws DALException;
	List<ReceptCompDTO> getReceptCompList(int receptId) throws DALException;
	List<ReceptCompDTO> getReceptCompList() throws DALException;
		void createReceptComp(ReceptCompDTO ReceptComponent) throws DALException;
	void updateReceptComp(ReceptCompDTO ReceptComponent) throws DALException;
}
