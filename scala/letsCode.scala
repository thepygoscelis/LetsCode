//Author  : Charlie R. Hicks
//Purpose : A program designed to roll dice 

import scala.swing._
//Used to listen
import scala.swing.event._
//For aligning the elements of BorderPanel
import scala.swing.BorderPanel.Position._
//Used for dice
import scala.util.Random
//Use the ImageIcon directly from Java
import javax.swing.ImageIcon

object LetsCode extends SimpleSwingApplication {
  var amountOfDice = 1
  def top = new MainFrame {
    title = "Let\'s Code ";
    minimumSize = new Dimension(400,200)

    //A label to show the result of the roll
    val result = new Label {
      text = "Roll the dice!"
    }
    //Button to roll the dice
    val roll_dice_button = new Button {
      text = "Roll Dice!"
    }



    //Setup radio buttons to select how many sides for the dice
    val sixSidedDie = new RadioButton() {
      text = "6"
    }
    val fourSidedDie = new RadioButton {
      text = "4"
    }
    val eightSidedDie = new RadioButton{
      text = "8"
    }
    val twentySidedDie = new RadioButton{
      text = "20"
    }
    //RadioButtons in Button Group
    val diceGroup = new ButtonGroup {
      buttons += sixSidedDie
      buttons += eightSidedDie
      buttons += fourSidedDie
      buttons += twentySidedDie
    }
    val amountOfDiceLabel = new Label{
      text = "Amount of Dice 1"
    }

    val up = new Label {
      icon = new ImageIcon("up.png")
      listenTo(mouse.clicks)
      reactions += {
        //this is when the label is clicked
        case MouseClicked(_,_,_,_,_) =>
          amountOfDice += 1
          amountOfDiceLabel.text = "Amount of Dice " + amountOfDice

      }
    }
    val down = new Label {
      icon = new ImageIcon("down.png")

      listenTo(mouse.clicks)
      reactions += {
        //this is when the label is clicked
        case MouseClicked(_,_,_,_,_) =>
          if(amountOfDice > 1){
            amountOfDice = amountOfDice -1
            amountOfDiceLabel.text = "Amount of Dice " + amountOfDice
          }

      }
    }
    val amountPanel = new BoxPanel(Orientation.Vertical) {
      contents += up
      //This helps seperate the elements
      contents += Swing.VStrut(30)
      contents += down
    }
    val amountControl = new FlowPanel {
      contents += amountPanel
      contents += amountOfDiceLabel
    }

    contents = new BoxPanel(Orientation.Vertical) {

      contents += roll_dice_button
      contents += result
      contents += amountControl
      contents += new BoxPanel(Orientation.Horizontal) {
        contents += sixSidedDie
        contents += eightSidedDie
        contents += fourSidedDie
        contents += twentySidedDie
      }
      border = Swing.EmptyBorder(30,30,10,30)
    }
    listenTo(roll_dice_button)
    reactions += {
      //when button is clicked shows what to do
      case ButtonClicked(`roll_dice_button`) =>
        val dice_number = diceGroup.selected
        if(dice_number.isDefined){
          var total = 0
          for(i <- 0 until amountOfDice)
            total += Random.nextInt(dice_number.get.text.toInt)+1
          result.text = "Your roll was: " + total
        }
        else {
          result.text = "You must selected how many sides."
        }
    }
  }
}
