package foo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main2 {
  public static void main(String[] args) {
    try {
      FileReader in = new FileReader("sampleout.txt");
      BufferedReader br = new BufferedReader(in);
      String line;

      while ((line = br.readLine()) != null) {
        String[] words = line.split(" ");

        if (words[0].contains("****")) {
          String fileName = words[1].replace('\\', '/');
          String testName = words[2];
          System.out.println(fileName);
          System.out.println(testName);
        } else if (words[0] != null) {
          line = line.replace('\\', '/');
          System.out.println(line);
        } else {
          continue;
        }
      }

      br.close();
      in.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
