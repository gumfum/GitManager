package foo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main2 {
  public static void main(String[] args) {
    try {
      FileReader in = new FileReader("out.txt");
      BufferedReader br = new BufferedReader(in);
      String line;

      while ((line = br.readLine()) != null) {
        System.out.println(line);
      }

      br.close();
      in.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
