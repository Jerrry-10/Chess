package edu.cuny.csi.csc330.protochess;

/**
 * @author Kevin Reid, Jerry Aviles, & Eric Zheng.
 * @date April 8 - May 3, 2022
 */

public final class Pawn extends ChessPiece implements MoveableGamePiece 
{
	
	public Pawn(Color color) 
	{
		super(color);
	}

	@Override
	public boolean moveIsValid(Position start, Position end) 
	{
		// TODO Auto-generated method stub
		return false;
		/*
		 * When NOT capturing, a pawn can only move one square directly forward.
		 * It also has the option to move two squares forward ON ITS FIRST MOVE ONLY.
		 */
		
	}

	/**
	 * 
	 * @param start The starting square of the proposed move.
	 * @param end   The ending square of the proposed move.
	 * @return True if the pawn can make the move, false otherwise.
	 */
	public boolean captureIsValid(Position start, Position end)
	{
		//STUB
		return true;
		//A pawn moves one square diagonally forwards when capturing.
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pawn [color=" + color + ", hasNeverMoved=" + hasNeverMoved + "]";
	}
	
	
}
