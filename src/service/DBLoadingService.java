package service;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import reader.IReader;
import reader.ReaderFactory;
import utils.DBUtils;

public class DBLoadingService {
	private static final Logger logger = Logger.getLogger(DBLoadingService.class);
	private Connection con = null;
	public static boolean isDBLoaded = false;

	public void writeOutputInDB(HttpServletRequest request) {
		con = DBUtils.establishConnection();

		IReader reader = ReaderFactory.getInstance("n");
		reader.showContent(request.getParameter("directory"));

		String[] contentArr = reader.getResult().split(System.getProperty("line.separator"));
		File[] files = new File[contentArr.length];

		for (int i = 0; i < contentArr.length; i++)
			files[i] = new File(contentArr[i].trim());

		Statement st = null;
		ResultSet rs = null;
		try {
			con.setAutoCommit(false);

			st = con.createStatement();

			String del = "DELETE FROM files";
			String del2 = "DELETE FROM extensions";
			String del3 = "ALTER TABLE files AUTO_INCREMENT = 1";
			String del4 = "ALTER TABLE extensions AUTO_INCREMENT = 1";

			st.executeUpdate(del);
			st.executeUpdate(del2);
			st.executeUpdate(del3);
			st.executeUpdate(del4);

			con.commit();

			logger.info("Table cleared fom old data. Transfering new data ..");

			for (int i = 0; i < files.length; i++) {

				String extension = null;

				if (!files[i].isDirectory()) {

					extension = FilenameUtils.getExtension(files[i].getAbsolutePath());

					String queryToExtensions = "INSERT INTO extensions (extension) SELECT '" + extension + "' WHERE"
							+ " NOT EXISTS (SELECT * FROM extensions WHERE extension = '" + extension + "')";
					st.executeUpdate(queryToExtensions);

					String sql = "SELECT idextensions FROM extensions WHERE extension = '" + extension + "'";
					rs = st.executeQuery(sql);
					int id = 0;

					while (rs.next()) {
						id = rs.getInt("idextensions");
					}

					String queryToFiles = "INSERT INTO files (name, size_bytes, extension, path) VALUES ('"
							+ files[i].getName() + "' , " + files[i].length() + " , " + id + " , '"
							+ files[i].getAbsolutePath().replace("\\", "\\\\") + "' )";

					st.executeUpdate(queryToFiles);

				} else {
					String queryToFiles = "INSERT INTO files (name, size_bytes, path) VALUES ('" + files[i].getName()
							+ "' , " + files[i].length() + "  , '" + files[i].getAbsolutePath().replace("\\", "\\\\")
							+ "' )";

					st.executeUpdate(queryToFiles);

				}
				con.commit();
			}

			isDBLoaded = true;
			logger.info("Output written successfully");
		} catch (SQLException e) {
			logger.info("Something went wrong with the execution of the query: " + e.getMessage());
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (st != null)
				DBUtils.closeStatement(st);
			if (rs != null)
				DBUtils.closeResultSet(rs);
			if (con != null)
				DBUtils.closeCon(con);
		}

	}

	public String getDataFromDB() {
		con = DBUtils.establishConnection();

		Statement st = null;
		StringBuilder sb = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();

			String getPathsQuery = "SELECT path FROM files";

			rs = st.executeQuery(getPathsQuery);
			sb = new StringBuilder();

			while (rs.next()) {
				sb.append(rs.getString("path")).append(System.getProperty("line.separator"));
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
		return sb.toString();
	}
}
