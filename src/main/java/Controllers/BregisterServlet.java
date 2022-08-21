package Controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.HEAllModel;

/**
 * Servlet implementation class BregisterServlet
 */
@WebServlet("/BregisterServlet")
public class BregisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BregisterServlet() {
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
		execute(request, response);
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("BIG5");
		
		String userId = request.getParameter("userId") == null ? "" : request.getParameter("userId");
		String userName = request.getParameter("userName") == null ? "" : request.getParameter("userName");
		String passwd = request.getParameter("passwd") == null ? "" : request.getParameter("passwd");
		String phone = request.getParameter("phone") == null ? "" : request.getParameter("phone");
		String address = request.getParameter("address") == null ? "" : request.getParameter("address");
		
		userName = java.net.URLDecoder.decode(userName, "UTF-8"); //中文編碼
		address = java.net.URLDecoder.decode(address, "UTF-8"); //中文編碼
		
		int result = new HEAllModel().insBregisterUser(userId, userName, passwd, phone, address); 
		//request.setAttribute("result", result);
		response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(result);
		//request.setAttribute("sent", true);
		
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/A003.jsp");
		//dispatcher.forward(request, response);
	}
	
}
