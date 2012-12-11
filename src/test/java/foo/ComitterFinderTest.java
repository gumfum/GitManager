package foo;

import static org.junit.Assert.*;

import org.junit.Test;

public class ComitterFinderTest {
  @Test
  public void test() {
    ComitterFinder cf = new ComitterFinder("C:/Project/TestSample/");
    cf.setFilePath("src/test/MinusTest.java");
    String authorName = cf.getAuthorName("GetBTest");

    assertEquals(authorName, "Ryohei Takasawa");
  }

  @Test
  public void test2() {
    ComitterFinder cf = new ComitterFinder("C:/Project/TestSample/");
    cf.setFilePath("src/test/ProductTest.java");

    assertEquals(cf.getAuthorName("GetBTest"), "LAB-PC");
  }

}
