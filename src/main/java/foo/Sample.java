package foo;

import java.io.File;

import org.eclipse.jgit.api.BlameCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LogCommand;
import org.eclipse.jgit.blame.BlameResult;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepository;

public class Sample {
  public static void main(String[] args) {
    try {
      File file = new File("c:/TeamGamification/.git");
      if (file.exists()) {
        Repository repos = new FileRepository(file);

        // git add
        // GitIndex index = repos.getIndex();
        // index.add(repos.getWorkDir(), new File(repos.getWorkDir(),
        // "test.txt"));
        // index.write();

        // git commit
        Git git = new Git(repos);
        // CommitCommand commit = git.commit();
        // commit.setMessage("コミット : " + new Date(0));
        // commit.call();

        // git log
        LogCommand log = git.log();

        // for (RevCommit revCommit : log.call()) {
        // System.out.print(revCommit.getFullMessage());
        // System.out.println(revCommit.getAuthorIdent().getName());
        // System.out.println();
        // }

        // git diff
        /*
         * DiffCommand diff = git.diff(); for (DiffEntry entry : diff.call()) {
         * System.out.println(entry); }
         */

        // git blame
        BlameCommand blame = git.blame();
        blame.setFilePath("/GitManager/src/main/java/foo/App.java");
        BlameResult result = blame.call();

        for (int i = 0; i < 5; i++) {
          result.computeNext();
          System.out.println(result.getSourceCommitter(i));
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
