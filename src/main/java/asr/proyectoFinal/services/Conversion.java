package asr.proyectoFinal.services;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.GetPronunciationOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.GetVoiceOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Pronunciation;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

import asr.proyectoFinal.dominio.Palabra;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ibm.watson.developer_cloud.http.ServiceCall;
import com.ibm.watson.developer_cloud.service.security.IamOptions;


public class Conversion
{
	public static void conversionToSpeech(String palabra)
	{
		IamOptions options = new IamOptions.Builder()
				.apiKey("8pm7crAkVSgKbP8oLb6488eqqYcr17OmDasOwaYNA-3h")
				.build();

		TextToSpeech textToSpeech = new TextToSpeech(options);
		textToSpeech.setEndPoint("https://gateway-lon.watsonplatform.net/text-to-speech/api");

		FileOutputStream fos = null;
		File file;
		OutputStream out = null;
		  
		try {
			  SynthesizeOptions synthesizeOptions =
			    new SynthesizeOptions.Builder()
			      .text(palabra)
			      .accept("audio/wav")
			      .voice("en-US_AllisonVoice")
			      .build();

			  InputStream inputStream = textToSpeech.synthesize(synthesizeOptions).execute();
			  InputStream in = WaveUtils.reWriteWaveHeader(inputStream);
			  
			  file = new File("c:/Users/Álvaro/Documents/GitHub/ProyectoFinalCloud/src/main/java/asr/proyectoFinal/services/hello_world.wav");
			  
			  //System.out.println(file.exists());
			  if (!file.exists()) 
			  {
				  file.createNewFile();
			  }
			  fos = new FileOutputStream(file);
			  	  
			  out = fos;
			  
			  byte[] buffer = new byte[1024];
			  int length;
			  while ((length = in.read(buffer)) > 0) {
			    out.write(buffer, 0, length);
			    //out.flush();
			  }

			  out.close();
			  in.close();
			  inputStream.close();
			  
			} catch (IOException e) {
			  e.printStackTrace();
			}
	}
}