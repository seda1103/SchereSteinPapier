package com.abas.game;

import com.abas.constants.SystemMessageConstant;
import com.abas.enums.GameOptionEnums;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameServiceTest {

    private GameService sut = new GameService();

    @Test
    void shouldUserWinWhenUserIsPaperAndComputerIsRock() {
        String computerChoice = GameOptionEnums.ROCK.textMessage;
        String userChoice = GameOptionEnums.PAPER.textMessage;

        String result = sut.decideGameResult(computerChoice, userChoice);

        assertEquals(SystemMessageConstant.WIN_MESSAGE, result);
    }

    @Test
    void shouldUserWinWhenUserIsRockAndComputerIsScissor() {
        String computerChoice = GameOptionEnums.SCISSOR.textMessage;
        String userChoice = GameOptionEnums.ROCK.textMessage;

        String result = sut.decideGameResult(computerChoice, userChoice);

        assertEquals(SystemMessageConstant.WIN_MESSAGE, result);
    }

    @Test
    void shouldUserWinWhenUserIsScissorAndComputerIsPaper() {
        String computerChoice = GameOptionEnums.PAPER.textMessage;
        String userChoice = GameOptionEnums.SCISSOR.textMessage;

        String result = sut.decideGameResult(computerChoice, userChoice);

        assertEquals(SystemMessageConstant.WIN_MESSAGE, result);
    }

    @Test
    void shouldUserLoseWhenUserIsScissorAndComputerIsRock() {
        String computerChoice = GameOptionEnums.ROCK.textMessage;
        String userChoice = GameOptionEnums.SCISSOR.textMessage;

        String result = sut.decideGameResult(computerChoice, userChoice);

        assertEquals(SystemMessageConstant.LOST_MESSAGE, result);
    }

    @Test
    void shouldUserLoseWhenUserIsRockAndComputerIsPaper() {
        String computerChoice = GameOptionEnums.PAPER.textMessage;
        String userChoice = GameOptionEnums.ROCK.textMessage;

        String result = sut.decideGameResult(computerChoice, userChoice);

        assertEquals(SystemMessageConstant.LOST_MESSAGE, result);
    }


    @Test
    void shouldUserLoseWhenUserIsPaperAndComputerIsScissor() {
        String computerChoice = GameOptionEnums.SCISSOR.textMessage;
        String userChoice = GameOptionEnums.PAPER.textMessage;

        String result = sut.decideGameResult(computerChoice, userChoice);

        assertEquals(SystemMessageConstant.LOST_MESSAGE, result);
    }

    @Test
    void shouldDrawWhenUserAndComputerAreScissor() {
        String computerChoice = GameOptionEnums.SCISSOR.textMessage;
        String userChoice = GameOptionEnums.SCISSOR.textMessage;

        String result = sut.decideGameResult(computerChoice, userChoice);

        assertEquals(SystemMessageConstant.DRAWN_MESSAGE, result);
    }

    @Test
    void shouldDrawWhenUserAndComputerAreRock() {
        String computerChoice = GameOptionEnums.ROCK.textMessage;
        String userChoice = GameOptionEnums.ROCK.textMessage;

        String result = sut.decideGameResult(computerChoice, userChoice);

        assertEquals(SystemMessageConstant.DRAWN_MESSAGE, result);
    }


    @Test
    void shouldDrawWhenUserAndComputerArePaper() {
        String computerChoice = GameOptionEnums.PAPER.textMessage;
        String userChoice = GameOptionEnums.PAPER.textMessage;

        String result = sut.decideGameResult(computerChoice, userChoice);

        assertEquals(SystemMessageConstant.DRAWN_MESSAGE, result);
    }

    @Test
    void shouldReturnWrongValueMessageWhenUserValueIsIncorrect() {
        String computerChoice = GameOptionEnums.SCISSOR.textMessage;
        String userChoice = "A";

        String result = sut.decideGameResult(computerChoice, userChoice);

        assertEquals(SystemMessageConstant.WRONG_VALUE_MESSAGE, result);
    }
}
