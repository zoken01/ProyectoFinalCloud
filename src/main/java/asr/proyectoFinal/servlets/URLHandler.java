package asr.proyectoFinal.servlets;

import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudant.client.api.Database;

import asr.proyectoFinal.dao.CloudantDB;
import asr.proyectoFinal.dominio.URLImagen;

import org.apache.commons.validator.routines.UrlValidator;

public class URLHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String input = request.getParameter("url"); 
		URLImagen url = new URLImagen();
		CloudantDB cloudantDb = new CloudantDB();
		UrlValidator urlValidator = new UrlValidator();
		
		// Get default database
		Database db = cloudantDb.getDB();
		
		// In class object url, set the input url from user
		url.setUrl(input);
		
		if(db.find(URLImagen.class, url.get_id()) != null) {
			System.out.println("ok");
		}

		// Check if url already is in databse
		
		
		
		// Save url class in databse
		String id = db.save(url).getId();
		

	}

}
