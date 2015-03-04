import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class MovingNQueens {

	public MovingNQueens() {

	}

	/**
	 * Given the coordinates of N chess queens placed on an infinite chess
	 * board. Goal is to make some valid minimized moves to rearrange the queens
	 * so that in the end no two queens threaten each other. (i.e. no two queens
	 * share the same row, column or diagonal)
	 * 
	 * @param queenRows
	 *            row (Y) coordinates of the queens.
	 * @param queenCols
	 *            column (X) coordinates of the queens.
	 * @return list of moves as a String[]. We can use at most 8*N moves. Each
	 *         element of the return should be formatted as "INDEX ROW COL".
	 *         Corresponding move will move queen INDEX (0-based) from its
	 *         current position to a cell with coordinates (ROW,COL). Each move
	 *         must be valid chess queen move: the queen can be moved any number
	 *         of unoccupied squares in a straight line vertically, horizontally
	 *         or diagonally.
	 * 
	 *         Time limit is 10 seconds
	 * 
	 */
	public static String[] rearrange(int[] queenRows, int[] queenCols) {

		System.err.println("queenRows :" + queenRows.length + " queenCols :"
				+ queenCols.length);

		int noOfQueens = queenRows.length;

		Map<Integer, SortedSet<Integer>> rowMap = new HashMap<Integer, SortedSet<Integer>>();
		Map<Integer, SortedSet<Integer>> colMap = new HashMap<Integer, SortedSet<Integer>>();

		List<String> results = new ArrayList<String>(noOfQueens);
		Set<Integer> movingIndex = new HashSet<Integer>(noOfQueens);
		// SortedSet<Integer> queenPositions = new TreeSet<Integer>();
		int preferred_row = -1;
		int preferred_col = -1;

		for (int i = 0; i < noOfQueens; i++) {
			SortedSet<Integer> colOccupiedInTheRow = rowMap.get(queenRows[i]);
			SortedSet<Integer> rowOccupiedInTheCol = colMap.get(queenCols[i]);

			if (colOccupiedInTheRow == null) {
				colOccupiedInTheRow = new TreeSet<Integer>();
			}

			if (rowOccupiedInTheCol == null) {
				rowOccupiedInTheCol = new TreeSet<Integer>();
			}

			// check whether multiple queens in the row.
			int row = queenRows[i];
			int col = queenCols[i];
			boolean isMoved = false;
			if (colOccupiedInTheRow.size() > 0) {
				// need to move this queen.

				while (true) {
					row--;
					if (rowMap.get(row) == null && colMap.get(col) == null) {
						isMoved = true;
						results.add(i + " " + row + " " + col);

						break;
					} else if (rowMap.get(row) != null) {
						col--;
					} else if (colMap.get(col) != null) {
						break;
					}
				}
				if (!isMoved) {
					movingIndex.add(i);
				}

			}

			if (rowOccupiedInTheCol.size() > 0) {
				// need to move this queen
				while (true && !isMoved) {
					col--;
					if (colMap.get(col) == null && rowMap.get(row) == null) {
						isMoved = true;
						results.add(i + " " + row + " " + col);
						break;
					} else {
						break;
					}
				}
				if (!isMoved) {
					movingIndex.add(i);
				}

			}

			if (isMoved) {
				colOccupiedInTheRow = rowMap.get(row);
				rowOccupiedInTheCol = colMap.get(col);

				if (colOccupiedInTheRow == null) {
					colOccupiedInTheRow = new TreeSet<Integer>();
				}

				if (rowOccupiedInTheCol == null) {
					rowOccupiedInTheCol = new TreeSet<Integer>();
				}
			}
			rowOccupiedInTheCol.add(row);
			colOccupiedInTheRow.add(col);

			if (isMoved) {
				rowMap.put(row, colOccupiedInTheRow);
				colMap.put(col, rowOccupiedInTheCol);
			} else {
				rowMap.put(queenRows[i], colOccupiedInTheRow);
				colMap.put(queenCols[i], rowOccupiedInTheCol);
			}

		}
		System.err.println("rowMap :" + rowMap);
		System.err.println("colMap :" + colMap);
		System.err.println("moving index :" + movingIndex);

		// now arrange queens which needs to be moved.
		// for (int i = 0; i < movingIndex.size(); i++) {
		// int row = queenRows[i];
		// int col = queenCols[i];
		// // get all the cols for this row
		//
		// }

		System.err.println(results);
		return results.toArray(new String[0]);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = null;
		// System.err.println("Hello Deb");
		try {
			scanner = new Scanner(System.in);
			int noOfQueens = scanner.nextInt();

			int[] queenRows = new int[noOfQueens];

			for (int i = 0; i < noOfQueens; i++) {
				queenRows[i] = scanner.nextInt();
			}

			noOfQueens = scanner.nextInt();
			int[] queenCols = new int[noOfQueens];

			for (int i = 0; i < noOfQueens; i++) {
				queenCols[i] = scanner.nextInt();
			}

			String[] ret = rearrange(queenRows, queenCols);

			System.out.println(ret.length);

			for (int i = 0; i < ret.length; i++) {
				System.out.println(ret[i]);
			}

			System.out.flush();

		} catch (Throwable th) {
			System.err.println("ERR :" + th.getMessage());
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}

	}

}