package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Models.Reimbursement_;

public class reimbursementDAO {
	public void update(Reimbursement_ unprocessedReimbursement) {
		try(Connection connection = ConnectionFactoryUtility.getConnection()){
			String sql = "UPDATE ers_reimbursements SET resolver = ?, status = ?::status WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, unprocessedReimbursement.getResolver());
		}
	}
}
