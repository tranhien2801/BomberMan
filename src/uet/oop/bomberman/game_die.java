package uet.oop.bomberman;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;

public class game_die {

    private static Media media;
    private static MediaPlayer mediaPlayer;

    public static void sound_effect(String sound_name, double vol, boolean repeat) {
        media = new Media(new File(sound_name).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(vol);
        if (repeat) {
            mediaPlayer.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.seek(Duration.ZERO);
                }
            });
        }
        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.play();
            }
        });
        //mediaPlayer.play();
    }
}
