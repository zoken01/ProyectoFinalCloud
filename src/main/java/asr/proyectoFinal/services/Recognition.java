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

	public static String visualRecognition(File file)
	{
		IamOptions options = new IamOptions.Builder()
				.apiKey("8Q5h1hGZYnbDG-QGnaGSV1wX4fkJcCrFeNC9Zmi5OFGK")
				.build();

		VisualRecognition service = new VisualRecognition("2018-03-19",options);
		service.setEndPoint("https://gateway.watsonplatform.net/visual-recognition/api");

		InputStream imagesStream = null;
		FileInputStream fis = null;
		
		String clase = null;

		try
		{
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
		String resultString = result.toString();	

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
							clase = null;
							for (JsonNode node3 : classes)
							{
								String score = node3.path("score").asText();
								Double scoreDouble = Double.parseDouble(score);
								if (scoreDouble > scoreMax)
								{
									scoreMax = scoreDouble;
									clase = node3.path("class").asText();
								}
							}
						}
					}
				}
			}
		}

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
		return clase;
	}
	
	public static String visualRecognitionURL(String URL)
	{
		IamOptions options = new IamOptions.Builder()
				.apiKey("8Q5h1hGZYnbDG-QGnaGSV1wX4fkJcCrFeNC9Zmi5OFGK")
				.build();

		VisualRecognition service = new VisualRecognition("2018-03-19",options);
		service.setEndPoint("https://gateway.watsonplatform.net/visual-recognition/api");
		
		String clase = null;
		
		try
		{
		ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
		  //.imagesFile(imagesStream)
		  .imagesFilename("imagen")
		  //.imagesFileContentType("imagen")
		  .url(URL)
		  //.threshold((float) 0.5)
		  //.classifierIds(Arrays.asList("imagen"))
		  .owners(Arrays.asList("IBM"))
		  .acceptLanguage("ES")
		  .build();
		ClassifiedImages result = service.classify(classifyOptions).execute();	
		String resultString = result.toString();	

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
							clase = null;
							for (JsonNode node3 : classes)
							{
								String score = node3.path("score").asText();
								Double scoreDouble = Double.parseDouble(score);
								if (scoreDouble > scoreMax)
								{
									scoreMax = scoreDouble;
									clase = node3.path("class").asText();
								}
							}
						}
					}
				}
			}
		}

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
		return clase;
	}
}