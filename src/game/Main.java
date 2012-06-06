package game;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.*;

import playingField.ReadFile;

public class Main {

	/**
	 * Sets up the game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new GUI("Game of Life");
	}
}
