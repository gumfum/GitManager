package foo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

  private List<String> list = new ArrayList<String>();

  private List<String> outputList = new ArrayList<String>();

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

      File outDir = new File(this.root + "output/");
      if (!outDir.exists()) {
        outDir.mkdir();
      }
    } catch (Exception e) {
      e.printStackTrace();
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

  public void makeOutputFile(String fileName, String testName) {
    String name = fileName.substring(fileName.lastIndexOf('/'), fileName.lastIndexOf('.'));
    String path = root + "output/" + name + "." + testName + ".output.txt";
    File outputFile = new File(path);
    try {
      PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));

      for (String str : outputList) {
        pw.println(str);
      }

      pw.close();
      outputList.clear();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void pushOutput(String line) {
    outputList.add(line);
  }

  public void push(String methodName, String authorName) {
    list.add(methodName);
    list.add(authorName);
  }

  public void clear() {
    list.clear();
  }

  public void makeAuthorList() {
    File file = new File(root + "output/auth.txt");
    try {
      PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

      for (int i = 0; i < list.size() / 2; ++i) {
        pw.print(list.get(2 * i) + " : ");
        pw.println(list.get(2 * i + 1));
      }

      pw.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
