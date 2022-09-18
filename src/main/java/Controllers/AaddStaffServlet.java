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
import Models.HEUser;

/**
 * Servlet implementation class AaddStaffServlet
 */
@WebServlet("/AaddStaffServlet")
public class AaddStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AaddStaffServlet() {
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
			setAshipper(request, response);
		}else if (funcType.equals("1")) {
			getAshipper(request, response);
		}
	}
	
	
	protected void setAshipper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("BIG5");
		
		String isOwn = request.getParameter("isOwn") == null ? "0" : request.getParameter("isOwn");
		String shopId = request.getParameter("shopId") == null ? "" : request.getParameter("shopId");
		String userId = request.getParameter("userId") == null ? "" : request.getParameter("userId");
		String userName = request.getParameter("userName") == null ? "" : request.getParameter("userName");
		String passwd = request.getParameter("passwd") == null ? "" : request.getParameter("passwd");
		String phone = request.getParameter("phone") == null ? "" : request.getParameter("phone");
		String address = request.getParameter("address") == null ? "" : request.getParameter("address");
		
		userName = java.net.URLDecoder.decode(userName, "UTF-8"); //中文編碼
		address = java.net.URLDecoder.decode(address, "UTF-8"); //中文編碼
		
		int result = new HEAllModel().setAshipper(isOwn, shopId, userId, userName, passwd, phone, address); 
		//request.setAttribute("result", result);
		response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(result);
		//request.setAttribute("sent", true);
		
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/A003.jsp");
		//dispatcher.forward(request, response);
	}
	
	protected void getAshipper(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("BIG5");
		
		String shopId = request.getParameter("shopId") == null ? "" : request.getParameter("shopId");
		//String notFinish = request.getParameter("notFinish") == null ? "" : request.getParameter("notFinish");
		
		List<HEUser> shipperList = new HEAllModel().getAshipper(shopId);
		
		
		String result = new JSONArray(shipperList).toString();//轉json字串
		
		
		response.setContentType("text/json;charset=UTF-8");
        response.getWriter().print(result);
		//request.setAttribute("sent", true);
		
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/A003.jsp");
		//dispatcher.forward(request, response);
	}
}
