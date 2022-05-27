package Controller;

import java.util.List;

import com.google.gson.Gson;

import Models.Users_;
import Service.User_Services;
import io.javalin.http.Handler;

public class UserController {
	User_Services us = new User_Services();
	
	//get all users
	
	
	//get by username
	
	
	//get by id
	
	public Handler getUserHandler = (ctx)->{
		List<Users_> allUsers = us.getAllUsers();
		Gson gson = new Gson();
		
		String JSONObject = gson.toJson(allUsers);
		
		ctx.result(JSONObject);
		ctx.status(200);
	};
	
	
	public Handler insertUserHandler = (ctx)->{
		String body = ctx.body();
		Gson gson = new Gson();
		Users_ user  = gson.fromJson(body, Users_.class);
		
		us.insertUser(user);
		ctx.result("Employee successfully added!");
		ctx.status(201);
	};
	
	
}
