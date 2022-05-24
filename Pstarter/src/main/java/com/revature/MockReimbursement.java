package com.revature;

import Models.Reimbursement_;
import Models.Status;
import Models.Type;

public class MockReimbursement {
	public void Reimbursement_() {
		Reimbursement_ userData = new Reimbursement_();
		//String ArrayList <(int, String, Type, Status, amount)>;//
		
		
		Reimbursement_ Process1 = new Reimbursement_(1, 1, 0, "Oracle Java Certifiction", Type.other, Status.pending, 450.00);
		Reimbursement_ Process2 = new Reimbursement_(2, 2, 0, "Travel to Busan HQ", Type.travel, Status.pending, 900.00);
		Reimbursement_ Approved1 = new Reimbursement_(3, 1, 3, "Free lunch offer from Suga", Type.food, Status.approved, 800.03);
		Reimbursement_ Approved2 = new Reimbursement_(4, 2, 4, "2-night hotel stay near Nigerian Office for visit", Type.lodging, Status.approved, 15750.00);
		Reimbursement_ Denied = new Reimbursement_(5, 1, 3, "Cruise trip from Busan to Nigera", Type.travel, Status.denied, 680.00);
		
		Reimbursement_.add(Process1);
		Reimbursement_.add(Process2);
		Reimbursement_.add(Approved1);
		Reimbursement_.add(Approved2);
		Reimbursement_.add(Denied);
	}
	
	public List<Reimbursement_>(){ 
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
			
		break;
	}
}
