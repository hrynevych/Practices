package ua.khpi.hrynevych.task08.db.entity;

public class User { // users

	private int id;

	private String login;

	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", name=" + name + "]";
	}

	public static User createUser(String login) {
		User res = new User();
		res.setLogin(login);
		return res;
	}
}
