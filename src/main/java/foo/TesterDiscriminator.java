package foo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.BlameCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.blame.BlameResult;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepository;

public class TesterDiscriminator {
  public static void main(String args[]) {

    try {
      File file = new File("c:/TeamGamification/.git");
      if (file.exists()) {
        Repository repos = new FileRepository(file);
        Git git = new Git(repos);

        // @Todo : ファイルパス
        String testFilePath = "../GitManager/src/test/java/foo/TesterTest2.java";
        BufferedReader fileReader = new BufferedReader(new FileReader(testFilePath));


        BlameCommand blame = git.blame();
        // ファイルパス注意
        testFilePath = testFilePath.substring(3, testFilePath.length());
        blame.setFilePath(testFilePath);
        BlameResult result = blame.call();

        String line;
        List<String> methodNames = new ArrayList<String>();
        List<String> authorNames = new ArrayList<String>();
        List<Integer> makeMethodLines = new ArrayList<Integer>();
        int lof = 0;
        while ((line = fileReader.readLine()) != null) {
          lof++;
          if (line.contains("@Test")) {
            do {
              line = fileReader.readLine();
              lof++;
            } while (line == null);
            makeMethodLines.add(lof);
            int epos;
            for (epos = 0; epos < line.length(); epos++) {
              if (line.charAt(epos) == '(') {
                break;
              }
            }
            while (line.charAt(epos) == ' ') {
              --epos;
            }
            int spos = epos;
            while (line.charAt(spos) != ' ') {
              --spos;
            }

            String methodName = line.substring(spos + 1, epos);
            methodNames.add(methodName);
          }
        }

        System.out.println(testFilePath);
        if (makeMethodLines.size() == 0) {
          System.out.println("No test cases.");
        } else {
          int pos = 0;
          for (int iLine = 0; iLine < lof; iLine++) {
            // System.out.println(result.getSourceAuthor(i).getName());
            System.out.println(iLine + ": " + result.lastLength() + ", " + result.computeNext()
                + ", " + result.getSourceCommitter(iLine) + result.getSourceAuthor(iLine)
                + result.lastLength());
            if (iLine == makeMethodLines.get(pos)) {
              authorNames.add(result.getSourceAuthor(iLine).getName());
              pos++;
              if (pos == methodNames.size()) {
                break;
              }
            }
          }
        }

        for (int i = 0; i < makeMethodLines.size(); i++) {
          System.out.println(methodNames.get(i));
          System.out.println("Author : " + authorNames.get(i));
        }

        repos.close();
      } else {
        System.out.println("No File...");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
