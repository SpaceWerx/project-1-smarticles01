package Service;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.Query;

import Models.Roles;
import Models.Users_;

public class User_Services {
	public static void main(String[] args) {
		User_Services us = new User_Services();
		Users_DAO uDAO = new Users_DAO();
		
	}
	//get user by username method//
	public List<Users_> getusername() throws SQLException {
		List<Users_> users = uDAO.getusername(username);
		
		return users;
	}
	//get user by id method//
	//query user exists by id method//
	public List<Users_> getid(int id) throws SQLException {
		List<Users_> users = uDAO.getid(id);
		
		try(Connection conn = ConnectionFactory.getConnection()){
			ResultSet rs = null;
			
			String sql = "select * from users where id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1,  id);
			rs = ps.executeQuery();
			
			List<Users_> users_List = new ArrayList<>();
			
			while(rs.next()) {
				Users_ u = new Users_(
						rs.getInt("id"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("role")
						);
				users_List.add(e);
			}
			return users_List;
		}
		catch(SQLException e) {
			System.out.println("Something went wrong, we were unable to find that ID.  Please try again.");
			e.printStackTrace();
			return null;
		}
		return users;
	}
	//get all users method//
	public List<Users_> getall(Users_ allusers) throws SQLException {
		List<Users_> users = uDAO.getusername(allusers);
		
		return users;
	}
	//get user by role method//
	public List<Users_> Role(Roles role) throws SQLException {
		List<Users_> users = uDAO.Roles(role);
		
		return users;
	}
}
