package edu.cuny.csi.csc330.protochess;

/**
 * @author Kevin Reid, Jerry Aviles, & Eric Zheng.
 * @date April 8 - May 3, 2022
 * Class to simulate a chessboard and play chess.
 */

import java.util.Arrays;

import edu.cuny.csi.csc330.lab1.ChessBoardPrinter;

public class PlayableChessBoard 
{
	final static int ROWS = 8;
	final static int COLUMNS = 8;

	/*Array of References to the interface type. Null references represent empty
	 * squares on the board.
	 */
	private MoveableGamePiece[][] pieces;

	private Color player;
	private boolean checkmate;
	private Position startOfMove;
	private Position endOfMove;
	private Position whiteKingsSquare;
	private Position blackKingsSquare;

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

	public MoveableGamePiece[][] getPieces() 
	{
		return pieces;
	}

	/**
	 * Method to print the pieces to the console. Useful for testing other methods.
	 * @post The elements of the array "pieces" have been interpreted as characters and printed
	 * to the console in a chessboard pattern.
	 */
	//Tested
	public void testPrintPieces() 
	{
		System.out.println();
		ChessBoardPrinter.displayCustomBoard(pieces);
	}

	@Override
	public String toString() {
		return "PlayableChessBoard [player=" + player + ", checkmate=" + checkmate + ", startOfMove=" + startOfMove
				+ ", endOfMove=" + endOfMove + ", whiteKingsSquare=" + whiteKingsSquare + ", blackKingsSquare="
				+ blackKingsSquare + "]";
	}

	/**
	 * Method to determine whether current player's king is/would be "in check" if positioned on a given square.
	 * @param boardSquare The Position whose coordinates match the array indices of the square on the board
	 * 	being evaluated.
	 * @return True if player's king would be in check if positioned on boardSquare. False otherwise.
	 */
	private boolean isInCheck(Position boardSquare)
	{
		//Can't test this method until we implement method isValidMove.

		Position attackersPosition;
		boolean inCheck = false;

		for (int row = 0; row < ROWS; row++)
		{
			for (int column = 0 ; column < COLUMNS; column++)
			{
				if (pieces[row][column] != null)
				{
					if (pieces[row][column].getColor() != player)
					{
						attackersPosition = new Position (row, column);

						if(isValidMove(attackersPosition, boardSquare) )
						{
							inCheck = true;
						}
					}

				}
			}

		}
		return inCheck;
	}

	/**
	 * Method to check whether the piece the player is trying to move is the correct color.
	 * @param boardSquare The position in the array of the piece to be moved.
	 * @return True if the piece is the player's color, false otherwise.
	 */
	//Tested successfully.
	private boolean pieceIsCorrectColor(Position boardSquare)
	{
		int row = boardSquare.getRow();
		int col = boardSquare.getColumn();

		return ( pieces[row][col].getColor() == player);
	}

	/**
	 * Method to determine whether a given move is illegal because another piece is blocking it.
	 * Does NOT test whether Castling is blocked.
	 * @param start The square (array indices) where the move begins.
	 * @param end The destination square.
	 * @return True if another piece is blocking the move. False otherwise.
	 */
	private boolean moveIsBlocked(Position startingSquare, Position destination)
	{
		//Needs finishing.

		//Freindly piece on destination square.
		if( pieces[destination.getRow()][destination.getColumn()].getColor()
				== player)
		{
			return false;
		} 

		/*
		 * Knights can jump over pieces on intermediate squares. Kings have no intermediate squares
		 * because they only move one square per turn.
		 */
		MoveableGamePiece pieceTryingToMove = pieces[startingSquare.getRow()]
				[startingSquare.getColumn()];  

		if ( (pieceTryingToMove.getClass().getSimpleName() == "Knight") ||
				(pieceTryingToMove.getClass().getSimpleName() == "King") )
		{
			return true;
		}

//		if (intermediate square is blocked by a piece of EITHER color)//Need logic to implement this!
//		{
//			return false;
//		}

		return true;
	}


