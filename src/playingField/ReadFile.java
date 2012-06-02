package playingField;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Reads the playing field from a file and puts it into a two dimensonal array
 * 
 * @author LittleFool
 */
public class ReadFile {
	private Charset encoding = Charset.defaultCharset();
	private InputStream in;
	private Reader reader;
	private File file;
	private String[][] gameField = new String[50][50];

	/**
	 * Constructor
	 *  
	 * @param fileName The name of the file containing the game field.
	 */
	public ReadFile(String fileName) {
		file = new File(fileName);
		try {
			openFile();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

	/**
	 * Opens the file
	 * 
	 * @throws FileNotFoundException
	 */
	private void openFile() throws FileNotFoundException {
		this.in = new FileInputStream(this.file);
		this.reader = new InputStreamReader(this.in, this.encoding);
	}

	/**
	 * Reads the file a two dimensonal array
	 * 
	 * @return char[][]
	 * @throws IOException
	 */
	public String[][] read() throws IOException {
		int r, i=0, j=0;
		char ch;
		
		while ((r = reader.read()) != -1) {
			ch = (char)r;
			
			// newline
			if(r == 13) {
				// there are less columns than allowed
				if(j < this.gameField.length) {
					System.out.println("There are only "+ j +" columns in row "+ (i+1) +" but it need to be "+ this.gameField.length);
					System.exit(0);
				}
				
				// there are more rows than allowed
				if(i+1 > this.gameField[0].length) {
					System.out.println("There are more rows than allowed!");
					System.exit(0);
				}
				
				i++;
				j=0;
				continue;
			}
			if(r == 10) {
				continue;
			}
			
			// there are more columns than allowed
			if(j > gameField.length) {
				System.out.println("There are more than "+ this.gameField.length +" columns in row "+ (i+1) +".");
				System.exit(0);
			}
			
			// are we reading a valid char?
			if( ch != 'x' && ch != ' ' ) {
				System.out.println("The character "+ ch +" in column "+ (j+1) +" and row "+ (i+1) +" is not allowed!");
				System.exit(0);
			}
			
			// everything is good
			gameField[i][j] = ""+ch;
			j++;
		}
		
		return this.gameField;
	}
}