package Models;

import java.util.List;

public class Reimbursement_ {
	//private static final String Reimbursement = null;//

	private final List<Reimbursement> reimbursements = new Arraylist<>();
	
	private int id;
	private String author;
	private String resolver;
	private String description;
	private Type type;
	private Status status;
	private double amount;
	
	
	public Reimbursement_() {
		Reimbursement_ userData = new Reimbursement_();
		//String ArrayList <(int, String, Type, Status, amount)>;//
		
		
		Reimbursement Process1 = new Reimbursement(id: 1, author: 1, resolver: 0, description: "Oracle Java Certifiction", Type.other, Status.pending, amount: 450.00);
		Reimbursement Process2 = new Reimbursement(id: 2, author: 2, resolver: 0, description: "Travel to Busan HQ", Type.travel, Status.pending, amount: 900.00);
		Reimbursement Approved1 = new Reimbursement(id: 3, author: 1, resolver: 3, description: "Free lunch offer from Suga", Type.food, Status.approved, amount: 800.03);
		Reimbursement Approved2 = new Reimbursement(id: 4, author: 2, resolver: 4, description: "2-night hotel stay near Nigerian Office for visit", Type.lodging, Status.approved, amount: 15750.00);
		Reimbursement Denied = new Reimbursement(id: 5, author: 1, resolver: 3, description: "Cruise trip from Busan to Nigera", Type.travel, Status.denied, amount: 680.00);
		
		reimbursements.add(Process1);
		reimbursements.add(Process2);
		reimbursements.add(Approved1);
		reimbursements.add(Approved2);
		reimbursements.add(Denied);
	}
	
	public List<Reimbursement>(){ 
		getReimbursements();
	return reimbursements;
	}
	
	
	//Reimbursements ***CANNOT*** go from Denied to Approved or vice versa.//
	if(status == pending) {
		//req to be approved//
		//-
	}else if(status == denied) {
		System.out.println("Thank you for your time, but maybe next time.");
	}else if(status == approved) {
		System.out.println("Thank you for your time, you are approved!");
	}
	else {
		System.out.println("Please submit an application or contact customer support at 555-555-5555 for assistance.");
	}
	
	
	switch(pending || denied|| approved) {
		case pending:
			
	}
}

}
