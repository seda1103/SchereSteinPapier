package com.abas.enums;

public enum PlayerEnums {
	SAME("G"),
	RANDOM("Z");

	public String textMessage;

	PlayerEnums(String textMessage) {
		this.textMessage = textMessage;
	}
}
