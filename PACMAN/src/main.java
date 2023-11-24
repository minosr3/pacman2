import java.io.IOException;

import javax.swing.JFrame;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class main extends JFrame{

	public main(){
		
		
		add (new Tablero());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(860,700);
		setLocationRelativeTo(null);
		setTitle ("----- PACMAN VERSIÓN: 0.1------");
		setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new main();

       libreriaAudio audio = new libreriaAudio();
       audio.musica();
  

	}

}
