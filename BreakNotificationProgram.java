import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import sun.audio.AudioPlayer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioStream;

public class BreakTimeNotification {

	public static void main(String[] args) {
		// run timer main program
		 System.out.println("Start "+LocalDateTime.now());
	    taskScedularRun(100L,10000L);

	}

	public static boolean infoBox(String infoMessage) throws ArrayIndexOutOfBoundsException {
		boolean dontShow = false;
		Object[] options = {"20 min","30 min", "60 min", "Stop timer"};

        int x = JOptionPane.showOptionDialog(null, infoMessage,
                "Pause timer for selected duration or Stop Timer!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);

        if(x == -1){
        	//if x has been clicked thread will continue to run
        	dontShow = false;
        } else{
		         if(options[x].equals("Stop timer")){
		        	dontShow = true;
		        	System.out.println("selected to end timer");
		        } else if( options[x].equals("20 min")) {
		            dontShow = true;
		            System.out.println("Start "+LocalDateTime.now());
		            taskScedularRun(250L,10000L);
		        } else if(options[x].equals("30 min")) {
		            dontShow = true;
		            System.out.println("Start "+LocalDateTime.now());
		            taskScedularRun(500L,10000L);
		        } else if(options[x].equals("60 min")) {
		            dontShow = true;
		            System.out.println("Start "+LocalDateTime.now());
		            taskScedularRun(1000L,10000L);
		        } else if (x == JOptionPane.CLOSED_OPTION) {
		        	dontShow = true;
		        }
        }
		return dontShow;
	  }


	public static void taskScedularRun(Long dt, Long inputTimer)throws ArrayIndexOutOfBoundsException {
		Timer timer = new Timer("Timer");
	    TimerTask repeatedTask = new TimerTask() {
	    boolean test = false;

	    public void run() {
	        String songFile = "c:\\sms.wav";
		    InputStream in = null;
		    AudioStream audioStream = null;

			try {
				in = new FileInputStream(songFile);
			} catch (FileNotFoundException e) {
				//System.out.println("No Audio file found");
			}

			try {
				audioStream = new AudioStream(in);
			} catch (IOException e) {
				//System.out.println("No Audio streaming");
			}

		    AudioPlayer.player.start(audioStream);

		    	 System.out.println(" finish " + LocalDateTime.now());

		    	test = infoBox("Time to take a break, walk around and stretch");
		    	//returning a boolean (test) if true cancel thread else keep running thread and wait for user input
	         	if(test){
		    	   //System.out.println("timer stopped");
		    	   timer.cancel();
		    	}
		       }
		    };

		    long delay  = dt* 60L * 60L;
		    long period = inputTimer* 60L * 60L;
		    timer.scheduleAtFixedRate(repeatedTask, delay, period);

	    }


	}



