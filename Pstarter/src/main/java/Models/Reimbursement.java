package Models;

public class Reimbursement {
	private final list<reimburse> reimbursements = new arraylist<>();
	/*
	private int id;
	private String author;
	private String resolver;
	private String description;
	private Type type;
	private Status status;
	private double amount;
	*/
	
	public Reimbursement() {
		Reimbursement userData = new Reimbursement();
		
		Reimburse Process1 = new Reimburse(id: 1, author: 1, resolver: 0, description: "Oracle Java Certifiction", Type.other, Status.pending, amount: 450.00);
		Reimburse Process2 = new Reimburse(id: 2, author: 2, resolver: 0, description: "Travel to Busan HQ", Type.travel, Status.pending, amount: 900.00);
		Reimburse Approved1 = new Reimburse(id: 3, author: 1, resolver: 3, description: "Free lunch offer from Suga", Type.food, Status.approved, amount: 800.03);
		Reimburse Approved2 = new Reimburse(id: 4, author: 2, resolver: 4, description: "2-night hotel stay near Nigerian Office for visit", Type.lodging, Status.approved, amount: 15750.00);
		Reimburse Denied = new Reimburse(id: 5, author: 1, resolver: 3, description: "Economy flight from Busan to Nigera", Type.travel, Status.denied, amount: 680.00);
		
		reimbursements.add(Process1);
		reimbursements.add(Process2);
		reimbursements.add(Approved1);
		reimbursements.add(Approved2);
		reimbursements.add(Denied);
	}
	public list<reimburse> getReimbursements(){return reimbursements;}
	
	
	//Reimbursements ***CANNOT*** go from Denied to Approved or vice versa.//
	/*if(status == pending) {
		//req to be approved//
		
	}else if(status == denied) {
		System.out.println("Thank you for your time, but maybe next time.");
	}else if(status == approved) {
		System.out.println("Thank you for your time, you are approved!");
	}
	else {
		System.out.println("Please submit an application or contact customer support at 555-555-5555 for assistance.");
	}*/
}
