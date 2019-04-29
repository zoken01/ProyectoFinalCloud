package asr.proyectoFinal.services;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.SynthesizeOptions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.ibm.watson.developer_cloud.service.security.IamOptions;

import org.apache.commons.io.IOUtils;

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
	public static InputStream conversionToSpeech(String palabra)
	{
	    System.out.println("====1" + palabra);
		IamOptions options = new IamOptions.Builder()
				.apiKey("8pm7crAkVSgKbP8oLb6488eqqYcr17OmDasOwaYNA-3h")
				.build();

		TextToSpeech textToSpeech = new TextToSpeech(options);
		textToSpeech.setEndPoint("https://gateway-lon.watsonplatform.net/text-to-speech/api");

		InputStream in = null;
		  
		try {
			  SynthesizeOptions synthesizeOptions =
			    new SynthesizeOptions.Builder()
			      .text(palabra)
			      .accept("audio/mp3")
			      .voice("en-US_MichaelV2Voice")
			      .build();
			  
			  System.out.println("====2");

			  InputStream inputStream = textToSpeech.synthesize(synthesizeOptions).execute();
			  in = inputStream;
			  System.out.println("====3"+in);
			  	  
			} catch (Exception e) {
			  e.printStackTrace();
			}
		System.out.println("====4" + in);
		return in;
	}
}

