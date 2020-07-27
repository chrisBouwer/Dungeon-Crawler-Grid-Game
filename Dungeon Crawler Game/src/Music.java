//Christiaan Bouwer
import java.applet.Applet;
import java.applet.AudioClip;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {
	
	private Clip clip;

	
	public static void main(String[] args){
		
	}
	
	public void music(String musicFile){
		// 
		try{
			clip = AudioSystem.getClip();
			clip.open(bais);
			//track = Applet.newAudioClip(Music.class.getResource(musicFile));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void loop(){
		try{
			new Thread(){
				public void run(){
					track.loop();
				}
			}.start();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
}
