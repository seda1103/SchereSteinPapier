package com.abas.game;

import com.abas.constants.SystemMessageConstant;
import com.abas.enums.GameOptionEnums;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class GameService {

	private Map<GameOptionEnums, GameOptionEnums> gameRulesMap;

	public GameService() {
		gameRulesMap = new EnumMap<>(GameOptionEnums.class);
		gameRulesMap.put(GameOptionEnums.ROCK, GameOptionEnums.SCISSOR);
		gameRulesMap.put(GameOptionEnums.SCISSOR, GameOptionEnums.PAPER);
		gameRulesMap.put(GameOptionEnums.PAPER, GameOptionEnums.ROCK);
	}

	public String decideGameResult(String computerValue, String userValue) {
		if (isEnteredValueIncorrect(userValue)) {
			return SystemMessageConstant.WRONG_VALUE_MESSAGE;
		}
		return checkIfUserHasWonOrLoseOrDrawn(computerValue, userValue);
	}

	private String checkIfUserHasWonOrLoseOrDrawn(String computerValue, String userValue) {
		if (StringUtils.equalsIgnoreCase(computerValue, userValue)) {
			return SystemMessageConstant.DRAWN_MESSAGE;
		}

		if (Objects.equals(
				gameRulesMap.get(GameOptionEnums.findByTextMessage(userValue))
				, GameOptionEnums.findByTextMessage(computerValue))
		) {
			return SystemMessageConstant.WIN_MESSAGE;
		} else {
			return SystemMessageConstant.LOST_MESSAGE;
		}
	}

	private boolean isEnteredValueIncorrect(String userValue) {
		return ObjectUtils.isEmpty(GameOptionEnums.findByTextMessage(userValue));
	}

}
