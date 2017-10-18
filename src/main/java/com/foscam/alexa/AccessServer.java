package com.foscam.alexa;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class AccessServer {
	
	private String url;
	
	private String accessId;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAccessId() {
		return accessId;
	}

	public void setAccessId(String accessId) {
		this.accessId = accessId;
	}
	
	
	public Camera getCamera(String token, String name) {

		Camera camera = new Camera();
		camera.setName(name);
		camera.setMac("000453fffff1");
		camera.setUserToken("8ba1fcee68d34766b857c608edd7528c");

		URI uri = null;
		try {
			uri = new URIBuilder()
					.setScheme("https")
					.setHost(url)
					.setPath("/gateway")
					.setParameter("service", "center.getDeviceInfo")
					.setParameter("clientId", accessId)
					.setParameter("accessToken", token)//c797aad827494e57a955e4f67fe41036
					.setParameter("deviceName", name)//FI9816P
					.build();
			
			HttpGet httpGet = new HttpGet(uri);
			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(httpGet);
			
			HttpEntity entity = response.getEntity();
			
			JsonFactory factory = new JsonFactory();
			JsonParser jsonParse = null;
			jsonParse = factory.createParser(entity.getContent());
			while(!jsonParse.isClosed()){
			    JsonToken jsonToken;
				jsonToken = jsonParse.nextToken();
			    if(JsonToken.FIELD_NAME.equals(jsonToken)){
			        String fieldName = jsonParse.getCurrentName();
			        jsonToken = jsonParse.nextToken();
			        if("errorCode".equals(fieldName)){
			        	jsonParse.getValueAsString();
			        }else if ("macAddr".equals(fieldName)){
			        	camera.setMac(jsonParse.getValueAsString());
			        } else if ("userTag".equals(fieldName)){
			        	camera.setUserToken(jsonParse.getValueAsString());
			        }
			    }
			}
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return camera;
	}
	

}
