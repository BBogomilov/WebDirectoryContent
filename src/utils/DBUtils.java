package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DBUtils {

	private static Connection con;
	private static final Logger logger = Logger.getLogger(DBUtils.class);

	public static Connection establishConnection() {

		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream stream = loader.getResourceAsStream("properties\\database.properties");
			
			Properties properties = new Properties();
			properties.load(stream);
			
			Class.forName(properties.getProperty("db.driver"));
			con = DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.username"),
					properties.getProperty("db.password"));
			
			logger.info("Connection  with database created successfully");

		} catch (ClassNotFoundException e) {
			logger.info("No such driver imported");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (SQLException e3) {
			logger.info("Something went wrong with the connection : " + e3.getMessage());
		}
		return con;

	}

	public static void closeCon(Connection con) {

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeResultSet(ResultSet rs) {

		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeStatement(Statement st) {

		try {
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}