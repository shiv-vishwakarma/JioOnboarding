package telcoHandsOn;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.xml.sax.InputSource;

import com.jio.resttalk.service.custom.exceptions.RestTalkInvalidURLException;
import com.jio.resttalk.service.custom.exceptions.RestTalkServerConnectivityError;
import com.jio.resttalk.service.impl.RestTalkBuilder;
import com.jio.resttalk.service.impl.RestTalkManager;
import com.jio.resttalk.service.response.RestTalkResponse;

public class ClientRestTalk {

	public static void main(String[] args) throws RestTalkInvalidURLException, RestTalkServerConnectivityError {

		RestTalkManager.getInstance().startRestTalk();

		RestTalkResponse restTalkResponse = new RestTalkBuilder().Get("http://127.0.0.1:8080/hello").send();
		int statusCode = restTalkResponse.getHttpStatusCode();
		System.out.println("GET REQUEST status: " + statusCode);
		System.out.println(restTalkResponse.answeredContent().responseString());

		/*
		 * String p1 = new Players("Pogba","Manchester United", "England").toString();
		 * //InputStream _stream = IOUtils.toInputStream(p1);
		 * 
		 * RestTalkResponse postRestTalkResponse = new RestTalkBuilder()
		 * .Post("http://127.0.0.1:8080/hello") .addRequestData(p1) .send();
		 * 
		 * System.out.println(postRestTalkResponse.getHttpStatusCode());
		 * System.out.println(postRestTalkResponse.answeredContent().responseString());
		 */

		
		 // String p2 = "Bruno"; // InputStream _stream = IOUtils.toInputStream(p1);
		  
		  //RestTalkResponse deleteRestTalkResponse = new
		 // RestTalkBuilder().Delete("http://127.0.0.1:8080/hello")
		 // .addRequestData(p2).send();
		  
		 // System.out.println("DELETE REQUEST status: " +
		 // deleteRestTalkResponse.getHttpStatusCode());
		  //System.out.println(deleteRestTalkResponse.answeredContent().responseString());
		  
		  
		 // RestTalkResponse restTalkResponse1 = new RestTalkBuilder().Get("http://127.0.0.1:8080/hello").send();
			//int statusCode1 = restTalkResponse.getHttpStatusCode();
			//System.out.println("GET REQUEST again status: " + statusCode1);
			//System.out.println(restTalkResponse1.answeredContent().responseString());
		 

	}

}
