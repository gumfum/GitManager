package foo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

public class ScoreCalculator {
  private String rootDir;
  private HashMap<String, Integer> scoreBoard = new HashMap<String, Integer>();

  public ScoreCalculator(String root) {
    rootDir = root;
  }

  public void calculate() {
    File outputDir = new File(rootDir);
    System.out.println("*** Calc ***");

    for (File file : outputDir.listFiles()) {
      try {
        BufferedReader br = new BufferedReader(new FileReader(file));
        HashMap<String, Integer> tmpScoreBoard = new HashMap<String, Integer>();
        HashMap<Integer, Integer> linePassCount = new HashMap<Integer, Integer>();
        HashMap<Integer, String> lineAuthorName = new HashMap<Integer, String>();

        String line;
        while ((line = br.readLine()) != null) {
          String words[] = line.split(":");
          String authorName = words[0];

          if (!scoreBoard.containsKey(authorName)) {
            scoreBoard.put(authorName, 0);
          }
          if (!tmpScoreBoard.containsKey(authorName)) {
            tmpScoreBoard.put(authorName, 0);
          }

          if (words[1].contains("-")) {
            // multiple lines
            String words2[] = words[1].split("-");
            int sta = Integer.parseInt(words2[0]);
            int end = Integer.parseInt(words2[1]);

            for (int j = sta; j <= end; j++) {
              int score = tmpScoreBoard.get(authorName);
              tmpScoreBoard.put(authorName, score + 10);
              lineAuthorName.put(j, authorName);

              if (!linePassCount.containsKey(j)) {
                linePassCount.put(j, 1);
              } else {
                int key = linePassCount.get(j);
                linePassCount.put(j, key + 1);
              }
            }
          } else {
            // single line
            int num = Integer.parseInt(words[1]);
            int score = tmpScoreBoard.get(authorName);
            tmpScoreBoard.put(authorName, score + 10);
            lineAuthorName.put(num, authorName);

            if (!linePassCount.containsKey(num)) {
              linePassCount.put(num, 1);
            } else {
              int key = linePassCount.get(num);
              linePassCount.put(num, key + 1);
            }
          }
        }
        Iterator it = linePassCount.keySet().iterator();
        while (it.hasNext()) {
          Object o = it.next();
          if (linePassCount.get(o) == 1) {
            String author = lineAuthorName.get(o);
            int score = tmpScoreBoard.get(author);
            tmpScoreBoard.put(author, score + 10);
          }
        }

        String origName = file.getName();
        String tempName = origName.substring(0, origName.lastIndexOf("."));
        String fileName = tempName.substring(0, tempName.lastIndexOf(".")) + ".result.txt";
        System.out.println(fileName);

        File outputFile = new File(rootDir + fileName);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));

        it = tmpScoreBoard.keySet().iterator();
        while (it.hasNext()) {
          // update score board
          Object o = it.next();

          int tmpScore = tmpScoreBoard.get(o);
          System.out.println(o + " : " + tmpScore);
          pw.println(o + " : " + tmpScore);

          int score = tmpScore + scoreBoard.get(o);
          scoreBoard.put((String) o, score);
        }

        br.close();
        pw.close();
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  public void show() {
    Iterator it = scoreBoard.keySet().iterator();
    System.out.println("**** RESULT ****");
    while (it.hasNext()) {
      Object o = it.next();
      System.out.println(o + " : " + scoreBoard.get(o));
    }
  }

  public void outputResult() {
    try {
      File file = new File(rootDir + ".result.txt");
      PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

      Iterator it = scoreBoard.keySet().iterator();
      while (it.hasNext()) {
        Object o = it.next();
        pw.println(o + " : " + scoreBoard.get(o));
      }

      pw.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
}
