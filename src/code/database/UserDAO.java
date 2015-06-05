package code.database;

import java.util.List;


public interface UserDAO {
	UserDTO getUser(int oprId) throws DALException;
	List<UserDTO> getUserList() throws DALException;
	void createUser(UserDTO opr) throws DALException;
	void updateUser(UserDTO opr) throws DALException;
}
