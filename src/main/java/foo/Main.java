package foo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    try {
      System.out.println("Root Directory : ");
      String root = br.readLine();
      ComitterFinder cf = new ComitterFinder(root);

      System.out.println("Data File Name : ");
      String name = br.readLine();
      cf.execute(name);

      ScoreCalculator sc = new ScoreCalculator(root + "output/");
      sc.calculate();

      sc.show();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
