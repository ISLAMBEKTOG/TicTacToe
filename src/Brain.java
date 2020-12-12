import java.util.Vector;

public interface Brain {
	/*
	@arg board interface for querying the board
	@return int[]{row, col}
	containing the coordinates of where to place the next move.
	*/
	public int[] getNextMove(IBoard board, char playerSymbol);

















	/* ********************************************************************************
	* Beneath this line are methods only provided to help. None of them are necessary * 
	********************************************************************************* */

	/*
	@return A list of all empty tiles, stored as int[]{row,col}.
	*/
	default public Vector<int[]> getAllAvailableMoves(IBoard board) {
		int height = board.getHeight();
		int width = board.getWidth();
		Vector<int[]> emptyLocs = new Vector<int[]>();
		for (int i = 0; i < height; ++i) {
			for (int j = 0; j < width; ++j) {
				char tileSymbol = board.getTileSymbol(i,j);
				if (tileSymbol == IBoard.emptySymbol) {
					emptyLocs.add(new int[]{i,j});
				}
			}
		}
		return emptyLocs;
	}
/*
	default boolean isInBounds(IBoard board, int[] pt) {
		return pt[0] >= 0 && pt[0] < board.getHeight()
			&& pt[1] >= 0 && pt[1] < board.getWidth();
	}

	/*
	@return position => (player symbol => how many in a row)
	* /
	default public HashMap<int[], HashMap<char,int>> getRiskOfAllTiles(IBoard board) {
		Vector<int[]> moves = getAllAvailableMoves(board);
		
		int height = board.getHeight();
		int width = board.getWidth();
		int dirs[2][] = {
			{1,0}, {1,1}, {0,1}, {-1,1}, {-1,0}, {-1,-1}, {0,-1}, {1,-1}
		};
		//risks[row][col][player]
		var risks = new HashMap<int[], HashMap<char,int>>();
		for (int[] move : moves) {
			int[] pt = new int[]{move[0], move[1]};
			int count = 0;
			var countPerSymbolPerDir = new HashMap<Integer, new HashMap<Character, Integer>>();
			for (int k = 0; k < dirs.length; k++) {
				int[] dir = dirs[k];
				if (isInBounds(board, pt)) {
					char s = board.getTileSymbol(pt[0], pt[1]);
					if (s != board.emptySymbol) {
						do {
							pt = new int[]{pt[0]+dir[0], pt[1]+dir[1]};
							if (!isInBounds(board, pt)) break;
							if (board.getTileSymbol(pt[0], pt[1]) != s) break;
							++count;
						} while (true);
					}
				}
				countPerSymbolPerDir.add(k, new HashMap<>( character and count ))
			}
			countPerSymbolPerDir.add(s, count);
			//add together opposite directions
			for (int k = 0; k < dirs.length; ++k) {
				var thisdir = countPerSymbolPerDir[k];
				var oppositedir = countPerSymbolPerDir[(k + dirs.length / 2) % dirs.length];
				if (thisdir.symbol == oppositedir.symbol) {
					thisdir.count = oppositedir.count = thisdir.count + oppositedir.count;
				}
			}
			countsPerSymbol = max of all dirs across countPerSymbolPerDir 
			risks.add(new int[]{move[0], move[1]}, countPerSymbolPerDir);
		}
	}
*/
}
