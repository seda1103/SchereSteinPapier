package com.abas.main;

import com.abas.exceptions.InCorrectConfigFileException;
import com.abas.game.RockPaperScissor;

import java.util.Scanner;

public class MainApplication {

    public static void main(String[] args) {
        RockPaperScissor rockPaperScissor = new RockPaperScissor();
        try {
            rockPaperScissor.playGame();
        } catch (InCorrectConfigFileException e) {
            e.printStackTrace();
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }

    }
}
