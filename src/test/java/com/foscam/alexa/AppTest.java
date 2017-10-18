package com.foscam.alexa;

import com.foscam.alexa.Camera;

public class AppTest {

	public static void main(String[] args) {
		
		Camera camera = new Camera("c797aad827494e57a955e4f67fe41036", "FI9816P", "000453fffff1");

		System.out.println("name: "+camera.getName());
		System.out.println("mac: "+camera.getMac());
		System.out.println("token: "+camera.getUserToken());
		
		camera.enableAlarm();
		
		camera.turnUp();
	}

}
