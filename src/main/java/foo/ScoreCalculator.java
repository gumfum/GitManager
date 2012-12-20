package foo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ScoreCalculator {
	private String rootDir;
	
	public ScoreCalculator(String root) {
		rootDir = root;
	}
	
	public void calculate() {
		File outputDir = new File(rootDir);
		
		for(File file : outputDir.listFiles()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				
				while((line = br.readLine()) != null) {
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
