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

public class SignalServer {
	
	private String ip;
	
	private String port;

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	public void sendSignal(String token, String name, String mac, String msg) {
		URI uri = null;
		try {
			uri = new URIBuilder()
					.setScheme("http")
					.setHost(ip+":"+port)
					.setPath("/")
					.setParameter("id", String.valueOf(System.currentTimeMillis()))
					.setParameter("userToken", token)
					.setParameter("mac", mac)
					.setParameter("name", name)
					.setParameter("msg", msg)
					.setParameter("msgTime", String.valueOf(System.currentTimeMillis()))
					.setParameter("service", "alexa")
					.setParameter("oemCode", "")
					.setParameter("version", "1.0.0-PRD")
					.build();
			System.out.println(uri.toString());
			
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
		
	}
	
}
