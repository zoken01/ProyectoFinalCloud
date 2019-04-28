package asr.proyectoFinal.servlets;

import java.io.IOException;
import javax.servlet.ServletException;

import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

import asr.proyectoFinal.dao.CloudantDB;
import asr.proyectoFinal.dominio.URLImagen;


public class Dbop extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		CloudantDB cloudantDb = new CloudantDB();
		CloudantClient client = cloudantDb.getClient();
		
		// Get default database
		Database db = cloudantDb.getDB();
		
		
		if (op.contentEquals("list_db")) {
//			Listar todos los elementos de la base de datos
			List<URLImagen> docs = null;
			try {
				docs = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(URLImagen.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Things in databsae: " + docs);
		}else if(op.contentEquals("empty_db")) {
			client.deleteDB("mydb");
			client.createDB("mydb");
		}
		
		response.sendRedirect("index.jsp");
	}

}
