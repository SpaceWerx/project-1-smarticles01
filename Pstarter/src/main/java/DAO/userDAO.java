package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.Roles;
import Models.Users_;
import Utilities.ConnectionFactory;

public class userDAO {
	//list all users//
	public List<Users_>getAllUsers() throws SQLException{
		try(Connection conn = ConnectionFactory.getConnection()){
			ResultSet rs;
			String sql = "select * from ers_user;";
			Statement s = conn.createStatement();
			rs = s.executeQuery(sql);
			
			List<Users_>userList = new ArrayList<>();
			
			while(rs.next()) {
				userList.add(new Users_(
						rs.getInt("id"),
						rs.getString("username"),
						rs.getString("password"),
						Roles.valueOf(rs.getString("role"))
						));
					
			}
			return userList;
		}
		catch(SQLException e) {
			System.out.println("Something went wrong while getting all users.");
			e.printStackTrace();
			return null;
		}
	}
	
	//insert new users to database//
	public void insertUser(Users_ newUser) throws SQLException{
		try(Connection conn = ConnectionFactory.getConnection()){
			String sql = "insert into user (username, password, role) " + "values (?, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, newUser.getUsername());
			ps.setString(2, newUser.getPassword());
			ps.setString(3, newUser.getRole().name());
			ps.executeUpdate();
			
			System.out.println("User " + newUser.getUsername() + " was created. Welcome to the team!");
		}
		catch(SQLException e) {
			System.out.println("Something went wrong");
			e.printStackTrace();
		}
	}
	
	//list users by id//
	public Users_ getUserById(int id){
		try(Connection conn = ConnectionFactory.getConnection()){
			ResultSet rs = null;
			String sql = "select * from ers_user where id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1,  id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return new Users_(
						rs.getInt("id"),
						rs.getString("username"),
						rs.getString("password"),
						Roles.valueOf(rs.getString("role"))
						);
			}
		}
		catch(SQLException e) {
			System.out.println("Something went wrong while getting all users.");
			e.printStackTrace();
		}
		return null;
	}
	
	//list users by username//
	public static Users_ getUserByUsername(String username){
		try(Connection conn = ConnectionFactory.getConnection()){
		
			String sql = "select * from users where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
                return new Users_(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    Roles.valueOf(rs.getString("role"))
                        );
			}
		}
		catch(SQLException e) {
			System.out.println("Something went wrong while getting all users.");
			e.printStackTrace();
			
		}
		return null;
	}

	public List<Users_> Roles(Roles role) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Users_> getid(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static int create(Users_ userToBeRegistered) {
		// TODO Auto-generated method stub
		return 0;
	}
}
