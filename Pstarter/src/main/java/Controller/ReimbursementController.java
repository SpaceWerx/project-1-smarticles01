package Controller;

import Models.Reimbursement_;
import Models.Status;
import Service.Reimbursement_Services;
import Service.User_Services;
import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class ReimbursementController {
	//re-submition
	public void handleSubmit(Context ctx) {
		try {
			String input = ctx.body();
			
			Reimbursement_ reimbursement = ObjectMapper.readValue(input, Reimbursement_.class);
			
			int id = Reimbursement_Services.submitReimbursement(reimbursement);
			
			if(id != 0) {
				ctx.status(HttpCode.CREATED);
				ctx.result("" + id);
			}else {
				ctx.status(HttpCode.BAD_REQUEST);
				ctx.result("Reimbursement submission was unsuccessful.");
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
	
	
	//
	public void handleProcess(Context ctx) {
		//retrieve header that stores user id
		String authHeader = ctx.header("Current-User");
		
		//verify header's with request
		if(authHeader != null) {
			int userId = Integer.parseInt(authHeader);
			
			try {
				String reimbursementIdInput = ctx.pathParam("id");
				int id =Integer.parseInt(reimbursementIdInput);
				String statusInput = ctx.formParam("status");
				Reimbursement_ reimbursement = Reimbursement_Services.getReimbursementById(id);
				
				//check if reimbursement exists in database
				if(reimbursement != null) {
					Reimbursement_ processedReimbursement = Reimbursement_Services.update(reimbursement, userId, Status.valueOf(statusInput));
					ctx.status(HttpCode.ACCEPTED);
					ctx.json(processedReimbursement);
				}else {
					ctx.status(HttpCode.BAD_REQUEST);
					ctx.result("Reimbursement processing was not successful.");
				}
			}catch(Exception e) {
				//return 500 status
				ctx.status(HttpCode.INTERNAL_SERVER_ERROR);
				if(!e.getMessage().isEmpty()) {
					ctx.result(e.getMessage());
				}
				e.printStackTrace();
			}
		}else {
			//missing authHeader
			ctx.status(HttpCode.FORBIDDEN);
			ctx.result("Missing Current User Header with ID");
		}
		
		
	}
	
	//entry point to get any reimbursement
	public void handleGetReimbursements(Context ctx) {
		if(ctx.queryParam("author") != null) {
			handleGetReimbursementsByAuthor(ctx);
		}else if(ctx.queryParam("status") != null) {
			handleGetReimbursementByStatus(ctx);
		}
	}
	
	//entry point to get reimbursement by status
	public void handleGetReimbursementByStatus(Context ctx) {
		try {
			String statusParam = ctx.queryParam("status");
			Status status = Status.valueOf(statusParam);
			
			//retrieve all pending and resolved reimbursements
			if(status == Status.pending) {
				ctx.status(HttpCode.OK);
				ctx.json(Reimbursement_Services.getPendingReimbursements());
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
	
	
	//entry point to get reimbursement by id
	public void handleGetReimbursementById(Context ctx) {
		try {
			String idParam = ctx.pathParam("id");
			int id = Integer.parseInt(idParam);
			
			Reimbursement_ reimbursement = Reimbursement_Services.getReimbursementById(id);
			
			if(reimbursement != null) {
				ctx.status(HttpCode.OK);
				ctx.json(reimbursement);
			}else {
				ctx.status(HttpCode.BAD_REQUEST);
				ctx.result("Could not retrieve the reimbursement.");
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
	
	
	//entry point to get reimbursement by author
	public void handleGetReimbursementsByAuthor(Context ctx) {
		try {
			String idParam = ctx.queryParam("author");
			
			if(idParam != null) {
				int id = Integer.parseInt(idParam);
				
				if(User_Services.checkUserExistsById(id)) {
					ctx.status(HttpCode.OK);
					ctx.json(Reimbursement_Services.getReimbursementsByAuthor(id);
				}else {
					ctx.status(HttpCode.NOT_FOUND);
					ctx.result("Unable to retrieve reimbursements, current user is not in the database");
				}
			}else {
				ctx.status(HttpCode.BAD_REQUEST);
				ctx.result("Missing Current User header");
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
}
