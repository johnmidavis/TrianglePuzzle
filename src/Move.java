
public class Move {
	
    // target is the space you are moving to
	private Position target;
	// pivot is the space you jump over and remove
	private Position pivot;
	// source is the space you move the peg from
	private Position source;
	
	// boolean used in isLegitimate() - does not go off the board
	// there is a separate check in the board to determine that
	// it is actually do-able on the board
	private boolean legitimate; 
	
	public Move(Position target, JUMP_DIRECTION direction)
	{
		if(!target.isValid())
		{
			legitimate = false;
			return;
		}
		
		this.target = target;
		pivot = backwardOne(target,direction);
		source = backwardOne(pivot,direction);
		
		if(!source.isValid())
		{
			legitimate = false;
			return;
		}
		
		legitimate = true;
		
		
	}
	
	public Position getTarget()
	{
		return target;
	}
	
	public Position getPivot()
	{
		return pivot;
	}
	
	public Position getSource()
	{
		return source;
	}
	
	public boolean isLegitimate()
	{
		return legitimate;
	}
	
	//
	// This diagram helps verify the 
	// validity of backwardOne
	//
    //  ---------> y
	// |      X
	// |     X X
	// |    X X X
	// |   X X X X
	// V  X X X X X
	// x
	
	// backward one from the point along the give direction
	private Position backwardOne(Position p, JUMP_DIRECTION direction)
	{
		if(direction == JUMP_DIRECTION.EAST)
		{
			return new Position(p.getX(),p.getY()-1); 	
		}
		if(direction == JUMP_DIRECTION.WEST)
		{
			return new Position(p.getX(),p.getY()+1); 	
		}
		if(direction == JUMP_DIRECTION.NORTHEAST)
		{
			return new Position(p.getX()+1,p.getY()); 	
		}
		if(direction == JUMP_DIRECTION.SOUTHWEST)
		{
			return new Position(p.getX()-1,p.getY()); 	
		}
		if(direction == JUMP_DIRECTION.NORTHWEST)
		{
			return new Position(p.getX()+1,p.getY()+1); 	
		}
		if(direction == JUMP_DIRECTION.SOUTHEAST)
		{
			return new Position(p.getX()-1,p.getY()-1); 	
		}
		return null ;
	}
}
