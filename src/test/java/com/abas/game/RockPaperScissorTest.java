package com.abas.game;

import com.abas.constants.SystemMessageConstant;
import com.abas.enums.GameOptionEnums;
import com.abas.enums.PlayerEnums;
import com.abas.exceptions.InCorrectConfigFileException;
import com.abas.game.GameService;
import com.abas.game.RockPaperScissor;
import com.abas.player.ComputerPlayer;
import com.abas.player.PlayerFactory;
import com.abas.player.RandomValuePlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RockPaperScissorTest {

	@Mock
	private GameService gameService;

	@Mock
	private PlayerFactory playerFactory;

	@Mock
	private RandomValuePlayer computerPlayer;

	private RockPaperScissor sut;

	@BeforeEach
	void setUp() {
		sut = new RockPaperScissor();
		sut.setPlayerFactory(playerFactory);
		sut.setGameService(gameService);
	}

	@Test
	void shouldGetImmerSameValueWhenComputerPlayerSameSelected() {
		when(playerFactory.getPlayer(any())).thenCallRealMethod();

		ComputerPlayer player = sut.createComputerPlayer(PlayerEnums.SAME.textMessage);

		assertEquals(GameOptionEnums.ROCK.textMessage, player.getComputerValue());
	}

	@Test
	void shouldCalledDecideGameWithTrueValue() throws InCorrectConfigFileException {
		String input = GameOptionEnums.ROCK.textMessage;
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);

		when(gameService.decideGameResult(any(), any())).thenReturn(SystemMessageConstant.WIN_MESSAGE);
		when(playerFactory.getPlayer(any())).thenReturn(computerPlayer);
		when(computerPlayer.getComputerValue()).thenReturn(GameOptionEnums.ROCK.textMessage);

		sut.playGame();

		verify(gameService).decideGameResult(GameOptionEnums.ROCK.textMessage, GameOptionEnums.ROCK.textMessage);
	}

	@Test
	void shouldReturnTrueValueWhenComputerUserIsRandom() {
		when(playerFactory.getPlayer(any())).thenCallRealMethod();

		ComputerPlayer computerPlayer = sut.createComputerPlayer(PlayerEnums.RANDOM.textMessage);
		List<String> list = Arrays.stream(GameOptionEnums.values()).map(s -> s.textMessage).collect(Collectors.toList());
		assertThat(list, hasItem(computerPlayer.getComputerValue()));

	}

	@Test
	void shouldReturnSamePlayerFromConfigurationFile() {
		String result = sut.getComputerUserOptionFromProperties("config.properties");
		assertEquals(PlayerEnums.SAME.textMessage, result);
	}

	@Test
	void shouldDefaultPlayerFromConfigurationFileWhenExceptionOccurs() {
		String result = sut.getComputerUserOptionFromProperties("NoFile.properties");
		assertEquals(PlayerEnums.RANDOM.textMessage, result);
	}


	@Test
	void shouldThrowExceptionCheckPropertiesFileWithWrongValue() {
		Assertions.assertThrows(InCorrectConfigFileException.class, () -> {
			sut.checkPropertiesFile("a");
		});
	}
}
