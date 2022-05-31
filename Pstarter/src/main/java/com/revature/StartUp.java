package com.revature;

import java.io.Console;
import java.sql.Connection;
import java.sql.SQLException;

import Controller.Menu;
import Controller.UserController;
import DAO.userDAO;
import Utilities.ConnectionFactory;
import io.javalin.Javalin;

public class StartUp {
	public static void main(String[] args)throws SQLException {
		UserController uc = new UserController();
		//userDAO ud = new userDAO();
		//Testing Database Connectivity - just testing whether our Connection (from ConnectionFactory) is successful
		try(Connection conn = ConnectionFactory.getConnection()){
			System.out.println("Connection Successful :)");
		} catch(SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
	
		//System.out.println(ud.getAllUsers());
	//make menu run
	Menu menu = new Menu();
	
	menu.displayMenu();
	
	//int _select;
	
	/*//entry to main menu//
	public static void RootMenu() {
		Console.WriteLine("application with exit");
	}
	
	//User input to determine which selection displayed//
	_select = Convert.ToInt32(Console.ReadLine());
	
	if(_select == 1) {
		Console.Clear();
		Console.WriteLine("Selection with options");
	}
	//User input to go back to main menu//
	if(_select == 5) {
		RootMenu();
	}
	
	//Allows main menu to be called in other classes/methods//
	public static void ReturnRootMenu() {
		Console.Clear();
		RootMenu();
	}*/
	
	/*//Javalin object, creates connection
	Javalin app = Javalin.create(
		config -> {
			config.enableCorsForAllOrigins();
		}
	).start(4000);
	
	//get endpoints//
	app.get("/user", uc.getUserHandler);
	app.post("/user", uc.insertUserHandler);
	/*app.post("/login", uc.loginHandler);
	
	
	//set login path
			path("login", ()->{
				post(authController::handleLogin);
			});
			
			//set register path
			path("register", ()->{
				post(authController::handleRegister);
			});
			
			//set users path
			path("users", ()->{
				get(userController::handleGetUsers);
				
				//set sub-path to request by id through user
				path("{id}", ()->{
					get(userController::handleGetUserById);
				});
			});
			
			//set reimbursement path
			path("reimbursements", ()->{
				get(reimbursementController::handleGetReimbursements);
				post(reimbursementController::handleSubmit);
				
				//set sub-path to request by id through reimbursement
				path("{id}", ()->{
						get(reimbursementController::handleGetReimbursementById);
						put(reimbursementController::handleProcess);
				});
			});
		};*/
	}
	
}


	
