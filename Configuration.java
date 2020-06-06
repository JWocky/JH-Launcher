import java.io.*;
import java.util.*;
import javax.swing.*;

public class Configuration {
	
	private JFrame mainwindow=null;
	private DirectoryEntry rootDirectory=null;
	private LinkedList<DirectoryEntry> aircraftDirectories=new LinkedList<DirectoryEntry>();
	private LinkedList<DirectoryEntry> terrainDirectories=new LinkedList<DirectoryEntry>();
	private boolean aiTraffic=false;
	private LinkedList<ProtocolEntry> protocolInterfaces=new LinkedList<ProtocolEntry>();
	private AircraftEntry aircraft=null;
	private AirportEntry airport=null;
	private boolean isJAFVA=true;
	private boolean jafvaOverwrite=false;
	private String jafvaName="JWocky";
	private String airportJafva="";
	private String latitudeJafva="";
	private String longitudeJafva="";
	private String headingJafva="";

	private AircraftSelect aircraftSelect=null;
	private boolean aircraftTreeClean=false;
	private AirportSelect airportSelect=null;
	private boolean airportListClean=false;
	private ProtocolSelect protocolSelect=null;
	private boolean protocolListClean=false;
	private JAFVASelect jafvaSelect=null;
	private boolean jafvaListClean=false;

	public Configuration(JFrame f) {
		mainwindow=f;
		// just some data for test purposes till I figure out where and how I want to store a configuration permanently
		rootDirectory=new DirectoryEntry();
		rootDirectory.setPath("/home/peter/fgdata/fgdata");

		DirectoryEntry dir=new DirectoryEntry();
		dir.setPath("/home/peter/fgdata/JWocky_Hangar/Aircraft");
		aircraftDirectories.add(dir);
		dir=new DirectoryEntry();
		dir.setPath("/home/peter/fgdata/fgdata/Aircraft");
		aircraftDirectories.add(dir);

		dir=new DirectoryEntry();
		dir.setPath("/home/peter/fgdata/fgdata/Blah");
		aircraftDirectories.add(dir);

		dir=new DirectoryEntry();
		dir.setPath("/home/peter/fgdata/terraGit");
		terrainDirectories.add(dir);
		aiTraffic=false;
		ProtocolEntry proto=new ProtocolEntry();
		proto.setParameters("file,out,0.17,JAFVA/blackbox.csv,blackbox");
		protocolInterfaces.add(proto);
		aircraft=new AircraftEntry();
		aircraft.setName("JH-767-300ER");
		aircraft.setDirectory(aircraftDirectories.get(0));
		airport=new AirportEntry();
		airport.setICAOCode("KBLV");
		airport.setName("Midamerica Whatever");
		airport.setDirectory(terrainDirectories.get(0));
		readConfig();
	}

	public JFrame getMainWindow() {
		return(mainwindow);
	}

	public DirectoryEntry getRoot() {
		return(rootDirectory);
	}

	public void setRoot(DirectoryEntry de) {
		rootDirectory=de;
	}

	public LinkedList<DirectoryEntry> getAircraftDirectories() {
		return(aircraftDirectories);
	}

	public void setAircraftDirectories(LinkedList<DirectoryEntry> l) {
		aircraftDirectories=l;
	}

	public LinkedList<DirectoryEntry> getTerrainDirectories() {
		return(terrainDirectories);
	}

	public void setTerrainDirectories(LinkedList<DirectoryEntry> l) {
		terrainDirectories=l;
	}

	public LinkedList<ProtocolEntry> getProtocols() {
		return(protocolInterfaces);
	}

	public void setProtocols(LinkedList<ProtocolEntry> l) {
		protocolInterfaces=l;
	}

	public void setJafva(boolean b) {
		isJAFVA=b;
	}

	public boolean getJafva() {
		return(isJAFVA);
	}

	public void setJafvaOverwrite(boolean b) {
		jafvaOverwrite=b;
	}

