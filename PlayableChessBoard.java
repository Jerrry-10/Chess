package edu.cuny.csi.csc330.protochess;

/**
 * @author Kevin Reid, Jerry Aviles, & Eric Zheng.
 * @date April 8 - May 3, 2022
 */

import java.util.Arrays;

public class PlayableChessBoard 
{
	final static int ROWS = 8;
	final static int COLUMNS = 8;
	
	private MoveableGamePiece[][] pieces;
	private Color player;
	private boolean checkmate;
	private Position startOfMove;
	private Position endOfMove;
	
	//I'm not sure whether we're going to need this next variable, since we'll have the method:
	private boolean validMove;
	
	//Tested successfully.
	public PlayableChessBoard()
	{
		init();
	}
	
	/**
	 * The master method to play a game of chess.
	 */
	public void play() 
	{
		// TODO Auto-generated method stub
		
	}
	
	//For testing purposes only.
	public MoveableGamePiece[][] getPieces() 
	{
		return pieces;
	}

	@Override
	public String toString() {
		return "PlayableChessBoard [pieces=" + Arrays.toString(pieces) + ", player=" + player + ", checkmate="
				+ checkmate + ", start=" + startOfMove + ", end=" + endOfMove + ", validMove=" + validMove + "]";
	}
	
	/**
	 * Method to determine whether current player's king is/would be "in check" if positioned on a given square.
	 * @param boardSquare The Position whose coordinates match the array indices of the square on the board
	 * 	being evaluated.
	 * @return True if player's king would be in check if positioned on boardSquare. False otherwise.
	 */
	private boolean isInCheck(Position boardSquare)
	{
		//STUB
		return false;
	}
	
	/**
	 * Method to check whether the piece the player is trying to move is the correct color.
	 * @param boardSquare The position in the array of the piece to be moved.
	 * @return True if the piece is the player's color, false otherwise.
	 */
	private boolean pieceIsCorrectColor(Position boardSquare)
	{
		int row = boardSquare.getRow();
		int col = boardSquare.getColumn();
		
		return ( pieces[row][col].getColor() == player) ? true : false;
	}
	
	/**
	 * Method to determine whether a given move is illegal because another piece is blocking it.
	 * @param start The square (array indices) where the move begins.
	 * @param end The destination square.
	 * @return True if another piece is blocking the move. False otherwise.
	 */
	private boolean moveIsBlocked(Position startingSquare, Position destination)
	{
		//STUB
		return false;
	}
	
	/**
	 * Method to execute a valid chess move.
	 *@precondition isValidMove has returned true for the move. 
	 * @param startingSquare 	The square (array indices) where the move begins.
	 * @param destination 	The destination square.
	 */
	private void executeMove(Position startingSquare, Position destination)
	{
		//STUB
	}
	
	/**
	 * 
	 * @return True if the player has checkmated his/her opponent. False otherwise.
	 */
	private boolean isCheckmate()
	{
		//STUB 
		return false;
	}
	
	/**
	 * Method to get the input from the user.
	 */
	private void getNextMove()
	{
		//STUB. Not sure how this will tie into the GUI.
	}

	/**
	 * Method to determine whether the move the user has entered is legal
	 * @param startingSquare   The square (array indices) where the move begins.
	 * @param destination  The destination square.
	 * @return True if the move is legal. False otherwise.
	 */
	//Calls many of the above helper methods.
	private boolean isValidMove(Position startingSquare, Position destination)
	{
		//STUB
		return true;
	}
	
	//Helper methods for constructor (tested successfully):
	
	private void init()
	{
		pieces = new MoveableGamePiece[ROWS][COLUMNS];
		player = Color.WHITE; //White always moves first in chess.
		checkmate = false;
		validMove = true;
		setUpBoard();
	}

	private void setUpBoard() 
	{
		for (int row = 0; row < ROWS; row++ )
		{
			switch (row)
			{
			case 0:
				setUpSeniorPieces(Color.WHITE, row);
				break;
			case 1:
				for (int col = 0; col <COLUMNS; col++)
				{
					pieces[row][col] = new Pawn(Color.WHITE);
				}
				break;
				
				//Do nothing for these cases. Rows 2-5 (3-6 on an actual chessboard) should reference null.
			case 2:
			case 3:
			case 4:
			case 5:
				break;
			case 6:
				for (int col = 0; col <COLUMNS; col++)
				{
					pieces[row][col] = new Pawn(Color.BLACK);
				}
				break;
			case 7:
				setUpSeniorPieces(Color.BLACK, row);
				break;
			default:
				System.err.println("Invalid value of row in PlayableChessBoard.setUpBoard().");
				System.exit(1);
	
			}
			
		}
		
	}

	private void setUpSeniorPieces(Color color, int row) 
	{
		for (int col = 0; col < COLUMNS; col++)
		{
			switch(col)
			{
			case 0: //columns A and H
			case 7:
				pieces[row][col] = new Rook(color);
				break;
			case 1: //Columns B and G
			case 6:
				pieces[row][col] = new Knight(color);
				break;
			case 2:	//Columns C and F
			case 5:
				pieces[row][col] = new Bishop(color);
				break;
			case 3:	//Column D
				pieces[row][col] = new Queen(color);
				break;
			case 4:	//Column E
				pieces[row][col] = new King(color);
				break;
			default:
				System.err.println("Invalid value of col in PlayableChessBoard.setUpSeniorPieces().");
				System.exit(2);
				
			}
		}
	}

}
