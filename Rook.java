package edu.cuny.csi.csc330.protochess;

/**
 * @author Kevin Reid, Jerry Aviles, & Eric Zheng.
 * @date April 8 - May 3, 2022
 * Class to represent a rook in chess.
 * @implements MoveableGamePiece
 * @extends ChessPiece
 */

public final class Rook extends ChessPiece implements MoveableGamePiece {

	public Rook(Color color) 
	{
		super(color);
	}

	@Override
	/**
	 * Rook can move vertically XOR horizontally on any turn.
	 * 
	 */
	public boolean moveIsValid(Position start, Position end, boolean moveIsACapture) 
	{
		return ( ( (start.getRow() - end.getRow() ) == 0 ) ^ ( (start.getColumn() - end.getColumn() ) == 0) );
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
		return "Rook [color=" + color + ", hasNeverMoved=" + hasNeverMoved + "]";
	}

}
