package foo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main2 {
  public static void main(String[] args) {
    String root = "";
    ComitterFinder cf = new ComitterFinder(root);

    try {
      FileReader in = new FileReader("sampleout.txt");
      BufferedReader br = new BufferedReader(in);
      String line;

      String fileName = new String("");
      String testName = new String("");
      while ((line = br.readLine()) != null) {
        String[] words = line.split(" ");

        // filename lines
        if (words[0].contains("****")) {
          fileName = words[1].replace('\\', '/').substring(0, words[1].length() - 1);
          testName = words[2].substring(words[2].lastIndexOf('.') + 1);
          System.out.println(fileName);
          System.out.println(testName);
        }
        // coverage data lines
        else if (words[0].contains(".java")) {
          System.out.println("data");
        }
        // empty lines
        else {
          System.out.println("empty line");
        }
      }

      br.close();
      in.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
