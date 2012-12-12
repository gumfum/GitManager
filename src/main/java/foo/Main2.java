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

      String fileName = new String("");
      String testName = new String("");
      String authorName = new String("");
      boolean flag = false;

      while ((line = br.readLine()) != null) {
        String[] words = line.split(" ");

        // filename lines
        if (words[0].contains("****")) {
          flag = true;
          fileName = words[1].replace('\\', '/').substring(0, words[1].length() - 1);
          testName = words[2].substring(words[2].lastIndexOf('.') + 1);
          System.out.println(fileName);
          System.out.println(testName);

          cf.setFilePath(fileName);
          authorName = cf.getAuthorName(testName);
          System.out.println(authorName);

          cf.push(testName, authorName);
        }
        // coverage data lines
        else if (words[0].contains(".java")) {
          System.out.println("** data **");
          cf.pushOutput(authorName + " : " + line);
        }
        // empty lines
        else {
          System.out.println("** empty line **");
          if (flag) {
            cf.makeOutputFile(testName);
          }
          flag = false;
        }

        cf.makeAuthorList();
      }
      if (flag) {
        cf.makeOutputFile(testName);
      }

      br.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
