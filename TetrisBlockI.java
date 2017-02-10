import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.awt.Color;


public class TetrisBlockI extends TetrisBlock {


	protected int rotationPos;

	protected Grid<Actor> gr;


	public TetrisBlockI() {
		super();
		rotationPos = 0;
		gr = TetrisGame.world.getGrid();

		if (gr.get(new Location(2,5)) != null
						|| gr.get(new Location(3,5)) != null) {
			javax.swing.JOptionPane.showMessageDialog(null, "Score: "
					+ TetrisGame.score, "GAME OVER!", 0);
			System.exit(0);
		}

		TetrisBug b;
		TetrisBug c;
		b = new TetrisBug(Color.blue);
		c = new TetrisBug(Color.blue);
		b.putSelfInGrid(gr, new Location(2,5));
		c.putSelfInGrid(gr, new Location(3,5));
		
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
		if (rotationPos == 0) {
			blocks.get(2).move();
			blocks.get(1).move();
			move();
			blocks.get(0).move();
		} else if (rotationPos == 1) {
			blocks.get(2).move();
			blocks.get(1).move();
			blocks.get(0).move();
			move();
		}
	}


	public boolean canMoveDown() {
		if(rotationPos == 0)
		{
			return blocks.get(2).canMove();
		}
		else
		{
			return blocks.get(2).canMove() && blocks.get(1).canMove() && blocks.get(0).canMove() && canMove();
		}
	}


	public void moveRight() {
		setDirection(90);
		for (TetrisBug tb : blocks)
			tb.setDirection(90);
		if(rotationPos == 0)
		{
			if(blocks.get(2).canMove() && blocks.get(1).canMove() && blocks.get(0).canMove() && canMove())
			{
				blocks.get(2).move();
				blocks.get(1).move();
				blocks.get(0).move();
				move();
			}
		}
		else if(rotationPos == 1)
		{
			if(blocks.get(2).canMove())
			{
				blocks.get(2).move();
				blocks.get(1).move();
				move();
				blocks.get(0).move();
			}
		}
	}

	public void moveLeft() {
		setDirection(-90);
		for (TetrisBug tb : blocks)
			tb.setDirection(-90);
		if(rotationPos == 0)
		{
			if(blocks.get(2).canMove() && blocks.get(1).canMove() && blocks.get(0).canMove() && canMove())
			{
				move();
				blocks.get(0).move();
				blocks.get(2).move();
				blocks.get(1).move();
			}
		}
		if(rotationPos == 1)
		{
			if(blocks.get(0).canMove())
			{
				blocks.get(0).move();
				move();
				blocks.get(1).move();
				blocks.get(2).move();
			}
		}
	}
	
	public void rotate() 
	{
		Location nextLoc;
		Location currLoc;
		Location nextLoc2;
		Location currLoc2;
		Location nextLoc3;
		Location currLoc3;
		if (rotationPos == 0) {
			nextLoc = new Location(getLocation().getRow() - 1, getLocation().getCol() + 1);
			nextLoc2 = new Location(getLocation().getRow() - 1, getLocation().getCol() + 2);
			nextLoc3 = new Location(getLocation().getRow() - 1, getLocation().getCol() + 3);
			if (gr.isValid(nextLoc) && gr.get(nextLoc) == null && gr.isValid(nextLoc2) && gr.get(nextLoc2) == null &&
					gr.isValid(nextLoc3) && gr.get(nextLoc3) == null) {
				moveTo(nextLoc);
				blocks.get(1).moveTo(nextLoc2);
				blocks.get(2).moveTo(nextLoc3);
				rotationPos = (rotationPos + 1) % 2;
			}
		} else if (rotationPos == 1) {
			currLoc = new Location(getLocation().getRow() + 1, getLocation().getCol() -1);
			currLoc2 = new Location(getLocation().getRow() + 2, getLocation().getCol() -1);
			currLoc3 = new Location(getLocation().getRow() + 3, getLocation().getCol() -1);
			if(gr.isValid(currLoc)&& gr.get(currLoc) == null&& gr.isValid(currLoc2) && gr.get(currLoc2) == null &&
					gr.isValid(currLoc3) && gr.get(currLoc3) == null)
			{
				moveTo(currLoc);
				blocks.get(1).moveTo(currLoc2);
				blocks.get(2).moveTo(currLoc3);
				rotationPos = 0;
			}
			
		}
	}
	
	
}