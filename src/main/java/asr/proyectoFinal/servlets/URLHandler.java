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

import asr.proyectoFinal.dao.CloudantDB;

public class URLHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("METODO POST SERVLET URL HANDLER");
		String input = request.getParameter("url"); 
		
		System.out.println("url introducida: " + input);
		
		CloudantDB cloudant = new CloudantDB();
		
		Database db = cloudant.getDB();
		
		db.save(input);
		
		List<String> urls = new ArrayList<>();
		
		try {
			urls = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(String.class);
		}catch (IOException e) {
			System.out.println(e.getStackTrace());
		}
		
		System.out.println(urls);
	}

}
