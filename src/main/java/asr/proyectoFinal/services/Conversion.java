package asr.proyectoFinal.services;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ibm.watson.developer_cloud.service.security.IamOptions;

import org.apache.commons.io.IOUtils;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;;


public class Conversion
{
	public static final String PREFIX = "stream2file";
    public static final String SUFFIX = ".tmp";
    
	public static File stream2file (InputStream in) throws IOException {
        final File tempFile = File.createTempFile(PREFIX, SUFFIX);
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return tempFile;
    }
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
			  
			  file = File.createTempFile("audio-", ".wav");
			  
			  if (!file.exists()) 
			  {
				  file.createNewFile();
				  System.out.println("CREATED TEMP FILE" + file);				  
			  }
			  fos = new FileOutputStream(file);
			  	  
			  out = fos;
			  
			  byte[] buffer = new byte[1024];
			  int length;
			  while ((length = in.read(buffer)) > 0) {
			    out.write(buffer, 0, length);
			    //out.flush();
			  }
			  
			  try {
				  System.out.println("TRYING TO PLAY FILE");
				  File fltemp = stream2file(in);
				  Media hit = new Media(fltemp.toURI().toString());
				  
				  MediaPlayer mediaPlayer = new MediaPlayer(hit);
				  mediaPlayer.play();
				}catch (IOException e) {
					e.printStackTrace();
				}

			  out.close();
			  in.close();
			  inputStream.close();
			  
			} catch (IOException e) {
			  e.printStackTrace();
			}
		
	}
	
	public static void playMP(InputStream in) {
		try {
		  File fltemp = stream2file(in);
		  Media hit = new Media(fltemp.toURI().toString());
		  
		  MediaPlayer mediaPlayer = new MediaPlayer(hit);
		  mediaPlayer.play();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}

