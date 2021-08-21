package com.abas.player;

import com.abas.enums.GameOptionEnums;

public class RandomValuePlayer implements ComputerPlayer {

    @Override
    public String getComputerValue() {
        return GameOptionEnums.randomLetter().textMessage;
    }

}
