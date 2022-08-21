package Controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.HEAllModel;
import Models.HEUser;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/HELoginServlet")
public class HELoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HELoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text/html; charset=BIG5");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("BIG5");
		
		String userId = request.getParameter("account") == null ? "" : request.getParameter("account");
		String passwd = request.getParameter("password") == null ? "" : request.getParameter("password");
		
		HEUser userModel = new HEAllModel().getLoginUsers(userId, passwd); 
		request.setAttribute("userModel", userModel);
		request.setAttribute("sent", true);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/HELogin.jsp");
		dispatcher.forward(request, response);
	}

}
