package cursoSelenium.testng;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class AssetsReports {
  @Test
  public void test() {
	  int i = 2;
	  int j = 6;
	  int k = i + j;
	  int expected = 8;
	  Assert.assertEquals(k , expected);
	  //we set true in the second argument if we want the string be printed out in the console.
	  Reporter.log((i + j) + ((expected == k) ?"=":"!=") + expected, true);
  }
}
