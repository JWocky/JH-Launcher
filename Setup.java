import java.io.*;

public class Setup {

	private Configuration config=null;

	public Setup(Configuration c) {
		config=c;
	}

	public boolean checkSetup() {
		// Here I need still a check whether all the data entered is complete and correct to start Flightgear with it.
		return(true);
	}

	public void startFG(StartFlightgear surf) {
System.out.println("trying to start FG");
		try {
			String commandLine="";
			commandLine="/usr/games/fgfs --fg-root=/home/peter/fgdata/fgdata --fg-scenery=/home/peter/fgdata/terraGit --fg-aircraft=/home/peter/fgdata/JWocky_Hangar/Aircraft/:/home/peter/fgdata/fgdata/Aircraft --disable-ai-traffic --generic=file,out,0.17,JAFVA/blackbox.csv,blackbox --aircraft=JH-767-300ER --airport=KBLV";
			Process process = new ProcessBuilder(
				"/usr/games/fgfs"
			).start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;

//		System.out.printf("Output of running %s is:", Arrays.toString(args));

			while ((line = br.readLine()) != null) {
  				System.out.println(line);
				surf.addLine(line);
			}
		} catch(IOException ioe) {
			// really not sure now what to do because I still need to program me a nice window to show the error
			System.out.println(ioe.getMessage());
		}
	}
	
}
