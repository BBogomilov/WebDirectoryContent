package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.SearchInDBService;

/**
 * Servlet implementation class SearchTabServlet
 */
@WebServlet("/search")
public class SearchTabServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SearchInDBService service = new SearchInDBService();
		service.getSearchedContent(request.getParameter("search"));

		if (service.areAnyMatches) {
			request.setAttribute("results", service.getFilesFound() + " files found! <br>"
					+ service.getResult().replaceAll(System.getProperty("line.separator"), "<br>"));
		} else
			request.setAttribute("results",
					"Sorry, there were no matches found for '" + request.getParameter("search") + "'.");

		request.getRequestDispatcher("/jsp/HomePage.jsp").forward(request, response);

	}
}
