import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioManager {

  ////////////////////////
  // Properties
  ////////////////////////

    private Clip clip;
  ////////////////////////
  // Methods
  ////////////////////////
    // DOne
    public void playBatSound() {
        playSound("BatSoundEffects.wav");
    }
    // Done
    public void playMenuMusic() {
        playSoundLoop("mixkit-drumming-atmospheric-570.wav");
    }
    // DONE
    public void caveMove(){
        playSound("cw_sound39.wav");
    }
    // DONE
    public void triviaCorrect(){
        playSound("TriviaCorrect.wav");
    }
    // DONE
    public void triviaIncorrect(){
        playSound("TriviaIncorrect.wav");
    }
    // DONE
    public void gameEnd(){
        playSound("GameLoss.wav");
    }
    // DONE
    public void playFallingPitSound() {
        playSound("PitFalling.wav");
    }
    //DONE
    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
    public void wumpusFightMusic(){
        playSound("WumpusFightMusic.wav");
    }
    // DONE
    public void wumpusStomp(){
        playSound("WumpusWalk.wav");
    }
    public void buttonClicking(){
        playSound("ButtonClicking.wav");
    }
    // DONE
    public void wumpusRoar(){
        playSound("wumpusRoar.wav");
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

}