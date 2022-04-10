package edu.cuny.csi.csc330.protochess;

/**
 * @author Kevin Reid, Jerry Aviles, & Eric Zheng.
 * @date April 8 - May 3, 2022
 */

public final class Queen extends ChessPiece implements MoveableGamePiece {

	Bishop bishopPowers;
	Rook rookPowers;
	
	public Queen(Color color) 
	{
		super(color);
		bishopPowers = new Bishop(color);
		rookPowers = new Rook(color);
	}

	@Override
	public boolean moveIsValid(Position start, Position end) 
	{
		//On any given turn, a queen can move like a bishop XOR move like a rook.
		return (bishopPowers.moveIsValid(start, end) ^ rookPowers.moveIsValid(start, end) );
		
	}
		
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bishopPowers == null) ? 0 : bishopPowers.hashCode());
		result = prime * result + ((rookPowers == null) ? 0 : rookPowers.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Queen other = (Queen) obj;
		if (bishopPowers == null) {
			if (other.bishopPowers != null)
				return false;
		} else if (!bishopPowers.equals(other.bishopPowers))
			return false;
		if (rookPowers == null) {
			if (other.rookPowers != null)
				return false;
		} else if (!rookPowers.equals(other.rookPowers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Queen [bishopPowers=" + bishopPowers + ", rookPowers=" + rookPowers + ", color=" + color
				+ ", hasNeverMoved=" + hasNeverMoved + "]";
	}

}
