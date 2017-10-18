package com.foscam.alexa.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;

public class TurnCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(TurnCommand.class);
	
	public TurnCommand(Intent intent, Session session) {
		super(intent, session);
	}

	@Override
	public void execute() {
		
		String action = getSlotValue("TurnAction");
		if("up".equals(action)) {
			camera.turnUp();
		}else if("down".equals(action)) {
			camera.turnDown();
		}else if("left".equals(action)) {
			camera.turnLeft();
		}else if("right".equals(action)) {
			camera.turnRight();
		}
		log.info("Action: turn " + action);
	}

}
