	package asr.proyectoFinal.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudant.client.api.Database;

import asr.proyectoFinal.dao.CloudantDB;
import asr.proyectoFinal.dominio.URLImagen;

public class ListImages extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CloudantDB cloudantDb = new CloudantDB();
		
//		Get default database
		Database db = cloudantDb.getDB();
		
		List<URLImagen> docs = null;
		try {
			docs = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(URLImagen.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<String> urls = new ArrayList<>();
		for(URLImagen urlImagen:docs) {
			String str = urlImagen.getUrl();
			urls.add(str);
		}
	
		request.setAttribute("urls", urls);
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}
}
