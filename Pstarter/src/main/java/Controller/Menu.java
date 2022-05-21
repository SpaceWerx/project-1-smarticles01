package Controller;

import java.util.List;

import Models.Reimbursement_;
import Models.Roles;
import Models.Status;
import Models.Type;
import Models.Users_;
import Service.Reimbursement_Services;
import Service.User_Services;

public class Menu {
	/*EmployeeStatus es = new EmployeeStatus();
	ManagerStatus ms = new ManagerStatus();
	*/
	//Menu application with options//
	public void submitReimbursement(Users_ employee) {
		Reimbursement_ reimbursementToBeSubmitted = new Reimbursement_();
		reimbursementToBeSubmitted.setAuthor(employee.getId());
		
		System.out.println("What type of reimbursement would you like to submit?");
		System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
		System.out.println("1 -> Lodging");
		System.out.println("2 -> Travel");
		System.out.println("3 -> Food");
		System.out.println("4 -> Other");
		
		int typeDecision = promptSelection(validEntries: 1,2,3,4);
		
		switch(typeDecision) {
		case 1:
			reimbursementToBeSubmitted.setType(Type.lodging);
			break;
		case 2:
			reimbursementToBeSubmitted.setType(Type.travel);
			break;
		case 3:
			reimbursementToBeSubmitted.setType(Type.food);
			break;
		case 4:
			reimbursementToBeSubmitted.setType(Type.other);
			break;
		}
		
		System.out.println("Please enter the dollar amount you are requesting to be reimbursed: ");
		System.out.print("$");
		
		
		reimbursementToBeSubmitted.setAmount(parseDoubleInput(fetchInput()));
		if(reimbursementToBeSubmitted.getAmount() <= 0) {
			System.out.println("Invalid Amount has been entered, please input a correct dollar amount.");
			
			boolean valid = false;
			while(!valid) {
				reimbursementToBeSubmitted.setAmount(parseDoubleInput(fetchInput()));
				if(reimbursementToBeSubmitted.getAmount() != 0) {
					valid = true;
				}
			}
		}
		
		System.out.println("Please enter a description/reason for your reimbursement request.");
		
		reimbursementToBeSubmitted.setDescription(scan.nextLine());
		if(reimbursementToBeSubmitted.getDescription().trim().equals("")) {
			System.out.println("You cannot submit a request with an empty description, please explain the reason for your request.");
			
			boolean valid = false;
			while(!valid) {
				reimbursementToBeSubmitted.setDescription(scan.nextLine());
				if(!reimbursementToBeSubmitted.getDescription().trim().equals("")) {
					valid = true;
				}
			}
		}
		Reimbursement_Services.submitReimbursement_(reimbursementToBeSubmitted);
	}
	
	
	
	public int promptSelection(int ...validEntries) {
		int input;
		boolean valid = false;
		
		do {
			//receives user input//
			input = parseIntegerInput(fetchInput());
			
			//checks if input is valid//
			for(int entry : validEntries) {
				if(entry == input) {
					//if input good, continue//
					valid = true;
					break;
				}
			}
		//checks if input isn't valid//
		if(!valid) {
			System.out.println("Input received was not a vald option, please try again.");
		}
		}while(!valid);
		return input;
	}
	
	
	
