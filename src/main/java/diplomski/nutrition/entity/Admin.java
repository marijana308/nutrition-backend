package diplomski.nutrition.entity;

import javax.persistence.Entity;

import diplomski.nutrition.enumeration.Role;

@Entity
public class Admin extends User{

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(Long id, String username, String password, String firstname, String lastname) {
		super(id, username, password, firstname, lastname, Role.ADMIN);
		// TODO Auto-generated constructor stub
	}
	
}
