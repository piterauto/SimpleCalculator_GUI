#Simple Kotlin Calculator with Graphical User Experience

## Calculator - Documentation

#### Overview
The "Calculator" project is a simple interactive calculator developed in Kotlin using the Swing library. It provides basic arithmetic operations (addition, subtraction, multiplication, division) and evaluates mathematical expressions.

#### Project Structure
The project consists of two main components:

1. **Custom Button Class (`MyButton`):**
    - Represents buttons in the calculator interface.
    - Handles mouse events for interaction.
    - Customizes appearance based on hover state.

2. **Calculator Setup and Functionality (`MySketch`):**
    - Initializes the user interface, including text fields and buttons.
    - Implements basic arithmetic operations.
    - Evaluates mathematical expressions using the exp4j library.

#### Usage
To use the calculator:
1. Run the program.
2. Enter mathematical expressions using the provided text field.
3. Click on the number and operation buttons to input expressions.
4. Click "=" to calculate the result.
5. Click "C" to clear the input.

#### Features
- Simple and intuitive user interface.
- Basic arithmetic operations supported.
- Ability to evaluate complex mathematical expressions.

#### Technologies
- Language: Kotlin
- GUI Library: Swing
- Mathematical Expression Evaluation: exp4j

#### Authors
- piterauto

## Calculator - Step-by-Step Documentation

#### Class `MyButton`

###### Lines 1-21: Imports and Class Declaration
- Imports: Import necessary classes from Swing and others.
- `MyButton`: This class inherits from `JPanel`, representing buttons in the user interface.

###### Lines 23-33: Variables and Initialization
- `hovered`: A boolean variable storing whether the mouse is over the button.
- `lastHovered`: Reference to the last hovered button.
- `defaultColor`: Default background color for the button.
- `hoverColor`: Background color of the button when hovered.
- `init`: Constructor of `MyButton`, setting preferred size and background color.

###### Lines 35-53: Mouse Event Handling
- `mouseEntered`: Method called when the mouse enters the button. Updates the `hovered` flag and refreshes appearance.
- `mouseExited`: Method called when the mouse exits the button. Updates the `hovered` flag and refreshes appearance.
- `mouseClicked`: Method called when the button is clicked.

###### Lines 55-64: `paintComponent` Method
- `paintComponent`: Method drawing the appearance of the button. Paints the background based on `hovered` state and draws the label in the center.

#### Class `MySketch`

###### Lines 66-80: Variables and Initialization
- `textField`: Text field where the user inputs mathematical expressions.
- `setup`: Method initializing the user interface and handling user actions.

###### Lines 82-115: `setup` Method
- Initialization of the text field and label.
- Setting up the frame and content pane.
- Adding the label and text field to the content pane.
- Initializing number and operation buttons.
- Adding buttons to the frame.
- Setting frame size and visibility.

###### Lines 117-144: `evaluateExpression` Method
- `evaluateExpression`: Method performing calculations based on the mathematical expression. It utilizes the `exp4j` library.

###### Lines 146-155: `clearError` Method
- `clearError`: Method removing the "Error" message from the text field, if present.

#### Class `Main`

###### Lines 157-169: `main` Method
- `main`: Method starting the program. It creates an instance of `MySketch` and calls its `setup` method.
