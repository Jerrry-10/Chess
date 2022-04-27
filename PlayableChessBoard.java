package chess;

import java.util.Scanner;

/**
 * @author Kevin Reid, Jerry Aviles, & Eric Zheng.
 * @date April 8 - May 3, 2022
 * Class to simulate a chessboard and play chess.
 */

public class PlayableChessBoard 
{
	private final static int ROWS = 8;
	private final static int COLUMNS = 8;
	private Scanner keyboard;

	/*Array of References to the interface type. Null references represent empty
	 * squares on the board.
	 */
	private MoveableGamePiece[][] pieces;

	private Color player;
	private Position startOfMove;
	private Position endOfMove;
	
	//Used for determining check and checkmate:
	private Position whiteKingsSquare;
	private Position blackKingsSquare;

	//Tested successfully.
	public PlayableChessBoard()
	{
		init();
	}

	public Color getPlayer() 
	{
		return player;
	}

	/**
	 * The master method to play a game of chess.
	 */
	
	public void play() 
	{
		
		do
		{
			//Switch player for new move.
			if(player == Color.WHITE)
			{
				player = Color.BLACK;
			}
			else if (player == Color.BLACK)
			{
				player = Color.WHITE;
			}
			else
			{
				System.err.println("Error. Invalid color of player.");
				System.exit(4);
			}
			//Replace this output with GUI output.
			System.out.println(player + "'s turn:\n");
			getNextMove();
			
			//Loop to check for an invalid Move and prompt user to try again.
//			while( (!isValidMove(startofMove, endOfMove) ) || 
//				( (isInCheck(king's square) ) && this move won't get him out) )
//				|| (isInCheck(endOfMove) 
//			{
//				//Replace this output with GUI output.
//				System.err.println("Illegal Move. Please try again.");
			//Be sure to send copy of result back to GUI.
//				getNextMove();
//			}
			
			executeMove(startOfMove, endOfMove);
			
		}while(!isCheckmate());
		
		//Replace this line with GUI output.
		System.out.println("CHECKMATE! " + player + "WINS!");
		keyboard.close();
		System.exit(0);

	}

	/**
	 * 
	 * @return A SHALLOW copy (reference) of the array pieces.
	 */
	//Tested.
	public MoveableGamePiece[][] getPieces() 
	{
		return pieces;
	}

	/**
	 * 
	 * @return A DEEP COPY of the array "pieces".
	 */
	//Tested.
	private MoveableGamePiece[][] getDeepCopyOfPieces()
	{
		MoveableGamePiece[][] copies = new MoveableGamePiece[ROWS][COLUMNS];
		
		for (int row = 0; row < ROWS; row ++)
		{
			for (int column = 0; column < COLUMNS; column ++)
			{
				copies[row][column] = pieces[row][column];
			}
		}
		
		return copies;
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
		return "PlayableChessBoard [player=" + player + ", startOfMove=" + startOfMove + ", endOfMove=" + endOfMove
				+ ", whiteKingsSquare=" + whiteKingsSquare + ", blackKingsSquare=" + blackKingsSquare + "]";
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

						if(isValidMove(attackersPosition, boardSquare, true) )
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

		//Friendly piece on destination square.
		if( (pieces[destination.getRow()][destination.getColumn()] != null) &&
				(pieces[destination.getRow()][destination.getColumn()].getColor()
				== player) )
		{
			return true; //This part has been tested.
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
			return false;
		}

		if (intermediateSquareIsBlocked(startingSquare, destination))//Need logic to implement this!
		{
			return true;
		}

		return false;
	}


