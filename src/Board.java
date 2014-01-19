import java.util.ArrayList;
import java.util.HashSet;

public class Board {

	private  HashSet<Position> pegs;
	private  HashSet<Position> emptySpots;

	private static final JUMP_DIRECTION[] allDirections = {	
		JUMP_DIRECTION.WEST,
		JUMP_DIRECTION.EAST,
		JUMP_DIRECTION.NORTHWEST,
		JUMP_DIRECTION.NORTHEAST,
		JUMP_DIRECTION.SOUTHWEST,
		JUMP_DIRECTION.SOUTHEAST
		};
			
	public Board(Board b)
	{
		pegs = b.pegs;
		emptySpots = b.emptySpots;
	}

	// constructor with position of the one empty spot
	// this is how all games start out
	public Board(int x_empty, int y_empty) {
		pegs = new HashSet<Position>();
		emptySpots = new HashSet<Position>();
		for(int i=0;i < Position.SIZE;i++)
		{
			for(int j=0;j <= i; j++)
			{
				if(i != x_empty || j != y_empty )
				{
					pegs.add(new Position(i,j));
				}
				else
				{
					emptySpots.add(new Position(i,j));
				}
			}
		}
	}
	
	private void putPeg(Position p)
	{
		pegs.add(p);
		emptySpots.remove(p);
	}
	
	private void removePeg(Position p)
	{
		pegs.remove(p);
		emptySpots.add(p);	
	}

	// instance method of board
	// output is the board which results
	// from the given move
	// if the move is illegitimate return null
	public Board performMove(Move move)
	{

		if(!legitimateOnCurrentBoard(move))
		{
			return null;
		}

		Position source = move.getSource();
		Position pivot = move.getPivot(); 
		Position target = move.getTarget();
		
		Board newBoard = new Board(this);

		newBoard.removePeg(pivot);
		newBoard.removePeg(source);
		newBoard.putPeg(target);
		return newBoard;
	}
	
	public boolean legitimateOnCurrentBoard(Move m )
	{
		Position source = m.getSource(); 
		Position pivot = m.getPivot(); 
		Position target = m.getTarget();
		return pegs.contains(pivot) && pegs.contains(source) && emptySpots.contains(target);
	}
	
	ArrayList<Move> findAllMoves()
	{
		ArrayList<Move> moves = new ArrayList<Move>();
		for(Position p: emptySpots)
		{
			for(JUMP_DIRECTION dir : allDirections)
			{
				Move move = new Move(p,dir);
				if ( move.isLegitimate() && legitimateOnCurrentBoard(move))
				{
					moves.add(move);
				}
			}
		}
		return moves;
	}
	
	public void print()
	{
		for(int i=0;i < Position.SIZE;i++)
		{
			String row = "";
			for(int spaces = Position.SIZE - 1 - i; spaces > 0 ; spaces--)
			{
				row = row + " " ;

			}
			
			for(int j=0;j <= i ; j++)
			{
				if (pegs.contains(new Position(i,j)))
				{
					row = row + "O " ;
				}
				else
				{
					row = row + ". " ;
				}
			}
			
			System.out.println(row);
		}
	}
	
	public void printMove(Move m)
	{
		for(int i=0;i < Position.SIZE;i++)
		{
			String row = "";
			for(int spaces = Position.SIZE - 1 - i; spaces > 0 ; spaces--)
			{
				row = row + " " ;
			}
			
			for(int j=0;j <= i ; j++)
			{
				Position p = new Position(i,j);
				if (pegs.contains(p))
				{
					if(p.equals(m.getPivot()))
					{
					    row = row + "x " ;						
					}
					else if(p.equals(m.getSource()))
					{
					    row = row + "* " ;						
					}
					else
					{
					    row = row + "O " ;
					}
				}
				else
				{
					row = row + ". " ;
				}
			}
			
			System.out.println(row);
		}
	}
}