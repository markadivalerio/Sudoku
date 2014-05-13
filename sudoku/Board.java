package sudoku;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import static java.lang.System.*;
import static java.lang.Character.*;

public class Board
{
	public static final int	EMPTY	= 0;
	
	public int				size;
	public int				box_size;
	
	private int[][]			board;
	private boolean[][][]	cell_notes;
	
	
	// private ArrayList<Cell> board;
	
	
	public Board(int size)
	{
		this.size = size;
		this.box_size = (int)Math.sqrt(size);
		this.board = new int[size][size];
		initiateCellNotes();
	}
	
	
	public Board(int[][] board)
	{
		this(board.length);
		this.board = board;
		initiateCellNotes();
	}
	
	
	private void initiateCellNotes()
	{
		cell_notes = new boolean[size][size][size];
		for(int r = 0; r < size; r++)
		{
			for(int c = 0; c < size; c++)
			{
				clearCellNotes(r, c);
			}
		}
	}
	
	
	public void clearCellNotes(int r, int c)
	{
		for(int n = 0; n < size; n++)
		{
			cell_notes[r][c][n] = false;
		}
	}
	
	
	public void setCellNote(int r, int c, int n)
	{
		cell_notes[r][c][n] = !(cell_notes[r][c][n]);
	}
	
	
	public boolean[] getCellNotes(int r, int c)
	{
		return cell_notes[r][c];
	}
	
	
	/**
	 * Puts a number into a specific cell.
	 * 
	 * @param num Number to put into the board cell.
	 * @param row Cell's row.
	 * @param col Cell's column.
	 */
	public void setValue(int row, int col, int num)
	{
		board[row][col] = num;
		clearCellNotes(row, col);
	}
	
	
	/**
	 * Returns the number contained in a specific cell.
	 * 
	 * @param row Cell's row.
	 * @param col Cell's column.
	 * @return The number contained in the cell.
	 */
	public int getValue(int row, int col)
	{
		return board[row][col];
	}
	
}
