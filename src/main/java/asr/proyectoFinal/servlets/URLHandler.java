package asr.proyectoFinal.servlets;

import java.awt.Image;
import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.validator.routines.UrlValidator;

import com.cloudant.client.api.Database;

import asr.proyectoFinal.dao.CloudantDB;
import asr.proyectoFinal.dominio.URLImagen;


public class URLHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String input = request.getParameter("url"); 
		String error = null;

		// Check if url is ok		
		UrlValidator urlValidator = new UrlValidator();
		boolean urlOk = urlValidator.isValid(input);
		boolean isImage = false;
		
		if(urlOk) {		
				URLImagen url = new URLImagen();
				CloudantDB cloudantDb = new CloudantDB();
				
//				Get default database
				Database db = cloudantDb.getDB();
				
//				// Check if url already is in databse
				
				

				
//				In class object url, set the input url from user
				url.setUrl(input);
				
				
				
				if(db.find(URLImagen.class, url.get_id()) != null) {
					System.out.println("ok");
				}

//				
//				
//				
//				// Save url class in databse
//				String id = db.save(url).getId();
		    
		}else {
			error = "Please enter a valid URL";
		}
		
		
		request.setAttribute("error", error);
		request.setAttribute("url", input);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

}
