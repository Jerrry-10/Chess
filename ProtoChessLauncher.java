package edu.cuny.csi.csc330.protochess;

/**
 * @author Kevin Reid, Jerry Aviles, & Eric Zheng.
 * @date April 8 - May 3, 2022
 */

public class ProtoChessLauncher 
{

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
		
//		System.out.println("Testing Position class:");
//		Position pos = new Position(4, 3);
//		System.out.println(pos.getRow());
//		System.out.println(pos.getColumn());
//		System.out.println(pos);
//		pos.setRow(7);
//		pos.setColumn(0);
//		System.out.println(pos);
//		Position pos2 = new Position(3, 9);
//		System.out.println(pos.equals(pos2));
		
//		System.out.println("Testing Bridge Pattern:");
//		MoveableGamePiece piece = new Pawn(Color.BLACK);
//		System.out.println(piece);
//		System.out.println(piece.getHasNeverMoved());
//		System.out.println(piece.getColor());
//		piece.setHasNeverMoved(false);
//		System.out.println(piece);
		
		board.play();

	}

}
