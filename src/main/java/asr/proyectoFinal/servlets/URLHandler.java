package asr.proyectoFinal.servlets;

import java.io.IOException;
import javax.servlet.ServletException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudant.client.api.Database;
import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.google.gson.JsonObject;

import asr.proyectoFinal.dao.CloudantDB;
import asr.proyectoFinal.dominio.Palabra;
import asr.proyectoFinal.dominio.URLImagen;

public class URLHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("METODO POST SERVLET URL HANDLER");
		String input = request.getParameter("url"); 
		
		System.out.println("url introducida: " + input);
		
		URLImagen url = new URLImagen();
		CloudantDB cloudantDb = new CloudantDB();
		
		Database db = cloudantDb.getDB();
				
		url.setUrl(input);
		String id = db.save(url).getId();
		
		URLImagen test = db.find(URLImagen.class, id);
		
		System.out.println("Put in databse: " + test);
		
		
		List<URLImagen> docs = null;
		try {
			docs = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(URLImagen.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Things in databsae: " + docs);

	}

}
