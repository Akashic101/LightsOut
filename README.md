# LightsOut

This is the game [Lights Out](https://www.logicgamesonline.com/lightsout/) programmed in JavaFX. If you want to know how to play the game you can look at the explanation [here](https://www.logicgamesonline.com/lightsout/tutorial.html) 

***

<h1>How To Play</h1>
  
First start at the top left in the second row. Look at the button on top of the first one in the second row, i.e. from 6 to 1 or from 7 to 2. The same goes with the 9-field. where for example you start at 10 to 1 and 11 to 2. If the field is black, click on it. If it's white, ignore it. Now go down one row and do the same again. Continue like this until you are at the last row. Then check the pattern against the list here, where . is white and * is black.  This will tell you which lights to click in the top row. When you chase the lights this time, the bottom row will end up white, just like the rest of the puzzle.

Bottom Row | Top Row
------------ | -------------
". * . * ." | ". + . . +
". . * * *" | ". . . + .
". * * . *" | "+ . . . . 
"* . * * ." | ". . . + +
"* * . * *" | ". . + . .
"* * * . ." | ". + . . .

For a 9x9 puzzle, you will either end up with a dark bottom row, or 

"* . * . * . * .*"

as your pattern. If you have the latter, click the top left button, and chase it to turn out all the lights.
