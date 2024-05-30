package fr.cs.group29.myFoodora.users;

public class Restaurant extends User{
	
		private String location;
		
		
	 	public Restaurant(String id, String name, String surname, String username, String email, String phone,
			String password, String location) {
	 		
		super(id, name, surname, username, email, phone, password);
		this.location = location;
	}

		

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}
	}