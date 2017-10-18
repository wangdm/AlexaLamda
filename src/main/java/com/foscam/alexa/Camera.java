package com.foscam.alexa;

public class Camera {
	
	private String name;
	
	private String mac;
	
	private String userToken;
	
	private SignalServer server;
	
	public Camera() {
		server = Configure.getInstance().getSignalServer();
	}
	
	public Camera(String token, String name, String mac) {
		
		this.userToken = token;
		this.name = name;
		this.mac = mac;
		
		server = Configure.getInstance().getSignalServer();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public void turnUp() {
		String msg = "{\"command\":\"turn\",\"value\":\"up\"}";
		execute(msg);
	}
	
	public void turnDown() {
		String msg = "{\"command\":\"turn\",\"value\":\"down\"}";
		execute(msg);
	}
	
	public void turnLeft() {
		String msg = "{\"command\":\"turn\",\"value\":\"left\"}";
		execute(msg);
	}
	
	public void turnRight() {
		String msg = "{\"command\":\"turn\",\"value\":\"right\"}";
		execute(msg);
	}
	
	public void turnStop() {
		String msg = "{\"command\":\"stop\",\"value\":\"\"}";
		execute(msg);
	}
	
	public void enableAlarm() {
		String msg = "{\"command\":\"enable_alarm\",\"value\":\"\"}";
		execute(msg);
	}
	
	public void disableAlarm() {
		String msg = "{\"command\":\"disable_alarm\",\"value\":\"\"}";
		execute(msg);
	}
	
	public void gotoPreset(String name) {
		String msg = "{\"command\":\"goto_preset\",\"value\":\"" + name + "\"}";
		execute(msg);
		
	}
	
	private void execute(String msg) {
		server.sendSignal(userToken, name, mac, msg);
	}

}
