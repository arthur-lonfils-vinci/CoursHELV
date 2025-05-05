public class Main {
  public static void main(String[] args) {
    // Create and display the interactive menu
    TestMenu menu = new TestMenu();
    TestRunner runner = new TestRunner();

    TestMenu.TestType selectedTest;
    do {
      selectedTest = menu.showMenu();
      runner.runTest(selectedTest);
    } while (selectedTest != TestMenu.TestType.EXIT);
  }
}