package fr.cs.group29.myFoodora.users;


public class User {
	protected String id;
    protected String name;
    protected String surname;
    protected String username;
    protected String email;
    protected String phone;
    protected String password;

    public User(String id, String name, String surname, String username, String email, String phone, String password) {
    	this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

}
