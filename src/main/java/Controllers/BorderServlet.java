package Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import Models.HEAllModel;
import Models.HEOrderDtl;
import Models.HEOrderTitle;

/**
 * Servlet implementation class BorderServlet
 */
@WebServlet("/BorderServlet")
public class BorderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType( "text/html; charset=UTF-8");
		
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
		request.setCharacterEncoding("UTF-8");
		
		String funcType = request.getParameter("funcType");
		if (funcType.equals("0")) {
			getBorderTitle(request, response);
		}else if (funcType.equals("1")) {
			getBorderDtl(request, response);
		}else if (funcType.equals("2")) {
			updBorderTitle(request, response);
		}
	}
	
	
	protected void getBorderTitle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("BIG5");
		
		String userId = request.getParameter("userId") == null ? "" : request.getParameter("userId");
		//String notFinish = request.getParameter("notFinish") == null ? "" : request.getParameter("notFinish");
		
		List<HEOrderTitle> orderTitleList = new HEAllModel().getBorderTitle(userId);
		
		
		String result = new JSONArray(orderTitleList).toString();//轉json字串
		
		
		response.setContentType("text/json;charset=UTF-8");
        response.getWriter().print(result);
		//request.setAttribute("sent", true);
		
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/A003.jsp");
		//dispatcher.forward(request, response);
	}
	
	protected void getBorderDtl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("BIG5");
		
		int orderId = request.getParameter("orderId") == null ? -1 : Integer.parseInt(request.getParameter("orderId"));
		
		List<HEOrderDtl> orderDtlList = new HEAllModel().getBorderDtl(orderId);
		
		
		String result = new JSONArray(orderDtlList).toString(); //轉json字串
		
		
		response.setContentType("text/json;charset=UTF-8");
        response.getWriter().print(result);
		//request.setAttribute("sent", true);
		
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/A003.jsp");
		//dispatcher.forward(request, response);
	}

	protected void updBorderTitle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("BIG5");
		
		String userId = request.getParameter("userId") == null ? "" : request.getParameter("userId");
		int orderId = request.getParameter("orderId") == null ? -1 : Integer.parseInt(request.getParameter("orderId"));
		
		
		int result = new HEAllModel().updBorderTitle(userId, orderId); 
		//request.setAttribute("result", result);
		response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(result);
		//request.setAttribute("sent", true);
		
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/A003.jsp");
		//dispatcher.forward(request, response);
	}

}
