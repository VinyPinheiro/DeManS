/*****************************
 * Class name: Dao (.java)
 * 
 * Purpose: Dad Class with persist data.
 *****************************/

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Dao {

	/**
	 * Method to try open connection with database
	 * 
	 * @return null if failed else connection object
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 *             failed in connection
	 */
	protected java.sql.Connection getConexaoMySQL() throws ClassNotFoundException, SQLException {

		Connection connection = null; // connection variable

		// Load Default JDBC Driver

		final String driverName = "com.mysql.jdbc.Driver";
		Class.forName(driverName);

		// Configuration connection

		final String SERVER_NAME = "127.0.0.1"; // Host
		final String DATABASE = "DeManS_db"; // DatabaseName
		final String URL = "jdbc:mysql://" + SERVER_NAME + "/" + DATABASE;
		final String USERNAME = "DeManS"; // UserName
		final String PASSWORD = "demolay"; // Password
		connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		return connection;

	}

}
