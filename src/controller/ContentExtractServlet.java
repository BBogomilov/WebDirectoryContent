package controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.IOUtils;

/**
 * Servlet implementation class ContentExtractServlet
 */
@WebServlet("/contentExtract")
public class ContentExtractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (IOUtils.isDirectory(new File(request.getParameter("directory")))) {
			boolean checkBox = "Y".equals(request.getParameter("check"));

			if (checkBox) {
		//		ZipDownloadService service = new ZipDownloadService();
			//	service.downloadZip(request, response);

			} else {
				if (request.getParameter("name").equals("btn1")) {

					RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/ExtractContent.jsp");
					dispatcher.forward(request, response);

				} else if (request.getParameter("name").equals("btn2")) {

	//				DBLoadingService dbService = new DBLoadingService();
//					dbService.writeOutputInDB(request);
					RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/loadDB.jsp");
					dispatcher.forward(request, response);
				}
			}

		} else {
			if (request.getParameter("directory") != null)
				request.setAttribute("inputError", "Invalid directory input ");

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/jsp/HomePage.jsp");
			dispatcher.forward(request, response);

		}

	}

}