	private boolean intermediateSquareIsBlocked(Position startingSquare, Position destination) 
	{
		//Step one: use math on coordinates to identify intermediate squares.
		//Step two: if all those squares are null, return false. Else, return true.
		
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Method to execute a chess move. Does NOT check whether the move is legal.
	 * @param startingSquare The square (array indices) where the move begins.
	 * @param destination The position of the destination square.
	 * @post The destination square in the array "pieces" now holds a reference to the piece
	 *  that was moved. Any piece previously referenced by the destination square has therefore been dereferenced, 
	 *  thus the captured piece is no longer "on the board." The starting square now references null (an empty square).
	 *  If a king has been moved, then whiteKingsSquare or blackKingsSquare has been updated to the destination square.
	 */
	//Tested.
	private void executeMove(Position startingSquare, Position destination)
	{
		MoveableGamePiece pieceBeingMoved = pieces[startingSquare.getRow()]
				[startingSquare.getColumn()];

		//Make destination hold a reference to the piece being moved.
		pieces[destination.getRow()][destination.getColumn()] = pieceBeingMoved;
		
		pieceBeingMoved.setHasNeverMoved(false);

		//Make starting square of move empty.
		pieces[startingSquare.getRow()][startingSquare.getColumn()] = null;

		testPrintPieces();
		
		if (pieceBeingMoved.getClass().getSimpleName().equals("King"))
		{
			if(pieceBeingMoved.getColor() == Color.WHITE)
			{
				whiteKingsSquare = destination;
//				System.out.println("King's Square: " + whiteKingsSquare);
			}
			else if (pieceBeingMoved.getColor() == Color.BLACK)
			{
				blackKingsSquare = destination;
//				System.out.println("King's Square: " + blackKingsSquare);
			}
			else
			{
				System.err.println("Error. Invalid color of pieceBeingMoved in PlayableChessBoard"
						+ ".executeMove(Position, Position).");
				System.exit(5);
			}
		}
//		else	//Test code.
//		{
//			System.out.println("Outer if statement evaluated as false.");
//		}
		

	}

	/**
	 * 
	 * @return True if the player has checkmated his/her opponent. False otherwise.
	 */
	private boolean isCheckmate()
	{
		Position enemyKingsSquare;
		Color opponent;
		
		if(player == Color.WHITE)
		{
			enemyKingsSquare = blackKingsSquare;
			opponent = Color.BLACK;
		}
		else if (player == Color.BLACK)
		{
			enemyKingsSquare = whiteKingsSquare;
			opponent = Color.WHITE;
		}
		else
		{
			enemyKingsSquare = null; //Null assignments make complier happy.
			opponent = null;
			System.err.println("Error. Invalid color of player.");
			System.exit(4);
		}
		
		return (isInCheck(enemyKingsSquare) && (cantGetOutOfCheck(enemyKingsSquare, opponent) ) );
	}

	//Needs finishing.
	private boolean cantGetOutOfCheck(Position enemyKingsSquare, Color opponent) 
	{
		MoveableGamePiece[][] dummyPieces = getDeepCopyOfPieces();
		
		for (int row = 0; row < ROWS; row ++)
		{
			for(int column = 0; column < COLUMNS; column ++)
			{
				if( (dummyPieces[row][column] != null) && (dummyPieces[row][column].getColor()
						== opponent) )
						{
					
						}
			}
		}
		//STUB
		return false;
		
	}

	/**
	 * Method to get the input from the user/GUI.
	 * @post startOfMove and endOfMove hold the coordinates of player's intended move.
	 */
	public void getNextMove()
	{
		//We can replace the keyboard input with mouse events later, but I want you to see how this works.
		
		System.out.print("Enter the row number of your piece's starting square: ");
		startOfMove.setRow(keyboard.nextInt());
		System.out.print("Enter the column: ");
		startOfMove.setColumn(keyboard.nextInt());
		System.out.print("Enter the row number of your piece's destination square: ");
		endOfMove.setRow(keyboard.nextInt());
		System.out.print("Enter the column: ");
		endOfMove.setColumn(keyboard.nextInt());
	}

	/**
	 * Method to determine whether the move the user has entered is legal, assuming "check" is
	 * not preventing it. This method cannot determine whether "check" is a factor because the
	 * method isInCheck calls it!
	 * @param startingSquare   The square (array indices) where the move begins.
	 * @param destination  The destination square.
	 * @param moveIsACapture True if the move involves capturing an enemy piece. False otherwise.
	 * @return True if the move is legal. False otherwise.
	 */
	//Calls many of the above helper methods.
	private boolean isValidMove(Position startingSquare, Position destination, boolean moveIsACapture)
	{
		
		if ( (startingSquare.getRow() < 0) || (startingSquare.getRow() > 7) || (startingSquare.getColumn()
				< 0) || (startingSquare.getColumn() > 7) || (destination.getRow() < 0) ||
				(destination.getRow() > 7) || (destination.getColumn() < 0) || (destination.getColumn() > 7) )
		{
			//Array indices out of bounds.
			return false;
		}
		
		MoveableGamePiece pieceBeingMoved = pieces[startingSquare.getRow()][startingSquare.getColumn()];
		
		/*
		 * If starting square has no piece on it, has opponent's piece on it,
		 * or matches destination square, or if another piece blocks the move:
		 */
		if ( (pieceBeingMoved == null ) || (startingSquare.equals(destination) ) ||
				(!pieceIsCorrectColor(startingSquare) ) || 
				moveIsBlocked(startingSquare, destination) )
		{
			return false;
		}
		else
		{
			//Ask the piece if it can make this move.
			return pieceBeingMoved.moveIsValid(startingSquare, destination, moveIsACapture);
		}
	}

	//Helper Methods for constructor (tested):

	private void init()
	{
		pieces = new MoveableGamePiece[ROWS][COLUMNS];
		
		//White always moves first in chess, but the color gets swapped at the start of each turn.
		player = Color.BLACK; 
		whiteKingsSquare = new Position(0, 4);
		blackKingsSquare = new Position(7, 4);
		startOfMove = new Position(0,0);
		endOfMove = new Position(0,0);
		keyboard = new Scanner(System.in);
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
//				Position pos = new Position(5,5);
		//		System.out.println(pos.getRow());
		//		System.out.println(pos.getColumn());
		//		System.out.println(pos);
		//		pos.setRow(7);
		//		pos.setColumn(0);
		//		System.out.println(pos);
//				Position pos2 = new Position(7,5);
		//		System.out.println(pos.equals(pos2));

//				System.out.println("Testing Bridge Pattern:");
//				MoveableGamePiece piece = new Pawn(Color.WHITE);
		//		System.out.println(piece);
		//		System.out.println(piece.getColor());
//				piece.setHasNeverMoved(false);
//				System.out.println("Never moved: " + piece.getHasNeverMoved());
//				System.out.println("Valid move: " + piece.moveIsValid(pos, pos2, true));
		//		System.out.println(piece);
				

//				System.out.println("Testing pieceIsCorrectColor method:");
//				Position whitePiece = new Position(0,0);
//				Position blackPiece = new Position(7,0);
//				System.out.println(board.pieceIsCorrectColor(whitePiece));
//				System.out.println(board.pieceIsCorrectColor(blackPiece));

//				System.out.println("Testing executeMove and testPrint methods: ");
//				board.testPrintPieces();
//				board.executeMove(new Position (1,3), new Position (3,3));
//				board.executeMove(new Position (6,4), new Position (4,4));
//				board.executeMove(new Position (0,2), new Position (4,6));
//				board.executeMove(new Position (7,3), new Position (4,6));
//				board.executeMove(new Position (3,3), new Position (4,4));
//				board.executeMove(new Position (1,4), new Position (3,4));
//				board.executeMove(new Position (0,4), new Position (1,4));
//				board.executeMove(new Position (7,4), new Position (6,4));
		
//				MoveableGamePiece[][] temp = board.getDeepCopyOfPieces();
//				
//				for (int i = 0; i < 8; i++)
//				{
//					System.out.println("Row: " + i);
//					for (int j = 0 ; j < 8; j++)
//					{
//						if (temp[i][j] != null)
//						{
//							System.out.println(temp[i][j].toString());
//						}
//					}
//					System.out.println();
//				}
		
//		System.out.println(board.moveIsBlocked(new Position(0,4), new Position(1,4) ) );
				
	}
}
