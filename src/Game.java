package src;
/**
 * @author Derya Aydede
 */
public class Game
{
	/*
	 * Regarding terminology, a box is one of the larger tiles in the meta tictactoe board and a square is one
	 * of the tiles in a box the board is the entire thing.
	 */

	// IMPLEMENT TIE CHECKER

	// array containing data for the entire board, board[2][4] is fourth square in second box, 1 for x, -1 y
	public byte[][] board = new byte[10][10];
	// array containing move data for the entire game, [movenumber][0 for box value or 1 for square value]
	private byte[][] movedata = new byte[82][2];
	// array containing data for who has won in the individual boxes, 1 for x -1 for y
	private byte[] windata = new byte[10];
	// byte value, 1 if x has won the game and -1 if y has won the game, 2 if it is a tie otherwise 0
	private byte win;

	// byte that is 1 when it is x's move, -1 when it is y's move
	private byte player;
	// boolean that is true when the next player can play in any box
	private boolean anybox;
	// byte that indicates which box is to be played in
	private byte boxtoplay;
	// byte value, number of moves that have been made plus one
	private byte movenumber;

	/**
	 * constructor resets everything so game begins with an empty board, x to play
	 */
	public Game()
	{
		// clears board and windata
		for( byte i = 0; i < 10; i++ )
		{
			windata[i] = 0;
			for( byte j = 0; j < 10; j++ )
				board[i][j] = 0;
		}

		// clears movedata
		for( byte i = 1; i < 82; i++ )
		{
			movedata[i][0] = 0;
			movedata[i][1] = 0;
		}

		// resets all other game variables
		player = 1;
		anybox = true;
		movenumber = 1;
		boxtoplay = 0;
	}

	/**
	 * use this to undo the previous move, will change board value appropriately, recheck for wins, and edit
	 * movenumber boxtoplay and player variables appropriately
	 * @return - true if previous move is undone, false if undoing is not allowed
	 */
	public boolean undo()
	{
		// checks if undoing is allowed
		if( movenumber == 1 )
			return false;

		// removes previous move from board
		board[movedata[movenumber - 1][0]][movedata[movenumber - 1][1]] = 0;
		// edits move number
		movenumber--;
		// edits player
		player *= -1;
		// checks for wins
		checkwin( movedata[movenumber - 1][0] );

		// sets anybox and boxtoplay to correct value
		if( movenumber == 1 )
		{
			anybox = true;
		}
		else
		{
			boxtoplay = movedata[movenumber - 1][1];
			anybox = checkfull( boxtoplay );
		}

		return true;
	}

	/**
	 * use this to redo the next move that was undone.
	 * @return - true if there is a move to be redone, false otherwise
	 */
	public boolean redo()
	{
		// checks if there is a move to be redone
		if( movedata[movenumber][0] == 0 )
			return false;

		// adds move to board
		board[movedata[movenumber][0]][movedata[movenumber][1]] = player;
		// checks for wins
		checkwin( movedata[movenumber][0] );
		// sets boxtoplay to the correct box
		boxtoplay = movedata[movenumber][1];
		// edits move number
		movenumber++;
		// edits player
		player *= -1;
		// checks if box to be played in is full, anybox gets true if it is
		anybox = checkfull( boxtoplay );

		return true;
	}

	/**
	 * use this to make a move, will change board value appropriately, check for wins, and edit movenumber
	 * boxtoplay and player variables appropriately
	 * @param box - the box the next player wants to play in
	 * @param square - the square the next player wants to play in
	 * @return - true if move is made, false if move is invalid
	 */
	public boolean move( byte box, byte square )
	{
		// checks if move is allowed
		// input must refer to a square on the board
		if( box > 9 || box < 1 || square > 9 || square < 1 )
			return false;
		// can only play in boxtoplay if anybox is not true
		if( ( anybox == false ) && ( box != boxtoplay ) )
			return false;
		// cannot play after the game is over
		if( win != 0 )
			return false;
		// cannot play in a square that has already been played in
		if( board[box][square] != 0 )
			return false;

		// adds move to movedata
		movedata[movenumber][0] = box;
		movedata[movenumber][1] = square;

		// clears all movedata beyond this one (in case more than one move was undone prior)
		for( byte i = (byte) ( movenumber + 1 ); movedata[i][0] != 0; i++ )
		{
			movedata[i][0] = 0;
			movedata[i][1] = 0;
		}

		// adds move to board
		board[box][square] = player;
		// edits move number
		movenumber++;
		// edits player
		player *= -1;
		// checks for wins
		checkwin( box );
		// sets boxtoplay to the correct box
		boxtoplay = square;
		// checks if box to be played in is full, anybox gets true if it is
		anybox = checkfull( boxtoplay );

		return true;
	}

	/**
	 * resets the game
	 */
	public void resetgame()
	{
		// resets game variables
		movenumber = 1;
		anybox = true;
		player = 1;

		//

		// clears board
		for( byte i = 0; i < 9; i++ )
			for( byte j = 0; j < 9; j++ )
				board[i][j] = 0;

		// clears windata
		for( byte i = 0; i < 9; i++ )
			windata[i] = 0;
	}

	/**
	 * checks if the given box is full or not
	 * @param boxtocheck - box to be checked
	 * @return - true if it is full, false otherwise
	 */
	private boolean checkfull( byte boxtocheck )
	{
		for( byte i = 0; i < 9; i++ )
			if( board[boxtocheck][i] == 0 )
				return false;
		return true;
	}

