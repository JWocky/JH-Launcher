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
					String para=protocolList.get(i).getParameters();
					int p=0;
					String[] s=new String[5];
					s[0]="";
					s[1]="";
					s[2]="";
					s[3]="";
					s[4]="";
					for (int j=0; j<para.length(); j++) {
						String c=para.substring(j,j+1);
						if (c.equals(",")) {
							p=p+1;
						} else {
							s[p]=s[p]+c;
						}
					}
					if (s[0].equalsIgnoreCase("file")) {
						File f = new File(s[3]);
						if(f.exists() && !f.isDirectory()) { 
							String name = s[3];
							int dot = name.lastIndexOf('.');
							String base = (dot == -1) ? name : name.substring(0, dot);
							String extension = (dot == -1) ? "" : name.substring(dot+1);
							String newname=base+f.lastModified()+"."+extension;
							File file2 = new File(newname);
System.out.println("Rename old "+s[3]+" to "+newname);
							f.renameTo(file2);						
						}						
					}
					protocols="--generic="+para;
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
				if (config.getJafvaCallsign()) {
					props.add("--callsign="+config.getAirlineJafva()+config.getSuffixJafva());
				}
			} else {
				props.add("--airport="+config.getSelectedAirport());
			}
			
			// then start the process with this list
			Process process = new ProcessBuilder(props).start();
			config.setInstancesRunning(config.getInstancesRunning()+1);
			surf.updateStartButton();
			Worker worker=new Worker(config);
			worker.setProcess(process);
			worker.setSurf(surf);
			worker.start();


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
