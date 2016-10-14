package es.java;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
* Test Case to try if the game works correctly
* @author: Sergio Penavades Suarez
* @version: 1
*/


public class TwoPlayerGameOfLifeTest {

	private TwoPlayerGameOfLife game;
	private String path="table.txt";
	private final static char[][] generation0 = {{'.','.','.','.','.','.','.','.','.','.'},
												 {'.','.','1','.','.','.','.','.','.','.'},
												 {'.','.','.','1','.','.','.','.','.','.'},
												 {'.','.','1','1','1','.','.','.','.','.'},
												 {'.','.','.','.','.','.','.','.','.','.'},
												 {'.','.','.','.','.','.','.','2','2','.'},
												 {'.','.','.','.','.','.','2','.','.','2'},
												 {'.','.','.','.','.','.','.','2','2','.'},
												 {'.','.','.','.','.','.','.','.','.','.'},
												 {'.','.','.','.','.','.','.','.','.','.'}};

	@Before
	public void setUp() throws Exception {
		int dimens[] = TwoPlayerGameOfLife.readDimensions(path);
		int rows = dimens[0];
		int columns = dimens[1];
		game = new TwoPlayerGameOfLife(rows, columns);
		game.readArray(path);
	}
	
	/**
	* In this method we will check if the dimensions are the same as the expected result.
	*/
	@Test
	public void dimensionsWellLoaded() throws Exception{
		assertEquals(10, game.getColumns());
		assertEquals(10, game.getRows());
	}
	
	/**
	* In this method we will check if the file read is exactly the same as the array generation0 
	* which is the expected result.
	*/
	@Test
	public void arrayWellLoaded(){
		char[][] gameGrid = game.getGrid();
		for(int i = 0; i < gameGrid.length; i++)
			for(int j = 0; j < gameGrid[0].length; j++)
				if(gameGrid[i][j] != generation0[i][j]){
					System.out.println(i + " " + j);
					System.out.println(gameGrid[i][j]+ " "+generation0[i][j]);
					Assert.fail("Loaded grid is different from test grid");
					return;
				}
					
	}
	/**
	* In the next tests we will try all the possible options of dead to alive, and alive to dead cells.
	* Depending on the generation in which the test cases happen, we will call more time the game method.
	* Therefore, if the case happens in the generation 7 we will run the game 7 times before checking.
	*/
	@Test
	public void live1CellWithFewerThanTwoNeighborsShouldDie(){
		assertEquals(game.getGrid()[1][2], '1');
		game.game();
		assertEquals(game.getGrid()[1][2], '.');
	}


	@Test
	public void live2CellWithFewerThanTwoNeighborsShouldDie(){
		game.game();
		game.game();
		game.game();
		game.game();
		game.game();
		game.game();
		game.game();
		assertEquals(game.getGrid()[6][9], '2');
		game.game();
		assertEquals(game.getGrid()[6][9], '.');
	}
	
	@Test
	public void live1CellWithMoreThanThreeNeighborsShouldDie(){
		assertEquals(game.getGrid()[2][3], '1');
		game.game();
		assertEquals(game.getGrid()[2][3], '.');
	}
	
	@Test
	public void live2CellWithMoreThanThreeNeighborsShouldDie(){
		game.game();
		game.game();
		game.game();
		game.game();
		game.game();
		assertEquals(game.getGrid()[6][6], '2');
		game.game();
		assertEquals(game.getGrid()[6][6], '.');
	}
	
	@Test
	public void DieCellWithTwoOrThreeNeighborsShouldLiveAs1IfOnesAreMore(){
		assertEquals(game.getGrid()[2][4], '.');
		game.game();
		assertEquals(game.getGrid()[2][4], '1');
	}
	
	@Test
	public void DieCellWithTwoOrThreeNeighborsShouldLiveAs1IfTwosAreMore(){
		game.game();
		game.game();
		game.game();
		game.game();
		game.game();
		game.game();
		game.game();
		assertEquals(game.getGrid()[6][8], '.');
		game.game();
		assertEquals(game.getGrid()[6][8], '2');
	}
	
	@Test
	public void dieCellWithFewerThanThreeNeighborsShouldDie(){
		assertEquals(game.getGrid()[0][0], '.');
		game.game();
		assertEquals(game.getGrid()[0][0], '.');
	}
	
	@Test
	public void dieCellWithMoreThanThreeNeighborsShouldDie(){
		assertEquals(game.getGrid()[2][2], '.');
		game.game();
		assertEquals(game.getGrid()[2][2], '.');
	}
	
	@Test
	public void live1CellWithThreeNeighborsShouldLive(){
		assertEquals(game.getGrid()[3][3], '1');
		game.game();
		assertEquals(game.getGrid()[3][3], '1');
	}
	
	@Test
	public void live1CellWithTwoNeighborsShouldLive(){
		assertEquals(game.getGrid()[3][2], '1');
		game.game();
		assertEquals(game.getGrid()[3][2], '1');
	}
	
	/*@Test
	This case cannot be checked because it doesn't occur in the generation0.txt file.
	public void live2CellWithThreeNeighborsShouldLive(){
		assertEquals(game.getGrid()[2][4], '2');
		game.drawGrid();
		game.game();
		game.drawGrid();
		assertEquals(game.getGrid()[2][4], '2');
	}*/
	
	@Test
	public void live2CellWithTwoNeighborsShouldLive(){
		assertEquals(game.getGrid()[6][6], '2');
		game.game();
		assertEquals(game.getGrid()[6][6], '2');
	}
	
}