package GameOfLifeGUI;


import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;
import GameOfLifeData.Field;

public class MainView extends VBox {
	
	private static int windowSize = 800;
	public static int getWindowSize() {
		return windowSize;
	}

	public void setWindowSize(int windowSize) {
		MainView.windowSize = windowSize;
	}

	private ToggleButton playButton;
	protected Button stepButton;
	private Canvas canvas;

	private Affine affine;

	protected Field field;

	public MainView() {

		this.stepButton = new Button("Fortschritt");
		this.playButton = new ToggleButton("Play");
		this.stepButton.setOnAction(ActionEvent  -> {	
					field.fieldTransition();
					draw();
		});
			
		this.stepButton.setAlignment(Pos.BASELINE_CENTER);
		this.playButton.setAlignment(Pos.BASELINE_CENTER);
		
		this.canvas = new Canvas(windowSize, windowSize);
		/**
		 * set.OnMousePressed(this::drawCells) enables to apply drawCells()-method with a 
		 * single mouse click
		 */
		this.canvas.setOnMousePressed(this::drawCells);
		/**
		 * set.OnMouseDragged(this::drawCells) enables to apply drawCells()-method with
		 * by dragging a the cursor over a cell while a mouse button is pressed.
		 */
		this.canvas.setOnMouseDragged(this::drawCells);
		this.field = new Field(50, 50);
		this.affine = new Affine();
		this.affine.appendScale(800 / (float) this.field.getWidth(), 800 / (float)this.field.getHeight());

		this.getChildren().addAll(this.stepButton, this.canvas, this.playButton);
		
	}
	
		/**
		 * Applies the method fieldTransition() to the current field and then the 
		 * method draw() to redraw the field after its transformation.
		 */
	
		public void progress() {
			field.fieldTransition();
			draw();
		}
		/**
		*With left mouse-button the drawCells()-method sets the cell corresponding to position of the mouse cursor to 
		*"alive". With the right mouse-button it sets the cells' state to "dead".
		*/
		
		private void drawCells(MouseEvent event) {
			double mouseX = event.getX();
			double mouseY = event.getY();
			System.out.println(mouseX + ", " + mouseY);
			System.out.println(event.getButton());
			
			try {
				
				Point2D fieldCoordinates = this.affine.inverseTransform(mouseX, mouseY);
				System.out.println(fieldCoordinates);
				if ((fieldCoordinates.getY() >= 0) && (fieldCoordinates.getX() >= 0) && (fieldCoordinates.getY() < this.field.getHeight()) && (fieldCoordinates.getX() < this.field.getWidth())) {
			
					int index = (int) (Math.floor(fieldCoordinates.getY()) * this.field.getWidth() +Math.floor(fieldCoordinates.getX()));

	
						if (fieldCoordinates.getY() >=0  && fieldCoordinates.getX() >= 0) {
							if (event.getButton() == MouseButton.PRIMARY) {
								this.field.field.get(index).setState(true);
							}
							if (event.getButton() == MouseButton.SECONDARY) {
								this.field.field.get(index).setState(false);
							}
							
							this.draw();
						}
					
				}
				
			} catch (NonInvertibleTransformException e) {
				System.out.println("Could not invert transform");
			}
			
		}
		
		/**
		 * Fills the canvas with a background-color and for each living cell draws a rectangle
		 * in a different color, so that the result is a grid of cells, representing the states
		 * of the cells in field.
		 */
	
	public void draw() {

		GraphicsContext graphics = this.canvas.getGraphicsContext2D();
		graphics.setTransform(this.affine);
		graphics.setFill(Color.LIGHTGRAY);
		graphics.fillRect(0, 0, 400, 400);

		graphics.setFill(Color.BLACK);
		int cellIndex = 0;

		for (int i = 0; i < this.field.getHeight(); i++) {
			for (int j = 0; j < this.field.getWidth(); j++) {
				if (this.field.field.get(cellIndex).getState() == true) {
					graphics.fillRect(j, i, 1, 1);

				}
				cellIndex += 1;
			}
		}

		graphics.setStroke(Color.DARKGRAY);
		graphics.setLineWidth(0.05f);
		for (int i = 0; i <= this.field.getHeight(); i++) {
			graphics.strokeLine(0, i, this.field.getWidth(), i);
			for (int j = 0; j <= this.field.getWidth(); j++) {
				graphics.strokeLine(j, 0, j, this.field.getHeight());

			}
		}
	}
}
