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
import Models.HEOrderTitle;

/**
 * Servlet implementation class RorderServlet
 */
@WebServlet("/RorderServlet")
public class RorderServlet extends HttpServlet{

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private static final long serialVersionUID = 1L;

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
			getRorderTitle(request, response);
		}else if (funcType.equals("1")) {
			//getAorderDtl(request, response);
		}else if (funcType.equals("2")) {
			updAorderTitle(request, response);
		}else if (funcType.equals("3")) {
			//updAorderTitleCancel(request, response);
		}
	}
	
	protected void getRorderTitle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String shopId = request.getParameter("shopId") == null ? "" : request.getParameter("shopId");

		List<HEOrderTitle> orderTitleList = new HEAllModel().getRorderTitle(shopId);
		
		String result = new JSONArray(orderTitleList).toString();//轉json字串
				
		response.setContentType("text/json;charset=UTF-8");
        response.getWriter().print(result);
	}
	
	protected void updAorderTitle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("BIG5");
		
		String shopId = request.getParameter("shopId") == null ? "" : request.getParameter("shopId");
		int orderId = request.getParameter("orderId") == null ? -1 : Integer.parseInt(request.getParameter("orderId"));
		String status = request.getParameter("status") == null ? "" : request.getParameter("status");
		
		int result = new HEAllModel().updAorderTitle(shopId, orderId, status); 
		//request.setAttribute("result", result);
		response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(result);
		//request.setAttribute("sent", true);
		
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/A003.jsp");
		//dispatcher.forward(request, response);
	}
}
