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

public class ControllerURL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("METODO POST SERVLET CONTROLLER");
		
		//Aquí cogemos la URL de la imagen
		String URL = request.getParameter("urlName");

		String input = Recognition.visualRecognitionURL(URL);
		System.out.println("Imagen reconocida: " + input);
		
		// EESTA PARTE TRADUCE
		// input = input.translate
		String trad = Traductor.translate(input, "es", "en", false);
		
		System.out.println("Imagen traducida: " + trad);
		
		// ESTA PARTE SINTETIZA EL TEXTO, CREA ARCHIVO MP3 Y LO ENVÍA AL JSP
		InputStream mp3stream = Conversion.conversionToSpeech(trad);
		
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
		
		request.setAttribute("mp3streamURL", mp3stream);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
