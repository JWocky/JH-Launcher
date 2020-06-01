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

			out.write("\t"+"<aircraft>"); out.newLine();
				for (int i=0; i<aircraftDirectories.size(); i++) {
					out.write("\t"+"\t"+"<aircraft>"); out.newLine();
					out.write("\t"+"\t"+"\t"+aircraftDirectories.get(i).getPath()); out.newLine();
					out.write("\t"+"\t"+"</aircraft>"); out.newLine();
				}
			out.write("\t"+"</aircraft>"); out.newLine();

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

}
