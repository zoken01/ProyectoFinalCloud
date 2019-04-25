package asr.proyectoFinal.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import asr.proyectoFinal.dao.CloudantPalabraStore;
import asr.proyectoFinal.dominio.Palabra;
import asr.proyectoFinal.services.Traductor;
import asr.proyectoFinal.services.Conversion;
import asr.proyectoFinal.services.Recognition;

@WebServlet(urlPatterns = {"/listar", "/insertar", "/hablar"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta charset=\"UTF-8\"></head><body>");
		
		CloudantPalabraStore store = new CloudantPalabraStore();
		System.out.println(request.getServletPath());
		switch(request.getServletPath())
		{
			case "/listar":
				if(store.getDB() == null)
					  out.println("No hay DB");
				else
					out.println("Palabras en la BD Cloudant:<br />" + store.getAll());
				break;
				
			case "/insertar":
				Palabra palabra = new Palabra();
				String parametro = request.getParameter("palabra");

				if(parametro==null)
				{
					out.println("usage: /insertar?palabra=palabra_a_traducir");
				}
				else
				{
					if(store.getDB() == null) 
					{
						out.println(String.format("Palabra: %s", palabra));
					}
					else
					{
						parametro = Traductor.translate(parametro, "es", "en", false);
						palabra.setName(parametro);
						store.persist(palabra);
					    out.println(String.format("Almacenada la palabra: %s", palabra.getName()));
					    Conversion.conversionToSpeech(palabra.getName());
					    Recognition.visualRecognition();
					}
				}
				break;
		}
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("METODO POST SERVLET CONTROLLER");
		String input = request.getParameter("t2speech"); 
		
		System.out.println("ha introducido: " + input);
		
		// EESTA PARTE TRADUCE
		// input = input.translate
		
		
		// ESTA PARTE SINTETIZA EL TEXTO, CREA ARCHIVO MP3 Y LO ENVÍA AL JSP
		InputStream mp3stream = Conversion.conversionToSpeech(input);
		
		String path_mp3 = request.getRealPath("/mp3");
		
		System.out.println(path_mp3);
		
		OutputStream out = new FileOutputStream(new File(path_mp3 + "/temp.mp3"));
		
		byte[] buffer = new byte[2048];
		  int length;
		  while ((length = mp3stream.read(buffer)) > 0) {
		    out.write(buffer, 0, length);
		    //out.flush();
		  }

		out.close();
		
		request.setAttribute("mp3stream", mp3stream);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
