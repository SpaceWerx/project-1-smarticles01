package com.revature;

import java.util.List;

import Models.Roles;
import Models.Users_;

public class MockData {
	public  void Users_() {
		Users_ Rapmon = new Users_(1, "Rapmon", "password", Roles.employee);
		Users_ V = new Users_(2, "V", "password", Roles.employee);
		Users_ Jimin = new Users_(3, "Jimin",  "password", Roles.employee);
		Users_ Suga = new Users_(4, "Suga",  "password", Roles.manager);
		Users_ JHope = new Users_(5, "JHope",  "password", Roles.manager);
		Users_ Kookie = new Users_(6, "Kookie",  "password", Roles.manager);
		
		Users_.add(Rapmon);
		Users_.add(V);
		Users_.add(Jimin);
		Users_.add(Suga);
		Users_.add(JHope);
		Users_.add(Kookie);
	}

	
	public List<Users_> getUsers(){
		return null;
	}
}
