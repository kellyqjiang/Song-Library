package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
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
	ListView<Song> songs;
	
	private ObservableList<Song> obvSongs;
	//ArrayList<Song> songList;
	//private ArrayList<String> songNames;
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField artist;
	
	@FXML
	private TextField year;
	
	@FXML
	private TextField album;
	
	@FXML
	private Label addError;

	public void start(Stage mainStage) {                
		// create an ObservableList 
		obvSongs = FXCollections.observableArrayList();
		//songList = new ArrayList<Song>();
		
		songs.setItems(obvSongs);
		
		// select the first item
		songs.getSelectionModel().selectFirst();
		
		songs
		.getSelectionModel()
		.selectedIndexProperty()
		.addListener(
				(obs, oldVal, newVal) -> 
				showDetails()); //instead of showItemInputDialog, show details
	}

	private void showDetails() {                
		Song song = songs.getSelectionModel().getSelectedItem();
		
		name.setText(song.getName());
		artist.setText(song.getArtist());
		album.setText(song.getAlbum());
		year.setText(song.getYear());
	}
	
	
	public void addSong(ActionEvent e) {
		//check if name and artist already exist
		//popup confirm?
		
		//get song details from user
		Song newSong = new Song();
		newSong.setName(name.getText());
		newSong.setArtist(artist.getText());
		newSong.setAlbum(album.getText());
		newSong.setYear(year.getText());
		
		//checking if at least song name and artist are filled out
		if (name.getText().equals("") || artist.getText().equals("")) {  
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Add Error");
			alert.setHeaderText(null);
			alert.setContentText("Please add at least a song name and artist");
			alert.showAndWait();
		}else {
			obvSongs.add(newSong);
		}
		
		//sorting alphabetically
		Comparator<Song> comparator = Comparator.comparing(Song::getName);
		obvSongs.sort(comparator);
	}
	
	public void delete(ActionEvent e) {
		//popup confirm
		 
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Confirmation");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete this song?");
		Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        	Song song = songs.getSelectionModel().getSelectedItem();
    		obvSongs.remove(song);
        }
	}
	
	public void edit(ActionEvent e) {
		//change listview automatically
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Edit Confirmation");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to edit this song?");
		Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        	Song existingSong = songs.getSelectionModel().getSelectedItem();
    		
    		existingSong.setName(name.getText());
    		existingSong.setArtist(artist.getText());
    		existingSong.setAlbum(album.getText());
    		existingSong.setYear(year.getText());
        }
	}
}
