package com.lib;
import com.lib.*;
public class MainClass {
  public static void main(String[] args){
	     Dictionary word = new FileProperties("resources\\alpha1.txt", 4, "[0-9]", "Word");
		 Dictionary num = new FileProperties("resources\\alpha2.txt", 5, "[a-zA-Z]", "Number");
		Menu consoleM = new ConsoleMenu(word, num);
		consoleM.run();
  }
}
