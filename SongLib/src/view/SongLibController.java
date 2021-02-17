  
package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter; 
import java.io.IOException;
import java.io.PrintWriter;

public class SongLibController {
	
	//null error???
	//file reading/writing
	
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
	
	@FXML
	private Label addError;

	public void start(Stage mainStage) throws IOException {                
		// create an ObservableList 
		obvSongs = FXCollections.observableArrayList();
		
		loadPrevSongs();
		
		songs.setItems(obvSongs);
		
		// select the first item
		songs.getSelectionModel().select(0);
		Song curr = songs.getSelectionModel().getSelectedItem();
		if(curr != null) {
			name.setText(curr.getName());
			artist.setText(curr.getArtist());
			album.setText(curr.getAlbum());
			year.setText(curr.getYear());
		}
		
		
		songs.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> showDetails());
		
		mainStage.setOnCloseRequest(event -> {
			try {
				FileWriter file = new FileWriter("songsList.txt");
				for(int i = 0; i < obvSongs.size(); i++) {
					Song currSong = obvSongs.get(i);
					String songInfo = currSong.getName() + "," + currSong.getArtist() + "," + currSong.getAlbum() + "," + currSong.getYear() + "\n";
					file.write(songInfo);
				}
				file.close();
				
			} catch (Exception e) {
			
			}
		});
	}

	private void showDetails() {                
		try {
		Song song = songs.getSelectionModel().getSelectedItem();
		
		name.setText(song.getName());
		artist.setText(song.getArtist());
		album.setText(song.getAlbum());
		year.setText(song.getYear());
		} catch(java.lang.NullPointerException exception) {

		}
	}
	
	
	public void addSong(ActionEvent e) {
		//get song details from user
		Song newSong = new Song();
		newSong.setName(name.getText());
		newSong.setArtist(artist.getText());
		newSong.setAlbum(album.getText());
		newSong.setYear(year.getText());
		
		boolean isCopy = false; //if we find a copy, this will turn true
		
		for(int i = 0; i < obvSongs.size(); i++) { //loops into obvSongs to check if the song exists or not
			if(obvSongs.get(i).getName().equals(name.getText()) && obvSongs.get(i).getArtist().equals(artist.getText())) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Add Error");
				alert.setHeaderText(null);
				alert.setContentText("A song with this name and artist already exists!");
				alert.showAndWait();
				isCopy = true;
				break;
			}
		}
		
		//checking if at least song name and artist are filled out
		if (name.getText().equals("") || artist.getText().equals("")) {  
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Add Error");
			alert.setHeaderText(null);
			alert.setContentText("Please add at least a song name and artist");
			alert.showAndWait();
		}else if(!isCopy){ //if it's a copy, we will not go into this if statement, if it's a new song, we confirm with user
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Add Confirmation");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to add this song?");
			Optional<ButtonType> result = alert.showAndWait();
	        if (result.get() == ButtonType.OK) {
	        	obvSongs.add(newSong);
	        	
	        	//sorting alphabetically
	    		Comparator<Song> comparator = Comparator.comparing(Song::getName);
	    		obvSongs.sort(comparator);
	    		
	    		int selectIndex = obvSongs.indexOf(newSong);
	    		songs.getSelectionModel().select(selectIndex);
	        }
		}
		
	}
	
	public void delete(ActionEvent e) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Confirmation");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete this song?");
		Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        	Song song = songs.getSelectionModel().getSelectedItem();
        	int selectIndex = obvSongs.indexOf(song);
    		obvSongs.remove(song);
    		
    		//select next, prev, or none depending on list after deleting the song
    		if(obvSongs.isEmpty()) {
    			name.setText("");
    			artist.setText("");
    			album.setText("");
    			year.setText("");
    		} else if(obvSongs.get(selectIndex) != null) {
    			songs.getSelectionModel().select(selectIndex);
    			Song curr = songs.getSelectionModel().getSelectedItem();
    			name.setText(curr.getName());
    			artist.setText(curr.getArtist());
    			album.setText(curr.getAlbum());
    			year.setText(curr.getYear());
    		} else if(obvSongs.get(selectIndex-1) != null) {
    			songs.getSelectionModel().select(selectIndex-1);
    		}
    		
        }
	}
	
	public void edit(ActionEvent e) {
		
		boolean isCopy = false;
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Edit Confirmation");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to edit this song?");
		Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        	for(int i = 0; i < obvSongs.size(); i++) { //loops into obvSongs to check if the song exists or not
    			if(obvSongs.get(i).getName().equals(name.getText()) && obvSongs.get(i).getArtist().equals(artist.getText())) {
    				Alert alert2 = new Alert(AlertType.ERROR);
    				alert2.setTitle("Edit Error");
    				alert2.setHeaderText(null);
    				alert2.setContentText("A song with this name and artist already exists!");
    				alert2.showAndWait();
    				isCopy = true;
    			} else {
    				break;
    			}
    		}
        	
        	if(!isCopy) {
        		Song existingSong = songs.getSelectionModel().getSelectedItem();
        		
        		existingSong.setName(name.getText());
        		existingSong.setArtist(artist.getText());
        		existingSong.setAlbum(album.getText());
        		existingSong.setYear(year.getText());
        		
        		//edits listview song to match once edited
        		int selectedSong = obvSongs.indexOf(existingSong);
        		songs.getItems().set(selectedSong, existingSong);
        	}
        }
	}
	
	public void loadPrevSongs() throws IOException {
		int chara;
		
		FileReader file = new FileReader("songsList.txt");
		
		String info = "";
		
		if((chara = file.read()) == -1) { //checks if it's the end of file automatically
			return;
		} else {
			info += (char)chara;
		}
		
		try {
			while((chara = file.read()) != -1) {
				if((char)chara == '\n') { //end of one song's detail in file
					String songName = info.substring(0, info.indexOf(","));
					info = info.substring(info.indexOf(",")+1);
					
					String songArtist = info.substring(0, info.indexOf(","));
					info = info.substring(info.indexOf(",")+1);
					
					String songAlbum = info.substring(0, info.indexOf(","));
					info = info.substring(info.indexOf(",")+1);
					
					String songYear = info;
					
					Song addSong = new Song(songName, songArtist, songAlbum, songYear);

					obvSongs.add(addSong);
					
					Comparator<Song> comparator = Comparator.comparing(Song::getName);
		    		obvSongs.sort(comparator);
		    		
		    		int selectIndex = obvSongs.indexOf(addSong);
		    		songs.getSelectionModel().select(selectIndex);
		    		
		    		info = "";
				} else {
					info += (char)chara;
				}
			}
		} catch (IOException e) {
			
		}
		
		file.close();
	}
}
