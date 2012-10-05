package foo;

import java.io.File;

import org.eclipse.jgit.api.BlameCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.blame.BlameResult;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepository;

public class Hoge {
  public static void main(String args[]) {
    try {
      File file = new File("C:/TeamGamification/.git");
      if (file.exists()) {
        Repository repos = new FileRepository(file);
        Git git = new Git(repos);
        BlameCommand command = git.blame();

        String testFilePath = "GitManager/src/test/java/foo/TesterTest2.java";
        command.setFilePath(testFilePath);

        BlameResult result = command.call();
        result.computeAll();

        System.out.println(repos);
        System.out.println(git);
        System.out.println(command);
        System.out.println(result);


        int i = 0;
        while (result.computeNext() != 0) {
          System.out.println(i + " : " + result.getSourceLine(i) + " : "
              + result.getSourceCommit(i++));
        }
      }
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
