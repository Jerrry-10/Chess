package edu.cuny.csi.csc330.protochess;

/**
 * @author Kevin Reid, Jerry Aviles, & Eric Zheng.
 * @date April 8 - May 3, 2022
 */

public interface MoveableGamePiece 
{
	public abstract Color getColor();
	
	/**
	 * @param start The position of the starting square on the board.
	 * @param end	The position of the ending square on the board.
	 * @precondition  start and end are two different valid squares on the board.
	 * @precondition  No other pieces or special conditions are impeding the proposed move.
	 * @return  True if the type of piece in question can legally move from start to end.
	 * 		False otherwise. 
	 */
	public abstract boolean moveIsValid(Position start, Position end);
	
	public boolean getHasNeverMoved();
	
	public void setHasNeverMoved(boolean hasNeverMoved);
}
