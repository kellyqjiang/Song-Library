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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Optional;

//import javafx.application.Application;

public class SongLibController {
	
	@FXML         
	ListView<String> songs;
	
	private ObservableList<String> obvSongs;
	ArrayList<Song> songList;
	//private ArrayList<String> songNames;
	
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
		songs = new ListView<String>();
		songs.setEditable(true);
		songList = new ArrayList<Song>();
		//songNames = new ArrayList<String>();
		obvSongs = FXCollections.observableArrayList();
		
		songs.setItems(obvSongs);
		
		// select the first item
		songs.getSelectionModel().selectFirst();
		
		songs
		.getSelectionModel()
		.selectedIndexProperty()
		.addListener(
				(obs, oldVal, newVal) -> 
				showItemInputDialog(mainStage));
	}

	private void showItemInputDialog(Stage mainStage) {                
		String item = songs.getSelectionModel().getSelectedItem();
		int index = songs.getSelectionModel().getSelectedIndex();

		TextInputDialog dialog = new TextInputDialog(item);
		dialog.initOwner(mainStage); dialog.setTitle("List Item");
		dialog.setHeaderText("Selected Item (Index: " + index + ")");
		dialog.setContentText("Enter name: ");

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) { obvSongs.set(index, result.get()); }
	}
	
	
	public void addSong(ActionEvent e) {
		Song newSong = new Song();
		newSong.setName(name.getText());
		newSong.setArtist(artist.getText());
		newSong.setAlbum(album.getText());
		newSong.setYear(year.getText());
		
		songList.add(0, newSong);
		
		String addString = newSong.toString();
		
		String nameSong = addString.substring(0, addString.indexOf(','));
		addString = addString.substring(addString.indexOf(',')+1);
		
		String artistSong = addString.substring(0, addString.indexOf(','));
		addString = addString.substring(addString.indexOf(',')+1);
		
		String albumSong = addString.substring(0, addString.indexOf(','));
		addString = addString.substring(addString.indexOf(',')+1);
		
		String yearSong = addString.substring(0);
		
		System.out.println(nameSong);
		System.out.println(artistSong);
		System.out.println(albumSong);
		System.out.println(yearSong);
		
		songs.getItems().add(0, nameSong);
	}
	
	public void delete(ActionEvent e) {
		
	}
	
	public void edit(ActionEvent e) {
		
	}
}
