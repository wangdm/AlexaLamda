package com.foscam.alexa.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;

public class SleepCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(SleepCommand.class);
	
	public SleepCommand(Intent intent, Session session) {
		super(intent, session);
	}

	@Override
	public void execute() {
		
		camera.sleep();
		log.info("Action: sleep ");
	}

}
