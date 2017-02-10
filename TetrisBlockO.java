import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.awt.Color;


public class TetrisBlockO extends TetrisBlock {


	protected int rotationPos;

	protected Grid<Actor> gr;


	public TetrisBlockO() {
		super();
		rotationPos = 0;
		gr = TetrisGame.world.getGrid();

		if (gr.get(new Location(0,6)) != null
						|| gr.get(new Location(1,6)) != null) {
			javax.swing.JOptionPane.showMessageDialog(null, "Score: "
					+ TetrisGame.score, "GAME OVER!", 0);
			System.exit(0);
		}

		TetrisBug b;
		TetrisBug c;
		b = new TetrisBug(Color.blue);
		c = new TetrisBug(Color.blue);
		
		b.putSelfInGrid(gr, new Location(0, 6));
		c.putSelfInGrid(gr, new Location(1, 6));

		blocks.add(b);
		blocks.add(c);

	}


	public void act() {
		setDirection(180);
		for (TetrisBug tb : blocks)
			tb.setDirection(180);
		if (canMoveDown())
			moveDown();
		else if (!TetrisGame.currentBlock.canMoveDown())
			TetrisGame.nextTetrisBlock();
	}


	public void moveDown() {
			move();
			blocks.get(2).move();
			blocks.get(1).move();
			blocks.get(0).move();
	}


	public boolean canMoveDown() {
		return canMove() && blocks.get(2).canMove();
	}


	public void moveRight() {
		setDirection(90);
		for (TetrisBug tb : blocks)
			tb.setDirection(90);
		if(blocks.get(1).canMove() && blocks.get(2).canMove())
		{
			blocks.get(2).move();
			blocks.get(1).move();
			blocks.get(0).move();
			move();
		}
	}

	public void moveLeft() {
		setDirection(-90);
		for (TetrisBug tb : blocks)
			tb.setDirection(-90);
		if(canMove() && blocks.get(0).canMove())
		{
			move();
			blocks.get(0).move();
			blocks.get(2).move();
			blocks.get(1).move();
		}
	}
	
	public void rotate() 
	{
		
	}
	
	
}
	
