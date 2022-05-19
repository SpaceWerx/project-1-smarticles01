package Models;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

public class Users_ {
	private final List<Users_> users = new ArrayList<Users_>();
	
	private int id;
	private String username;
	private String password;
	private Roles role;
	
	public Users_() {
		Users_ Rapmon = new Users_(id: 1, username: "Rapmon", password: "password", Role.employee);
		Users_ V = new Users_(id: 2, username: "V", password: "password", Role.employee);
		Users_ Jimin = new Users_(id: 3, username: "Jimin", password: "password", Role.employee);
		Users_ Suga = new Users_(id: 4, username: "Suga", password: "password", Role.manager);
		Users_ JHope = new Users_(id: 5, username: "JHope", password: "password", Role.manager);
		Users_ Kookie = new Users_(id: 6, username: "Kookie", password: "password", Role.manager);
		
		users.add(Rapmon);
		users.add(V);
		users.add(Jimin);
		users.add(Suga);
		users.add(JHope);
		users.add(Kookie);
	}
	
	
	public List<Users_> getUsers(){
		return users;
	}
}

}
