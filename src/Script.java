import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.Timer;
import java.util.TimerTask;

public class Script {
    public static void main(String[] args) throws Exception {
        System.out.println("Enter BPM: ");

        int x = 0;

        ArrayList<Timer> timerList = new ArrayList<Timer>();
        Scanner inputBPM = new Scanner(System.in);
        long beatGapMillis = BPMToMilliseconds(inputBPM);
        TimerTask task = new PlayTask();
        Timer timer = new Timer();
        timerList.add(timer);
        timerList.get(0).schedule(task, beatGapMillis, beatGapMillis);

        System.out.println("now playing speed: " + x);

        for (int i = 0; i < 100; i += 0) {
            Scanner inputBPM2 = new Scanner(System.in);
            long beatGapMillis2 = BPMToMilliseconds(inputBPM2);
            TimerTask task2 = new PlayTask();
            Timer timer2 = new Timer();
            timerList.get(0).cancel();
            timerList.remove(0);
            timerList.add(timer2);
            timerList.get(0).schedule(task2, beatGapMillis2, beatGapMillis2);
            x++;
            System.out.println("now playing speed: " + x);
        }

    }

    public static long BPMToMilliseconds(Scanner inputBPM) {
        int BPM = Integer.parseInt(inputBPM.nextLine());
        double beatGapSeconds = 60.0 / BPM;
        long beatGapMillis = (long) (beatGapSeconds * 1000);
        return beatGapMillis;
    }

    public static void PlayMusic(String fileLocation) {

        try {
            File soundPath = new File(fileLocation);
            if (soundPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            } else
                System.out.println("file path error");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}

class PlayTask extends TimerTask {

    String soundFilePath = "sounds/metronome_sound.wav";

    public void run() {
        Script.PlayMusic(soundFilePath);
    }

}
