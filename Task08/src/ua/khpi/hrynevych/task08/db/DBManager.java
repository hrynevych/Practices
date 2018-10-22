package ua.khpi.hrynevych.task08.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import  ua.khpi.hrynevych.task08.db.entity.User;

public class DBManager {

	private static final String CONNECTION_URL = "jdbc:mysql://192.168.65.43:3306/newbase"
			+ "?user=root";

	////////////////////////////////
	// queries

	private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";

	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

	private static final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?, ?)";

	///////////////////////////////
	// singleton

	private static DBManager instance; // == null

	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	////////////////////////////////
	// logic

	public List<User> findAllUsers() throws SQLException {
		List<User> users = new ArrayList<>();

		Connection con = getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_USERS);
		while (rs.next()) {
			User user = extractUser(rs);
			users.add(user);
		}
		return users;
	}

	public User findUserByLogin(String login) throws SQLException {
		Connection con = getConnection();

		PreparedStatement pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
		int k = 1;
		pstmt.setString(k++, login);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			return extractUser(rs);
		}
		return null;
	}

	public boolean createUser(User user) throws SQLException {
		Connection con = getConnection();

		PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);
		int k = 1;
		pstmt.setString(k++, user.getLogin());
		pstmt.setString(k++, user.getName());

		if (pstmt.executeUpdate() > 0) {
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getInt(1));
				return true;
			}
		}
		return false;
	}
	/////////////////////////
	// util methods

	public Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(CONNECTION_URL);
		// ...
		return con;
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		user.setName(rs.getString("name"));
		return user;
	}
	
	public void insertUser(User u) {
		
	}

}
