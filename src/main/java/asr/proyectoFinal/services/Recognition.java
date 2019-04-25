package asr.proyectoFinal.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifiedImages;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyOptions;


public class Recognition
{
	private static final ObjectMapper mapper = new ObjectMapper();

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
		file = new File("");
		
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
		  //.threshold((float) 0.5)
		  //.classifierIds(Arrays.asList("imagen"))
		  .owners(Arrays.asList("IBM"))
		  .acceptLanguage("ES")
		  .build();
		ClassifiedImages result = service.classify(classifyOptions).execute();
		System.out.println("=================");	
		String resultString = result.toString();	
		System.out.println(resultString);
		
		JsonNode root = mapper.readTree(resultString);
		
		JsonNode images = root.path("images");
		if (images.isArray())
		{
			for (JsonNode node : images) 
			{
				JsonNode classifiers = node.path("classifiers");
				if (classifiers.isArray())
				{
					for (JsonNode node2 : classifiers)
					{
						JsonNode classes = node2.path("classes");
						if (classes.isArray())
						{
							Double scoreMax = 0.0;
							String clase = null;
							for (JsonNode node3 : classes)
							{
								String score = node3.path("score").asText();
								//System.out.println("Score: " + score);
								Double scoreDouble = Double.parseDouble(score);
								if (scoreDouble > scoreMax)
								{
									scoreMax = scoreDouble;
									clase = node3.path("class").asText();
								}
							}
							System.out.println("Clase: " + clase);
						}
					}
				}
			}
		}
		
		//ObjectMapper mapper = new ObjectMapper();
		//JsonNode node = mapper.readTree(resultString);
		//String images = node.get("images").asText();
		//System.out.println(images);
		//JsonNode node2 = mapper.readTree(images.toString());
		//String image = node2.get("image").asText();
		//System.out.println(image);
		//String cat = node.get("cat").asText();
		//String clase = object.getJSONObject("images").getJSONObject("classifiers").getJSONObject("classes").getString("class");
		
		//System.out.println(clase);
		}
		catch (JsonGenerationException e) {
			e.printStackTrace();
		}
		catch (JsonMappingException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			  e.printStackTrace();
		}
	}
}