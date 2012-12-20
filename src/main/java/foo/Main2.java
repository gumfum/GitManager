package foo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main2 {
  public static void main(String[] args) {
    String root = "C:/Project/TestSample/";
    ComitterFinder cf = new ComitterFinder(root);

    try {
      BufferedReader br = new BufferedReader(new FileReader(root + "testout.txt"));
      String line;

      String testFileName = new String("");
      String testMethodName = new String("");
      String authorName = new String("");

      while ((line = br.readLine()) != null) {
        String[] words = line.split(" ");

        // filename lines
        if (words[0].contains("****")) {
          testFileName = words[1].replace('\\', '/').substring(0, words[1].length() - 1);
          testMethodName = words[2].substring(words[2].lastIndexOf('.') + 1);
          System.out.println(testFileName);
          System.out.println(testMethodName);

          cf.setFilePath(testFileName);
          authorName = cf.getAuthorName(testMethodName);
          System.out.println(authorName);

          cf.push(testMethodName, authorName);
        }
        // coverage data lines
        else if (words[0].contains(".java")) {
          System.out.println("** data **");
          cf.output(authorName, line);
        }
        // empty lines
        else {
          System.out.println("** empty line **");
        }

        cf.makeAuthorList();
      }

      br.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
