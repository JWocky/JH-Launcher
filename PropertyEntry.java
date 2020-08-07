import java.util.*;

public class PropertyEntry {
	
	public static final int TYPE_DEFAULT=0;
	public static final int TYPE_STRING=1;
	public static final int TYPE_INT=2;
	public static final int TYPE_FLOAT=3;
	public static final int TYPE_DOUBLE=4;
	public static final int TYPE_BOOLEAN=5;

	private String name="";
	private PropertyEntry parent=null;
	private int type=TYPE_DEFAULT;
	private int givenNumber=-1;
	private String value= null;
	LinkedList<PropertyEntry> children= new LinkedList<PropertyEntry>();


	public PropertyEntry() {
	}

	public PropertyEntry(String n, PropertyEntry p) {
		name=n;
		parent=p;
	}
	
	public void setName(String s) {
		name=s;
	}
	
	public void setParent(PropertyEntry p) {
		parent=p;
	}
	
	public PropertyEntry getParent() {
		return(parent);
	}

	public void setType(int i) {
		type=i;
	}
	
	public int getType() {
		return(type);
	}

	public void setNumber(int i) {
		givenNumber=i;
	}
	
	public int getNumber() {
		return(givenNumber);
	}

	public void setValue(String s) {
		value=s;
	}
	
	public String getValue() {
		return(value);
	}
	
	public void addChild(PropertyEntry p) {
		children.add(p);
	}
	
	public void showTree(String ind) {
		String indent=ind;
		System.out.println(indent+name+"-->"+value);
		for (int i=0; i<children.size(); i++) {
			children.get(i).showTree(ind+"  ");
		}
	}	

}
