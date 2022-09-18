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
 * Servlet implementation class AorderServlet
 */
@WebServlet("/AorderServlet")
public class AorderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AorderServlet() {
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
			getAorderTitle(request, response);
		}else if (funcType.equals("1")) {
			getAorderDtl(request, response);
		}else if (funcType.equals("2")) {
			updAorderTitle(request, response);
		}else if (funcType.equals("3")) {
			updAorderTitleCancel(request, response);
		}
	}
	
	
	protected void getAorderTitle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("BIG5");
		
		String shopId = request.getParameter("shopId") == null ? "" : request.getParameter("shopId");
		//String notFinish = request.getParameter("notFinish") == null ? "" : request.getParameter("notFinish");
		
		List<HEOrderTitle> orderTitleList = new HEAllModel().getAorderTitle(shopId);
		
		
		String result = new JSONArray(orderTitleList).toString();//轉json字串
		
		
		response.setContentType("text/json;charset=UTF-8");
        response.getWriter().print(result);
		//request.setAttribute("sent", true);
		
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/A003.jsp");
		//dispatcher.forward(request, response);
	}
	
	protected void getAorderDtl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	
	protected void updAorderTitleCancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("BIG5");
		
		String shopId = request.getParameter("shopId") == null ? "" : request.getParameter("shopId");
		int orderId = request.getParameter("orderId") == null ? -1 : Integer.parseInt(request.getParameter("orderId"));
		
		
		int result = new HEAllModel().updAorderTitleCancel(shopId, orderId); 
		//request.setAttribute("result", result);
		response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(result);
		//request.setAttribute("sent", true);
		
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/A003.jsp");
		//dispatcher.forward(request, response);
	}
}
