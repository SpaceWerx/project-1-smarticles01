package Service;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.Query;

import com.revature.MockData;

import DAO.userDAO;
import Models.Roles;
import Models.Users_;
import Models.Users_;
public class User_Services {
	static userDAO uDAO = new userDAO();
	MockData md = new MockData();
	
	//get all users//
	public List<Users_> getAllUsers() throws SQLException{
		List<Users_> users = uDAO.getAllUsers();
		return users;
	}
	////////////////////////////////////////////////////////////o,limkjnjbv
	//get user by username method//
	public static Users_ getUserByUsername(String username){
		return uDAO.getUserByUsername(username);
		
	}
	////////////////////////////////////////////////////////
	//get user by id method//
	public static Users_ getUserById(int id){
		Users_ user = uDAO.getUserById(id);
		return user;
	}
	
	//query user exists by id method//
	public List<Users_> getid(int id) throws SQLException {
		List<Users_> users = uDAO.getid(id);
		return users;
		
	}	
	//get user by role method//
	public List<Users_> getUsersByRole(Roles role) throws SQLException {
		List<Users_> users = new ArrayList<>();
		
		for(Users_ user : uDAO.getAllUsers()) {
			if(user.getRole() == role) {
				users.add(user);
			}
		}
		
		return users;
	}
	
	//create new user//
	public void insertUser(Users_ newUser) throws SQLException{
		uDAO.insertUser(newUser);
	}
	public static List<Users_> getReimbursementsByRole(Roles role) {
		// TODO Auto-generated method stub
		return null;
	}
}
