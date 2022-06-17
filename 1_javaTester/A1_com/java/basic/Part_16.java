package A1_com.java.basic;

public class Part_16 {
	// file trong thư mục uploadFiles
	public String iosName = "audi.PNG";
	
	// Lấy ra tên của hệ điều hành đang dùng
	public String osName = System.getProperty("os.name");
	
	// Lấy ra cái đường dẫn của thư mục chứa code
	public String projectPath = System.getProperty("user.dir");

	public static void main(String[] args) {
		Part_16 osx = new Part_16();
		
		System.out.println("OS Name = " + osx.osName);
		System.out.println("Relative directory path = " + osx.projectPath);
		System.out.println("Upload folder = " + osx.getDirectorySlash("uploadFiles"));
		System.out.println("Image path = " + osx.projectPath + osx.getDirectorySlash("uploadFiles") + osx.iosName);
	}
	public String getDirectorySlash (String folderName) {
		if (isMac() || isUnix() || isSolaris()) {
			folderName = "/" + folderName + "/";
		}else {
			folderName = "\\" + folderName + "\\";
		}
		return folderName;
	}
	
	public boolean isWindows() {
		return (osName.toLowerCase().indexOf("win") >= 0);
	}
	
	public boolean isMac() {
		return (osName.toLowerCase().indexOf("mac") >= 0);
	}
	
	public boolean isUnix() {
		return (osName.toLowerCase().indexOf("nix") >= 0 || osName.toLowerCase().indexOf("nux") >= 0 || osName.toLowerCase().indexOf("aix") >= 0);
	}
	
	public boolean isSolaris() {
		return (osName.toLowerCase().indexOf("sunos") >= 0);
	}
}
