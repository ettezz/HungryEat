package Controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.jspsmart.upload.Request;

import Models.HEAllModel;


/**
 * Servlet implementation class BregisterServlet
 */
@WebServlet("/AregisterServlet")
public class AregisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AregisterServlet() {
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
		
		
        request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//1、建立SmartUpload物件
        SmartUpload smartUpload = new SmartUpload();

        //2、初始化建立的SmartUpload物件
        smartUpload.initialize(getServletConfig(), request, response);
        smartUpload.setAllowedFilesList("jpg,jpeg,bmp,gif,tif,png,JPG,JPEG,BMP,GIF,TIF,PNG");
        try {
            //3、進行檔案的上傳
            smartUpload.upload();

            Request req = smartUpload.getRequest();
            
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
            
            
            String userId = req.getParameter("userId") == null ? "" : req.getParameter("userId");
    		String userName = req.getParameter("userName") == null ? "" : req.getParameter("userName");
    		String passwd = req.getParameter("passwd") == null ? "" : req.getParameter("passwd");
    		String phone = req.getParameter("phone") == null ? "" : req.getParameter("phone");
    		String address = req.getParameter("address") == null ? "" : req.getParameter("address");
    		
    		userName = java.net.URLDecoder.decode(userName, "UTF-8"); //中文編碼
    		address = java.net.URLDecoder.decode(address, "UTF-8"); //中文編碼
    		
    		int result = new HEAllModel().insAregisterUser(userId, userName, passwd, phone, address, filename); 
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
	
}
