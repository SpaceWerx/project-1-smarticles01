package Controller;

import java.util.Objects;

import Models.Users_;
import Service.AuthorizeService;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class AuthController {
	//authorize creation of new user and have json user object have an Id of 0 to map correctly
	public void handleRegister(Context ctx) {
		try {
			String input = ctx.body();
			ObjectMapper mapper = new ObjectMapper();
			Users_ user = mapper.readValue(input, Users_.class);
			
			//stores positive integer
			int id = AuthorizeService.register(user);
			
			//unsuccessful if ID still 0
			if(id == 0) {
				ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
				ctx.result("Registration unsuccessful.");
			}else {
				ctx.status(HttpCode.CREATED);
				ctx.result("Registration successful.");
			}
		}
		catch(Exception e) {
			//return 500 status
			ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
			if(!e.getMessage().isEmpty()) {
				ctx.result(e.getMessage());
			}
			e.printStackTrace();
		}
	}
	
	//authorize login credentials
	public void handleLogin(Context ctx) {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		if(Objects.equals(username, "") || Objects.equals(password, "")) {
			ctx.status(HttpCode.BAD_REQUEST);
			ctx.result("Invalid Credentials");
		}else {
			Users_ user = AuthorizeService.login(username, password);
			
			if(user != null) {
				ctx.status(HttpCode.ACCEPTED);
				ctx.header("Access-Control-Expose-Headers", "Current-User");
				ctx.header("Current-Usesr", "" + user.getId());
				ctx.result(user.getRole().toString());
			}else {
				ctx.status(HttpCode.BAD_REQUEST);
				ctx.result("Invalid Credentials");
			}
		}
	}
}
