import java.util.HashMap;

/*
 * Composite for a Folder. This object has a parent, name and children as attributes. Root folder does not have parent.
 * This class can be extended to implement additional functionalities like ls('list'), mv('move') etc.
 */
public class Folder {
	public String name;
	Folder parent = null;
	HashMap<String, Folder> children = new HashMap<>();
	
	Folder(String name){
		this.name = name;
	}
	Folder(String name, Folder parent) {
		this.name = name;
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Folder getParent() {
		return parent;
	}
	public void setParent(Folder parent) {
		this.parent = parent;
	}
	public HashMap<String, Folder> getChildren() {
		return children;
	}
	public void setChildren(HashMap<String, Folder> child) {
		this.children = child;
	}
	public void addChild(Folder child){
		children.put(child.getName(), child);
	}
	public Folder getChild(String name){
		if (children.containsKey(name)){
			return children.get(name);
		}else{
			return this; // Since there is no way to go ahead, return current folder
		}
	}
}