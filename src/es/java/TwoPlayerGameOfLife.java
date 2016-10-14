package es.java;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 *  This class contains the functioning of the game TwoPlayerGameOfLife which consists of a two player 
 *  game with the objective of making the contrary player have no live cells. The rules that apply are:
 * 	Any live cell with fewer than two live neighbors of either player dies.
 * 	Any live cell with two or three live neighbors of either player lives.
 * 	Any live cell with more than three neighbors of either player dies.
 * 	Any dead cell with exactly three neighbors becomes a live cell owned by the player who has the
 * 	majority of live neighboring cells.
 * 
 * @author: Sergio Penavades Suarez
 * @version: 1
 */

public class TwoPlayerGameOfLife{

	private char[][] grid;
	private int rows=0;
	private int columns=0;
	private int generation=0;
	private boolean endOfGame = false;
	private int count=0;
	
	/**
	 * The method TwoPlayerGameOfLife is the constructor method which creates the grid of the game with
	 * the initial values of all dead cells
	 * @param rows Total number of rows of the grid
	 * @param columns Total number of columns of the grid
	 */
	public TwoPlayerGameOfLife(int rows, int columns) {
		this.rows=rows;
		this.columns=columns;
		grid = new char[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				grid[i][j] = '.';
			}
		}
	}

	/**
	 * The method getRows returns the number of rows of the file
	 * @return gives the number of rows of the file
	 */
	public int getRows(){
		return rows;
	}   

	/**
	 * The method getColumns returns the number of columns of the file
	 * @return gives the number of columns of the file
	 */
	public int getColumns(){
		return columns;
	}

	/**
	 * The method readDimensions gives the dimensions of the file which are collected from the first line
	 * of the file by reading the generation0.txt.
	 * @param path carries the path where to find the file which in this case is: 
	 * /table.txt
	 * @return an integer array, being the first element the rows and the second one the columns 
	 * of the array.
	 */
	public static int[] readDimensions(String path){
		int dimensions[]= null;
		try{
			// Opens the file
			FileInputStream fileInput = new FileInputStream(path);
			// Input object
			DataInputStream dataFile = new DataInputStream(fileInput);
			// LectureBuffer
			BufferedReader buffer = new BufferedReader(new InputStreamReader(dataFile));
			String strLine = buffer.readLine();
			int[] rowsColumnsNumber = dimensionsOk(strLine);
			dataFile.close();
			return rowsColumnsNumber;
		}catch (Exception e){ 
			System.err.println("Error happened in " + e.getMessage());
		}  
		return dimensions;
	}

	/**
	 * This method reads the whole file with the exception of the first line that carries
	 * the dimensions of the array.
	 * @param path carries the path where to find the file which in this case is
	 * /table.txt
	 * @return count which counts the number of rows of the grid for the game.
	 */
	public int readArray(String path){
		try{
			// Open file
			FileInputStream fileInput = new FileInputStream(path);
			// Input object
			DataInputStream dataFile = new DataInputStream(fileInput);
			// Reading the Buffer
			BufferedReader buffer = new BufferedReader(new InputStreamReader(dataFile));
			String strLine;   
			
			while ((strLine = buffer.readLine()) != null){ 
				if (count!=0){
					initCharAndColumnsOk (strLine);
					for (int j = 0; j < strLine.length(); j++) {
						grid[count-1][j]=strLine.charAt(j);
					}                          
				}
				count++;  
			}
			dataFile.close();
		}catch (Exception e){ 
			System.err.println("Error happened in " + e.getMessage());
		} initRowsOk(count-1);
		  return count;
	}

	/**
	 * Checks if the file only contains two numbers (rows, columns) and a space in between. 
	 * @param row1 Obtains the first row of the file table.txt.
	 * @return result which are the rows and columns of the grid to use in the game.
	 */
	public static int[] dimensionsOk(String row1){
	
		// By using this pattern we check that the first line of the file contains only two integers separated by a space.
		
		if(Pattern.matches("^\\d+\\s\\d+$", row1)){
			String [] rowsColumns = row1.split("\\s");
			int rows = Integer.valueOf(rowsColumns[0]);
			int columns = Integer.valueOf(rowsColumns[1]);
			if(rows == 0 || columns == 0){
				System.out.println("Rows and columns number must be greater than zero.");
				return null;
			}
			int[] result = {rows, columns};
			return result;
		}else{
			System.out.println("The number of rows and columns has not an accepted format.");
			return null;
		}
	}

	/**
	 * Method that checks if the file only contains dots or the integer 1 or 2, as well as 
	 * if they are equal to the total number of columns in each row.

	 * @param  strLine line from the file which starts in the second line till the end of the text.
	 */ 
	public void initCharAndColumnsOk(String strLine) {
		if(Pattern.matches(String.format("^[\\.12]{%d}$", columns), strLine)){
		
		}else{
			if(strLine.length() != columns) System.out.println("Invalid initial generation, rows don't match with dimensions");
			else System.out.println("Invalid initial generation, characters are not allowed");
			System.exit(1);	
		}                       	
	} 

	/**
	 * Method that checks if the number of rows read in the method ReadArray are the same as the rows. 
	 * from the dimensions defined in the first line of the file.
	 * @param  count number of rows read with any value.
	 */ 
	public void initRowsOk( int count) { 
		if (count != getRows() ){
			System.out.println("Invalid initial generation, rows don't match with dimensions");
			System.exit(1);       	       
		}                         	
	}

	/**
	 * Method that brings out in the console:
	 * 	Number of generation.
	 * 	Number of cells alive of Player1.
	 * 	Number of cells alive of Player2.
	 * 	What player won or if there was a tie.
	 * 
	 * Additionally, it draws the grid.
	 */ 
	public void drawGrid() {
		int numberOfOnes=0;
		int numberOfTwos=0;
		System.out.println ("Generation #"+generation);

		for (int i=0; i < grid.length; i++) {
			for (int j=0; j < grid[i].length; j++) {
				if (grid[i][j]=='1')numberOfOnes++;
				else if (grid[i][j]=='2')numberOfTwos++;  
			}
		}  
		System.out.println ("Player 1 Cells: "+numberOfOnes);
		System.out.println ("Player 2 Cells: "+numberOfTwos);


		for (int i=0; i < grid.length; i++) {
			for (int j=0; j < grid[i].length; j++) {
				System.out.print (grid[i][j]);
			}System.out.println ();
		}
		
		if (numberOfOnes==0 || numberOfTwos==0){
			if (numberOfOnes==0 && numberOfTwos!=0 ){
				System.out.println ("PLAYER 2 WINS WITH" +numberOfTwos+" CELLS ALIVE");
			}
			else if (numberOfOnes!=0 && numberOfTwos==0 ){ 
				System.out.println ("PLAYER 1 WINS WITH " +numberOfOnes+ " CELLS ALIVE");
			}
			else if (numberOfOnes==0 && numberOfTwos==0){
				System.out.println("Ended game as a tie between two players");
			}
			endOfGame=true;		
		}
	}

	/**
	 * Method used to check if the neighbour cell is in or outside the grid.
	 * @param  row position to check for neighbours.
	 * @param  column position to check for neighbours.
	 * @return True if the neighbour is inside the grid.
	 */ 
	private boolean insideGrid(int row, int column) {
		return row >= 0 && column >= 0 && row < getRows() && column < getColumns();
	}

	/**
	 * Method that looks for the 8 adjacent neighbours and calculates if is alive or dead.
	 * @param  row position to calculate the neighbours
	 * @param  column position to calculate the neighbours
	 * @param grid to check the play
	 * 
	 */ 
	public int[] neighbours (int row, int column, char[][] grid){
		//The first element will give the number of cells from player 1 alive and the
		//second the number of alive cells from palyer 2.
		int[] nei={0,0};
		//Will determinate which player has the majority in the case of being a dead 
		//cell with 3 alive neighbours.
		int aux=0;
		//Neighbour on the left
		if (insideGrid(row,column-1)) {
			if(grid[row][column-1]=='1')nei[0]++;
			else if (grid[row][column-1]=='2')nei[1]++;
		}
		//Neighbour on the right
		if (insideGrid(row,column+1)){
			if(grid[row][column+1]=='1')nei[0]++;
			else if (grid[row][column+1]=='2')nei[1]++;
		}
		//Neighbour above
		if (insideGrid(row-1,column)){
			if(grid[row-1][column]=='1')nei[0]++;
			else if (grid[row-1][column]=='2')nei[1]++;
		}
		//Neighbour  below
		if (insideGrid(row+1,column)){ 
			if(grid[row+1][column]=='1')nei[0]++;
			else if (grid[row+1][column]=='2')nei[1]++;
		}
		//Neighbour northwest
		if (insideGrid(row-1,column-1)){
			if(grid[row-1][column-1]=='1')nei[0]++;
			else if (grid[row-1][column-1]=='2')nei[1]++;
		}
		//Neighbour northeast
		if (insideGrid(row-1,column+1)){
			if(grid[row-1][column+1]=='1')nei[0]++;
			else if (grid[row-1][column+1]=='2')nei[1]++;
		}
		//Neighbour southwest
		if (insideGrid(row+1,column-1)){
			if(grid[row+1][column-1]=='1')nei[0]++;
			else if (grid[row+1][column-1]=='2')nei[1]++;
		}
		//Neighbour southeast
		if (insideGrid(row+1,column+1)){
			if(grid[row+1][column+1]=='1')nei[0]++;
			else if (grid[row+1][column+1]=='2')nei[1]++; 
		}
		//If the number of alive cells from player one are greater than player 2, then the aux becomes 1, meaning that in case of being a dead cell with exactly three alive neighbour, it will become alive with the number 1.
		if (nei[0]>nei[1])aux=1;
		//aux=2 means that there are a majority of cells being part of palyer 2.
		else aux=2;
		//The first element of nei contains the total of the live cells of the neighbours.
		nei[0]=nei[0]+nei[1];
		nei[1]=aux;
		return nei;
	}


	/**
	 * Realizes the algorithm of the game.
	 */ 
	public void game(){
		int[] neighbours={0,0};//1-total vecinos 2-quien gana 1o2
		char[][] aux_grid= new char[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				neighbours=neighbours (i,j,grid);
				//If the cell is dead and has exactly three neighbours, it will become alive with 
				//the number of the majority of alive cells from either player 1 or 2. 
				//If not, the dead cell will reamin dead.
				if (grid [i][j]=='.' && neighbours[0]==3) {
					if(neighbours[1]==1)
						aux_grid[i][j]='1';
					else aux_grid[i][j]='2';

				}
				else {if (grid [i][j]=='.')aux_grid[i][j]='.';}

				//If the cell has either the value 1 or 2, which means they are allive, they will:
			    // Become dead if they have less than 2 adjacent alive cells or more than 3 adjacent alive cells. 
				//If it has 2 or 3 live adjacent cells it will remain alive.
				if (grid[i][j]=='1' || grid[i][j]=='2'){
					if (neighbours[0]<2 || neighbours[0]>3 ) aux_grid[i][j]='.';
					else aux_grid[i][j]=grid[i][j];
				}
			}
		} 
		grid=aux_grid;
		generation++;
	}
	
	public boolean isGameEnded(){
		return endOfGame;
	}
	
	public char[][] getGrid(){
		return grid;
	}

	/**
	 * Method that starts the application
	 * @args[0] contains the path of the file table.txt
	 */ 
	public static void main(String args[]) throws IOException {
		if(args == null || args.length == 0){
			System.out.println("Command of the game: java TwoPlayerGameOfLife generation0.txt");
			System.out.println("Args generation0.txt is missing!");
			return;
		}
		int[] dimens = readDimensions(args[0]);
		if(dimens == null) return;
		TwoPlayerGameOfLife game = new TwoPlayerGameOfLife(dimens[0], dimens[1]);
		game.readArray(args[0]);
		game.drawGrid();
		while (!(game.isGameEnded())) {
			game.game();
			game.drawGrid();
		}
	}
}

