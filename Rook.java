package edu.cuny.csi.csc330.protochess;

/**
 * @author Kevin Reid, Jerry Aviles, & Eric Zheng.
 * @date April 8 - May 3, 2022
 */

public final class Rook extends ChessPiece implements MoveableGamePiece {

	public Rook(Color color) 
	{
		super(color);
	}

	@Override
	public boolean moveIsValid(Position start, Position end) 
	{
		// TODO Auto-generated method stub
		return false;
		//Can move vertically XOR horizontally on any turn.
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
