package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reimbursement_ {
	//private static final String Reimbursement = null;//

	private final List<Reimbursement_> reimbursements = new ArrayList<>();
	
	private int id;
	private String author;
	private int resolver;
	private String description;
	private Type type;
	private Status status;
	private double amount;
	
	
	
	


	public Reimbursement_(int id, String i, int j, String description, Type type, Status status,
			double amount) {
		super();
		this.id = id;
		this.author = i;
		this.resolver = j;
		this.description = description;
		this.type = type;
		this.status = status;
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "Reimbursement_ [reimbursements=" + reimbursements + ", id=" + id + ", author=" + author + ", resolver="
				+ resolver + ", description=" + description + ", type=" + type + ", status=" + status + ", amount="
				+ amount + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(amount, author, description, id, reimbursements, resolver, status, type);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement_ other = (Reimbursement_) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(author, other.author) && Objects.equals(description, other.description)
				&& id == other.id && Objects.equals(reimbursements, other.reimbursements)
				&& Objects.equals(resolver, other.resolver) && status == other.status && type == other.type;
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


	public void setAuthor(String author) {
		this.author = author;
	}


	public void setResolver(int resolverId) {
		this.resolver = resolverId;
	}


	public Reimbursement_() {
		// TODO Auto-generated constructor stub
	}


	public void setDescription(String nextLine) {
		// TODO Auto-generated method stub
		
	}


	public void setAuthor(int id2) {
		// TODO Auto-generated method stub
		
	}


	public void setType(Type lodging) {
		// TODO Auto-generated method stub
		
	}


	public void setAmount(double parseDoubleInput) {
		// TODO Auto-generated method stub
		
	}


	public int getAmount() {
		// TODO Auto-generated method stub
		return 0;
	}


	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}


	public int getAuthor() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int getResolver() {
		// TODO Auto-generated method stub
		return 0;
	}


	public static void add(Reimbursement_ process1) {
		// TODO Auto-generated method stub
		
	}
}


