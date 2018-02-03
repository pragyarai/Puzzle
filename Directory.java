public class Directory {
	public static void main(String[] args) {

		final Shell shell = new Shell();
		// Root folder is created when shell is instantiated. Other folders need to be created before accessing.
		shell.mkdir("usr/");
		shell.mkdir("usr/local/");
		shell.mkdir("/lib");
		
		assert shell.path().equals("/");

		shell.cd("/");
		assert shell.path().equals("/");

		shell.cd("usr/..");
		assert shell.path().equals("/");
		// cd can be chained. 
		shell.cd("usr").cd("local");
		shell.cd("../local").cd("./");
		assert shell.path().equals("/usr/local");

		shell.cd("..");
		assert shell.path().equals("/usr");
		// leading and trailing slashes
		shell.cd("//lib///");
		assert shell.path().equals("/lib");
		
		shell.cd("/usr/local");
		shell.mkdir("../bin");
		shell.cd("/usr/bin");
		assert shell.path().equals("/usr/bin");
		
		// Nested folders with same name can be handled properly.
		shell.mkdir("/usr/usr/");
		shell.cd("/usr").cd("usr");
		assert shell.path().equals("/usr/usr");
		
		shell.cd("/usr/local");
		assert shell.path().equals("/usr/local");
		// mkdir can cd can be chained
		shell.mkdir("/usr/usr/local").cd("/usr/usr/local");
		assert shell.path().equals("/usr/usr/local");
	}
}