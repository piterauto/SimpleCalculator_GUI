import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import net.objecthunter.exp4j.ExpressionBuilder // Import the ExpressionBuilder class

// Custom button class for calculator buttons
class MyButton(private val label: String) : JPanel() {

    private var hovered: Boolean = false
    private var lastHovered: MyButton? = null // Reference to the last hovered button
    private val defaultColor = Color.WHITE
    private val hoverColor = Color(128, 128, 128, 128)

    init {
        preferredSize = Dimension(50, 50)
        background = defaultColor

        // Add mouse listener for button interaction
        addMouseListener(object : MouseAdapter() {
            override fun mouseEntered(e: MouseEvent?) {
                hovered = true
                repaint()

                // Clear the background of the previously hovered button
                lastHovered?.background = defaultColor
                // Set the current button as the last hovered one
                lastHovered = this@MyButton
            }

            override fun mouseExited(e: MouseEvent?) {
                hovered = false
                repaint()
            }

            override fun mouseClicked(e: MouseEvent?) {
                // Do something on click if needed
            }
        })
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        if (hovered) {
            g.color = hoverColor
            g.fillRect(0, 0, width, height)
        }
        g.color = Color.BLACK
        val fontMetrics = g.fontMetrics
        val stringWidth = fontMetrics.stringWidth(label)
        val stringHeight = fontMetrics.height
        val x = (width - stringWidth) / 2
        val y = (height - stringHeight) / 2 + fontMetrics.ascent
        g.drawString(label, x, y)
    }
}

// Main class for setting up the calculator interface and functionality
class MySketch {

    lateinit var textField: JTextField

    fun setup() {
        // Initialize text field
        textField = JTextField()
        textField.setBounds(10, 10, 230, 40)

        // Initialize label
        val label = JLabel("NJP Project Computer Science")
        label.setBounds(10, 290, 230, 40)

        // Initialize frame
        val frame = JFrame("Calculator")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        val contentPane = frame.contentPane // Access the content pane
        contentPane.layout = null
        contentPane.add(label) // Add the label to the content pane
        contentPane.add(textField) // Add the text field to the content pane

        // Initialize number and operation buttons
        val x = 10
        val y = 60
        for (i in 0..9) {
            val number = if (i == 9) 0 else i + 1 // Replace 9 with 0 for the last button
            val button = MyButton(number.toString())
            button.setBounds(x + (i % 3) * 60, y + (i / 3) * 60, 50, 50)
            button.addMouseListener(object : MouseAdapter() {
                override fun mouseClicked(e: MouseEvent?) {
                    clearError() // Clear error
                    textField.text += number.toString()
                }
            })
            frame.add(button)

            // Add operation buttons
            if (i < 4) {
                val operation = when (i) {
                    0 -> "+"
                    1 -> "-"
                    2 -> "*"
                    else -> "/"
                }
                val operationButton = MyButton(operation)
                operationButton.setBounds(x + 3 * 60, y + (i * 60), 50, 50)
                operationButton.addMouseListener(object : MouseAdapter() {
                    override fun mouseClicked(e: MouseEvent?) {
                        clearError() // Clear error
                        textField.text += operation
                    }
                })
                frame.add(operationButton)
            }
        }

        // Add "=" and "C" buttons
        val equalsButton = MyButton("=")
        equalsButton.setBounds(x + 2 * 60, y + 3 * 60, 50, 50)
        equalsButton.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                clearError() // Clear error
                val expression = textField.text
                textField.text = evaluateExpression(expression)
            }
        })
        frame.add(equalsButton)

        val clearButton = MyButton("C")
        clearButton.setBounds(x + 1 * 60, y + 3 * 60, 50, 50)
        clearButton.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent?) {
                clearError() // Clear error
                textField.text = ""
            }
        })
        frame.add(clearButton)

        frame.size = Dimension(265, 400) // Set frame size
        frame.isVisible = true
    }

    private fun evaluateExpression(expression: String): String {
        return try {
            val result = ExpressionBuilder(expression).build().evaluate()
            result.toString()
        } catch (e: Exception) {
            "Error"
        }
    }

    private fun clearError() {
        if (textField.text == "Error") {
            textField.text = ""
        }
    }
}

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val sketch = MySketch()
        sketch.setup()
    }
}
