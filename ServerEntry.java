public class ServerEntry {

	private String name="";
	private String hostname="";
	private String location="";
	private String inPort="";
	private String outPort="";
	private String inHz="";
	private String outHz="";
	private boolean pinged=false;

	public void setName(String s) {
		name=s;
	}

	public String getName() {
		return(name);
	}

	public void setHostname(String s) {
		hostname=s;
	}

	public String getHostname() {
		return(hostname);
	}

	public void setLocation(String s) {
		location=s;
	}

	public String getLocation() {
		return(location);
	}

	public void setInPort(String s) {
		inPort=s;
	}

	public String getInPort() {
		return(inPort);
	}

	public void setOutPort(String s) {
		outPort=s;
	}

	public String getOutPort() {
		return(outPort);
	}

	public void setInHz(String s) {
		inHz=s;
	}

	public String getInHz() {
		return(inHz);
	}

	public void setOutHz(String s) {
		outHz=s;
	}

	public String getOutHz() {
		return(outHz);
	}

	public void setPinged(boolean b) {
		pinged=b;
	}

	public boolean getPinged() {
		return(pinged);
	}

}
