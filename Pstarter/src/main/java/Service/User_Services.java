package Service;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.Query;

import DAO.userDAO;
import Models.Roles;
import Models.Users_;
i
public class User_Services {
	userDAO uDAO = new userDAO();
	
	
	//get all users//
	public List<Users_> getAllUsers() throws SQLException{
		List<Users_> users = uDAO.getAllUsers();
		return users;
	}
	
	//get user by username method//
	public List<Users_> getUserByUsername() throws SQLException {
		List<Users_> user = uDAO.getusername(username);
		
		return user;
	}
	
	//get user by id method//
	public List<Users_> getUserById(int id){
		List<Users_> user = uDAO.getUserById(id);
		return user;
	}
	
	//query user exists by id method//
	public List<Users_> getid(int id) throws SQLException {
		List<Users_> users = uDAO.getid(id);
		return users;
		
	}	
	//get user by role method//
	public List<Users_> Role(Roles role) throws SQLException {
		List<Users_> users = uDAO.Roles(role);
		
		return users;
	}
	
	//create new user//
	public void insertUser(Users_ newUser) throws SQLException{
		uDAO.insertUser(newUser);
	}
}
