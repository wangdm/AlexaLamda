package com.foscam.alexa.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.User;
import com.foscam.alexa.Camera;

public abstract class Command {

    private static final Logger log = LoggerFactory.getLogger(Command.class);
    
	private Intent intent;
	private Session session;
	protected Camera camera;
	
	public Command(Intent _intent, Session _session) {
		this.intent = _intent;
		this.session = _session;
		
        User user = session.getUser();
        String token = user.getAccessToken();
		String cameraName = getSlotValue("IPCName");
		camera = new Camera(token, cameraName, "000453fffff1");
		//camera = Configure.getInstance().getAccessServer().getCamera(token, cameraName);
		log.info("UserToken: " + token + ", Camera: " + cameraName);
	}
	
	public String getSlotValue(String name)
	{
		Slot slot = intent.getSlot(name);
		return slot.getValue();
	}
	
	public abstract void execute();

}
