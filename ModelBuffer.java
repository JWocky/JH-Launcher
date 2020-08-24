import java.io.*;

public class ModelBuffer {

	private boolean clear=true;
	private String name="";
	private String path="";
	private String rootfile="";
	
	private PropertyEntry rootnode=new PropertyEntry("model", null);
	private PropertyEntry work=null;
	
	public void clear() {
		clear=true;
	}

	public boolean isClear() {
		return(clear);
	}

	public PropertyEntry getRootNode() {
		return(rootnode);
	}

	public void setName(String s) {
		name=s;
	}
	
	public String getName() {
		return(name);
	}

	public void setPath(String s) {
		path=s;
	}
	
	public String getPath() {
		return(path);
	}

	public void setRootfile(String s) {
		rootfile=s;
	}
	
	public String getRootFile() {
		return(rootfile);
	}

	private String readFile(String fn) {
		String readBuffer="";
		try {
			BufferedReader in = new BufferedReader(new FileReader(fn)); 
			String line="";
			while ((line=in.readLine()) != null)  {
				if (readBuffer.length()>0) {
					readBuffer=readBuffer+"\n";
				}
				readBuffer=readBuffer+line;
			}
			in.close();
 		} catch (IOException ioe) {
			System.out.println("Error: "+ioe.getMessage());
		}
		return(readBuffer);
	}
	
	private void nodeParameter(PropertyEntry pe, String pp) {
		String n="";
		String v="";
			
		if (pp.indexOf("=")<0) {
			n=pp;
			v="true";
		} else {
			n=pp.substring(0, pp.indexOf("="));
			v=pp.substring(pp.indexOf("=")+1, pp.length());
		}
		if (n.equals("type")) {
			v=v.substring(1, v.length()-1);
			if (v.equals("string")) {
				pe.setType(PropertyEntry.TYPE_STRING);
			}
			if (v.equals("int")) {
				pe.setType(PropertyEntry.TYPE_INT);
			}
			if (v.equals("float")) {
				pe.setType(PropertyEntry.TYPE_FLOAT);
			}
			if (v.equals("double")) {
				pe.setType(PropertyEntry.TYPE_DOUBLE);
			}
			if (v.equals("bool")) {
				pe.setType(PropertyEntry.TYPE_BOOLEAN);
			}
		} else if (n.equals("n")) {
			v=v.substring(1, v.length()-1);
			try {
			int j=Integer.parseInt(v);
			pe.setNumber(j);
			} catch(NumberFormatException nfe) {
				System.out.println("Error: "+nfe.getMessage());
			}
		} else if (n.equals("include")) {
			v=v.substring(1, v.length()-1);
//			System.out.println("------- begin include "+v);
//			System.out.println("        load path:"+path+System.getProperty("file.separator")+v);
			String filebuf=readFile(path+System.getProperty("file.separator")+v);
			PropertyEntry stacked=work;
			work=pe;
			parseXML(filebuf);
			work=stacked;
//			System.out.println("------- end include "+v);
		} else {
//			System.out.println(n+"-->"+v);
		}
	}
	
	private void openPropertyNode(String t) {
		int num=0;
		String b="";
		String n="";
		PropertyEntry pe=new PropertyEntry();
		for (int i=0; i<t.length(); i++) {
			String c=t.substring(i, i+1);
			if (c.equals("<")) {
			} else if (c.equals(">")) {
				if (b.endsWith("/")) {
					if (b.length()>1) {
						b=b.substring(0, b.length()-1);
					} else {
						b="";
					}
				}
				if (b.length()>0) {
					if (num==0) {
						n=b;
						pe.setName(n);
					} else {
						nodeParameter(pe, b);
					}
					b="";
				}
				num=num+1;
			} else if (c.equals(" ")) {
				if (b.length()>0) {
					if (num==0) {
						n=b;
						pe.setName(n);
					} else {
						nodeParameter(pe, b);
					}
					b="";
				}
				num=num+1;
			} else {
				b=b+c;
			}
			
		}
		pe.setParent(work);
		work.addChild(pe);
		work=pe;
	}

	private void closePropertyNode(String t, String c) {
		work.setValue(c);
		PropertyEntry pe=work.getParent();
		if (pe!=null) {
			work=pe;
		}
//		System.out.println("  "+t+"--> "+c);
	}
	
	private void parseXML(String b) {
		boolean inTag=false;
		boolean inComment=false;
		String tag="";
		String content="";
		int level=0;
//System.out.println("Parsing XML");
		for (int i=0; i<b.length(); i++) {
			String c=b.substring(i, i+1);
			if (c.equals("<")) {
				if (inTag) {
					tag=tag+c;
				} else {
					tag="<";
					inTag=true;
				}
			} else if (c.equals(">")) {
				if (inTag) {
					tag=tag+c;
					inTag=false;
					if (tag.startsWith("<!--")) {
						inComment=true;
					}

					if (!inComment) {
						if (tag.startsWith("</")) {
							level=level-1;
							closePropertyNode(tag, content);
						} else {
							if (!tag.startsWith("<?")) {
								if (level>0) {
									openPropertyNode(tag);
								}
							}
							level=level+1;
							if (tag.endsWith("/>")) {
								level=level-1;
								closePropertyNode(tag, content);
							}	
							if (tag.endsWith("?>")) {
								level=level-1;
							}	
						}
					}


					if (tag.endsWith("-->")) {
						inComment=false;
					}

					tag="";
					content="";
				} else {
					content=content+c;
				}
			} else {
				if (inTag) {
					tag=tag+c;
				} else {
					content=content+c;
				}
			}	
		}
	}
	
	public void load() {
		String filebuf=readFile(path+System.getProperty("file.separator")+rootfile);
		work=rootnode;
		parseXML(filebuf);
System.out.println("---------------------- TREE 2 ---------------------------------------------------------------------------");
		rootnode.showTree("");
	}
	
}
