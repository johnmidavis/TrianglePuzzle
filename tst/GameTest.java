import static org.junit.Assert.*;

import java.util.ArrayList;


import org.junit.Test;


public class GameTest {

	@Test
	public void test() {

		Board b;
		Move m;

		b = new Board(3,1);
		b.print();
		
	    m = new Move(new Position(3,1),JUMP_DIRECTION.SOUTHWEST);
		b.printMove(m);
		b = b.performMove(m);
		b.print();
	    m = new Move(new Position(1,1),JUMP_DIRECTION.NORTHWEST);
		b.printMove(m);
		b = b.performMove(m);
		b.print();
		
		ArrayList<Move> moves = b.findAllMoves();
		for(Move m1: moves)
		{
			b.printMove(m1);
		}
	}

}
