package example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainClass {
  public static void main(String[] args) {
    ApplicationContext context = new FileSystemXmlApplicationContext("resources\\dictionary-context.xml");
    Menu consoleMenu = (Menu) context.getBean("menu");
    consoleMenu.run();
  }
}
        /*{
        Dictionary word = new FileProperties("resources\\alpha1.txt", 4, "[0-9]", "Word");
        Dictionary num = new FileProperties("resources\\alpha2.txt", 5, "[a-zA-Z]", "Number");
        Menu consoleM = new ConsoleMenu(word, num);
        consoleM.run();
        }*/