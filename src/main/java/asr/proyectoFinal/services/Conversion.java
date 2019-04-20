package asr.proyectoFinal.services;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.GetPronunciationOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.GetVoiceOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Pronunciation;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.ibm.watson.developer_cloud.service.security.IamOptions;


public class Conversion
{
	public static void conversionToSpeech()
	{
		IamOptions options = new IamOptions.Builder()
				  .apiKey("8pm7crAkVSgKbP8oLb6488eqqYcr17OmDasOwaYNA-3h")
				  .build();

				TextToSpeech textToSpeech = new TextToSpeech(options);
				textToSpeech.setEndPoint("https://gateway-lon.watsonplatform.net/text-to-speech/api");

				try {
					  SynthesizeOptions synthesizeOptions =
					    new SynthesizeOptions.Builder()
					      .text("Hello world")
					      .accept("audio/wav")
					      .voice("en-US_AllisonVoice")
					      .build();

					  InputStream inputStream =
					    textToSpeech.synthesize(synthesizeOptions).execute();
					  InputStream in = WaveUtils.reWriteWaveHeader(inputStream);

					  OutputStream out = new FileOutputStream("hello_world.wav");
					  byte[] buffer = new byte[1024];
					  int length;
					  while ((length = in.read(buffer)) > 0) {
					    out.write(buffer, 0, length);
					  }

					  out.close();
					  in.close();
					  inputStream.close();
					} catch (IOException e) {
					  e.printStackTrace();
					}
	}
}