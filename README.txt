i. Two Player Game of Life 

Give a general description of your implementation.
My implementation has been done in the same class TwoPlayerGameOfLife where it complies with all the requirements and rules of the assignement. It has 15 classes to implement the game correctly. The method that executes the game is in the main which the order in which it executes the game is:
I create the object game calling the method readDimensions which is in charge of giving the number of rows and colmns of the array.
I read from the second to the end of the file in the method readArray.
I draw the result of the array full of the characters found in the file.
I call the method game that implements the algorithm of the game and stope when the variable endOfGame is set to true, either because one of the two player has 0 living cells, or both of them have.

A. How did you decide to store the grid (i.e. what data type for the grid, what data type for
the cells)?
In order to save the grid from the file I have done three steps:
Create a character grid with the dimensions found in the first line of the grid with all the cells being dead as a default, so each cells is a character containing either a dot, 1 or 2.
Read the rest of the file of generation0 (every line except for the first one).
Pass the information of the file into the new grid created.

B. Walk through the methods called during the generation of a new state.
The methods in charge of creating a new state are called all from the method game which is in charge of implementing the algorithm. 
First, it creates two arrays which are:
Neighbours which is a 2 element array that contains in the first element the total number of live cells and the second one the player that has the mayority in case of being a dead cell and having exactly three neighbours.
For each element of the cell we call the method neighbours. This method looks for the eight adjacent cells of each position (taking into account if they are inside the grid) and returns the number of neighbour cells adjacent and the player that has the majority of cells.
Then it continues in the method game applying the four rules specified in the game:
	Any live cell with fewer than two live neighbors of either player dies.
 	Any live cell with two or three live neighbors of either player lives.
 	Any live cell with more than three neighbors of either player dies.
 	Any dead cell with exactly three neighbors becomes a live cell owned by the player who 	has the	majority of live neighboring cells.
      4.   Once it has covered the whole array, it transfers this information to the old grid.
      5.   Shows the number of the generation and the live cells of each player and then draws the 	updated grid.   

ii. What specific problems or challenges did you have implementing your solution? For example,
was there a particular requirement that you had difficulty implementing? Or perhaps
there was a particularly nasty bug in your implementation you had problems tracking down.
The main problem was the little knowledge of implementing any game in Java. My first barrier was knowing how to read a .txt file in eclipse. 
Additionally, checking if the format of the file was the one required was a great challenge, as well as knowing if each neighbour was inside or not of the grid.
Finally, it was hard to implement the junit based on the requirements.

iii. Were there any requirements that were not implemented or not implemented properly in
your solution? If so, please elaborate.
No, everything has been done.

iv. Were there any requirements that were vague or open to interpretation that you had to make
a decision on how to implement? How did you elect to interpret them?
At first, as the game had the name of a two player game, my first interpretation was that each player was able to put either a 1, in the case Player 1 or a  in the case of the player 2 in each turn. Therefore, at first I implemented the options of each player being able to put a 1 or 2 in the position they want with the help of a keyboard. However, after reading about the game in depth I realized that every file with have the same result at the end which was confusing, but I implemented this solution.

v. How would you rate the complexity of this MP on a scale of 1 to 10 where 1 is very easy
and 10 is very difficult. Why did you give this rating?
I would give it a 9, because it was really hard for me writing my first program in Java. Additionally, I had to implement other programs such as the bowling kata in order to fully understand this assignement, which required many hours of dedication.
