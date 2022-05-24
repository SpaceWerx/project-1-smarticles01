package com.revature;

import java.io.Console;
import java.sql.Connection;
import java.sql.SQLException;

import Controller.Menu;
import Utilities.ConnectionFactory;

public class StartUp {
	public static void main(String[] args)throws SQLException {
		//Testing Database Connectivity - just testing whether our Connection (from ConnectionFactory) is successful
		try(Connection conn = ConnectionFactory.getConnection()){
			System.out.println("Connection Successful :)");
		} catch(SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
		}
	}
	//make menu run
	//Menu menu = new Menu();
	
	//menu.displayMenu();
	
/*	private static int _select;
	
	//entry to main menu//
	public static void RootMenu() {
		Console.WriteLine(@"application with exit");
	}
	
	//User input to determine which selection displayed//
	_select = Convert.ToInt32(Console.ReadLine());
	
	if(_select == 1) {
		Console.Clear();
		Console.WriteLine(@"Selection with options");
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
}
