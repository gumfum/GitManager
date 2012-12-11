package foo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.jgit.api.BlameCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.blame.BlameResult;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepository;

public class ComitterFinder {
  private String root;
  private File gitFile;
  private Repository repository;
  private Git git;

  private BlameCommand command;
  private BlameResult result;
  private BufferedReader fileReader;

  ComitterFinder(Git git) {
    this.git = git;
    command = this.git.blame();
  }

  ComitterFinder(String root) {
    try {
      this.root = root;
      gitFile = new File(this.root + ".git");
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

  public void setFilePath(String path) {
    try {
      fileReader = new BufferedReader(new FileReader(root + path));
      command.setFilePath(path);
      result = command.call();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private int getMethodLine(String methodName) throws IOException {
    int lineNum = 0;
    String line;

    while ((line = fileReader.readLine()).contains(methodName)) {
      System.out.println(line);
      lineNum++;
    }

    return lineNum;
  }

  public String getAuthorName(String methodName) {
    int lineNum = -1;

    try {
      lineNum = getMethodLine(methodName);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return result.getSourceAuthor(lineNum).getName();
  }
}
