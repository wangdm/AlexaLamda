package com.foscam.alexa.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;

public class SwitchCommand extends Command {

    private static final Logger log = LoggerFactory.getLogger(SwitchCommand.class);
  
    public enum SwitchType{
    	ON,OFF
    }
    
    private SwitchType type;
	
	public SwitchCommand(Intent intent, Session session, SwitchType type) {
		super(intent, session);
		this.type = type;
	}

	@Override
	public void execute() {
		
		String name = getSlotValue("SwitchName");
		
		if(type == SwitchType.ON)
		{
			if("alarm".equals(name)) {
				camera.enableAlarm();
			}
			log.info("Action: switch on alarm");
		}
		else if(type == SwitchType.OFF)
		{
			if("alarm".equals(name)) {
				camera.disableAlarm();
			}
			log.info("Action: switch off alarm");
		}
	}

}
