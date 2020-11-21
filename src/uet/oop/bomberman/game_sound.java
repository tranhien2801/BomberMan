package uet.oop.bomberman;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class game_sound {

    private static Media media;
    private static MediaPlayer mediaPlayer;
    private boolean repeat;

    public game_sound() {}

    public void sound_effect(String sound_name, double vol, boolean repeat) {
        this.repeat = repeat;
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
        /*mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.play();
            }
        });*/
        mediaPlayer.play();
    }

    public void stopMedia() {
        mediaPlayer.stop();
    }
}
