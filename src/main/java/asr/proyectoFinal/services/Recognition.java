package asr.proyectoFinal.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;

public class Recognition
{
	public static void visualRecognition()
	{
		IamOptions options = new IamOptions.Builder()
				.apiKey("8Q5h1hGZYnbDG-QGnaGSV1wX4fkJcCrFeNC9Zmi5OFGK")
				.build();

		VisualRecognition service = new VisualRecognition("2018-03-19",options);
		service.setEndPoint("https://gateway.watsonplatform.net/visual-recognition/api");
		
		File file;
		InputStream imagesStream = null;
		FileInputStream fis = null;

		try
		{
		//file = new File("c:/Users/Álvaro/Documents/GitHub/ProyectoFinalCloud/src/main/java/asr/proyectoFinal/services/fruitbowl.jpg");
		file = new File("c:/Users/Álvaro/Documents/GitHub/ProyectoFinalCloud/src/main/java/asr/proyectoFinal/services/platano.jpg");
		
		System.out.println(file.exists());
		  if (!file.exists()) 
		  {
			  file.createNewFile();
		  }
		  
		fis = new FileInputStream(file);
		imagesStream = fis;
		
		ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
		  .imagesFile(imagesStream)
		  .imagesFilename("imagen")
		  //.imagesFileContentType("imagen")
		  //.url("https://i.blogs.es/9e2919/platano/450_1000.jpg")
		  .threshold((float) 0.9)
		  //.classifierIds(Arrays.asList("imagen"))
		  .owners(Arrays.asList("IBM"))
		  .acceptLanguage("ES")
		  .build();
		ClassifiedImages result = service.classify(classifyOptions).execute();
		System.out.println(result);
		
		System.out.println("=================");
		}
		catch (IOException e) {
			  e.printStackTrace();
		}
	}
}