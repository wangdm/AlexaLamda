package com.foscam.alexa.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;

public class StopCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(StopCommand.class);
	
	public StopCommand(Intent intent, Session session) {
		super(intent, session);
	}

	@Override
	public void execute() {
		
		camera.turnStop();
		log.info("Action: stop ");
	}

}
