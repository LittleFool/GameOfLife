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
		Scanner in = new Scanner(System.in);
		char[][] gameField = new char[50][50];
		int n = 0;
		ReadFile rf = new ReadFile("src/playingField/beacon.txt");
		
		try {
			gameField = rf.read();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.print("How pany steps to prozess? ");
		n=in.nextInt();
		in.close();
		
		NextStep next = new NextStep(gameField);
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<char[][]> result;
		for(int i=0; i <= n; i++) {
			result = executor.submit(next);
			Helper.output(gameField);
			
			try {
				gameField = result.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
			next.reset();
		}
		executor.shutdown();
		
		System.exit(0);
	}
}
