import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the interactive menu for selecting and running tree tests
 */
public class TestMenu {
  private static final String MENU_CURSOR = "→ ";
  private static final String MENU_SPACE = "  ";

  // Test options
  private List<TestOption> testOptions;
  private int selectedIndex = 0;

  /**
   * Constructor - initialize the test options
   */
  public TestMenu() {
    testOptions = new ArrayList<>();
    testOptions.add(new TestOption("Run All Tests", TestType.ALL));

    // Example Methods
    testOptions.add(new TestOption("--- EXAMPLE METHODS ---", null));
    testOptions.add(new TestOption("Tree Display Test", TestType.DISPLAY));
    testOptions.add(new TestOption("Number of Leaves Test", TestType.NBR_LEAVES));
    testOptions.add(new TestOption("Flatten Leaves Test", TestType.FLATTEN));
    testOptions.add(new TestOption("Path Tests", TestType.PATH));

    // Exercise 1
    testOptions.add(new TestOption("--- EXERCISE 1 ---", null));
    testOptions.add(new TestOption("Number of Nodes Test (1.1)", TestType.NBR_NODE));
    testOptions.add(new TestOption("Minimum Value Test (1.2)", TestType.MIN));
    testOptions.add(new TestOption("Sum Test (1.3)", TestType.SUM));
    testOptions.add(new TestOption("Equals Test (1.4)", TestType.EQUALS));
    testOptions.add(new TestOption("Depth Test (1.5)", TestType.DEPTH));
    testOptions.add(new TestOption("Same Node Test (1.6)", TestType.SAME_ONE));
    testOptions.add(new TestOption("DFS Print Test (1.7)", TestType.DFS));
    testOptions.add(new TestOption("BFS Print Test (1.8)", TestType.BFS));

    // Exercise 2
    testOptions.add(new TestOption("--- EXERCISE 2 ---", null));
    testOptions.add(new TestOption("Path Printing Tests (2.1-2.3)", TestType.EX2_PATHS));

    // Exercise 3
    testOptions.add(new TestOption("--- EXERCISE 3 ---", null));
    testOptions.add(new TestOption("Array Conversion Test (3.1-3.2)", TestType.ARRAY));

    // Exercise 4
    testOptions.add(new TestOption("--- EXERCISE 4 ---", null));
    testOptions.add(new TestOption("Lowest Common Ancestor Test (4)", TestType.LCA));

    testOptions.add(new TestOption("Exit", TestType.EXIT));
  }

  /**
   * Display and handle the test menu
   *
   * @return The selected test type
   */
  public TestType showMenu() {
    boolean menuActive = true;

    // Alternative approach using Scanner
    java.util.Scanner scanner = new java.util.Scanner(System.in);

    while (menuActive) {
      clearScreen();
      displayMenu();

      // Count valid options (exclude headers)
      int validOptions = 0;
      for (TestOption option : testOptions) {
        if (option.getType() != null) {
          validOptions++;
        }
      }

      System.out.print("Enter option number (1-" + validOptions + "): ");
      String input = scanner.nextLine().trim();

      try {
        if (input.equalsIgnoreCase("q")) {
          return TestType.EXIT;
        }

        // Check for w/s/up/down input for selection movement
        if (input.equalsIgnoreCase("w") || input.equalsIgnoreCase("up")) {
          moveSelection(-1);
          continue;
        } else if (input.equalsIgnoreCase("s") || input.equalsIgnoreCase("down")) {
          moveSelection(1);
          continue;
        }

        // Try to parse as number
        int option = Integer.parseInt(input);

        // Map the option number to the actual index
        if (option >= 1 && option <= validOptions) {
          int count = 0;
          for (int i = 0; i < testOptions.size(); i++) {
            if (testOptions.get(i).getType() != null) {
              count++;
              if (count == option) {
                selectedIndex = i;
                TestType type = testOptions.get(selectedIndex).getType();
                if (type != null) {
                  return type;
                }
                break;
              }
            }
          }
        } else {
          System.out.println(TreeDisplay.RED + "Invalid option. Please try again." + TreeDisplay.RESET);
          waitForKeyPress();
        }
      } catch (NumberFormatException e) {
        // If not a valid number, check if it's Enter (empty input)
        if (input.isEmpty()) {
          TestType type = testOptions.get(selectedIndex).getType();
          if (type != null) {
            return type;
          }
        } else {
          System.out.println(TreeDisplay.RED + "Invalid input. Please enter a number or press Enter to select." + TreeDisplay.RESET);
          waitForKeyPress();
        }
      }
    }

    return TestType.EXIT;
  }

