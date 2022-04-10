package edu.cuny.csi.csc330.protochess;

/**
 * @author Kevin Reid, Jerry Aviles, & Eric Zheng.
 * @date April 8 - May 3, 2022
 */

public final class King extends ChessPiece implements MoveableGamePiece
{
	
	public King(Color color) 
	{
		super(color);
	}

	/**
	 * Because an instance of King cannot directly talk to the other pieces on the board, it is the CALLING CLASS'S
	 * RESPONSIBILITY to determine whether the king is in check, is trying to move into check, or is able to
	 * castle.
	 */
	@Override
	public boolean moveIsValid(Position start, Position end) 
	{
		// TODO Auto-generated method stub
		return false;
		//King can move one square in any direction.
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
		return "King [color=" + color + ", hasNeverMoved=" + hasNeverMoved + "]";
	}
	
}
