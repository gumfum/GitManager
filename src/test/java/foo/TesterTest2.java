package foo;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class TesterTest2 {
  @Test
  public void Test12() {
    assertThat(2 * 5, is(10));
  }

  @Test
  public void Test22() {
    assertThat(true, is(true));
  }

  @Test
  public void hogehoge2() {
    assertThat("hoge", is(new String("hoge")));
  }

  @Test
  public void nothing() {}


  @Test
  public void lab() {

  }
}
