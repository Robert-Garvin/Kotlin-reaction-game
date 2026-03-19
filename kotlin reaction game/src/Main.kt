import java.awt.Color
import java.awt.Frame
import java.awt.GridLayout
import javax.swing.JButton
import javax.swing.JFrame
import java.util.*
import javax.swing.JPanel
import javax.swing.JTextField

// Timer
// https://www.youtube.com/watch?v=-IMys4PCkIA
// BASIC Frames Buttons Text
// https://www.youtube.com/watch?v=STObCH8d2-k
// GRID
// https://www.youtube.com/watch?v=dfhmTyRTCSQ&t=785s

fun main() {
    var x = colorSquare()
    x.gameStart()
}

class colorSquare() {
    private var nextNumber = 1
    private var time = 30
    init {
        this.nextNumber
        this.time
    }
    fun gameStart() {
        var x = colorSquare()
        x.nextFrame(1)
        var textfield = JTextField()
        textfield.setBounds(300,650,600,100)
        textfield.setEditable(false)
        var panel: JPanel = JPanel()
        panel.setBounds(300,500,600,100)
        x.time()
    }
    fun gameOverFrame() {
        var frame: JFrame = JFrame()
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        frame.setSize(600, 700)
        var button = JButton("RESTART")
        button.addActionListener {
            frame.dispose()
            gameStart()
        }
        var textfield = JTextField()
        textfield.setBounds(0,0,600,80)
        textfield.setEditable(false)
        textfield.setBackground(Color.RED)
        textfield.setText("Game Over Time left! ${this.time} Seconds left! Rounds completed ${this.nextNumber}")
        frame.add(textfield)
        frame.add(button)
        frame.setVisible(true)
    }

    fun time() {
        oneSecondTimer()


    }
    // https://www.youtube.com/watch?v=STObCH8d2-k
    fun oneSecondTimer() {
        val timer = java.util.Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                if (time == 0) {
                    return
                }
                time--
                oneSecondTimer()
            }
        }, 1000)

    }


    fun nextFrame(number: Int) {
        var frame: JFrame = JFrame()
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        frame.setSize(600, 700)
        var textfield = JTextField()
        textfield.setBounds(0,0,600,80)
        textfield.setEditable(false)
        textfield.setBackground(Color.gray)
        textfield.setText("click the green square!       Time left! ${this.time} Seconds       Round ${this.nextNumber}")
        var panel: JPanel = JPanel()
        panel.setBounds(0,80,600,600)

        frame.setLayout(null)
        panel.setLayout(GridLayout(number,number,0,0))
        buttons(number * number,frame, panel)
        frame.add(textfield)
        frame.add(panel)
        frame.setVisible(true)
    }
    fun randomSquare(x: Int): Int {
        var lowerBound = 1
        var upperBound = x
        var range = (upperBound - lowerBound) + 1
        var random = Math.random() * range + lowerBound
        // println(random.toInt())
        return random.toInt()
    }
    fun buttonColors(x: Int): Color {
        val colors = listOf(Color.RED, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.PINK, Color.CYAN)
        var randomSquare = randomSquare(5)
        return colors[randomSquare]
    }
    fun buttons(x: Int, frame: Frame, panel: JPanel) {
        var randomSquare = randomSquare(x)
        for (i in 1..x) {
            var button: JButton = JButton()
            // gets rid of boarder
            button.setFocusable(false)
            panel.add(button)
            frame.add(panel)


            if (i == randomSquare) {
                button.setBackground(Color.GREEN)
                button.addActionListener {
                    if (time == 0) {
                        frame.dispose()
                        gameOverFrame()
                    }
                    else {
                        frame.dispose()
                        this.nextNumber += 1
                        nextFrame(this.nextNumber)
                    }
                }
            }
            else {
                button.setBackground(buttonColors(x))
                button.addActionListener {
                    frame.setVisible(false)
                    gameOverFrame()
                }
            }
            button.setOpaque(true)
            button.setBorderPainted(false)

        }
    }
}