	public boolean getJafvaOverwrite() {
		return(jafvaOverwrite);
	}

	public void setJafvaName(String s) {
		jafvaName=s;
	}

	public String getJafvaName() {
		return(jafvaName);
	}

	public void setAirportJafva(String s) {
		airportJafva=s;
	}

	public String getAirportJafva() {
		return(airportJafva);
	}

	public void setLatitudeJafva(String s) {
		latitudeJafva=s;
	}

	public String getLatitudeJafva() {
		return(latitudeJafva);
	}

	public void setLongitudeJafva(String s) {
		longitudeJafva=s;
	}

	public String getLongitudeJafva() {
		return(longitudeJafva);
	}

	public void setHeadingJafva(String s) {
		headingJafva=s;
	}

	public String getHeadingJafva() {
		return(headingJafva);
	}

	public void setSelectedAircraft(String ae) {
		aircraft.setName(ae);
	}

	public String getSelectedAircraft() {
		return(aircraft.getName());
	}

	public void setAircraftSelect(AircraftSelect as) {
		aircraftSelect=as;
	}

	public boolean isAircraftClean() {
		return(aircraftTreeClean);
	}

	public void setAircraftClean() {
		aircraftTreeClean=true;
	}

	public void setAircraftDirty() {
		aircraftTreeClean=false;
		aircraftSelect.reloadTree();
	}

	public void setAirportSelect(AirportSelect as) {
		airportSelect=as;
	}

	public void setSelectedAirport(String ae) {
		airport.setICAOCode(ae);
	}

	public String getSelectedAirport() {
		return(airport.getICAOCode());
	}

	public boolean isAirportClean() {
		return(airportListClean);
	}

	public void setAirportClean() {
		airportListClean=true;
	}

	public void setAirportDirty() {
		airportListClean=false;
		airportSelect.reloadList();
	}

	public void setProtocolSelect(ProtocolSelect ps) {
		protocolSelect=ps;
	}

	public boolean isProtocolsClean() {
		return(protocolListClean);
	}

	public void setProtocolsClean() {
		protocolListClean=true;
	}

	public void setProtocolsDirty() {
		protocolListClean=false;
		protocolSelect.reloadList();
	}

	public void setJAFVASelect(JAFVASelect js) {
		jafvaSelect=js;
	}

