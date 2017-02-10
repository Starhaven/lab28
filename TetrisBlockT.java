import info.gridworld.actor.*;
import info.gridworld.grid.*;
import java.awt.Color;


public class TetrisBlockT extends TetrisBlock {


	protected int rotationPos;

	protected Grid<Actor> gr;


	public TetrisBlockT() {
		super();
		rotationPos = 0;
		gr = TetrisGame.world.getGrid();

		if (gr.get(new Location(0,6)) != null || gr.get(new Location(0,4)) != null) 
		{
			javax.swing.JOptionPane.showMessageDialog(null, "Score: " + TetrisGame.score, "GAME OVER!", 0);
			System.exit(0);
		}

		TetrisBug d;
		TetrisBug f;
		
		d = new TetrisBug(Color.blue);
		f = new TetrisBug(Color.blue);
		
		d.putSelfInGrid(gr, new Location(0, 6));
		f.putSelfInGrid(gr, new Location(0, 4));
		
		blocks.add(d);
		blocks.add(f);

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
		if(rotationPos == 0)
		{
			move();
			blocks.get(2).move();
			blocks.get(1).move();
			blocks.get(0).move();
		}
		if(rotationPos == 1)
		{
			move();
			blocks.get(2).move();
			blocks.get(0).move();
			blocks.get(1).move();
		}
		if(rotationPos == 2)
		{
			blocks.get(2).move();
			blocks.get(1).move();
			blocks.get(0).move();
			move();
		}
		if(rotationPos == 3)
		{
			blocks.get(1).move();
			blocks.get(0).move();
			blocks.get(2).move();
			move();
		}
	}


	public boolean canMoveDown() {
		if(rotationPos == 0)
		{
			return canMove() && blocks.get(2).canMove() && blocks.get(1).canMove();
		}
		if(rotationPos == 1)
		{
			return blocks.get(2).canMove() && canMove();
		}
		if(rotationPos == 2)
		{
			return blocks.get(1).canMove() && blocks.get(2).canMove() && blocks.get(0).canMove();
		}
		else
		{
			return blocks.get(1).canMove() && canMove();
		}
	}


	public void moveRight() {
		setDirection(90);
		for (TetrisBug tb : blocks)
			tb.setDirection(90);
		if(rotationPos == 0)
		{
			if(blocks.get(1).canMove() && canMove())
			{
				blocks.get(1).move();
				blocks.get(0).move();
				move();
				blocks.get(2).move();
			}
		}
		if(rotationPos == 1)
		{
			if(blocks.get(1).canMove() && blocks.get(2).canMove() && canMove())
			{
				blocks.get(1).move();
				move();
				blocks.get(2).move();
				blocks.get(0).move();
			}
		}
		if(rotationPos == 2)
		{
			if(blocks.get(2).canMove() && canMove())
			{
				move();
				blocks.get(2).move();
				blocks.get(0).move();
				blocks.get(1).move();
			}
		}
		if(rotationPos == 3)
		{
			if(blocks.get(2).canMove() && blocks.get(0).canMove() && blocks.get(1).canMove())
			{
				blocks.get(2).move();
				blocks.get(0).move();
				blocks.get(1).move();
				move();
			}
		}
	}

	public void moveLeft() {
		setDirection(-90);
		for (TetrisBug tb : blocks)
			tb.setDirection(-90);
		if(rotationPos == 0)
		{
			if(blocks.get(2).canMove() && canMove())
			{
				move();
				blocks.get(2).move();
				blocks.get(0).move();
				blocks.get(1).move();
			}
		}
		if(rotationPos == 1)
		{
			if(blocks.get(2).canMove() && blocks.get(0).canMove() && blocks.get(1).canMove())
			{
				blocks.get(2).move();
				blocks.get(0).move();
				blocks.get(1).move();
				move();
			}
		}
		if(rotationPos == 2)
		{
			if(blocks.get(1).canMove() && canMove())
			{
				blocks.get(1).move();
				move();
				blocks.get(0).move();
				blocks.get(2).move();
			}
		}
		if(rotationPos == 3)
		{
			if(blocks.get(1).canMove() && blocks.get(2).canMove() && canMove())
			{
				blocks.get(2).move();
				move();
				blocks.get(0).move();
				blocks.get(1).move();
			}
		}
	}
	
	public void rotate() 
	{
		Location nextLoc;
		Location nextLoc2;
		Location nextLoc3;
		if (rotationPos == 0) 
		{
			nextLoc = new Location(getLocation().getRow(), getLocation().getCol() - 1);
			nextLoc2 = new Location(getLocation().getRow() - 1, getLocation().getCol() - 1);
			nextLoc3 = new Location(getLocation().getRow() + 1, getLocation().getCol() - 1);
			if(gr.isValid(nextLoc3) && gr.get(nextLoc3) == null && gr.isValid(nextLoc) && gr.get(nextLoc) == null) 
			{
				blocks.get(2).moveTo(nextLoc3);
				blocks.get(1).moveTo(nextLoc2);
				blocks.get(0).moveTo(nextLoc);
				rotationPos = 1;
			}
		} 
		else if(rotationPos == 1)
		{
			nextLoc = new Location(getLocation().getRow() + 1,getLocation().getCol());
			nextLoc2 = new Location(getLocation().getRow() + 1,getLocation().getCol() - 1);
			nextLoc3 = new Location(getLocation().getRow() + 1,getLocation().getCol() + 1);
			if(gr.isValid(nextLoc3) && gr.get(nextLoc3) == null && gr.isValid(nextLoc) && gr.get(nextLoc) == null)
			{
				blocks.get(2).moveTo(nextLoc3);
				blocks.get(1).moveTo(nextLoc2);
				blocks.get(0).moveTo(nextLoc);
				rotationPos = 2;
			}
			
			
		}
		else if(rotationPos == 2)
		{
			nextLoc = new Location(getLocation().getRow(),getLocation().getCol() + 1);
			nextLoc2 = new Location(getLocation().getRow() + 1,getLocation().getCol() + 1);
			nextLoc3 = new Location(getLocation().getRow() - 1,getLocation().getCol() + 1);
			if(gr.isValid(nextLoc3) && gr.get(nextLoc3) == null && gr.isValid(nextLoc) && gr.get(nextLoc) == null)
			{
				blocks.get(2).moveTo(nextLoc3);
				blocks.get(1).moveTo(nextLoc2);
				blocks.get(0).moveTo(nextLoc);
				rotationPos = 3;
			}
		}
		else if(rotationPos == 3)
		{
			nextLoc = new Location(getLocation().getRow() - 1,getLocation().getCol());
			nextLoc2 = new Location(getLocation().getRow() - 1,getLocation().getCol() + 1);
			nextLoc3 = new Location(getLocation().getRow() - 1,getLocation().getCol() - 1);
			if(gr.isValid(nextLoc3) && gr.get(nextLoc3) == null && gr.isValid(nextLoc) && gr.get(nextLoc) == null)
			{
				blocks.get(2).moveTo(nextLoc3);
				blocks.get(1).moveTo(nextLoc2);
				blocks.get(0).moveTo(nextLoc);
				rotationPos = 0;
			}
		}
	}
	
	
}
	
