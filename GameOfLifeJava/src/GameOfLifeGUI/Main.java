package GameOfLifeGUI;

	
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			MainView mainView = new MainView();
			
			Scene scene = new Scene(mainView, 800, 832);
			primaryStage.setScene(scene);
			primaryStage.show();
			Timeline timeline = new Timeline();
			KeyFrame frame1 = new KeyFrame(Duration.millis(400), ActionEvent -> mainView.progress());
			timeline.getKeyFrames().add(frame1);
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.play();
			mainView.draw();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
