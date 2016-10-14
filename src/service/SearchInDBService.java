package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;

import utils.DBUtils;

@ManagedBean
public class SearchInDBService {
	
	private Connection con = null;
	private int filesFound = 0;
	public boolean areAnyMatches = false;
	private StringBuilder result = new StringBuilder();
	
	public SearchInDBService() {
		
	}

	public void getSearchedContent(String input) {
		con = DBUtils.establishConnection();
		Statement st = null;
		ResultSet rs = null;

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
	}

	public boolean getAreAnyMatches() {
		return areAnyMatches;
	}

	public int getFilesFound() {
		return filesFound;
	}

	public String getResult() {
		return result.toString();
	}
}
