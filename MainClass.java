public class MainClass {
  public static void main(String[] args){
	     Dictionary word = new FileProperties("alpha1.txt", 4, "[0-9]", "Word");
		 Dictionary num = new FileProperties("alpha2.txt", 5, "[a-zA-Z]", "Number");
		Menu consoleM = new ConsoleMenu(word, num);
		consoleM.run();
  }
}
