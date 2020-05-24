import java.io.*;
import java.util.*;

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
		try {
			// build a list of all the parameters
			LinkedList<String> props=new LinkedList<String>();
			props.add("fgfs");
			props.add("--fg-root="+config.getRoot().getPath());

			String sep=System.getProperty("path.separator");

			String terrains="--fg-scenery=";
			LinkedList<DirectoryEntry> terrainList=config.getTerrainDirectories();
			for (int i=0; i<terrainList.size(); i++) {
				if (!terrains.equals("--fg-scenery=")) {
					terrains=terrains+sep;
				}
				terrains=terrains+terrainList.get(i).getPath();
			}
			props.add(terrains);

			String aircrafts="--fg-aircraft=";
			LinkedList<DirectoryEntry> aircraftList=config.getAircraftDirectories();
			for (int i=0; i<aircraftList.size(); i++) {
				if (!aircrafts.equals("--fg-aircraft=")) {
					aircrafts=aircrafts+sep;
				}
				aircrafts=aircrafts+aircraftList.get(i).getPath();
			}
			props.add(aircrafts);

			String protocols="";
			LinkedList<ProtocolEntry> protocolList=config.getProtocols();
			for (int i=0; i<protocolList.size(); i++) {
				if (protocolList.get(i).getSelected()) {
					protocols="--generic="+protocolList.get(i).getParameters();
					props.add(protocols);
				}
			}

			props.add("--aircraft="+config.getSelectedAircraft());

			if (config.getJafva()) {
				if (config.getJafvaOverwrite()) {
					props.add("--airport="+config.getAirportJafva());
					props.add("--lat="+config.getLatitudeJafva());
					props.add("--lon="+config.getLongitudeJafva());
					props.add("--heading="+config.getHeadingJafva());
				} else {
					props.add("--airport="+config.getSelectedAirport());
				}
			} else {
				props.add("--airport="+config.getSelectedAirport());
			}

			// then start the process with this list
			Process process = new ProcessBuilder(props).start();

			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;

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
