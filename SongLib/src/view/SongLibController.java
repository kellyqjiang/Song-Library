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
		//check if name and artist are filled out at least (album and year optional)
		//add in alphabetical order
		//popup confirm?
		
		Song newSong = new Song();
		newSong.setName(name.getText());
		newSong.setArtist(artist.getText());
		newSong.setAlbum(album.getText());
		newSong.setYear(year.getText());
		
		obvSongs.add(newSong);
	}
	
	public void delete(ActionEvent e) {
		//popup confirm
		
		Song song = songs.getSelectionModel().getSelectedItem();
		
		obvSongs.remove(song);
	}
	
	public void edit(ActionEvent e) {
		Song existingSong = songs.getSelectionModel().getSelectedItem();
		
		existingSong.setName(name.getText());
		existingSong.setArtist(artist.getText());
		existingSong.setAlbum(album.getText());
		existingSong.setYear(year.getText());
	}
}
