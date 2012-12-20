package foo;

public class Main {
  public static void main(String[] args) {
    String root = "C:/Project/TestSample/";
    ComitterFinder cf = new ComitterFinder(root);

    cf.execute("testout.txt");
  }
}
