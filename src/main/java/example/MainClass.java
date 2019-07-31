package example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MainClass {
  public static void main(String[] args) {


    ApplicationContext context = new FileSystemXmlApplicationContext("resources\\dictionary-context.xml");
    Menu consoleMenu = context.getBean(Menu.class);
    consoleMenu.run();
  }
}