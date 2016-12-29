import scala.swing._
import scala.swing.event._
import scala.util.Random
object LetsCode extends SimpleSwingApplication {

  def top = new MainFrame {
    title = "Let\'s Code "

    val

    val result = new Label {
      text = "Roll the dice!"
    }
    val roll_dice_button = new Button {
      text = "Roll Dice!"
    }

    contents = new BoxPanel(Orientation.Vertical) {

      contents += roll_dice_button
      contents += result
      border = Swing.EmptyBorder(30,30,10,30)
    }
    listenTo(roll_dice_button)
    reactions += {

      case ButtonClicked(`roll_dice_button`) =>
        randomNumber = (Random.nextInt%6)+1
        result.text = "Your roll was: " + randomNumber
    }
  }
}
