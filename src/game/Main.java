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
		String[][] gameField = new String[50][50];
		ReadFile rf = new ReadFile("src/playingField/gleiter.txt");
		try {
			gameField = rf.read();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		NextStep next = new NextStep(gameField);
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<String[][]> result = executor.submit(next);
		
		for(String[] str1 : gameField) {
			for(String str2: str1) {
				System.out.print(str2);
			}
			System.out.println();
		}
		
		try {
			gameField = result.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		for(String[] str1 : gameField) {
			for(String str2: str1) {
				System.out.print(str2);
			}
			System.out.println();
		}
	}
}