	public void writeConfig() {
		File cfg=new File("JHLConfig.xml");
		try {
			BufferedWriter out=new BufferedWriter(new FileWriter("JHLConfig.xml"));
			out.write("<directories>"); out.newLine();

			out.write("\t"+"<root>"); out.newLine();
				out.write("\t"+"\t"+rootDirectory.getPath()); out.newLine();
			out.write("\t"+"</root>"); out.newLine();

			out.write("\t"+"<aircrafts>"); out.newLine();
				for (int i=0; i<aircraftDirectories.size(); i++) {
					out.write("\t"+"\t"+"<aircraft>"); out.newLine();
					out.write("\t"+"\t"+"\t"+aircraftDirectories.get(i).getPath()); out.newLine();
					out.write("\t"+"\t"+"</aircraft>"); out.newLine();
				}
			out.write("\t"+"</aircrafts>"); out.newLine();

			out.write("\t"+"<terrains>"); out.newLine();
				for (int i=0; i<terrainDirectories.size(); i++) {
					out.write("\t"+"\t"+"<terrain>"); out.newLine();
					out.write("\t"+"\t"+"\t"+terrainDirectories.get(i).getPath()); out.newLine();
					out.write("\t"+"\t"+"</terrain>"); out.newLine();
				}
			out.write("\t"+"</terrains>"); out.newLine();

			out.write("</directories>"); out.newLine();

			out.write("<protocols>"); out.newLine();

				for (int i=0; i<protocolInterfaces.size(); i++) {
					out.write("\t"+"<protocol>"); out.newLine();
					out.write("\t"+"\t"+protocolInterfaces.get(i).getParameters()); out.newLine();
					out.write("\t"+"</protocol>"); out.newLine();
				}

			out.write("</protocols>"); out.newLine();

			out.write("<jafva>"); out.newLine();

			out.write("\t"+"<isjafva>"); out.newLine();
				if (isJAFVA) {
					out.write("\t"+"\t"+"TRUE"); out.newLine();
				} else {
					out.write("\t"+"\t"+"FALSE"); out.newLine();
				}
			out.write("\t"+"</isjafva>"); out.newLine();

			out.write("\t"+"<jafvaoverwrite>"); out.newLine();
				if (jafvaOverwrite) {
					out.write("\t"+"\t"+"TRUE"); out.newLine();
				} else {
					out.write("\t"+"\t"+"FALSE"); out.newLine();
				}
			out.write("\t"+"</jafvaoverwrite>"); out.newLine();

			out.write("\t"+"<jafvaname>"); out.newLine();
				out.write("\t"+"\t"+jafvaName); out.newLine();
			out.write("\t"+"</jafvaname>"); out.newLine();

			out.write("\t"+"<jafvaairport>"); out.newLine();
				out.write("\t"+"\t"+airportJafva); out.newLine();
			out.write("\t"+"</jafvaairport>"); out.newLine();

			out.write("\t"+"<jafvalat>"); out.newLine();
				out.write("\t"+"\t"+latitudeJafva); out.newLine();
			out.write("\t"+"</jafvalat>"); out.newLine();

			out.write("\t"+"<jafvalon>"); out.newLine();
				out.write("\t"+"\t"+longitudeJafva); out.newLine();
			out.write("\t"+"</jafvalon>"); out.newLine();

			out.write("\t"+"<jafvaheading>"); out.newLine();
				out.write("\t"+"\t"+headingJafva); out.newLine();
			out.write("\t"+"</jafvaheading>"); out.newLine();

			out.write("</jafva>"); out.newLine();

			out.write("<options>"); out.newLine();

			out.write("\t"+"<aitraffic>"); out.newLine();
				if (aiTraffic) {
					out.write("\t"+"\t"+"TRUE"); out.newLine();
				} else {
					out.write("\t"+"\t"+"FALSE"); out.newLine();
				}
			out.write("\t"+"</aitraffic>"); out.newLine();

			out.write("</options>"); out.newLine();

			out.close();
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
	}

	public void readRootDirectory(BufferedReader in) {
		try {
			String line="";
			while (line!=null && !line.equalsIgnoreCase("</root>")) {
				line=in.readLine();
				line=line.trim();
				if (line!=null && !line.equalsIgnoreCase("</root>")) {
					System.out.println("set FG Root to "+line);
					rootDirectory=new DirectoryEntry();
					rootDirectory.setPath(line);
				} else {
					System.out.println("inRoot: "+line); 
				}
			}
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
	}

	public void addAircraftDirectory(BufferedReader in) {
		try {
			String line="";
			while (line!=null && !line.equalsIgnoreCase("</aircraft>")) {
				line=in.readLine();
				line=line.trim();
				if (line!=null && !line.equalsIgnoreCase("</aircraft>")) {
					System.out.println("add Aircraft Dir "+line);
					DirectoryEntry de=new DirectoryEntry();
					de.setPath(line);
					aircraftDirectories.add(de);
				} else {
					System.out.println("inAircraft: "+line); 
				}
			}
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
	}

	public void readAircraftDirectories(BufferedReader in) {
		try {
			String line="";
			while (line!=null && !line.equalsIgnoreCase("</aircrafts>")) {
				line=in.readLine();
				line=line.trim();
				if (line!=null && !line.equalsIgnoreCase("</aircrafts>")) {
					if (line.equalsIgnoreCase("<aircraft>")) {
						addAircraftDirectory(in);
					}
				} else {
					System.out.println("inAircraft: "+line); 
				}
			}
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
	}

	public void addTerrainDirectory(BufferedReader in) {
		try {
			String line="";
			while (line!=null && !line.equalsIgnoreCase("</terrain>")) {
				line=in.readLine();
				line=line.trim();
				if (line!=null && !line.equalsIgnoreCase("</terrain>")) {
					System.out.println("add Terrain Dir "+line);
					DirectoryEntry de=new DirectoryEntry();
					de.setPath(line);
					terrainDirectories.add(de);
				} else {
					System.out.println("inTerrain: "+line); 
				}
			}
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
	}

	public void readTerrainDirectories(BufferedReader in) {
		try {
			String line="";
			while (line!=null && !line.equalsIgnoreCase("</terrains>")) {
				line=in.readLine();
				line=line.trim();
				if (line!=null && !line.equalsIgnoreCase("</terrains>")) {
					if (line.equalsIgnoreCase("<terrain>")) {
						addTerrainDirectory(in);
					}
				} else {
					System.out.println("inTerrain: "+line); 
				}
			}
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
	}

	public void readDirectories(BufferedReader in) {
		try {
			String line="";
			while (line!=null && !line.equalsIgnoreCase("</directories>")) {
				line=in.readLine();
				line=line.trim();
				if (line!=null && !line.equalsIgnoreCase("</directories>")) {
					if (line.equalsIgnoreCase("<root>")) {
						readRootDirectory(in);
					} else if (line.equalsIgnoreCase("<aircrafts>")) {
						aircraftDirectories.clear();
						readAircraftDirectories(in);
					} else if (line.equalsIgnoreCase("<terrains>")) {
						terrainDirectories.clear();
						readTerrainDirectories(in);
					} else {
						System.out.println("inDirectories: "+line); 
					}
				}
			}
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
	}

	public void addProtocol(BufferedReader in) {
		try {
			String line="";
			while (line!=null && !line.equalsIgnoreCase("</protocol>")) {
				line=in.readLine();
				line=line.trim();
				if (line!=null && !line.equalsIgnoreCase("</protocol>")) {
					System.out.println("add Protocol "+line);
					ProtocolEntry pe=new ProtocolEntry();
					pe.setParameters(line);
					pe.setSelected(false);
					protocolInterfaces.add(pe);
				} else {
					System.out.println("inProtocol: "+line); 
				}
			}
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
	}

	public void readProtocols(BufferedReader in) {
		try {
			String line="";
			while (line!=null && !line.equalsIgnoreCase("</protocols>")) {
				line=in.readLine();
				line=line.trim();
				if (line!=null && !line.equalsIgnoreCase("</protocols>")) {
					if (line.equalsIgnoreCase("<protocol>")) {
						protocolInterfaces.clear();
						addProtocol(in);
					} else {
						System.out.println("inDirectories: "+line); 
					}
				}
			}
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
	}

	public void readIsJafva(BufferedReader in) {
		try {
			String line="";
			while (line!=null && !line.equalsIgnoreCase("</isjafva>")) {
				line=in.readLine();
				line=line.trim();
				if (line!=null && !line.equalsIgnoreCase("</isjafva>")) {
					System.out.println("set IsJafva "+line);
					isJAFVA=line.equalsIgnoreCase("TRUE");
				} else {
					System.out.println("inJafva: "+line); 
				}
			}
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
	}

	public void readJafvaOverwrite(BufferedReader in) {
		try {
			String line="";
			while (line!=null && !line.equalsIgnoreCase("</jafvaoverwrite>")) {
				line=in.readLine();
				line=line.trim();
				if (line!=null && !line.equalsIgnoreCase("</jafvaoverwrite>")) {
					System.out.println("set JafvaOverwrite "+line);
					jafvaOverwrite=line.equalsIgnoreCase("TRUE");
				} else {
					System.out.println("inJafva: "+line); 
				}
			}
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
	}

	public void readJafvaName(BufferedReader in) {
		try {
			String line="";
			while (line!=null && !line.equalsIgnoreCase("</jafvaname>")) {
				line=in.readLine();
				line=line.trim();
				if (line!=null && !line.equalsIgnoreCase("</jafvaname>")) {
					System.out.println("set JafvaName "+line);
					jafvaName=line;
				} else {
					System.out.println("inJafva: "+line); 
				}
			}
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
	}

	public void readJafvaAirport(BufferedReader in) {
		try {
			String line="";
			while (line!=null && !line.equalsIgnoreCase("</jafvaairport>")) {
				line=in.readLine();
				line=line.trim();
				if (line!=null && !line.equalsIgnoreCase("</jafvaairport>")) {
					System.out.println("set JafvaAirport "+line);
					airportJafva=line;
				} else {
					System.out.println("inJafva: "+line); 
				}
			}
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
	}

	public void readJafvaLatitude(BufferedReader in) {
		try {
			String line="";
			while (line!=null && !line.equalsIgnoreCase("</jafvalat>")) {
				line=in.readLine();
				line=line.trim();
				if (line!=null && !line.equalsIgnoreCase("</jafvalat>")) {
					System.out.println("set JafvaLatitude "+line);
					latitudeJafva=line;
				} else {
					System.out.println("inJafva: "+line); 
				}
			}
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
	}

	public void readJafvaLongitude(BufferedReader in) {
		try {
			String line="";
			while (line!=null && !line.equalsIgnoreCase("</jafvalon>")) {
				line=in.readLine();
				line=line.trim();
				if (line!=null && !line.equalsIgnoreCase("</jafvalon>")) {
					System.out.println("set JafvaLongitude "+line);
					longitudeJafva=line;
				} else {
					System.out.println("inJafva: "+line); 
				}
			}
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
	}

	public void readJafvaHeading(BufferedReader in) {
		try {
			String line="";
			while (line!=null && !line.equalsIgnoreCase("</jafvaheading>")) {
				line=in.readLine();
				line=line.trim();
				if (line!=null && !line.equalsIgnoreCase("</jafvaheading>")) {
					System.out.println("set JafvaHeading "+line);
					headingJafva=line;
				} else {
					System.out.println("inJafva: "+line); 
				}
			}
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
	}

	public void readJafva(BufferedReader in) {
		try {
			String line="";
			while (line!=null && !line.equalsIgnoreCase("</jafva>")) {
				line=in.readLine();
				line=line.trim();
				if (line!=null && !line.equalsIgnoreCase("</jafva>")) {
					if (line.equalsIgnoreCase("<isjafva>")) {
						readIsJafva(in);
					} else if (line.equalsIgnoreCase("<jafvaoverwrite>")) {
						readJafvaOverwrite(in);
					} else if (line.equalsIgnoreCase("<jafvaname>")) {
						readJafvaName(in);
					} else if (line.equalsIgnoreCase("<jafvaairport>")) {
						readJafvaAirport(in);
					} else if (line.equalsIgnoreCase("<jafvalat>")) {
						readJafvaLatitude(in);
					} else if (line.equalsIgnoreCase("<jafvalon>")) {
						readJafvaLongitude(in);
					} else if (line.equalsIgnoreCase("<jafvaheading>")) {
						readJafvaHeading(in);
					} else {
						System.out.println("inJafva: "+line); 
					}
				}
			}
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
	}

	public void readConfig() {
		File cfg=new File("JHLConfig.xml");
		try {
			BufferedReader in = new BufferedReader(new FileReader(cfg)); 
			String line="";
			while ((line=in.readLine()) != null)  {
				line=line.trim();
				if (line.equalsIgnoreCase("<directories>")) {
					readDirectories(in);
				} else if (line.equalsIgnoreCase("<protocols>")) {
					readProtocols(in);
				} else if (line.equalsIgnoreCase("<jafva>")) {
					readJafva(in);
				} else {
					System.out.println(line); 
				}
			}
			in.close();
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
	}

}
