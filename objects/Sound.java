
package objects;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Sound {
    
    public static final AudioClip diePacman = Applet.newAudioClip(Sound.class.getResource("../audio/Pacman Dies.wav"));
    public static final AudioClip eatCherry = Applet.newAudioClip(Sound.class.getResource("../audio/Pacman Eating Cherry.wav"));
    public static final AudioClip eatGhost = Applet.newAudioClip(Sound.class.getResource("../audio/Pacman Eating Ghost.wav"));
    public static final AudioClip extraLive = Applet.newAudioClip(Sound.class.getResource("../audio/Pacman Extra Live.wav"));
    public static final AudioClip intermission = Applet.newAudioClip(Sound.class.getResource("../audio/Pacman Intermission.wav"));
    public static final AudioClip opening = Applet.newAudioClip(Sound.class.getResource("../audio/Pacman Opening Song.wav"));
    public static final AudioClip siren = Applet.newAudioClip(Sound.class.getResource("../audio/Pacman Siren.wav"));
    //public static final AudioClip wakawaka = Applet.newAudioClip(Sound.class.getResource("/audio/Pacman Waka Waka.wav"));
    public static final AudioClip wakawaka = Applet.newAudioClip(Sound.class.getResource("/audio/PacmanWakaWaka04.wav"));

}
/*
    private static Sound staticSound = new Sound();
    
    public String name;
    public AudioClip sound;
    
    private Sound(){}
    
    public Sound(String name, URL url){
        this.name = name;
        try{
            sound = Applet.newAudioClip(url);
        } catch(Exception e){System.out.println("System error: "+e);}
    }
    
    public void play(){
        new Thread(new Runnbale(){
            @Override
            public void run(){
                if(sound != null)
                    sound.play();
            }
        }).start();
    }
    
    public void loop(){
        new Thread(new Runnbale(){
            @Override
            public void run(){
                if(sound != null)
                    sound.loop();
            }
        }).start();
    }
    
    public void stop(){
        if(sound != null)
            sound.stop();
    }
    
    public static URL getURL(String fileName){
        return staticSound.getClass().getResource("../audio/"+fileName);        
    }
}
 * 
 */