  /**
   * Move the menu selection up or down
   *
   * @param direction -1 for up, 1 for down
   */
  private void moveSelection(int direction) {
    do {
      selectedIndex += direction;

      // Wrap around when reaching the edges
      if (selectedIndex < 0) {
        selectedIndex = testOptions.size() - 1;
      } else if (selectedIndex >= testOptions.size()) {
        selectedIndex = 0;
      }

      // Skip section headers
    } while (testOptions.get(selectedIndex).getType() == null);
  }

  /**
   * Display the menu with current selection highlighted
   */
  private void displayMenu() {
    System.out.println(TreeDisplay.BOLD + TreeDisplay.BLUE + "╔══════════════════════════════════╗" + TreeDisplay.RESET);
    System.out.println(TreeDisplay.BOLD + TreeDisplay.BLUE + "║      TREE TESTING FRAMEWORK      ║" + TreeDisplay.RESET);
    System.out.println(TreeDisplay.BOLD + TreeDisplay.BLUE + "╚══════════════════════════════════╝" + TreeDisplay.RESET);
    System.out.println(TreeDisplay.YELLOW + "Enter option number, use 'w'/'s' to navigate, or press Enter to select current option" + TreeDisplay.RESET);
    System.out.println();

    int validOptionCount = 0;
    for (int i = 0; i < testOptions.size(); i++) {
      TestOption option = testOptions.get(i);

      // Check if this is a section header (null type)
      if (option.getType() == null) {
        System.out.println(TreeDisplay.BOLD + TreeDisplay.CYAN + option.getName() + TreeDisplay.RESET);
      } else {
        validOptionCount++;
        if (i == selectedIndex) {
          System.out.println(TreeDisplay.BOLD + TreeDisplay.GREEN + validOptionCount + ". " + MENU_CURSOR +
                  option.getName() + TreeDisplay.RESET);
        } else {
          System.out.println(TreeDisplay.BOLD + validOptionCount + ". " + TreeDisplay.RESET + MENU_SPACE + option.getName());
        }
      }
    }
  }

  /**
   * Clear the console screen
   */
  private void clearScreen() {
    // This might not work in all terminals, but it's a common way to clear the screen
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  /**
   * Wait for a key press
   */
  private void waitForKeyPress() {
    System.out.println(TreeDisplay.YELLOW + "Press Enter to continue..." + TreeDisplay.RESET);
    try {
      System.in.read();
      // Clear input buffer
      while (System.in.available() > 0) {
        System.in.read();
      }
    } catch (IOException e) {
      // Ignore
    }
  }

  /**
   * Inner class to represent a test menu option
   */
  private static class TestOption {
    private String name;
    private TestType type;

    public TestOption(String name, TestType type) {
      this.name = name;
      this.type = type;
    }

    public String getName() {
      return name;
    }

    public TestType getType() {
      return type;
    }
  }

  /**
   * Enum representing different test types
   */
  public enum TestType {
    ALL,
    DISPLAY,
    NBR_LEAVES,
    NBR_NODE,
    MIN,
    FLATTEN,
    PATH,
    SUM,
    EQUALS,
    DEPTH,
    SAME_ONE,
    DFS,
    BFS,
    ARRAY,
    LCA,
    EX2_PATHS,
    EXIT
  }
}