import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Soundtrack {
	MediaPlayer mediaPlayer;
	List<String> mediaFeed;
	double volume = 1.0;
	IntegerProperty currentTrack;
	
	public Soundtrack(InputStream playListStream) throws FileNotFoundException{
		this.mediaFeed = new ArrayList<String>();
		Scanner scanner = new Scanner(playListStream);
		while(scanner.hasNextLine()) {
			mediaFeed.add(scanner.nextLine());
			
		};
		scanner.close();
		
		currentTrack = new SimpleIntegerProperty(0);
		playCurrentTrack();
	}
	
	public void nextTrack () {
		currentTrack.add(1).divide(mediaFeed.size());
		playCurrentTrack();
	}
	
	public void previousTrack() {
		currentTrack.subtract(1).add(mediaFeed.size()).divide(mediaFeed.size());
		playCurrentTrack();
	}
	
	public void setVolume(double volume) {
		this.volume = volume;
		mediaPlayer.setVolume(volume);
	}
	
	public void playCurrentTrack() {
		if (mediaPlayer != null)
			mediaPlayer.dispose();
		Media track = new Media(getClass().getResource(mediaFeed.get(currentTrack.get())).toString());
		mediaPlayer = new MediaPlayer(track);
		mediaPlayer.setVolume(volume);
		mediaPlayer.setOnEndOfMedia(() -> {
			this.nextTrack();
		});
		mediaPlayer.play();
	}
	
	public String trackName() {
		String name = mediaFeed.get(currentTrack.get());
		name = name.replaceAll(".*[/\\\\]", "");
		return name;
	}
}