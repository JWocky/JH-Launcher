public class AircraftEntry {

	private String name="";
	private DirectoryEntry directory=null;

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
