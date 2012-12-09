package foo;

import java.io.BufferedReader;
import java.io.File;

import org.eclipse.jgit.api.BlameCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.blame.BlameResult;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepository;

public class Discriminator {
  private File gitFile;
  private Repository repository;
  private Git git;

  private BlameCommand command;
  private BlameResult result;
  private BufferedReader fileReader;

  Discriminator(Git git) {
    this.git = git;
    command = this.git.blame();
  }

  Discriminator(String path) {
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

  public File getGitFile() {
    return gitFile;
  }

  public void getAuthorName() {

  }
}
