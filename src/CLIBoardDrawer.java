package src;
/**
 * @author Derya Aydede
 */
public class CLIBoardDrawer
{
	Game thegame;

	public CLIBoardDrawer( Game gametodraw )
	{
		thegame = gametodraw;
	}

	private char getchar( int box, int square )
	{
		if( thegame.board[box + 1][square + 1] == 1 )
			return 'x';
		if( thegame.board[box + 1][square + 1] == -1 )
			return 'o';
		return ' ';
	}

	public void drawboard()
	{
		System.out.println( "              |             |" );
		
		System.out.println( "  _" + getchar( 0, 0 ) + "_|_" + getchar( 0, 1 ) + "_|_" + getchar( 0, 2 )
				+ "_ | _" + getchar( 1, 0 ) + "_|_" + getchar( 1, 1 ) + "_|_" + getchar( 1, 2 ) + "_ |  _"
				+ getchar( 2, 0 ) + "_|_" + getchar( 2, 1 ) + "_|_" + getchar( 2, 2 ) + "_" );
		System.out.println( "  _" + getchar( 0, 3 ) + "_|_" + getchar( 0, 4 ) + "_|_" + getchar( 0, 5 )
				+ "_ | _" + getchar( 1, 3 ) + "_|_" + getchar( 1, 4 ) + "_|_" + getchar( 1, 5 ) + "_ |  _"
				+ getchar( 2, 3 ) + "_|_" + getchar( 2, 4 ) + "_|_" + getchar( 2, 5 ) + "_" );
		System.out.println( "   " + getchar( 0, 6 ) + " | " + getchar( 0, 7 ) + " | " + getchar( 0, 8 )
				+ "  |  " + getchar( 1, 6 ) + " | " + getchar( 1, 7 ) + " | " + getchar( 1, 8 ) + "  |   "
				+ getchar( 2, 6 ) + " | " + getchar( 2, 7 ) + " | " + getchar( 2, 8 ) + " " );
		
		System.out.println( "-------------------------------------------" );
		
		System.out.println( "  _" + getchar( 3, 0 ) + "_|_" + getchar( 3, 1 ) + "_|_" + getchar( 3, 2 )
				+ "_ | _" + getchar( 4, 0 ) + "_|_" + getchar( 4, 1 ) + "_|_" + getchar( 4, 2 ) + "_ |  _"
				+ getchar( 5, 0 ) + "_|_" + getchar( 5, 1 ) + "_|_" + getchar( 5, 2 ) + "_" );
		System.out.println( "  _" + getchar( 3, 3 ) + "_|_" + getchar( 3, 4 ) + "_|_" + getchar( 3, 5 )
				+ "_ | _" + getchar( 4, 3 ) + "_|_" + getchar( 4, 4 ) + "_|_" + getchar( 4, 5 ) + "_ |  _"
				+ getchar( 5, 3 ) + "_|_" + getchar( 5, 4 ) + "_|_" + getchar( 5, 5 ) + "_" );
		System.out.println( "   " + getchar( 3, 6 ) + " | " + getchar( 3, 7 ) + " | " + getchar( 3, 8 )
				+ "  |  " + getchar( 4, 6 ) + " | " + getchar( 4, 7 ) + " | " + getchar( 4, 8 ) + "  |   "
				+ getchar( 5, 6 ) + " | " + getchar( 5, 7 ) + " | " + getchar( 5, 8 ) + " " );

		System.out.println( "-------------------------------------------" );

		System.out.println( "  _" + getchar( 6, 0 ) + "_|_" + getchar( 6, 1 ) + "_|_" + getchar( 6, 2 )
				+ "_ | _" + getchar( 7, 0 ) + "_|_" + getchar( 7, 1 ) + "_|_" + getchar( 7, 2 ) + "_ |  _"
				+ getchar( 8, 0 ) + "_|_" + getchar( 8, 1 ) + "_|_" + getchar( 8, 2 ) + "_" );
		System.out.println( "  _" + getchar( 6, 3 ) + "_|_" + getchar( 6, 4 ) + "_|_" + getchar( 6, 5 )
				+ "_ | _" + getchar( 7, 3 ) + "_|_" + getchar( 7, 4 ) + "_|_" + getchar( 7, 5 ) + "_ |  _"
				+ getchar( 8, 3 ) + "_|_" + getchar( 8, 4 ) + "_|_" + getchar( 8, 5 ) + "_" );
		System.out.println( "   " + getchar( 6, 6 ) + " | " + getchar( 6, 7 ) + " | " + getchar( 6, 8 )
				+ "  |  " + getchar( 7, 6 ) + " | " + getchar( 7, 7 ) + " | " + getchar( 7, 8 ) + "  |   "
				+ getchar( 8, 6 ) + " | " + getchar( 8, 7 ) + " | " + getchar( 8, 8 ) + " " );

		System.out.println( "              |             |" );
	}
}
