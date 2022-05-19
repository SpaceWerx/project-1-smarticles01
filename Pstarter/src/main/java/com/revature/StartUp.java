package com.revature;

import java.io.Console;

public class StartUp {
	private static int _select;
	
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
	}
}
