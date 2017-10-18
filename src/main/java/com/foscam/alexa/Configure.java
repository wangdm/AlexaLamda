package com.foscam.alexa;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class Configure {
	
    private static final Logger log = LoggerFactory.getLogger(Configure.class);
	
	private String configFile = "config.json";
	
	private String accessServerUrl = "dev-api.myfoscam.com";
	private String accessClientId = "foscloud";
	
	private String signalServerIP = "34.213.217.1";
	private String signalServerPort = "80";
	
	private static Configure instance = null;
	
	private Configure() {
	    
		JsonFactory factory = new JsonFactory();
		JsonParser jsonParse = null;
		try {
			jsonParse = factory.createParser(ClassLoader.getSystemResourceAsStream(configFile));
			//jsonParse = factory.createParser(this.getClass().getResourceAsStream(configFile));
		} catch (JsonParseException e) {
			log.error("Json parse error");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			log.error("The file is not found");
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Unknow io error");
			e.printStackTrace();
		}

		while(!jsonParse.isClosed()){
		    JsonToken jsonToken;
			try {
				jsonToken = jsonParse.nextToken();
			    if(JsonToken.FIELD_NAME.equals(jsonToken)){
			        String fieldName = jsonParse.getCurrentName();
			        jsonToken = jsonParse.nextToken();
			        if("accessServerUrl".equals(fieldName)){
			        	accessServerUrl = jsonParse.getValueAsString();
			        }else if ("accessClientId".equals(fieldName)){
			        	accessClientId = jsonParse.getValueAsString();
			        } else if ("signalServerIP".equals(fieldName)){
			        	signalServerIP = jsonParse.getValueAsString();
			        }else if ("signalServerPort".equals(fieldName)){
			        	signalServerPort = jsonParse.getValueAsString();
			        }
			    }
			} catch (JsonParseException e) {
				log.error("Json Parse error");
				e.printStackTrace();
			} catch (IOException e) {
				log.error("unknow io error");
				e.printStackTrace();
			}
		}
		log.info("Singal Server: " + signalServerIP + ":" + signalServerPort);
		
	}
	
	public static Configure getInstance()
	{
		if(instance == null) {
			instance = new Configure();
		}
		return instance;
	}
	
	public AccessServer getAccessServer() {
		AccessServer server = new AccessServer();
		server.setUrl(accessServerUrl);
		server.setAccessId(accessClientId);
		return server;
	}
	
	public SignalServer getSignalServer() {
		SignalServer server = new SignalServer();
		server.setIp(signalServerIP);
		server.setPort(signalServerPort);
		return server;
	}
	
	public String getAccessServerUrl() {
		return accessServerUrl;
	}
	
	public String getSignalServerIP() {
		return signalServerIP;
	}
	
	public String getSignalServerPort() {
		return signalServerPort;
	}
	
}
