package com.abas.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum GameOptionEnums {
	SCISSOR("S"),
	ROCK("R"),
	PAPER("P");

	public String textMessage;

	GameOptionEnums(String textMessage) {
		this.textMessage = textMessage;
	}

	private static final List<GameOptionEnums> values = Arrays.asList(values());
	private static final int ENUMSIZE = values.size();
	private static final Random random = new Random();

	public static GameOptionEnums randomLetter() {
		return values.get(random.nextInt(ENUMSIZE));
	}

	public static GameOptionEnums findByTextMessage(String text) {
		for (GameOptionEnums v : values()) {
			if (StringUtils.equalsIgnoreCase(v.textMessage, text)) {
				return v;
			}
		}
		return null;
	}


}
