package com.altf4studios.corebringer.screens.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class JournalWindow extends Window {
    public JournalWindow(final Stage stage, final Skin skin) {
        super("Java Tutorial Journal", skin);
        this.getTitleLabel().setAlignment(Align.center);
        this.getTitleTable().padLeft(20).padRight(20);
        this.setModal(true);
        this.setMovable(false);
        this.pad(-40);
        // WINDOW SIZE AND POSITION:
        // - setSize(1200, 900): window dimensions (width x height)
        // - setPosition: window position (x, y coordinates)
        //   - x: Gdx.graphics.getWidth() / 2 - 800
        //   - y: Gdx.graphics.getHeight() / 2 - 800
        this.setSize(1400, 900);
        this.setPosition(
            Gdx.graphics.getWidth() / 2 - 800,
            Gdx.graphics.getHeight() / 2 - 800
        );
        Texture optionBG = new Texture(Gdx.files.internal("ui/optionsBG.png"));
        Drawable optionBGDrawable = new TextureRegionDrawable(new TextureRegion(optionBG));
        this.background(optionBGDrawable);
        this.setColor(1, 1, 1, 1);

        Table contentTable = new Table();
        contentTable.setFillParent(true);

        // Create sidebar with title buttons
        // SIDEBAR BUTTONS MEASUREMENTS:
        // - pad: 8 (vertical spacing between buttons)
        // - padLeft: 15 (left margin from window edge)
        // - width: 180 (button width)
        // - height: 45 (button height)
        Table sidebarTable = new Table();
        sidebarTable.top().left().padTop(195);
        sidebarTable.defaults().pad(20).padLeft(120).width(180).height(45);

        TextButton btnJava = new TextButton("Java", skin);
        TextButton btnCards = new TextButton("Cards", skin);
        TextButton btnGame = new TextButton("Game Logic", skin);
        TextButton btnClose = new TextButton("Close Journal", skin);

        btnJava.setColor(0.8f, 0.9f, 1.0f, 1.0f);
        btnCards.setColor(0.8f, 0.9f, 1.0f, 1.0f);
        btnGame.setColor(0.8f, 0.9f, 1.0f, 1.0f);
        btnClose.setColor(1.0f, 0.7f, 0.7f, 1.0f);

        sidebarTable.add(btnJava).row();
        sidebarTable.add(btnCards).row();
        sidebarTable.add(btnGame).row();
        sidebarTable.add(btnClose).row();

        // Create main content area with topic buttons and tutorial content
        Table mainContentTable = new Table();
        mainContentTable.top().left();

        // Topic buttons table
        // TOPIC BUTTONS MEASUREMENTS:
        // - pad: 6 (vertical spacing between buttons)
        // - width: 280 (button width)
        // - height: 35 (button height)
        Table topicButtonTable = new Table();
        topicButtonTable.top().left().padLeft(15).padTop(200);
        topicButtonTable.defaults().pad(15).width(420).height(35);

        TextButton btnVariables = new TextButton("Variables & Data Types", skin);
        TextButton btnLoops = new TextButton("Loops", skin);
        TextButton btnOOP = new TextButton("OOP", skin);
        TextButton btnArrays = new TextButton("Arrays & Collections", skin);
        TextButton btnMethods = new TextButton("Methods & Functions", skin);
        TextButton btnExceptions = new TextButton("Exception Handling", skin);

        btnVariables.setColor(0.9f, 1.0f, 0.9f, 1.0f);
        btnLoops.setColor(0.9f, 1.0f, 0.9f, 1.0f);
        btnOOP.setColor(0.9f, 1.0f, 0.9f, 1.0f);
        btnArrays.setColor(0.9f, 1.0f, 0.9f, 1.0f);
        btnMethods.setColor(0.9f, 1.0f, 0.9f, 1.0f);
        btnExceptions.setColor(0.9f, 1.0f, 0.9f, 1.0f);

        topicButtonTable.add(btnVariables).row();
        topicButtonTable.add(btnLoops).row();
        topicButtonTable.add(btnOOP).row();
        topicButtonTable.add(btnArrays).row();
        topicButtonTable.add(btnMethods).row();
        topicButtonTable.add(btnExceptions).row();

        // Hide topic buttons by default
        topicButtonTable.setVisible(false);

        final TextArea tutorialContent = new TextArea("Welcome to the Java Tutorial Journal!\n\nSelect a category from the left sidebar to get started!\n\nAvailable categories:\n• Java - Programming concepts and tutorials\n• Cards - Game card system explanation\n• Game Logic - How programming integrates with gameplay", skin);
        tutorialContent.setDisabled(true);
        tutorialContent.setPrefRows(150);
        ScrollPane contentScroll = new ScrollPane(tutorialContent, skin);
        contentScroll.setFadeScrollBars(false);
        contentScroll.setScrollBarPositions(false, true);

        // LAYOUT SPACING MEASUREMENTS:
        // - padRight(15): spacing between topic buttons and content area
        // - size(480, 580): content scroll area dimensions (width x height)
        // - padRight(30): right margin of content area
        mainContentTable.add(topicButtonTable).top().left().padRight(20);
        mainContentTable.add(contentScroll).size(480, 580).top().left().padRight(30).padTop(150);

        // OVERALL LAYOUT SPACING:
        // - padRight(15): spacing between sidebar and main content
        // - pad(15): overall window padding
        contentTable.add(sidebarTable).top().left().padRight(15);
        contentTable.add(mainContentTable).grow().top().left();
        this.add(contentTable).grow().pad(15);

        // Sidebar button listeners
        btnJava.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Toggle topic buttons visibility
                boolean isVisible = topicButtonTable.isVisible();
                topicButtonTable.setVisible(!isVisible);

                if (!isVisible) {
                    // Show topic buttons and display Java overview
                    String content = "JAVA PROGRAMMING BASICS\n" +
                        "=======================\n\n" +
                        "Java is a powerful, object-oriented programming language.\n\n" +
                        "AVAILABLE TOPICS:\n" +
                        "• Variables & Data Types - Learn about primitive and reference types\n" +
                        "• Loops - Control flow with for, while, and do-while loops\n" +
                        "• OOP - Object-Oriented Programming concepts\n" +
                        "• Arrays & Collections - Working with data structures\n" +
                        "• Methods & Functions - Creating reusable code blocks\n" +
                        "• Exception Handling - Managing errors and exceptions\n\n" +
                        "Select a specific topic from the buttons to the right to get started!";
                    tutorialContent.setText(content);
                } else {
                    // Hide topic buttons and show welcome message
                    String content = "Welcome to the Java Tutorial Journal!\n\nSelect a category from the left sidebar to get started!\n\nAvailable categories:\n• Java - Programming concepts and tutorials\n• Cards - Game card system explanation\n• Game Logic - How programming integrates with gameplay";
                    tutorialContent.setText(content);
                }
            }
        });

        btnCards.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Hide topic buttons when Cards is selected
                topicButtonTable.setVisible(false);

                String content = "CARD SYSTEM IN COREBRINGER\n" +
                    "===========================\n\n" +
                    "The card system is the core gameplay mechanic of CoreBringer.\n\n" +
                    "CARD TYPES:\n" +
                    "• Attack Cards - Deal damage to enemies\n" +
                    "• Status Cards - Apply buffs or debuffs\n" +
                    "• Utility Cards - Provide special effects\n\n" +
                    "CARD MECHANICS:\n" +
                    "• Each card has specific effects and costs\n" +
                    "• Cards can be combined for powerful combinations\n" +
                    "• Strategic card play is key to victory\n\n" +
                    "PROGRAMMING CONNECTION:\n" +
                    "Cards are implemented using Java classes and objects.\n" +
                    "Understanding OOP concepts helps in creating custom cards!";
                tutorialContent.setText(content);
            }
        });

        btnGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Hide topic buttons when Game Logic is selected
                topicButtonTable.setVisible(false);

                String content = "GAME LOGIC & PROGRAMMING\n" +
                    "=========================\n\n" +
                    "CoreBringer combines programming concepts with gameplay.\n\n" +
                    "GAME SYSTEMS:\n" +
                    "• Battle System - Turn-based combat with cards\n" +
                    "• Code Editor - Write and execute Java code\n" +
                    "• Map System - Navigate through different areas\n" +
                    "• Character Progression - Level up and gain new abilities\n\n" +
                    "PROGRAMMING INTEGRATION:\n" +
                    "• Write Java code to solve programming challenges\n" +
                    "• Use variables, loops, and methods in your solutions\n" +
                    "• Learn programming concepts through gameplay\n\n" +
                    "TIPS:\n" +
                    "• Start with simple code snippets\n" +
                    "• Practice with the tutorial topics\n" +
                    "• Experiment with different approaches";
                tutorialContent.setText(content);
            }
        });

        btnClose.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                JournalWindow.this.remove();
            }
        });

        btnVariables.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String content = "VARIABLES & DATA TYPES IN JAVA\n" +
                    "=====================================\n\n" +
                    "Variables are containers that store data values in Java.\n\n" +
                    "PRIMITIVE DATA TYPES:\n" +
                    "• int: 32-bit integer (-2,147,483,648 to 2,147,483,647)\n" +
                    "• long: 64-bit integer\n" +
                    "• float: 32-bit floating point\n" +
                    "• double: 64-bit floating point\n" +
                    "• boolean: true or false\n" +
                    "• char: single Unicode character\n" +
                    "• byte: 8-bit integer (-128 to 127)\n" +
                    "• short: 16-bit integer\n\n" +
                    "REFERENCE DATA TYPES:\n" +
                    "• String: sequence of characters\n" +
                    "• Arrays: collections of elements\n" +
                    "• Classes: custom data types\n\n" +
                    "DECLARATION EXAMPLES:\n" +
                    "int age = 25;\n" +
                    "String name = \"John Doe\";\n" +
                    "double salary = 50000.50;\n" +
                    "boolean isStudent = true;\n\n" +
                    "VARIABLE NAMING RULES:\n" +
                    "• Start with letter, underscore, or dollar sign\n" +
                    "• Can contain letters, digits, underscore, dollar sign\n" +
                    "• Case sensitive\n" +
                    "• Cannot use Java keywords\n" +
                    "• Use camelCase convention (e.g., firstName)\n\n" +
                    "✅ DOS:\n" +
                    "• Use meaningful variable names (age, userName, isActive)\n" +
                    "• Initialize variables when declaring them\n" +
                    "• Use appropriate data types for your needs\n" +
                    "• Follow camelCase naming convention\n" +
                    "• Use final for constants (final int MAX_SIZE = 100)\n\n" +
                    "❌ DON'TS:\n" +
                    "• Don't use single letters (a, b, c) for variable names\n" +
                    "• Don't use reserved keywords (class, public, static)\n" +
                    "• Don't use confusing names (l1, O0, temp1)\n" +
                    "• Don't mix naming conventions in the same project\n" +
                    "• Don't declare variables without initializing them when possible";
                tutorialContent.setText(content);
            }
        });
        btnLoops.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String content = "LOOPS & CONTROL FLOW IN JAVA\n" +
                    "================================\n\n" +
                    "Loops allow you to execute code blocks multiple times.\n\n" +
                    "FOR LOOP:\n" +
                    "for (int i = 0; i < 5; i++) {\n    System.out.println(\"Count: \" + i);\n}\n\n" +
                    "WHILE LOOP:\n" +
                    "int count = 0;\nwhile (count < 5) {\n    System.out.println(\"Count: \" + count);\n    count++;\n}\n\n" +
                    "DO-WHILE LOOP:\n" +
                    "int num = 1;\ndo {\n    System.out.println(\"Number: \" + num);\n    num++;\n} while (num <= 5);\n\n" +
                    "ENHANCED FOR LOOP (for arrays):\n" +
                    "String[] fruits = {\"Apple\", \"Banana\", \"Orange\"};\nfor (String fruit : fruits) {\n    System.out.println(fruit);\n}\n\n" +
                    "CONTROL STATEMENTS:\n" +
                    "• break: exits the loop\n" +
                    "• continue: skips current iteration\n" +
                    "• return: exits the method\n\n" +
                    "NESTED LOOPS:\n" +
                    "for (int i = 1; i <= 3; i++) {\n    for (int j = 1; j <= 3; j++) {\n        System.out.print(i * j + \" \" );\n    }\n    System.out.println();\n}\n\n" +
                    "✅ DOS:\n" +
                    "• Use for loops when you know the number of iterations\n" +
                    "• Use while loops for condition-based repetition\n" +
                    "• Always ensure loop conditions will eventually become false\n" +
                    "• Use meaningful loop variable names (i, j, k are acceptable for simple loops)\n" +
                    "• Use enhanced for loops when iterating through collections\n\n" +
                    "❌ DON'TS:\n" +
                    "• Don't create infinite loops (while(true) without break)\n" +
                    "• Don't modify loop variables inside enhanced for loops\n" +
                    "• Don't use loops when simple if-else would suffice\n" +
                    "• Don't forget to increment/decrement loop counters in while loops\n" +
                    "• Don't use nested loops unnecessarily (can cause performance issues)";
                tutorialContent.setText(content);
            }
        });
        btnOOP.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String content = "OBJECT-ORIENTED PROGRAMMING IN JAVA\n" +
                    "==========================================\n\n" +
                    "OOP is a programming paradigm based on objects.\n\n" +
                    "CORE CONCEPTS:\n" +
                    "1. ENCAPSULATION: Bundling data and methods\n" +
                    "2. INHERITANCE: Creating new classes from existing ones\n" +
                    "3. POLYMORPHISM: Same interface, different implementations\n" +
                    "4. ABSTRACTION: Hiding complex implementation details\n\n" +
                    "CLASS DEFINITION:\n" +
                    "public class Student {\n    // Private fields (encapsulation)\n    private String name;\n    private int age;\n\n    // Constructor\n    public Student(String name, int age) {\n        this.name = name;\n        this.age = age;\n    }\n\n    // Getter methods\n    public String getName() { return name; }\n    public int getAge() { return age; }\n\n    // Setter methods\n    public void setName(String name) { this.name = name; }\n    public void setAge(int age) { this.age = age; }\n}\n\n" +
                    "INHERITANCE EXAMPLE:\n" +
                    "public class GraduateStudent extends Student {\n    private String major;\n\n    public GraduateStudent(String name, int age, String major) {\n        super(name, age); // Call parent constructor\n        this.major = major;\n    }\n}\n\n" +
                    "INTERFACE EXAMPLE:\n" +
                    "public interface Drawable {\n    void draw();\n    void erase();\n}\n\n" +
                    "✅ DOS:\n" +
                    "• Use private fields and public methods (encapsulation)\n" +
                    "• Create meaningful class names (Student, Car, BankAccount)\n" +
                    "• Use inheritance for 'is-a' relationships\n" +
                    "• Implement interfaces for 'can-do' relationships\n" +
                    "• Use this keyword to distinguish between instance and parameter variables\n\n" +
                    "❌ DON'TS:\n" +
                    "• Don't make fields public (breaks encapsulation)\n" +
                    "• Don't use inheritance for 'has-a' relationships (use composition)\n" +
                    "• Don't create classes with too many responsibilities\n" +
                    "• Don't forget to call super() in constructors when extending classes\n" +
                    "• Don't use inheritance just to reuse code";
                tutorialContent.setText(content);
            }
        });
        btnArrays.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String content = "ARRAYS & COLLECTIONS IN JAVA\n" +
                    "==============================\n\n" +
                    "Arrays store multiple values of the same type.\n\n" +
                    "ARRAY DECLARATION:\n" +
                    "// Declare and initialize\nint[] numbers = {1, 2, 3, 4, 5};\n\n// Declare with size\nint[] scores = new int[10];\n\n// Access elements (0-based indexing)\nint first = numbers[0]; // 1\nint last = numbers[numbers.length - 1]; // 5\n\n" +
                    "MULTIDIMENSIONAL ARRAYS:\n" +
                    "int[][] matrix = {\n    {1, 2, 3},\n    {4, 5, 6},\n    {7, 8, 9}\n};\n\n" +
                    "ARRAY METHODS:\n" +
                    "// Sort array\nArrays.sort(numbers);\n\n// Fill array with value\nArrays.fill(scores, 0);\n\n// Copy array\nint[] copy = Arrays.copyOf(numbers, numbers.length);\n\n" +
                    "COLLECTIONS FRAMEWORK:\n" +
                    "// ArrayList (dynamic array)\nArrayList<String> names = new ArrayList<>();\nnames.add(\"Alice\");\nnames.add(\"Bob\");\nnames.remove(0);\n\n// HashMap (key-value pairs)\nHashMap<String, Integer> ages = new HashMap<>();\nages.put(\"Alice\", 25);\nages.put(\"Bob\", 30);\nint aliceAge = ages.get(\"Alice\"); // 25\n\n// HashSet (unique elements)\nHashSet<String> uniqueNames = new HashSet<>();\nuniqueNames.add(\"Alice\");\nuniqueNames.add(\"Alice\"); // Won't add duplicate\n\n" +
                    "ITERATING OVER COLLECTIONS:\n" +
                    "for (String name : names) {\n    System.out.println(name);\n}\n\n" +
                    "✅ DOS:\n" +
                    "• Use ArrayList when you need dynamic sizing\n" +
                    "• Use HashMap for key-value pair storage\n" +
                    "• Always check array bounds before accessing elements\n" +
                    "• Use enhanced for loops when you don't need the index\n" +
                    "• Initialize collections with appropriate initial capacity when known\n\n" +
                    "❌ DON'TS:\n" +
                    "• Don't access array elements beyond the array length\n" +
                    "• Don't use arrays when you need dynamic sizing frequently\n" +
                    "• Don't modify collections while iterating (use Iterator)\n" +
                    "• Don't use raw types (ArrayList instead of ArrayList<String>)\n" +
                    "• Don't forget that arrays are 0-indexed";
                tutorialContent.setText(content);
            }
        });
        btnMethods.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String content = "METHODS & FUNCTIONS IN JAVA\n" +
                    "============================\n\n" +
                    "Methods are blocks of code that perform specific tasks.\n\n" +
                    "METHOD STRUCTURE:\n" +
                    "accessModifier returnType methodName(parameters) {\n    // method body\n    return value; // if not void\n}\n\n" +
                    "BASIC METHOD EXAMPLES:\n" +
                    "// Simple method with no parameters\npublic void sayHello() {\n    System.out.println(\"Hello, World!\");\n}\n\n" +
                    "// Method with parameters and return value\npublic int add(int a, int b) {\n    return a + b;\n}\n\n" +
                    "// Method with multiple parameters\npublic String createGreeting(String name, int age) {\n    return \"Hello \" + name + \", you are \" + age + \" years old.\";\n}\n\n" +
                    "METHOD OVERLOADING:\n" +
                    "public int multiply(int a, int b) { return a * b; }\npublic double multiply(double a, double b) { return a * b; }\npublic int multiply(int a, int b, int c) { return a * b * c; }\n\n" +
                    "RECURSION EXAMPLE:\n" +
                    "public int factorial(int n) {\n    if (n <= 1) { return 1; }\n    return n * factorial(n - 1);\n}\n\n" +
                    "VARARGS (Variable Arguments):\n" +
                    "public int sum(int... numbers) {\n    int total = 0;\n    for (int num : numbers) { total += num; }\n    return total;\n}\n\n// Usage:\nint result1 = sum(1, 2, 3); // 6\nint result2 = sum(1, 2, 3, 4, 5); // 15\n\n" +
                    "✅ DOS:\n" +
                    "• Use descriptive method names (calculateTotal, validateInput)\n" +
                    "• Keep methods focused on a single responsibility\n" +
                    "• Use appropriate return types (void for actions, specific types for calculations)\n" +
                    "• Include parameter validation when necessary\n" +
                    "• Use method overloading for similar functionality with different parameters\n\n" +
                    "❌ DON'TS:\n" +
                    "• Don't create methods that are too long (keep under 50 lines)\n" +
                    "• Don't use void methods when you need to return a value\n" +
                    "• Don't forget to handle edge cases in recursive methods\n" +
                    "• Don't use too many parameters (consider using objects)\n" +
                    "• Don't create methods that do multiple unrelated things";
                tutorialContent.setText(content);
            }
        });
        btnExceptions.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String content = "EXCEPTION HANDLING IN JAVA\n" +
                    "============================\n\n" +
                    "Exceptions are events that disrupt normal program flow.\n\n" +
                    "TYPES OF EXCEPTIONS:\n" +
                    "1. CHECKED EXCEPTIONS: Must be handled (IOException, SQLException)\n" +
                    "2. UNCHECKED EXCEPTIONS: Runtime exceptions (NullPointerException, ArrayIndexOutOfBoundsException)\n" +
                    "3. ERRORS: Serious problems (OutOfMemoryError, StackOverflowError)\n\n" +
                    "BASIC TRY-CATCH BLOCK:\n" +
                    "try {\n    // Code that might throw an exception\n    int result = 10 / 0;\n} catch (ArithmeticException e) {\n    // Handle the exception\n    System.out.println(\"Error: \" + e.getMessage());\n}\n\n" +
                    "MULTIPLE CATCH BLOCKS:\n" +
                    "try {\n    // Risky code\n    int[] numbers = {1, 2, 3};\n    int value = numbers[5];\n} catch (ArrayIndexOutOfBoundsException e) {\n    System.out.println(\"Array index out of bounds: \" + e.getMessage());\n} catch (Exception e) {\n    System.out.println(\"General error: \" + e.getMessage());\n}\n\n" +
                    "FINALLY BLOCK:\n" +
                    "try {\n    // Open file\n    File file = new File(\"data.txt\");\n    // Process file\n} catch (IOException e) {\n    System.out.println(\"File error: \" + e.getMessage());\n} finally {\n    // Always executed, even if exception occurs\n    System.out.println(\"Cleaning up resources...\");\n}\n\n" +
                    "THROWING EXCEPTIONS:\n" +
                    "public void checkAge(int age) throws IllegalArgumentException {\n    if (age < 0) {\n        throw new IllegalArgumentException(\"Age cannot be negative\");\n    }\n    System.out.println(\"Valid age: \" + age);\n}\n\n" +
                    "CUSTOM EXCEPTION:\n" +
                    "public class InvalidInputException extends Exception {\n    public InvalidInputException(String message) {\n        super(message);\n    }\n}\n\n// Usage:\ntry {\n    throw new InvalidInputException(\"Invalid user input\");\n} catch (InvalidInputException e) {\n    System.out.println(\"Custom error: \" + e.getMessage());\n}\n\n" +
                    "✅ DOS:\n" +
                    "• Always handle checked exceptions\n" +
                    "• Use specific exception types in catch blocks\n" +
                    "• Provide meaningful error messages\n" +
                    "• Use finally blocks for cleanup operations\n" +
                    "• Log exceptions for debugging purposes\n\n" +
                    "❌ DON'TS:\n" +
                    "• Don't catch exceptions and ignore them (empty catch blocks)\n" +
                    "• Don't catch Exception unless absolutely necessary\n" +
                    "• Don't use exceptions for normal program flow\n" +
                    "• Don't throw exceptions without good reason\n" +
                    "• Don't forget to close resources in finally blocks";
                tutorialContent.setText(content);
            }
        });
    }

    public void showOnStage() {
        this.setVisible(true);
        this.toFront();
        if (this.getStage() == null) {
            throw new IllegalStateException("JournalWindow must be added to a stage before calling showOnStage()");
        }
    }
}

