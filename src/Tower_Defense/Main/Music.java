package Tower_Defense.Main;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music {
    static Clip clip;
    AudioInputStream audioInputStream;

    public Music(String filePath)
    {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(0);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stop() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        clip.stop();
        clip.close();
    }

    public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.start();
    }
}
