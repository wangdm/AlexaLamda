package com.foscam.alexa;

import java.util.HashSet;
import java.util.Set;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public class CameraRequestHandler extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds = new HashSet<String>();
    static {
        supportedApplicationIds.add("amzn1.ask.skill.30141fb6-1a72-4dc4-a4c1-050e637f2945");
    }

	public CameraRequestHandler() {
		super(new CameraSpeechlet(), supportedApplicationIds);
	}

}
