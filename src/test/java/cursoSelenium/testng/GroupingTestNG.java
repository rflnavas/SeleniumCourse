package cursoSelenium.testng;

import org.junit.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cursoSelenium.utils.LogUtils;

public class GroupingTestNG {
	
//	private Logger log = Logger.getLogger(GroupingTestNG.class);
	@BeforeClass
	public void init() {
		LogUtils.prepareConfig(this.getClass());
		System.out.println("Test has just began");
	}

	@BeforeMethod
	public void before() {
//		System.out.println("Before executing");
	}
	
	//If no priority is set in test then it will be first executed. After that, 
	//testng begins with a 1-st level prioritized method
	@Test(groups={"java", "backend"}, priority=4)
	public void java() {
		System.out.println("java");
	}
	
	@Test(groups={"java", "backend"}, priority=3)
	public void spring() {
		System.out.println("spring");
	}
	@Test(groups={"java", "backend"}, priority=1)
	public void hibernate() {
		System.out.println("hibernate");
	}
	
	@Test(groups={"javascript", "frontend"}, priority=2)
	public void javascript() {
		System.out.println("javascript");
	}
	
	@Test(groups={"frontend"}, priority=2, enabled=false)
	public void jQuery() {
		System.out.println("jQuery");
	}
	
	@Test(groups={"javascript", "backend", "frontend"}, priority=2)
	public void node() {
		System.out.println("node");
	}
	
	@Test(groups={"testing"}, enabled=false)
	public void junit() {
		System.out.println("junit");
	}
	@Test(groups={"backend", "db"}, enabled=false)
	public void sql() {
		System.out.println("sql");
	}
	@Test(groups={"db"})
	public void oracle() {
		System.out.println("oracle");
	}

	@AfterMethod
	public void after() {
//		System.out.println("Executing after");
	}

	@AfterClass
	public void end() {
		System.out.println("Ending test...");
	}
}
