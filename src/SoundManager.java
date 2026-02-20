import javax.sound.sampled.*;
import java.io.File;

//hangfajl lejatszasa
public class SoundManager {

    private static Clip backgroundClip;

    public static void play(String fileName) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(fileName)));
            clip.start();
        } catch (Exception e) {
            System.out.println("Sound error");
        }
    }

    // háttérzene
    public static void playBackground(String fileName) {
        try {
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(
                    AudioSystem.getAudioInputStream(new File(fileName))
            );
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("Background sound error");
        }
    }

    public static void stopBackground() {
        if (backgroundClip != null) {
            backgroundClip.stop();
        }
    }
}
