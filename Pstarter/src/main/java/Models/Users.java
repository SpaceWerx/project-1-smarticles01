package Models;

import javax.management.relation.Role;

public class Users {
	private final list<User> indiv = new arraylist<>();
	/*
	private int id;
	private String username;
	private String password;
	private Roles role;
	*/
	public Users() {
		User Rapmon = new User(id: 1, username: "Rapmon", password: "password", Role.employee);
		User V = new User(id: 2, username: "V", password: "password", Role.employee);
		User Jimin = new User(id: 3, username: "Jimin", password: "password", Role.employee);
		User Suga = new User(id: 4, username: "Suga", password: "password", Role.manager);
		User JHope = new User(id: 5, username: "JHope", password: "password", Role.manager);
		User Kookie = new User(id: 6, username: "Kookie", password: "password", Role.manager);
		
		indiv.add(Rapmon);
		indiv.add(V);
		indiv.add(Jimin);
		indiv.add(Suga);
		indiv.add(JHope);
		indiv.add(Kookie);
	}
	
	
	public list<User> getUsers(){return indiv;}
}
