import java.io.*;
import java.util.*;

public class Worker extends Thread {

	private Process process=null;
	private StartFlightgear surf=null;

	public void setProcess(Process p) {
		process=p;
	}

	public void setSurf(StartFlightgear s) {
		surf=s;
	}

	public void run() {
		if (process !=null) {

			try {
				boolean processRunning=true;
				InputStream isIn = process.getInputStream();
				InputStreamReader isrIn = new InputStreamReader(isIn);
				BufferedReader brIn = new BufferedReader(isrIn);
				InputStream isErr = process.getErrorStream();
				InputStreamReader isrErr = new InputStreamReader(isErr);
				BufferedReader brErr = new BufferedReader(isrErr);
				String line;

				while (processRunning) {
					try {
						int exitCode=process.exitValue();
						processRunning=false;
					} catch(IllegalThreadStateException itse) {
						processRunning=true;
					}

					if (brIn.ready()) {
						line=brIn.readLine();
						System.out.println("Input: "+line);
						surf.addLine(line);
					}

					if (brErr.ready()) {
						line=brErr.readLine();
						System.out.println("Error: "+line);
						surf.addLine(line);
					}
					Thread.yield();
				}

			} catch(IOException ioe) {
				// really not sure now what to do because I still need to program me a nice window to show the error
				System.out.println(ioe.getMessage());
			} catch(SecurityException se) {
				// really not sure now what to do because I still need to program me a nice window to show the error
				System.out.println(se.getMessage());
			} catch(UnsupportedOperationException uoe) {
				// really not sure now what to do because I still need to program me a nice window to show the error
				System.out.println(uoe.getMessage());
			}

		}
	}

}
