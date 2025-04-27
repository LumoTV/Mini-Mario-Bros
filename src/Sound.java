import javax.sound.sampled.*;
import java.io.*;

public class Sound {

    private Clip clip;

    public void setFile(String path) {
        try {
            // Charge le fichier audio depuis le chemin spécifié
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(path));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);  // Joue en boucle
    }

    public void stop() {
        clip.stop();
    }
}
