package sudoku;

public class SudokuSolver
{
	private Board	board;
	
	
	public SudokuSolver(int[][] arr)
	{
		board = new Board(arr);
	}
	
	
	public SudokuSolver(Board board)
	{
		this.board = board;
	}
	
	
	/**
	 * Check if a number is, according to Sudoku rules, a legal candidate for
	 * the given cell.
	 * 
	 * @param row Cell's row.
	 * @param col Cell's column.
	 * @param num Number to check.
	 * @return <code>false<code> if <code>num<code> already appears in the row,
	 *         column or box the cell belongs to or <code>true<code> otherwise.
	 */
	private boolean isValidAt(int row, int col, int num)
	{
		if((checkRow(row, col, num) == false) || (checkColumn(row, col, num) == false) ||
				(checkBox(row, col, num) == false))
		{
			return false;
		}
		return true;
	}
	
	
	private boolean check(int row, int col, int num)
	{
		int r = (row / board.box_size) * board.box_size;
		int c = (col / board.box_size) * board.box_size;
		
		for(int i = 0; i < board.size; i++)
		{
			if(board.getValue(row, i) == num || board.getValue(i, col) == num ||
					board.getValue(r + (i % board.box_size), c + (i / board.box_size)) == num)
			{
				return false;
			}
		}
		return true;
	}
	
	
	private boolean checkRow(int row, int col, int num)
	{
		for(int i = 0; i < board.size; i++)
		{
			if(board.getValue(row, i) == num)
			{
				return false;
			}
		}
		return true;
	}
	
	
	private boolean checkColumn(int row, int col, int num)
	{
		for(int i = 0; i < board.size; i++)
		{
			if(board.getValue(i, col) == num)
			{
				return false;
			}
		}
		return true;
	}
	
	
	private boolean checkBox(int row, int col, int num)
	{
		int box_row = (row / board.box_size) * board.box_size;
		int box_col = (col / board.box_size) * board.box_size;
		
		for(int i = 0; i < board.size; i++)
		{
			if(board.getValue(box_row + (i % board.box_size), box_col + (i / board.box_size)) == num)
			{
				return false;
			}
		}
		return true;
	}
	
	
	public boolean solve()
	{
		return guess(0, 0);
	}
	
	
	public boolean guess(int row, int col)
	{
		int nextCol = (col + 1) % board.size;
		int nextRow = row;
		
		if(nextCol == 0)
		{
			nextRow = row + 1;
		}
		
		try
		{
			if(board.getValue(row, col) != board.EMPTY)
			{
				return guess(nextRow, nextCol);
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return true;
		}
		
		
		for(int i = 1; i <= board.size; i++)
		{
			if(isValidAt(row, col, i) == true)
			{
				board.setValue(row, col, i);
				if(guess(nextRow, nextCol))
				{
					return true;
				}
			}
		}
		board.setValue(row, col, board.EMPTY);
		return false;
	}
}
