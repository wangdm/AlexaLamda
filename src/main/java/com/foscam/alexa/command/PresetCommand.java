package com.foscam.alexa.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;

public class PresetCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(PresetCommand.class);
	
	public PresetCommand(Intent intent, Session session) {
		super(intent, session);
	}

	@Override
	public void execute() {
		
		String name = getSlotValue("PresetName");
		camera.gotoPreset(name);
		log.info("Action: goto preset " + name);
	}

}
