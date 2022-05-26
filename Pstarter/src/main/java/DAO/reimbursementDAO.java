package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Models.Reimbursement_;
import Models.Status;
import Models.Type;
import Utilities.ConnectionFactory;

public class reimbursementDAO {
	public void update(Reimbursement_ unprocessedReimbursement) {
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			String sql = "UPDATE ers_reimbursements SET resolver = ?, status = ?::status WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, unprocessedReimbursement.getResolver());
			preparedStatement.setObject(2, unprocessedReimbursement.getStatus().name());
			preparedStatement.setInt(3, unprocessedReimbursement.getId());
			preparedStatement.executeUpdate();
			
			System.out.println("Reimbursement Successfully Updated!");
		}
		catch(SQLException e) {
			System.out.println("Updating Failed!");
			e.printStackTrace();
		}
	}
	public List<Reimbursement_> getReimbursementsByUser(int userId){
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			String sql = "select * from ers_reimbursements where author = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			List<Reimbursement_> reimbursements = new ArrayList<>();
			
			while(resultSet.next()){
				reimbursements.add(new Reimbursement_(
						resultSet.getInt("id"),
						resultSet.getInt("author"),
						resultSet.getInt("resolver"),
						resultSet.getString("description"),
						Type.valueOf(resultSet.getString("type")),
						Status.valueOf(resultSet.getString("status")),
						resultSet.getDouble("amount")
						));
			}
			return reimbursements;
		}
		catch(SQLException e) {
			System.out.println("Something went wrong obtaning your list!");
			e.printStackTrace();
		}
		return null;
	}
	
	public Reimbursement_ getReimbursementsById(int id) {
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			String sql = "select * from ers_reimbursements where id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return new Reimbursement_(
						resultSet.getInt("id"),
						resultSet.getInt("author"),
						resultSet.getInt("resolver"),
						resultSet.getString("description"),
						Type.valueOf(resultSet.getString("type")),
						Status.valueOf(resultSet.getString("status")),
						resultSet.getDouble("amount")
						);
			}
		}
		catch(SQLException e) {
			System.out.println("Something went wrong with the database!");
			e.printStackTrace();
		}
		return null;
	}

	public List<Reimbursement_> getReimbursementsByStatus(int id) {
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			String sql = "select * from ers_reimbursements where status = ?::status";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, status.toString());
			ResultSet resultSet = preparedStatement.executeQuery();
			
			List<Reimbursement_> reimbursements = new ArrayList<>();
			
			while(resultSet.next()) {
				reimbursements.add(new Reimbursement_(
						resultSet.getInt("id"),
						resultSet.getInt("author"),
						resultSet.getInt("resolver"),
						resultSet.getString("description"),
						Type.valueOf(resultSet.getString("type")),
						Status.valueOf(resultSet.getString("status")),
						resultSet.getDouble("amount")
				));
			}
			return reimbursements;
		}
		catch(SQLException e) {
			System.out.println("Something went wrong with the database!");
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Reimbursement_> getAllReimbursements(){
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			List<Reimbursement_> reimbursements = new ArrayList<>();
			String sql = "Select * from ers_reimbursements";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()) {
				reimbursements.add(new Reimbursement_(
						resultSet.getInt("id"),
						resultSet.getInt("author"),
						resultSet.getInt("resolver"),
						resultSet.getString("description"),
						Type.valueOf(resultSet.getString("type")),
						Status.valueOf(resultSet.getString("status")),
						resultSet.getDouble("amount")
				));
			}
			return reimbursements; 
		}
		catch(SQLException sqlException) {
			System.out.println("Something went wrong with the database!");
			sqlException.printStackTrace();
		}
		return null;
	}
	
	public int create(Reimbursement_ reimbursementToBeSubmitted) {
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			String sql = "INSERT INTO ers_reimbursements (author, description, type, status, amount)"
					+ "VALUES (?, ?, ?::type, ?::status, ?)"
					+ "RETURNING ers_reimbursements.id";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, reimbursementToBeSubmitted.getAuthor());
			preparedStatement.setString(2, reimbursementToBeSubmitted.getDescription());
			preparedStatement.setObject(3, reimbursementToBeSubmitted.getType().name());
			preparedStatement.setObject(4, reimbursementToBeSubmitted.getStatus().name());
			preparedStatement.setDouble(5, reimbursementToBeSubmitted.getAmount());
			ResultSet resultSet;
			
			if((resultSet = preparedStatement.executeQuery()) != null) {
				resultSet.next();
				return resultSet.getInt(1);
			}
		}
		catch(SQLException e) {
			System.out.println("Creating reimbursement has failed.");
			e.printStackTrace();
		}
		return 0;
	}
}
