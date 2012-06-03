package game;

import java.io.IOException;
import java.util.concurrent.*;

import playingField.ReadFile;

public class Main {

	/**
	 * Sets up the game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		char[][] gameField = new char[50][50];
		ReadFile rf = new ReadFile("src/playingField/beacon");
		try {
			gameField = rf.read();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		NextStep n = new NextStep(gameField);
		ExecutorService executor = Executors.newCachedThreadPool();
		
		try {
			gameField = n.call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		Helper.output(gameField);
		
		executor.shutdown();
	}
}
