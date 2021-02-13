package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SongLib extends Application {

	@Override
	public void start(Stage primaryStage) {
		try { 
			FXMLLoader loader = new FXMLLoader();
		
			loader.setLocation(getClass().getResource("SongLib.fxml"));
			AnchorPane root = (AnchorPane)loader.load();

			SongLibController listController = loader.getController();
			listController.start(primaryStage);

			Scene scene = new Scene(root, 650, 500);
			primaryStage.setScene(scene);
			primaryStage.show(); 
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

}
