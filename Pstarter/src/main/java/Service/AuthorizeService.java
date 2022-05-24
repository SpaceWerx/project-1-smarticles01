package Service;

import DAO.userDAO;
import Models.Users_;

public class AuthorizeService {
	//make new user object
	public int register(Users_ userToBeRegistered) {
		
		//check  if username exists, so if null username available
		try {
			if(userDAO.getUserByUsername(userToBeRegistered.getUsername()) != null) {
				
				//userame already exists
				throw new NullPointerException("Username already exists. Modify your input.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//take in user object sent from menu ad sent to userDAO to insert into Database
		//after entry's made, the id of the new user's returned
		return userDAO.create(userToBeRegistered);
	}
	
	public Users_ login(String username, String password) {
		//temp user
		Users_ user;
		
		//use try+catch to catch any exceptions thrown by userDAO
		try {
			//get user data from username given
			user = userDAO.getUserByUsername(username);
			
			//check if user exists
			if(user != null) {
				
				//check if password is correct
				if(password.equals(user.getPassword())) {
					System.out.println("Logged In Successfully!");
					return user;
				}else if (!password.equals(user.getPassword())) {
					System.out.println("Wrong Password. Watch for caps!");
					return null;
				}
			}
			else {
				System.out.println("If you would like to create a user and sign in, please contact our HR Department at 555-555-5555. Thank you!");
				return null;
			}
		}catch(Exception e) {
			System.out.println("Login Unsuccessful");
			e.printStackTrace();//helpful debugging tool
		}
		return null;
	}
}