	public void processReimbursement(Users_ manager) {
		boolean processPortal = true;
		System.out.println("---------------------------");
		System.out.println("Welcome to the Manager Processing Portal, " + manager.getusername());
		System.out.println("---------------------------");
		System.out.println();
		
		while(processPortal) {
			List<Reimbursement_> reimbursements = Reimbursement_Services.getPendingReimbursements();
			
			if(reimbursements.isEmpty()) {
				System.out.println("There are no reimbusements to process.");
				System.out.println("Returning to previous menu...");
				return;
			}
			
			int[] ids = new int[reimbursements.size()];
			
			for(int i = 0; i < reimbursements.size(); i++) {
				Reimbursement_ r = reimbursements.get(i);
				Users_ author = User_Services.getUserById(r.getAuthor());
				System.out.println(r.getId()) + " -> " + author.getUsername() + " : $" + r.getAmount());
				ids[i] = r.getId();
			}
			
			System.out.println("Please enter the ID of the Reimbursement you wish to process.");
			
			int selection = promptSelection(ids);
			
			Reimbursement_ reimbursementToBeProcessed = Reimbursement_Services.getReimbursementById(selection);
			System.out.println("Processing reimbursement #" + reimbursementToBeProcessed.getId());
			System.out.println("Details\nAuthor: "
					+ User_Services.getUserById(reimbursementToBeProcessed.getAuthor()).getUsername()
					+ "\nAmount: " + reimbursementToBeProcessed.getAmount()
					+ "\nDescription: " + reimbursementToBeProcessed.getDescription()
			);
			
			System.out.println("PLEASE ENTER THE NUMBRE OF YOUR CHOICE");
			System.out.println("1 -> Approve");
			System.out.println("2 -> Deny");
			
			int decision = promptSelection(...validEntries 1,2);
			Status status = (decision == 1) ? Status.approved : Status.denied;
			Reimbursement_Services.update(reimbursementToBeProcessed, manager.getId(),status);
			
			System.out.println("Would you like to process another reimbursement?");
			System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
			System.out.println("1 -> Yes");
			System.out.println("2 -> No");
			
			int lastChoice = promptSelection(...validEntries 1,2);
			
			if(lastChoice == 2) {
				processPortal = false;
			}
		}
	}
	
	public int parseIntegerInput(String input) {
		try {
			return Integer.parseInt(input);
		}catch(NumberFormatException e) {
			System.out.println("The input received was malformed, please try again.");
			return -1;
		}
	}
	
	public double parseDoubleInput(String input) {
		try {
			return Double.parseDouble(input);
		}catch(NumberFormatException e) {
			System.out.println("The input received was not valid, please try again.");
			//rejects other validation contingencies//
			return -1;
		}
		
	public void handlePortal(Roles role) {
		//get the list of employees//
		List<Users_> users = User_Services.getByRole(role);
		
		int[] ids = new int [users.size() + 1];
		ids[users.size()] = 0;
		for(int i = 0; i < users.size(); i++) {
			ids[i] = users.get(i).getId();
		}
		
		//Ask for employee ID number to continue//
		System.out.println("---------------------------");
		System.out.println("PLEASE ENTER THE NUMBER OF YOUR CHOICE");
		
		//Enhanced for loop to print out all users//
		for(Users_ u : users) {
			System.out.println(u.getId() + " -> " + u.getUsername());
		}
		System.out.println("0 -> Return to Main Menu");
		System.out.println();
		
		int userChoice = promptSelection(ids);
		
		if(userChoice == 0) {
			return;
		}
		Users_ employee = Reimbursement_Services.getUserById(userchoice);
		
		if(role == Roles.manager) {
			System.out.println("Opening Manager Portal for " + employee.getUsername());
			displayFinanceManagerMenu(employee);
		}else {
			System.out.println("Opening Employee Portal for " + employee.getUsername());
			displayEmployeeMenu(employee);
		}
	}
	}
	
	public String fetchInput() {
		//scan.nextLine() gets whole line including whitespace//
		//scan.split() gets line and separates into arrays by whitespace//
		//scan[0] keeps only the first element//
		return scan.nextLine().split(regex:" ")[0];
	}
	
	public void displayPendingReimbursements() {
		List<Reimbursement_> pendingReimbursements = Reimbursement_Services.getPendingReimbursements();
		
		if(pendingReimbursements.isEmpty()) {
			System.out.println("No Pending requests...");
			System.out.println("Returning to Previous Menu...");
		}
		for(Reimbursement_ r : pendingReimbursements) {
			System.out.println(r);
		}
	}
	
	public void displayResolvedReimbursements() {
		List<Reimbursement_> resolvedReimbursements - Reimbursement_Services.getResolvedReimbursements();
		
		if(resolvedReimbursements.isEmpty()) {
			System.out.println("No resolved Requests...");
			System.out.println("Returning to Previous Menu...");
		}
		for(Reimbursement_ r : resolvedReimbursements) {
			System.out.println(r);
		}
	}
	
	public void displayPreviousRequests(Users_ employee)
}