	/**
	 * Method to execute a chess move. Does NOT check whether the move is legal.
	 * @param startingSquare The square (array indices) where the move begins.
	 * @param destination The position of the destination square.
	 * @post The destination square in the array "pieces" now holds a reference to the piece
	 *  that was moved. Any piece previously referenced by the destination square has therefore been dereferenced, 
	 *  thus the captured piece is no longer "on the board." The starting square now references null (an empty square).
	 */
	//Tested
	private void executeMove(Position startingSquare, Position destination)
	{
		MoveableGamePiece pieceBeingMoved = pieces[startingSquare.getRow()]
				[startingSquare.getColumn()];

		//Make destination hold a reference to the piece being moved.
		pieces[destination.getRow()][destination.getColumn()] = pieceBeingMoved;

		pieceBeingMoved.setHasNeverMoved(false);

		//Make starting square of move empty.
		pieces[startingSquare.getRow()][startingSquare.getColumn()] = null;

		//		testPrintPieces();

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
		//STUB. Not sure how this will tie into the controller for the GUI.
	}

	/**
	 * Method to determine whether the move the user has entered is legal, assuming "check" is
	 * not preventing it.
	 * @precondition This move would not violate the rules about putting a king in/getting him out
	 * "check".
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

	//Helper Methods for constructor (tested):

	private void init()
	{
		pieces = new MoveableGamePiece[ROWS][COLUMNS];
		player = Color.WHITE; //White always moves first in chess.
		checkmate = false;
		whiteKingsSquare = new Position(0, 4);
		blackKingsSquare = new Position(7, 4);
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

	//This main is for testing individual methods. Our program's real launch point is ProtoChessLauncher.main();
	public static void main(String[] args) 
	{
		PlayableChessBoard board = new PlayableChessBoard();

		//		System.out.println("Testing constructors:");
		//		for (int i = 0; i < 8; i++)
		//		{
		//			System.out.println("Row: " + i);
		//			for (int j = 0 ; j < 8; j++)
		//			{
		//				if (board.getPieces()[i][j] != null)
		//				{
		//					System.out.println(board.getPieces()[i][j].toString());
		//				}
		//			}
		//			System.out.println();
		//		}
		//		System.out.println(board);

		//		System.out.println("Testing Position class:");
				Position pos = new Position(5, 5);
		//		System.out.println(pos.getRow());
		//		System.out.println(pos.getColumn());
		//		System.out.println(pos);
		//		pos.setRow(7);
		//		pos.setColumn(0);
		//		System.out.println(pos);
				Position pos2 = new Position(7,5);
		//		System.out.println(pos.equals(pos2));

//				System.out.println("Testing Bridge Pattern:");
				MoveableGamePiece piece = new Rook(Color.WHITE);
		//		System.out.println(piece);
		//		System.out.println(piece.getHasNeverMoved());
		//		System.out.println(piece.getColor());
		//		piece.setHasNeverMoved(false);
				System.out.println(piece.moveIsValid(pos, pos2, false));
		//		System.out.println(piece);
				

		//		System.out.println("Testing pieceIsCorrectColor method:");
		//		Position whitePiece = new Position(0,0);
		//		Position blackPiece = new Position(7,0);
		//		System.out.println(board.pieceIsCorrectColor(whitePiece));
		//		System.out.println(board.pieceIsCorrectColor(blackPiece));

		//		System.out.println("Testing executeMove and testPrint methods: ");
		//		board.testPrintPieces();
		//		board.executeMove(new Position (1,3), new Position (3,3));
		//		board.executeMove(new Position (6,4), new Position (4,4));
		//		board.executeMove(new Position (0,2), new Position (4,6));
		//		board.executeMove(new Position (7,3), new Position (4,6));
		//		board.executeMove(new Position (3,3), new Position (4,4));
		

	}
}
