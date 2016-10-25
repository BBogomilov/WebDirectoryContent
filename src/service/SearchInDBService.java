package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.DBUtils;

public class SearchInDBService {

	private Connection con = null;
	private int filesFound = 0;
	public boolean areAnyMatches = false;

	public String getSearchedContent(String input) {
		con = DBUtils.establishConnection();
		Statement st = null;
		ResultSet rs = null;
		StringBuilder result = new StringBuilder();

		try {
			st = con.createStatement();
			String query = "SELECT path FROM files WHERE name LIKE '%" + input + "%'";

			rs = st.executeQuery(query);

			while (rs.next()) {
				result.append(rs.getString("path"));
				result.append(System.getProperty("line.separator"));
				filesFound++;
				areAnyMatches = true;
			}
			return result.toString();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (st != null)
				DBUtils.closeStatement(st);
			if (rs != null)
				DBUtils.closeResultSet(rs);
			if (con != null)
				DBUtils.closeCon(con);
		}
		return input;
	}

	public boolean inputcheck(String input) {
		if (input == null || input == "")
			return false;
		return true;
	}

	public boolean getAreAnyMatches() {
		return areAnyMatches;
	}

	public int getFilesFound() {
		return filesFound;
	}

}
