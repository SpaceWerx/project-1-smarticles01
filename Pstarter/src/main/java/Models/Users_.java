package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.management.relation.Role;

public class Users_ {
	private final List<Users_> users = new ArrayList<Users_>();
	
	private int id;
	private String username;
	private String password;
	private Roles role;
	
	
	public Users_() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users_(int id, String username, String password, Roles role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}
	public List<Users_> getUsers() {
		return users;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, password, role, username, users);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users_ other = (Users_) obj;
		return id == other.id && Objects.equals(password, other.password) && role == other.role
				&& Objects.equals(username, other.username) && Objects.equals(users, other.users);
	}
	@Override
	public String toString() {
		return "Users_ [users=" + users + ", id=" + id + ", username=" + username + ", password=" + password + ", role="
				+ role + "]";
	}
	public static void add(Users_ rapmon) {
		// TODO Auto-generated method stub
		
	}
	
	
}


