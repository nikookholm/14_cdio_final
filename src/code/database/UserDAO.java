package code.database;

import java.util.ArrayList;
import java.util.List;


public interface UserDAO {
	UserDTO getUser(int oprId) throws DALException;
	ArrayList<UserDTO> getUserList() throws DALException;
	void createUser(UserDTO opr) throws DALException;
	void updateUser(UserDTO opr) throws DALException;
	void deleteUser(UserDTO opr) throws DALException;
}
