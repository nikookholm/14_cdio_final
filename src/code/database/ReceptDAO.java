package code.database;

import java.util.ArrayList;
import java.util.List;


public interface ReceptDAO {
	ReceptDTO getRecept(int receptId) throws DALException;
	ArrayList<ReceptDTO> getReceptList() throws DALException;
	void createRecept(ReceptDTO recept) throws DALException;
	void updateRecept(ReceptDTO recept) throws DALException;
}
