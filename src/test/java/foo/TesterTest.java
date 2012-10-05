package foo;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TesterTest {
  @Test
  public void Test1() {
    assertThat(2 * 5, is(10));
  }

  @Test
  public void Test2() {
    assertThat(true, is(true));
  }

  @Test
  public void hogehoge() {
    assertThat("hoge", is(new String("hoge")));
  }

  @Test
  public void foobar() {


  }

  @Test
  public void hoge() {
    assertThat(false, is(false));
  }
}
