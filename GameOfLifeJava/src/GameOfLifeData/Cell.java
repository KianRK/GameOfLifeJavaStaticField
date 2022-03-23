package GameOfLifeData;

public class Cell {
	private boolean state; // True gleich lebendig und False gleich tot
	private int xCoordinate;
	private int yCoordinate;
	private int cellIndex;
	private int aliveNeighbours;
	
	public Cell(boolean state, int x, int y, int index) {
		this.state = state;
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.cellIndex = index;
	}
	
	public boolean getState() {
		return this.state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public int calcAliveNeighbours(Field f) {
		int fieldWidth = f.getWidth();
		int fieldHeight = f.getHeight();
		int neighbours=0;
		int index = this.getCellIndex();
		if (this.yCoordinate < fieldHeight) {											// Wenn Zelle nicht in oberster Reihe liegt,
			if( this.xCoordinate > 1) {													// wird Nachbar links-oben nur gezählt, wenn Zelle nicht am linken Rand liegt,
				
				neighbours += f.field.get(index-(fieldWidth+1)).getState() ? 1 : 0;
				}
			neighbours += f.field.get(index-(fieldWidth)).getState() ? 1 : 0;			// der obere Nachbar wird dann immer gezählt,
			if (this.xCoordinate < fieldWidth) {
				neighbours += f.field.get(index-(fieldWidth-1)).getState() ? 1 : 0;		// und der Nachbar oben-rechts nur, wenn Zelle nicht am rechten Rand liegt.
				}
		}
		if (this.xCoordinate > 1) {														// Der linke Nachbar wird gezählt, wenn Zelle nicht am linken Rand liegt.
			neighbours += f.field.get(index-1).getState() ? 1 : 0;
			}
		if (this.xCoordinate < fieldWidth) {											// Der rechte Nachbar wird gezählt, wenn Zelle nicht am rechten Rand liegt.
			neighbours += f.field.get(index+1).getState() ? 1 : 0;
			}
		if (this.yCoordinate > 1) {														// Wenn Zelle nicht in unterster Reihe liegt,
			if (this.xCoordinate > 1) {
				neighbours += f.field.get(index+(fieldWidth-1)).getState() ? 1 : 0;		// wird der Nachbar unten-links nur gezählt, wenn Zelle nicht am linken Rand liegt,
			}
			neighbours += f.field.get(index+(fieldWidth)).getState() ? 1 : 0;			// der untere Nachbar wird dann immer gezählt,
			if (this.xCoordinate < fieldWidth) {
				neighbours += f.field.get(index+(fieldWidth+1)).getState() ? 1 : 0;		// und der Nachbar unten-rechts nur, wenn die Zelle nicht am rechten Rand liegt.
			}
		}
		this.aliveNeighbours = neighbours;
		return neighbours;
	}
	
	public int getAliveNeighbours() {
		return this.aliveNeighbours;
	}
	
	public void setAliveNeighbours(Field field) {
		this.aliveNeighbours = this.calcAliveNeighbours(field);
	}
	
	public int getxCoordinate() {
		return xCoordinate;
	}

	public void setxCoordinate(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public int getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public int getCellIndex() {
		return cellIndex;
	}
	
}
