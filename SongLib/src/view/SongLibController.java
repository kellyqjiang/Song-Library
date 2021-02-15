package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

//import javafx.application.Application;

public class SongLibController {
	
	@FXML         
	ListView<Song> songs;
	
	private ObservableList<Song> obvSongs;
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField artist;
	
	@FXML
	private TextField year;
	
	@FXML
	private TextField album;

	public void start(Stage mainStage) {                
		// create an ObservableList 
		// from an ArrayList  
		obvSongs = FXCollections.observableArrayList();
		
		//songs.setItems(obvSongs);
	}
	
	public void add(ActionEvent e) {
		Song add = new Song();
		add.setName(name.getText());
		add.setArtist(artist.getText());
		String nameSong = add.toString().substring(0, add.toString().indexOf(','));
		String artistSong = add.toString().substring(0, add.toString().indexOf(','));
		String nameSong = add.toString().substring(0, add.toString().indexOf(','));
		String nameSong = add.toString().substring(0, add.toString().indexOf(','));
		obvSongs.add(add);
	}
	
	public void delete(ActionEvent e) {
		
	}
	
	public void edit(ActionEvent e) {
		
	}
}
