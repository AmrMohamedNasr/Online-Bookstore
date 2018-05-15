package servlet;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import provider.ConnectionProvider;
import utils.ParseUtils;

@WebServlet("/reportmgr")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String[] reports = {
			"Report_1-1", "Report_1-2", "Report_2-1", "Report_2-2", "Report_3-1", "Report_3-2"
	};
    public ReportServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String repI = request.getParameter("reportIndex");
    	Integer i = 0;
    	if (ParseUtils.isNumeric(repI)) {
    		try {
    			i = Integer.parseInt(repI);
    			if (i < 0 || i > 5) {
    				response.setStatus(400);
            		response.getWriter().write("INVALID REQUEST");
            		response.getWriter().close();
            		return;
    			}
    		} catch(Exception e) {
    			response.setStatus(400);
        		response.getWriter().write("INVALID REQUEST");
        		response.getWriter().close();
        		return;
    		}
    	} else {
    		response.setStatus(400);
    		response.getWriter().write("INVALID REQUEST");
    		response.getWriter().close();
    		return;
    	}
    	try {
    		String path = getServletContext().getRealPath("/WEB-INF/");
    		path = path +"jrxml/";
    		JasperDesign jasperDesign = JRXmlLoader.load(path + reports[i] +".jrxml");
    		JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			Map<String, Object> parameters = new HashMap<String,Object>();
			parameters.put("basePath", path);
			/** PDF **/
			
			byte[] byteStream = JasperRunManager.runReportToPdf(jasperReport, parameters,
					ConnectionProvider.getConnection());
			String s = Base64.getEncoder().encodeToString(byteStream);
            response.setContentType("application/json; charset=utf-8");
            JSONObject resp = new JSONObject();
            ParseUtils.doJsonResponse(resp, response, 200, s);
		} catch (JRException e) {
			e.printStackTrace();
		}
    }
    
}
