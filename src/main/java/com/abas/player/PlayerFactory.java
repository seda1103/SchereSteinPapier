package com.abas.player;

import com.abas.enums.PlayerEnums;
import org.apache.commons.lang3.StringUtils;

public class PlayerFactory {

    public ComputerPlayer getPlayer(String playerType) {
        if (StringUtils.equalsIgnoreCase(playerType, PlayerEnums.RANDOM.textMessage)) {
            return new RandomValuePlayer();
        } else if (StringUtils.equalsIgnoreCase(playerType, PlayerEnums.SAME.textMessage)) {
            return new SameValuePlayer();
        }
        return null;
    }
}
