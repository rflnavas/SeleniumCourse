package cursoSelenium.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesHelper {
	private FilesHelper() {
	}
	
	public static void createFolderIfNotExists(Path path){
		if(!Files.exists(path)){
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
