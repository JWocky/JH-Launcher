public class AirportEntry {

	private String icaoCode="";
	private String name="";
	private DirectoryEntry directory=null;

	public void setICAOCode(String s) {
		icaoCode=s;
	}

	public String getICAOCode() {
		return(icaoCode);
	}

	public void setName(String s) {
		name=s;
	}

	public String getName() {
		return(name);
	}

	public void setDirectory(DirectoryEntry d) {
		directory=d;
	}

	public DirectoryEntry getDirectory() {
		return(directory);
	}

}
