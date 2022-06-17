package A1_com.java.basic;

public class Part_15_Upload {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String filePath = GlobalConstants.UPLOAD_FILE_FOLDER;
		String filePath = "";
		
		String [] fileNames = {"audi.PNG", "honda.PNG", "toyota.PNG"};
	
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
			/*
			 *  giải thích
			 * lần chạy thứ 1: fullFileName = "" sau đó + filepath + tên file audi.PNG + "\n"
			 * lần chạy thứ 2: filepath + tên file audi.PNG + "\n" + filepath + tên file honda.PNG + "\n"
			 * lần chạy thứ 3: filepath + tên file audi.PNG + "\n" + filepath + tên file honda.PNG + "\n" + filepath + tên file toyota.PNG + "\n"
			 */
		}
		// Hàm trim Cắt bỏ khoảng trắng ( )/ tab (\t)/ xuống dòng (\n) ở đầu/ cuối chuỗi 
		fullFileName = fullFileName.trim();	
		System.out.println("start" + fullFileName + "End");
		
		
		String osName = System.getProperty("os.name");
		System.out.println(osName);
		System.out.println(System.getProperty("user.dir"));
		
		osName = "windows";
		System.out.println(osName.toLowerCase().indexOf("urturu"));
	}
}
