import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioManager {
    private Clip clip;

    public void playBatSound() {
        playSound("sounds/bat_sound.wav");
    }

    public void playMenuMusic() {
        playSoundLoop("sounds/menu_music.wav");
    }

    public void playFallingPitSound() {
        playSound("sounds/falling_pit_sound.wav");
    }

    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void setVolume(float volume) {
        if (clip != null) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(20f * (float) Math.log10(volume));
        }
    }

    private void playSound(String filePath) {
        try {
            stopMusic();
            clip = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void playSoundLoop(String filePath) {
        try {
            stopMusic();
            clip = AudioSystem.getClip();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AudioManager audioManager = new AudioManager();
        audioManager.playMenuMusic();
        // To stop the music
        // audioManager.stopMusic();
        // To set the volume
        // audioManager.setVolume(0.5f);
    }
}