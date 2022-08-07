package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HELogoutServlet
 */
@WebServlet("/HELogoutServlet")
public class HELogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HELogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType( "text/html; charset=BIG5");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(request, response);
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration em = request.getSession().getAttributeNames();
		while(em.hasMoreElements()){
			request.getSession().removeAttribute(em.nextElement().toString());
		}
		PrintWriter out=response.getWriter();
        out.print("YES");
	}
}
