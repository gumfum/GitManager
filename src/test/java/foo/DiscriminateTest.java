package foo;

import static org.junit.Assert.*;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepository;
import org.junit.Test;

public class DiscriminateTest {
  @Test
  public void Test() {
    TesterDiscriminator td1 = new TesterDiscriminator("C:/GitManager/.git");
    File file = new File("C:/GitManager/.git");
    try {
      Repository repos = new FileRepository(file);
      Git git = new Git(repos);
      TesterDiscriminator td2 = new TesterDiscriminator(git);
      assertEquals(td1.getGitFile().length(), td2.getGitFile().length());
    } catch (Exception e) {

    }
  }
}
