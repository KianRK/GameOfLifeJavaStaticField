package GameOfLifeData;

import java.util.ArrayList;

public class Field implements TransitionRules{

	private int width;
	private int height;
	private int ratio = 50;
	
	public ArrayList<Cell> field = new ArrayList<Cell>();
		
	public Field() {

	}

	public Field(int x, int y) {
		this.width = x;
		this.height = y;
		fillField();
	}

	public void fillField() {													// Füllt das Array "field" dieser Klasse mit Zellen zufälligen Zustandes.
		boolean cellState;
		for (int i = 0; i < this.height; i++) {
			for ( int j = 0; j < this.width; j++) {
				double decideState = Math.random();								// Die Math.random() Methode liefert einen pseudozufälligen double-Wert 
				if (decideState <= (double) ratio / 100) {											// zwischen 0 und 1 und weist diesen der Variable "decideState".
					cellState = true;											// Durch die if-else Abfrage und das Festlegen des Schwellenwerts bei 0.5
				} else {														// erfolgt in diesem Fall eine 50 : 50 Verteilung von toten und lebenden
					cellState = false;											// Zellen.
				}
				field.add(new Cell(cellState, j+1, this.height - i, (i*this.width)+j));	//Da die "oberste" Zeile zuerst initialisiert wird, ergibt sich die Y-Koordinate
			}																				//der Zelle rückwärtszählend beginnend mit der höchsten.
		}
		for (Cell cell : field) {
			cell.setAliveNeighbours(this);
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int xSize) {
		this.width = xSize;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int ySize) {
		this.height = ySize;
	}
	
	public int getSize() {
		return this.width * this.height;
	}
	
	public void printField() {													// Gibt Zelle mit Index 0 in der oberen-linken Ecke des Feldes aus
		int k = 0;																// und setzt Ausgabe dann von links nach rechts und von oben nach unten fort.
		for (int i = 0; i < this.height; i++) {									// index k wird bei jeder Ausgabe um 1 inkrementiert und stellt somit den Index der jeweiligen
			for (int j = 0; j < this.width; j++) {								// Zelle dar. Könnte auch mit (i*this.xSize)+j berechnet werden, wofür aus Gründer der Lesbarkeit
				System.out.print(this.field.get(k).getState() + " ");			// verzichtet wird.
//				System.out.print(this.field.get((k).getyCoordinate() + " ");
//				System.out.print(this.field.get(k).getxCoordinate() + " ");
//				System.out.print(k + " ");
				k +=1;
			}
			System.out.println();
		} 
	}

	@Override
	public void singleTransition(Cell cell, int aliveNeighbours)  {
		
		if (cell.getState() == true) {
			if(aliveNeighbours < 2 || aliveNeighbours > 3) {
				cell.setState(false);
			}
		}
		if (cell.getState() == false) {
			if( aliveNeighbours == 3) {
				cell.setState(true);
			}
		}
	}

	@Override
	public void fieldTransition(){
		for (Cell cell : field) {
			cell.calcAliveNeighbours(this);
		}
		for (Cell cell : this.field) {
			this.singleTransition(cell, cell.getAliveNeighbours());
		}
		for (Cell cell : this.field) {
			cell.setAliveNeighbours(this);
		}
	}
}
