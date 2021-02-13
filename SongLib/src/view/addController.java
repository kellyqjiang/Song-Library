package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class addController {

	@FXML
	private AnchorPane rootPane;
	
	//@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
	@FXML
	private void loadSecond(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("SongLib.fxml"));
		rootPane.getChildren().setAll(pane);
	}
}
