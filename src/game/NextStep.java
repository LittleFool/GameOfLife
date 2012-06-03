package game;

import java.util.concurrent.Callable;

public class NextStep implements Callable<char[][]> {
	private char[][] gameField;

	public NextStep(char[][] gameField) {
		this.gameField = gameField;
	}

	private int countNeighbors(int row, int column) {
	    int neighbours = 0;

	    for (int i = row - 1; i <= row + 1; i++) {
	        for (int j = column - 1; j <= column + 1; j++) {
	            // wir dürfen uns selbst nicht mitzählen!
	            if (i == row && j == column)
	                continue;

	            if(i == -1 || j == -1) {
	            	continue;
	            }
	            
	            // ein lebender Nachbar
	            if (gameField[i][j] == 'x') {
	                neighbours++;
	            }
	        }
	    }

	    return neighbours;
	}
	
	@Override
	public char[][] call() throws Exception {
		int count = 0;
		
		for (int i = 0; i < this.gameField.length - 1; i++) {
			for (int j = 0; j < this.gameField[0].length - 1; j++) {
				count = countNeighbors(i, j);

				// More than 3 neighbors and the cell dies
				if(count > 3) {
					gameField[i][j] = ' ';
				}
				
				// Exactly 3 neighbors and the cell lives
				if(count == 3) {
					gameField[i][j] = 'x';
				}
				
				if(count < 2) {
					gameField[i][j] = ' ';
				}
			}
		}
		return gameField;
	}

}