	/*
	 * checks absolutely for wins, currently unneeded
	 */
	/*
	 * private void checkwin() { for( byte i = 0; i < 9; i++ ) checkwinbox( i ); checkwinboard(); }
	 */

	/**
	 * checks for wins, given boxchanged value which must be the only box played in since this method was last
	 * called (otherwise call with no parameters)
	 * @param boxchanged - only board played in since last checkwin call
	 */
	private void checkwin( byte boxchanged )
	{
		checkwinbox( boxchanged );
		checkwinboard();
	}

	/**
	 * given a box to check, will change windata appropriately if there is a winner
	 * @param boxtocheck - the box to be checked for victors
	 */
	private void checkwinbox( byte boxtocheck )
	{
		if( ( board[boxtocheck][1] == board[boxtocheck][2] )
				&& ( board[boxtocheck][2] == board[boxtocheck][0] ) )
			if( windata[boxtocheck] == 0 )
				windata[boxtocheck] = board[boxtocheck][1];
		if( ( board[boxtocheck][3] == board[boxtocheck][4] )
				&& ( board[boxtocheck][4] == board[boxtocheck][5] ) )
			if( windata[boxtocheck] == 0 )
				windata[boxtocheck] = board[boxtocheck][4];
		if( ( board[boxtocheck][6] == board[boxtocheck][7] )
				&& ( board[boxtocheck][7] == board[boxtocheck][8] ) )
			if( windata[boxtocheck] == 0 )
				windata[boxtocheck] = board[boxtocheck][7];
		if( ( board[boxtocheck][0] == board[boxtocheck][3] )
				&& ( board[boxtocheck][3] == board[boxtocheck][6] ) )
			if( windata[boxtocheck] == 0 )
				windata[boxtocheck] = board[boxtocheck][3];
		if( ( board[boxtocheck][1] == board[boxtocheck][4] )
				&& ( board[boxtocheck][4] == board[boxtocheck][7] ) )
			if( windata[boxtocheck] == 0 )
				windata[boxtocheck] = board[boxtocheck][4];
		if( ( board[boxtocheck][2] == board[boxtocheck][5] )
				&& ( board[boxtocheck][5] == board[boxtocheck][8] ) )
			if( windata[boxtocheck] == 0 )
				windata[boxtocheck] = board[boxtocheck][5];
		if( ( board[boxtocheck][0] == board[boxtocheck][4] )
				&& ( board[boxtocheck][4] == board[boxtocheck][8] ) )
			if( windata[boxtocheck] == 0 )
				windata[boxtocheck] = board[boxtocheck][4];
		if( ( board[boxtocheck][2] == board[boxtocheck][4] )
				&& ( board[boxtocheck][4] == board[boxtocheck][6] ) )
			if( windata[boxtocheck] == 0 )
				windata[boxtocheck] = board[boxtocheck][4];
	}

	/**
	 * based on windata, determines if anyone has won the game
	 */
	private void checkwinboard()
	{
		if( ( windata[1] == windata[2] ) && ( windata[2] == windata[0] ) )
			if( win == 0 )
				win = windata[1];
		if( ( windata[3] == windata[4] ) && ( windata[4] == windata[5] ) )
			if( win == 0 )
				win = windata[4];
		if( ( windata[6] == windata[7] ) && ( windata[7] == windata[8] ) )
			if( win == 0 )
				win = windata[7];
		if( ( windata[0] == windata[3] ) && ( windata[3] == windata[6] ) )
			if( win == 0 )
				win = windata[3];
		if( ( windata[1] == windata[4] ) && ( windata[4] == windata[7] ) )
			if( win == 0 )
				win = windata[4];
		if( ( windata[2] == windata[5] ) && ( windata[5] == windata[8] ) )
			if( win == 0 )
				win = windata[5];
		if( ( windata[0] == windata[4] ) && ( windata[4] == windata[8] ) )
			if( win == 0 )
				win = windata[4];
		if( ( windata[2] == windata[4] ) && ( windata[4] == windata[6] ) )
			if( win == 0 )
				win = windata[4];
	}

	/**
	 * @return - true if x to play, false if o to play
	 */
	public boolean xToPlay()
	{
		if( player == 1 )
			return true;
		return false;
	}

	/**
	 * @return - box to be played in
	 */
	public byte boxtoplay()
	{
		return boxtoplay;
	}

	public boolean anybox()
	{
		return anybox;
	}

	/**
	 * @return - box that last move was played in
	 */
	public byte latestmovebox()
	{
		return movedata[movenumber - 1][0];
	}

	/**
	 * @return - square that last move was played in
	 */
	public byte latestmovesquare()
	{
		return movedata[movenumber - 1][1];
	}

	/**
	 * @return - array with move data for the entire game, [movenumber][0 for box value or 1 for square value]
	 */
	public byte[][] movedata()
	{
		return movedata;
	}

	/**
	 * @return - array containing data for who has won in the individual boxes, 1 for x -1 for y
	 */
	public byte[] windata()
	{
		return windata;
	}

	/**
	 * @return - byte value, 1 if x has won the game and -1 if y has won the game, 2 if it is a tie, otherwise
	 *         0
	 */
	public byte win()
	{
		return win;
	}

	/**
	 * @return - move number, first move returns 1 etc
	 */
	public byte movenumber()
	{
		return movenumber;
	}
}
