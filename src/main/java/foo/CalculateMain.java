package foo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalculateMain {
  public static void main(String[] args) {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    try {
      System.out.println("Root Directory : ");
      String root;
      root = br.readLine();

      ScoreCalculator sc = new ScoreCalculator(root + "output/");
      sc.calculate();

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
