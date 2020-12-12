import java.util.HashMap;

interface IBoard {
	/*
	Contains the symbol returned by getTileSymbol() when querying empty tiles
	*/
	public static char emptySymbol = '.';

	/*
	Queries a tile for which player's symbol is on the tile at row 'row' and column 'col'.
	*/
	public char getTileSymbol(int row, int col);

	/*
	@return the board height
	*/
	public int getHeight();

	/*
	@return the board width
	*/
	public int getWidth();

	/*
	@return how many tiles in a row must the player place consecutive moves in order to win.
	*/
	public int getHowManyInARowToWin();

	/*
	@return the total number of players playing this game.
	*/
	public int getNumPlayers();

	/*
	@return an array of the symbols for all the players, in the same order as the player turn order.
	*/
	public char[] getAllPlayerSymbols();

	/*
	@return a hashmap that maps from player symbol characters to player brain names.
	*/
	public HashMap<Character, String> getBrainNameForSymbol();
}
