import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.awt.Color;


public class TetrisBlockZ extends TetrisBlock {


	protected int rotationPos;

	protected Grid<Actor> gr;


	public TetrisBlockZ() {
		super();
		rotationPos = 0;
		gr = TetrisGame.world.getGrid();

		if (gr.get(new Location(0,4)) != null
						|| gr.get(new Location(1,6)) != null) {
			javax.swing.JOptionPane.showMessageDialog(null, "Score: "
					+ TetrisGame.score, "GAME OVER!", 0);
			System.exit(0);
		}

		TetrisBug b;
		TetrisBug c;
		b = new TetrisBug(Color.blue);
		c = new TetrisBug(Color.blue);
		b.putSelfInGrid(gr, new Location(0,4));
		c.putSelfInGrid(gr, new Location(1,6));
		
		
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
		if (rotationPos == 0) 
		{
			blocks.get(2).move();
			blocks.get(1).move();
			move();
			blocks.get(0).move();
		} 
		else if (rotationPos == 1) 
		{
			blocks.get(2).move();
			move();
			blocks.get(0).move();
			blocks.get(1).move();
		}
	}


	public boolean canMoveDown() {
		if(rotationPos == 0)
		{
			return canMove() && blocks.get(2).canMove() && blocks.get(1).canMove();
		}
		else
		{
			return blocks.get(2).canMove() && blocks.get(0).canMove();
		}
	}


	public void moveRight() {
		setDirection(90);
		for (TetrisBug tb : blocks)
			tb.setDirection(90);
		if(rotationPos == 0)
		{
			if(blocks.get(2).canMove() && blocks.get(0).canMove())
			{
				blocks.get(2).move();
				blocks.get(0).move();
				move();
				blocks.get(1).move();
			}
		}
		else if(rotationPos == 1)
		{
			if(blocks.get(0).canMove() && blocks.get(1).canMove() && blocks.get(2).canMove())
			{
				blocks.get(1).move();
				blocks.get(0).move();
				move();
				blocks.get(2).move();
			}
		}
	}

	public void moveLeft() {
		setDirection(-90);
		for (TetrisBug tb : blocks)
			tb.setDirection(-90);
		if(rotationPos == 0)
		{
			if(blocks.get(1).canMove() && canMove())
			{
				blocks.get(1).move();
				move();
				blocks.get(0).move();
				blocks.get(2).move();
			}
		}
		else if(rotationPos == 1)
		{
			if(canMove() && blocks.get(2).canMove() && blocks.get(1).canMove())
			{
				move();
				blocks.get(0).move();
				blocks.get(1).move();
				blocks.get(2).move();
			}
		}
	}
	
	public void rotate() 
	{
		Location nextLoc;
		Location nextLoc2;
		Location nextLoc3;
		if (rotationPos == 0) {
			nextLoc = new Location(getLocation().getRow(), getLocation().getCol() + 1);
			nextLoc2 = new Location(getLocation().getRow() - 1, getLocation().getCol() + 1);
			nextLoc3 = new Location(getLocation().getRow() + 1, getLocation().getCol());
			if (gr.isValid(nextLoc2) && gr.get(nextLoc2) == null && gr.isValid(nextLoc3) && gr.get(nextLoc3) == null) 
			{
				blocks.get(1).moveTo(nextLoc2);
				blocks.get(2).moveTo(nextLoc3);
				blocks.get(0).moveTo(nextLoc);
				rotationPos = 1;
			}
		} 
		else if(rotationPos == 1)
		{
			nextLoc = new Location(getLocation().getRow() - 1,getLocation().getCol());
			nextLoc2 = new Location(getLocation().getRow() - 1,getLocation().getCol() - 1);
			nextLoc3 = new Location(getLocation().getRow(),getLocation().getCol() + 1);
			if(gr.isValid(nextLoc) && gr.get(nextLoc) == null && gr.isValid(nextLoc2) && gr.get(nextLoc2) == null)
			{
				blocks.get(1).moveTo(nextLoc2);
				blocks.get(0).moveTo(nextLoc);
				blocks.get(2).moveTo(nextLoc3);
				rotationPos = 0;
			}
			
		}
	}
	
	
}