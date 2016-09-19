package cursoSelenium.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Constants {

	public enum Visibility {
		SHOW, HIDDEN
	};

	public enum ResultType {
		OK, WARN, ERROR
	}

	public static final int CHILDREN_MIN_AGE = 0;
	public static final int CHILDREN_MAX_AGE = 17;

	public static final int BEGIN_ROW_INDEX = 0;

	public static final int COL_INDEX_RESULT_SEARCHPAGE = 5;

	public static final String DEFAULT_ROOT = File.listRoots()[0].toString();

	private Constants() {

	}

	public static final Path XLS_DATA_PATH = Paths.get("D:\\", "MisProgramas",
			"Desarrollos", "SeleniumCourse", "cursoSelenium", "src", "main",
			"resources", "data.xlsx");

	public static final Path XLS_DATA_PATH_2 = Paths.get("D:\\",
			"MisProgramas", "Desarrollos", "SeleniumCourse", "cursoSelenium",
			"src", "main", "resources", "data2.xlsx");

	public static final Path REPORT_PATH = Paths.get(DEFAULT_ROOT, "Test",
			"reports");
}
