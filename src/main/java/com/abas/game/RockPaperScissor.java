package com.abas.game;

import com.abas.constants.SystemConstant;
import com.abas.constants.SystemMessageConstant;
import com.abas.enums.PlayerEnums;
import com.abas.exceptions.InCorrectConfigFileException;
import com.abas.game.GameService;
import com.abas.player.ComputerPlayer;
import com.abas.player.PlayerFactory;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class RockPaperScissor {

	private GameService gameService;
	private PlayerFactory playerFactory;

	public RockPaperScissor() {
		gameService = new GameService();
		playerFactory = new PlayerFactory();
	}

	public void playGame() throws InCorrectConfigFileException {
		String computerUserOptionFromProperties = getComputerUserOptionFromProperties(SystemConstant.CONFIG_PROPERTIES);
		checkPropertiesFile(computerUserOptionFromProperties);
		ComputerPlayer computerPlayer = createComputerPlayer(computerUserOptionFromProperties);
		System.out.println(SystemMessageConstant.USER_GAME_CHOICE_MESSAGE);
		String userChoice = readUserInputFromConsole();
		String computerPlayerChoice;

		while (!wantUserToKeepPlaying(userChoice)) {
			computerPlayerChoice = computerPlayer.getComputerValue();
			System.out.println("Computerauswahl : " + computerPlayerChoice);
			System.out.println(gameService.decideGameResult(computerPlayerChoice, userChoice));
			userChoice = readUserInputFromConsole();
		}
	}

	public String getComputerUserOptionFromProperties(String fileName) {
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream stream = loader.getResourceAsStream(fileName);
		try {
			prop.load(stream);
			return prop.getProperty(SystemConstant.KEY_COMPUTER_USER_OPTIONS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return PlayerEnums.RANDOM.textMessage;
	}

	public void checkPropertiesFile(String computerUserOptionFromProperties) throws InCorrectConfigFileException {
		if (!hasPropertiesFileCorrectValues(computerUserOptionFromProperties)) {
			System.out.println(SystemMessageConstant.SYSTEM_ERROR_WRONG_CONFIG);
			throw new InCorrectConfigFileException(SystemMessageConstant.SYSTEM_ERROR_WRONG_CONFIG);
		}
	}

	private boolean hasPropertiesFileCorrectValues(String computerUserOptionFromProperties) {
		return Arrays.stream(PlayerEnums.values())
				.anyMatch(s -> StringUtils.equalsIgnoreCase(s.textMessage, computerUserOptionFromProperties));
	}

	public ComputerPlayer createComputerPlayer(String playerString) {
		return playerFactory.getPlayer(playerString);
	}

	private String readUserInputFromConsole() {
		Scanner sc = new Scanner(System.in);
		if (sc.hasNext()) {
			return sc.nextLine();
		} else {
			return SystemConstant.SYSTEM_EXIT;
		}
	}

	private boolean wantUserToKeepPlaying(String userInput) {
		return StringUtils.equalsIgnoreCase(SystemConstant.SYSTEM_EXIT, userInput);
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

	public void setPlayerFactory(PlayerFactory playerFactory) {
		this.playerFactory = playerFactory;
	}
}
