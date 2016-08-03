A simple java command line application that plays nine by nine tic tac toe. This is a surprisingly complex game, and definitely nontrivial to solve. To play the game, let us first define the outer 3x3 grid of smaller tic tac toe boards to be comprised of "boxes". Then let us call the squares inside the smaller tic tac toe boards "squares". The first player moves in whichever box they want, and whichever square within it. After that, players must move in the box corresponding to the square their opponent just moved in. Thus if the first player moved in the top left square of the center box, the second player must also move in the top left box. The second player could then move in the top left square of the top left box to force their opponent to also move in the top left box. If a player is forced to move in a completely filled box, they can move anywhere they want. A box is won when a player has 3 in a row vertically, horizontally, or diagonally inside it, just as in a regular tic tac toe game. As you might expect, the game is won when a player has won 3 boxes in a row on the grid of boxes.

#### To run:
- `java -jar MetaTicTacToe.jar`