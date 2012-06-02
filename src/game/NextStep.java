package game;

import java.util.concurrent.Callable;

public class NextStep implements Callable<String[][]> {
	private String[][] gameField;

	public NextStep(String[][] gameField) {
		this.gameField = gameField;
	}

	private int countNeighbours(int row, int column) {
	    int nachbarn = 0;

	    for (int i = row - 1; i <= row + 1; i++) {
	        for (int j = column - 1; j <= column + 1; j++) {
	            // wir dürfen uns selbst nicht mitzählen!
	            if (i == row && j == column)
	                continue;

	            if(i == -1 || j == -1) {
	            	continue;
	            }
	            
	            // ein lebender Nachbar
	            if (gameField[i][j].equals("x")) {
	                nachbarn++;
	            }
	        }
	    }

	    return nachbarn;
	}
	
	@Override
	public String[][] call() throws Exception {
		int count = 0;
		
		for (int i = 0; i < this.gameField.length - 1; i++) {
			for (int j = 0; j < this.gameField[0].length - 1; j++) {
				count = countNeighbours(i, j);

				// Mehr als 3 --> die Zelle stirbt
				if (count > 3) {
					gameField[i][j] = " ";
					// genau 3 --> die zelle lebt
				} else if (count == 3) {
					gameField[i][j] = "x";
					// genau 3 --> die Zelle bleibt am leben, aber tote werden
					// nicht geboren
				} else if (count == 2) {
					if (gameField[i][j] == "x") {
						gameField[i][j] = "x";
					} else {
						gameField[i][j] = " ";
					}
					// weniger als 2 --> die Zelle stirbt
				} else if (count < 2) {
					gameField[i][j] = " ";
				} else {
					gameField[i][j] = " ";
				}
			}
		}
		return gameField;
	}

}
