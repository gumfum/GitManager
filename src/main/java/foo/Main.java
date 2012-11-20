package foo;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepository;

public class Main {
  public static void main(String[] args) {
    String path = "../GitManager/src/test/java/foo/";
    File dir = new File(path);

    File file = new File("C:/GitManager/.git");
    try {
      Repository repos = new FileRepository(file);
      Git git = new Git(repos);
      TesterDiscriminator td = new TesterDiscriminator(git);

      File[] files = dir.listFiles();

      for (int i = 0; i < files.length; i++) {
        /*
         * System.out.println(files[i]); System.out.println(files[i].getAbsolutePath());
         * System.out.println(files[i].getPath());
         */
        td.setFilePath(files[i].getPath());
        td.printTesterName();
      }

    } catch (Exception e) {

    }
  }
}
