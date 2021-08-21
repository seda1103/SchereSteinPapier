package com.abas.player;

import com.abas.constants.SystemConstant;

public class SameValuePlayer implements ComputerPlayer {

    @Override
    public String getComputerValue() {
        return SystemConstant.SAME_VALUE;
    }
}
