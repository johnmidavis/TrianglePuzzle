public class Position
{
	
	static final int SIZE = 5 ;

	private int x;
	private int y;
	
	public Position(int x, int y)
	{
		this.x = x ;
		this.y = y ;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public boolean equals(Object obj)
	{
        if (obj == null)
        {
            return false;
        }
        if (obj == this)
        {
            return true;
        }
        if (!(obj instanceof Position))
        {
            return false;
        }
        
        Position rhs = (Position)obj;
             		
        if( rhs.x == this.x && rhs.y == this.y)
        {
        	return true;
        }
    	return false;
	}
	
	public int hashCode()
	{
		// no boards large than 100 for now please
		return 100 * x + y;
	}
	
	boolean isValid()
	{
		return (x >= 0 && x < SIZE && y <= x);
	}
	
	
    //  ---------> y
	// |      X
	// |     X X
	// |    X X X
	// |   X X X X
	// V  X X X X X
	// x

}
