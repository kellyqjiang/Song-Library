package view;

public class Song {
	
	String name;
	String artist;
	String album;
	String year;
	
	public Song() {
		name = "";
		artist = "";
		album = "";
		year = "";
	}
	
	public Song(String getName, String getArtist, String getAlbum, String getYear) {
		name = getName;
		artist = getArtist;
		album = getAlbum;
		year = getYear;
	}
	
	public String getName() {
		return name;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setName(String gotName) {
		name = gotName;
	}
	
	public void setArtist(String gotArtist) {
		artist = gotArtist;
	}
	
	public void setAlbum(String gotAlbum) {
		album = gotAlbum;
	}
	
	public void setYear(String gotYear) {
		year = gotYear;
	}
	
	public String toString() {
		return name;
	}
}
