package asr.proyectoFinal.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

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

				VisualRecognition service = new VisualRecognition("2019-04-09", options);
				
				File file;

				try
				{
				file = new File("c:/Users/Álvaro/Documents/GitHub/ProyectoFinalCloud/src/main/java/asr/proyectoFinal/services/fruitbowl.jpg");
				
				System.out.println(file.exists());
				  if (!file.exists()) 
				  {
					  file.createNewFile();
				  }
				  
				InputStream imagesStream = new FileInputStream(file);
				ClassifyOptions classifyOptions = new ClassifyOptions.Builder()
				  .imagesFile(imagesStream)
				  .classifierIds(Arrays.asList("food"))
				  .build();
				ClassifiedImages result = service.classify(classifyOptions).execute();
				System.out.println(result);
				}
				catch (IOException e) {
					  e.printStackTrace();
				}
	}
}