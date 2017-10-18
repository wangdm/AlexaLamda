package com.foscam.alexa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.foscam.alexa.command.Command;
import com.foscam.alexa.command.SwitchCommand;
import com.foscam.alexa.command.TurnCommand;
import com.foscam.alexa.command.SwitchCommand.SwitchType;

public class CameraSpeechlet implements Speechlet {
    private static final Logger log = LoggerFactory.getLogger(CameraSpeechlet.class);

	@Override
	public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        

	}

	@Override
	public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
        log.info("onSessionEnded requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());

	}

	@Override
	public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText("Hello, Sir");

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(outputSpeech);
        
		return SpeechletResponse.newAskResponse(outputSpeech, reprompt);
	}

	@Override
	public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        
        Intent intent = request.getIntent();
        String intentName = intent.getName();
        
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        
        if("Turn".equals(intentName)) {
        	Command cmd = new TurnCommand(intent,session);
        	cmd.execute();

        	outputSpeech.setText("Yes, Sir, turn " + cmd.getSlotValue("TurnAction"));
        	return SpeechletResponse.newTellResponse(outputSpeech);
        	
        }else if("SwitchOn".equals(intentName)){
        	Command cmd = new SwitchCommand(intent, session, SwitchType.ON);
        	cmd.execute();

        	outputSpeech.setText("Yes, Sir, swtich on " + cmd.getSlotValue("SwitchName"));
        	return SpeechletResponse.newTellResponse(outputSpeech);
        	
        }else if("SwitchOff".equals(intentName)){
        	Command cmd = new SwitchCommand(intent, session, SwitchType.OFF);
        	cmd.execute();

        	outputSpeech.setText("Yes, Sir, swtich off " + cmd.getSlotValue("SwitchName"));
        	return SpeechletResponse.newTellResponse(outputSpeech);
        	
        }else {
        	outputSpeech.setText("Sorry, Sir, I do't know that!");
        }

    	return SpeechletResponse.newTellResponse(outputSpeech);
	}

}
