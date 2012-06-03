package game;

public class Helper {
	
	public static void output(char[][] gameField) {
		for(char[] str1 : gameField) {
			for(char str2: str1) {
				System.out.print(str2);
			}
			System.out.println();
		}
	}
}
