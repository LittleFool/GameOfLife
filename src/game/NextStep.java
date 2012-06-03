package game;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class NextStep implements Callable<char[][]> {
	private char[][] gameField;
	private char[][] newField;

	public NextStep(char[][] gameField) {
		this.gameField = gameField;
		this.newField = new char[gameField.length][gameField[0].length];
		for (char[] row : newField)
	        Arrays.fill(row, ' ');
	}

	public void setGameField(char[][] gameField) {
		this.gameField = gameField;
	}

	private int countNeighbors(int row, int column) {
	    int neighbors = 0;
	    
	    for(int i=row-1; i <= row+1; i++) {
	    	for(int j=column-1; j <= column+1; j++) {
	    		if(i == -1 || j == -1) {
	    			continue;
	    		}
	    		
	    		if(i == row && j == row) {
	    			continue;
	    		}
	    		
	    		if(gameField[i][j] == 'x') {
	    			neighbors++;
	    		}
	    	}
	    }
	    
	    System.out.println("count["+row+"]["+column+"] = "+neighbors);
	    
	    return neighbors;
	}
	
	@Override
	public char[][] call() throws Exception {
		int count = 0;
		
		for (int i = 0; i < this.gameField.length - 1; i++) {
			for (int j = 0; j < this.gameField[0].length - 1; j++) {
				count = countNeighbors(i, j);

				// More than 3 neighbors and the cell dies
				if(count > 3) {
					newField[i][j] = ' ';
				}
				
				// Exactly 3 neighbors and the cell lives
				if(count == 3) {
					newField[i][j] = 'x';
				}
				
				// A living cell with 2 neighbors lives
				if(count == 2 && gameField[i][j] == 'x') {
					newField[i][j] = 'x';
				}
				
				// Less than 2 neighbors and the cell dies
				if(count < 2) {
					newField[i][j] = ' ';
				}
			}
		}
		return newField;
	}

}
