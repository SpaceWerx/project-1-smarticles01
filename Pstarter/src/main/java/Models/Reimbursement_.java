package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reimbursement_ {
	//private static final String Reimbursement = null;//

	private final List<Reimbursement_> reimbursements = new ArrayList<>();
	
	private int id;
	private int Author;
	private int Resolver;
	private String Description;
	private Type type;
	private Status status;
	private double Amount;
	
	
	
	


	public Reimbursement_(int id, int author, int resolver, String description, Type type, Status status, double amount) {
		super();
		this.id = id;
		Author = author;
		Resolver = resolver;
		Description = description;
		this.type = type;
		this.status = status;
		Amount = amount;
	}


	public Reimbursement_() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public List<Reimbursement_> getReimbursements() {
		return reimbursements;
	}


	public Type getType() {
		return type;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setResolver(int resolverId) {
		this.Resolver = resolverId;
	}


	/*public Reimbursement_() {
		// TODO Auto-generated constructor stub
	}
*/

	public void setDescription(String nextLine) {
		// TODO Auto-generated method stub
		Description = nextLine;
	}


	public void setAuthor(int id2) {
		// TODO Auto-generated method stub
		Author = id2;
	}


	public void setType(Type type) {
		// TODO Auto-generated method stub
		this.type = type;
	}


	public void setAmount(double parseDoubleInput) {
		// TODO Auto-generated method stub
		Amount = parseDoubleInput;
	}


	public double getAmount() {
		// TODO Auto-generated method stub
		return Amount;
	}


	public String getDescription() {
		// TODO Auto-generated method stub
		return Description;
	}


	public int getAuthor() {
		// TODO Auto-generated method stub
		return Author;
	}


	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}


	public int getResolver() {
		// TODO Auto-generated method stub
		return Resolver;
	}


	public static void add(Reimbursement_ process1) {
		// TODO Auto-generated method stub
		
	}
}


