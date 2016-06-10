/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmash.utils;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author a.cerella
 */
public class Sound {
   
    public static float SAMPLE_RATE = 8000f;

    public static void tone(int hz, int msecs) throws LineUnavailableException {
       tone(hz, msecs, 1.0);
    }

    public static void tone(int hz, int msecs, double vol) throws LineUnavailableException  {
      byte[] buf = new byte[1];
      AudioFormat af = 
          new AudioFormat(
              SAMPLE_RATE, // sampleRate
              8,           // sampleSizeInBits
              1,           // channels
              true,        // signed
              false);      // bigEndian
      SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
      sdl.open(af);
      sdl.start();
      for (int i=0; i < msecs*8; i++) {
        double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
        buf[0] = (byte)(Math.sin(angle) * 127.0 * vol);
        sdl.write(buf,0,1);
      }
      sdl.drain();
      sdl.stop();
      sdl.close();
    }
          
    public static void play() {
      for (int i=0;i<3; i++) {
        try {
          Sound.tone(3000,200);
          Sound.tone(3000,200);
          Thread.sleep(500);
        } catch (LineUnavailableException | InterruptedException e) {
            System.err.println("xx");  
        }
      }
    }
}
