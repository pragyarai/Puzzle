class Shell {
	static final String ROOT_NAME = "/"; // For non-linux OS, like windows we can use 'C:/'
	static final String DIRECTORY_SEPRATOR = "/";
	static final Folder ROOT = new Folder(ROOT_NAME);
	
	Folder currFolder;
	
	Shell(){
		this.currFolder = Shell.ROOT;
	}
	
	public Shell cd(final String path) {
		String sanatizedPath = path.replaceAll("/+$", "");
		Folder toFolder = reachFolder(this.currFolder, sanatizedPath);
		if(toFolder != null){
			this.currFolder = toFolder;
		}
		return this;
	}
	
	/*
	 * Creates the new folder in the specified path.
	 * If folder already exists on given path, do nothing
	 */
	public Shell mkdir(final String path) {
		String sanatizedPath = path.replaceAll("/+$", "");
		String[] fld = sanatizedPath.split(Shell.DIRECTORY_SEPRATOR);
		Folder createIn = this.currFolder;
		if(fld.length>1){
			sanatizedPath = sanatizedPath.replaceAll(fld[fld.length-1]+"$", ""); // remove folder name to be created from this path 
			createIn = reachFolder(this.currFolder, sanatizedPath);
		}
		if(createIn != null){
			Folder newFolder = new Folder(fld[fld.length-1], createIn);
			
			if(createIn.getChild(newFolder.name) == createIn){
				createIn.addChild(newFolder); // Create folder if it does not already exist.
			}else{
				System.out.println("Folder already exists on given path: " + path);
			}
		}
		return this;
	}
	/*
	 * Traverse from the given folder to the given path. This function traverse through the path up to the existing folder.
	 * After that if the folder in path does not exit, this function just returns the last folder reached. 
	 */
	private Folder reachFolder(Folder from, String to){
		if(to.indexOf(Shell.ROOT_NAME) == 0){
			// User gave absolute path instead of relative. So calculating path from root.
			from = Shell.ROOT;
		}
		String[] foldersToCd = to.split(Shell.DIRECTORY_SEPRATOR);
		for(String folder : foldersToCd){
			if(folder.equals("..")){
				if(from.parent != null){
					from = from.parent;
				}
			}else if(folder.isEmpty() || folder.equals(".")){
				continue;
			}else{
				if(from != from.getChild(folder)){
					from = from.getChild(folder);
				}else{
					System.out.println("Directory does not exists: " + to);
					return null;
				}
			}
		}
		return from;
	}
	/*
	 * Returns the absolute path of the current directory.
	 */
	public String path() {
		String interPath = "";
		Folder tempFolder = this.currFolder;
		while(tempFolder != null){
			if (tempFolder.parent == null || interPath.isEmpty()){
				interPath = tempFolder.getName() + interPath;
			}else{
				interPath = tempFolder.getName() + Shell.DIRECTORY_SEPRATOR + interPath;
			}
			tempFolder = tempFolder.parent;
		}
		return interPath;
	}
}