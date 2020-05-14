public class ProtocolEntry {

	private String parameters="";
	private boolean selected=false;

	public void setParameters(String s) {
		parameters=s;
	}

	public String getParameters() {
		return(parameters);
	}

	public void setSelected(boolean b) {
		selected=b;
	}

	public boolean getSelected() {
		return(selected);
	}

	public String toString() {
		return(parameters);
	}

}
