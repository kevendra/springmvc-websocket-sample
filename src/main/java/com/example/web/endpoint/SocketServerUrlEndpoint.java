package com.example.web.endpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
// import com.google.appengine.api.utils.SystemProperty;

@Controller
public class SocketServerUrlEndpoint {

	/* ************************************ Static Fields ************************************ */
    private static final int DEFAULT_PORT = 65080;
    private static final String NETWORK_INTERFACE_METADATA_URL = "http://metadata.google.internal/computeMetadata/v1/instance/network-interfaces/0/access-configs/0/external-ip";

	/* ************************************ Instance Fields ************************************ */
    
	/* ************************************ Public Methods ************************************ */
    @RequestMapping("/get-ws-url")
    @ResponseBody
    public String testGetWebSocketURL() {
		return getWebSocketURL();
    }

	/* ************************************ Private Methods ************************************ */
    private String getHostname() throws Exception {
    	String hostname = "localhost";
//        if (SystemProperty.environment.value().equals(SystemProperty.Environment.Value.Production)) {
          URL url = new URL(NETWORK_INTERFACE_METADATA_URL);
          HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
          httpUrlConnection.setRequestProperty("Metadata-Flavor", "Google");
//        httpUrlConnection.setRequestProperty("X-Google-Metadata-Request", "true");          
          BufferedReader reader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
          String result, line = reader.readLine();
          result = line;
          while ((line = reader.readLine()) != null) {
            result += line;
          }
          hostname = result;
//      }
      return hostname;
    }

    /**
     * Returns a Websocket URL of this server.
     *
     * @return a Websocket URL of this server.
     * @throws IOException when failed to get the external IP address from the metadata server.
     */
    private String getWebSocketURL() {
    	try{  
    		return "ws://" + getHostname() + ":" + this.getPort();
	    }catch(Exception e){
	    	return "ws://localhost:8080";
		}
    }
	/**
	 * Gets the port number that this server listens on.
	 * 
	 * @return The port number.
	 */
    private int getPort() {
//		int port = getAddress().getPort();
//		if( port == 0 && server != null ) {
//			port = server.socket().getLocalPort();
//		}
		return DEFAULT_PORT;
	}    
}
