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
  private File gitFile;
  private Repository repository;
  private Git git;

  private BlameCommand command;
  private BlameResult result;
  private BufferedReader fileReader;

  TesterDiscriminator(Git git) {
    this.git = git;
    command = this.git.blame();
  }

  TesterDiscriminator(String path) {
    try {
      gitFile = new File(path);
      repository = new FileRepository(gitFile);
      git = new Git(repository);
    } catch (Exception e) {
      System.out.println("Error");
      return;
    }

    command = git.blame();
  }

  public File getGitGile() {
    return gitFile;
  }

  public void setFilePath(String filePath) {
    try {
      fileReader = new BufferedReader(new FileReader(filePath));

      command.setFilePath(filePath.substring(3).replace('\\', '/'));
      result = command.call();

      System.out.println("File : " + filePath.substring(3));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getTesterName() {
    String line;
    List<String> methodNames = new ArrayList<String>();
    List<String> authorNames = new ArrayList<String>();
    List<Integer> makeMethodLines = new ArrayList<Integer>();
  }

  public void printTesterName() {
    String line;
    List<String> methodNames = new ArrayList<String>();
    List<String> authorNames = new ArrayList<String>();
    List<Integer> makeMethodLines = new ArrayList<Integer>();

    try {
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

      if (makeMethodLines.size() == 0) {
        System.out.println("No test cases.");
      } else {
        int pos = 0;
        for (int iLine = 0; iLine < lof; iLine++) {
          // System.out.println(result.getSourceAuthor(i).getName());
          // System.out.println(iLine + ": " + result.lastLength() + ", " + result.computeNext()
          // + ", " + result.getSourceCommitter(iLine) + result.getSourceAuthor(iLine)
          // + result.lastLength());
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

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
