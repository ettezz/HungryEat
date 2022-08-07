package Controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import Models.HEAllModel;
import Models.HEItem;

/**
 * Servlet implementation class AupProductServlet
 */
@WebServlet("/AupProductServlet")
public class AupProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AupProductServlet() {
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
		
        try {
        	if (funcType != null){
        		if (funcType.equals("0")) {
        			getAupProduct_Items(request, response);
        		}else if (funcType.equals("3")) {
        			delAupProduct_Items(request, response);
        		}
        	}
        	else{
            	//1、建立SmartUpload物件
            	SmartUpload smartUpload = new SmartUpload();
                
                //2、初始化建立的SmartUpload物件
                smartUpload.initialize(getServletConfig(), request, response);
                smartUpload.setAllowedFilesList("jpg,jpeg,bmp,gif,tif,png");
                
                //3、進行檔案的上傳
            	smartUpload.upload();
            	
            	Request req = smartUpload.getRequest();
            	
            	funcType = req.getParameter("funcType");
            	
            	if (funcType.equals("1")) {
        			insAupProduct_Items(request, response, smartUpload, req);
        		}else if (funcType.equals("2")) {
        			updAupProduct_Items(request, response, smartUpload, req);
        		}
            }
            
    		
        }
        catch(Exception ex) {
        	ex.printStackTrace();
        }
        
	}
	
	protected void getAupProduct_Items(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String shopId = request.getParameter("shopId") == null ? "" : request.getParameter("shopId");
		String itemId = request.getParameter("itemId") == null ? "" : request.getParameter("itemId");
		String itemName = request.getParameter("itemName") == null ? "" : request.getParameter("itemName");
		
		List<HEItem> itemList = new HEAllModel().getAupProduct_Items(shopId, itemId, itemName); 
		if (itemList != null) {
			request.setAttribute("itemList", itemList);
		}
		request.setAttribute("sent", true);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/AupProduct.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void insAupProduct_Items(HttpServletRequest request, HttpServletResponse response, SmartUpload smartUpload, Request req) throws ServletException, IOException {
		//request.setCharacterEncoding("BIG5");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//1、建立SmartUpload物件
        //SmartUpload smartUpload = new SmartUpload();

        //2、初始化建立的SmartUpload物件
        //smartUpload.initialize(getServletConfig(), request, response);
        //smartUpload.setAllowedFilesList("jpg,jpeg,bmp,gif,tif,png");
        try {
            //3、進行檔案的上傳
            //smartUpload.upload();

            //Request req = smartUpload.getRequest();
            
            //4、把上傳的檔案儲存到伺服器上相應的資料夾，我這裡建立的是upload資料夾
            String fileSavePath = request.getServletContext().getRealPath("upload");    //  得到upload資料夾的路徑
            String filename = "";
            
            Files files = smartUpload.getFiles();                                  //得到所有上傳的檔案
            for (int i = 0; i < files.getCount(); i++) {                                //遍歷所有上傳的每一個檔案
                File curFile = files.getFile(i);                                   //得到當前上傳的檔案

                //為了防止重名檔案的bug，我們這裡採用檔案上傳的時候作為檔名來儲存
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                String fileExt = curFile.getFileExt();                                  //獲得當前檔案的字尾名
                filename = simpleDateFormat.format(new Date()) + "." + fileExt;

                String lastFilePath = fileSavePath + "/" + filename;    //當前檔案的儲存路徑

                curFile.saveAs(lastFilePath);
            }
            
            
		
			String shopId = req.getParameter("shopId") == null ? "" : req.getParameter("shopId");
			String itemName = req.getParameter("itemName") == null ? "" : req.getParameter("itemName");
			int itemPrice = req.getParameter("itemPrice") == null ? 0 : Integer.parseInt(req.getParameter("itemPrice"));
			String itemMemo = req.getParameter("itemMemo") == null ? "" : req.getParameter("itemMemo");
			
			itemName = java.net.URLDecoder.decode(itemName, "UTF-8");
			itemMemo = java.net.URLDecoder.decode(itemMemo, "UTF-8");
			
			int result = new HEAllModel().insAupProduct_Items(shopId, itemName, itemPrice, itemMemo, filename); 
			//request.setAttribute("result", result);
			response.setContentType("text/html;charset=UTF-8");
	        response.getWriter().print(result);
			//request.setAttribute("sent", true);
		
		
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/A003.jsp");
			//dispatcher.forward(request, response);
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }
	}
	
	protected void updAupProduct_Items(HttpServletRequest request, HttpServletResponse response, SmartUpload smartUpload, Request req) throws ServletException, IOException {
		//request.setCharacterEncoding("BIG5");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//1、建立SmartUpload物件
        //SmartUpload smartUpload = new SmartUpload();

        //2、初始化建立的SmartUpload物件
        //smartUpload.initialize(getServletConfig(), request, response);
        //smartUpload.setAllowedFilesList("jpg,jpeg,bmp,gif,tif,png");
        try {
            //3、進行檔案的上傳
            //smartUpload.upload();

            //Request req = smartUpload.getRequest();
            
            //4、把上傳的檔案儲存到伺服器上相應的資料夾，我這裡建立的是upload資料夾
            String fileSavePath = request.getServletContext().getRealPath("upload");    //  得到upload資料夾的路徑
            String filename = "";
            
            Files files = smartUpload.getFiles();                                  //得到所有上傳的檔案
            for (int i = 0; i < files.getCount(); i++) {                                //遍歷所有上傳的每一個檔案
                File curFile = files.getFile(i);                                   //得到當前上傳的檔案

                //為了防止重名檔案的bug，我們這裡採用檔案上傳的時候作為檔名來儲存
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                String fileExt = curFile.getFileExt();                                  //獲得當前檔案的字尾名
                filename = simpleDateFormat.format(new Date()) + "." + fileExt;

                String lastFilePath = fileSavePath + "/" + filename;    //當前檔案的儲存路徑

                curFile.saveAs(lastFilePath);
            }
            
            
			String shopId = req.getParameter("shopId") == null ? "" : req.getParameter("shopId");
			int itemId = req.getParameter("itemId") == null ? -1 : Integer.parseInt(req.getParameter("itemId"));
			String itemName = req.getParameter("itemName") == null ? "" : req.getParameter("itemName");
			int itemPrice = req.getParameter("itemPrice") == null ? 0 : Integer.parseInt(req.getParameter("itemPrice"));
			String itemMemo = req.getParameter("itemMemo") == null ? "" : req.getParameter("itemMemo");
			
			itemName = java.net.URLDecoder.decode(itemName, "UTF-8"); //中文編碼
			itemMemo = java.net.URLDecoder.decode(itemMemo, "UTF-8"); //中文編碼
			
			int result = new HEAllModel().updAupProduct_Items(shopId, itemId, itemName, itemPrice, itemMemo, filename); 
			//request.setAttribute("result", result);
			response.setContentType("text/html;charset=UTF-8"); 
	        response.getWriter().print(result);
			//request.setAttribute("sent", true);
			
			
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/A003.jsp");
			//dispatcher.forward(request, response);
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }
	}
	
	protected void delAupProduct_Items(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("BIG5");
		
		String shopId = request.getParameter("shopId") == null ? "" : request.getParameter("shopId");
		int itemId = request.getParameter("itemId") == null ? -1 : Integer.parseInt(request.getParameter("itemId"));
		
		int result = new HEAllModel().delAupProduct_Items(shopId, itemId); 
		//request.setAttribute("result", result);
		response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(result);
		//request.setAttribute("sent", true);
		
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/A003.jsp");
		//dispatcher.forward(request, response);
	}

}
