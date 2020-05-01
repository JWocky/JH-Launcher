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
	private String jafvaName="JWocky";

	public Configuration(JFrame f) {
		mainwindow=f;
		// just some data for test purposes till I figure out where and how I want to store a configuration permanently
		DirectoryEntry rootDirectory=new DirectoryEntry();
		rootDirectory.setPath("/home/peter/fgdata/fgdata");
		DirectoryEntry dir=new DirectoryEntry();
		dir.setPath("/home/peter/fgdata/JWocky_Hangar/Aircraft");
		aircraftDirectories.add(dir);
		dir=new DirectoryEntry();
		dir.setPath("/home/peter/fgdata/fgdata/Aircraft");
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

	public void setJafvaName(String s) {
		jafvaName=s;
	}

	public String getJafvaName() {
		return(jafvaName);
	}

}
