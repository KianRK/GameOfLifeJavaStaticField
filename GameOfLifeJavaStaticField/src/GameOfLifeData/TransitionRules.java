package GameOfLifeData;

public interface TransitionRules {
	
	/**
	 * abstract method of the singleTransition() that is defined in Field
	 * 
	 */
	
	public void singleTransition(Cell cell, int aliveNeighbours);
	
	/**
	 * abstract method of the fieldTransition() that is defined in Field
	 * 
	 */
	
	public void fieldTransition ();
}
