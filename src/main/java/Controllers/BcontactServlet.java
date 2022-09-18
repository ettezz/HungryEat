package Controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;

import Models.HEAllModel;
import Models.HEItem;

/**
 * Servlet implementation class BcontactServlet
 */
@WebServlet("/BcontactServlet")
public class BcontactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BcontactServlet() {
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
		request.setCharacterEncoding("UTF-8");
		
		String funcType = request.getParameter("funcType");
		
        if (funcType != null){
    		if (funcType.equals("0")) {
    			getBcontactItems(request, response);
    		} else if (funcType.equals("1")) {
    			insContactOrder(request, response);
    		}
        }
	}
	
	protected void getBcontactItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String shopId = request.getParameter("shopId") == null ? "" : request.getParameter("shopId");
		
		List<HEItem> itemList = new HEAllModel().getAupProductItems(shopId, "", ""); 
		//if (itemList != null) {
		//	request.setAttribute("itemList", itemList);
		//}
		String result = new JSONArray(itemList).toString();//轉json字串
		
		
		response.setContentType("text/json;charset=UTF-8");
        response.getWriter().print(result);
		
		//request.setAttribute("sent", true);
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/Bcontact.jsp");
		//dispatcher.forward(request, response);
	}

	protected void insContactOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("BIG5");
		
		String shopId = request.getParameter("shopId") == null ? "" : request.getParameter("shopId");
		String userId = request.getParameter("userId") == null ? "" : request.getParameter("userId");
		String shipDate = request.getParameter("shipDate") == null ? "" : request.getParameter("shipDate");
		String shipAddress = request.getParameter("shipAddress") == null ? "" : request.getParameter("shipAddress");
		String shipPhone = request.getParameter("shipPhone") == null ? "" : request.getParameter("shipPhone");
		String orderMemo = request.getParameter("orderMemo") == null ? "" : request.getParameter("orderMemo");
		int totalPrice = request.getParameter("totalPrice") == null ? 0 : Integer.parseInt(request.getParameter("totalPrice"));
		String itemId = request.getParameter("itemId") == null ? "" : request.getParameter("itemId");
		String num = request.getParameter("num") == null ? "" : request.getParameter("num");
		String orderDtlPrice = request.getParameter("orderDtlPrice") == null ? "" : request.getParameter("orderDtlPrice");
		String orderDtlMemo = request.getParameter("orderDtlMemo") == null ? "" : request.getParameter("orderDtlMemo");
		
		shipDate=shipDate.replaceAll("%20"," ");
		shipAddress = java.net.URLDecoder.decode(shipAddress, "UTF-8");
		orderMemo = java.net.URLDecoder.decode(orderMemo, "UTF-8");
		orderDtlMemo = java.net.URLDecoder.decode(orderDtlMemo, "UTF-8");
		
		int result = new HEAllModel().insContactOrder(shopId, userId, shipDate, shipAddress, shipPhone, 
				orderMemo, totalPrice, itemId, 
				num, orderDtlPrice, orderDtlMemo); 
		//request.setAttribute("result", result);
		response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(result);
		//request.setAttribute("sent", true);
		
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/A003.jsp");
		//dispatcher.forward(request, response);
	}
}
