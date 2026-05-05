import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game {

    JFrame window;
    Container con;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel;
    JLabel titleNameLabel;
    Font titleFont = new Font("Palatino", Font.PLAIN, 40);
    JButton startButton, choice1, choice2;
    Font normalFont = new Font("Palatino", Font.PLAIN, 28);
    JTextArea mainTextArea;
    String position;
    String weapon = "none";
    String bullets = "none";

    ChoiceHandler choiceHandler = new ChoiceHandler();

    public static void main(String[] args) throws Exception {
        new Game();
    }

    public Game() {
        createTitleScreen();
    }

    public void createTitleScreen() {
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);
        con = window.getContentPane();

        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 225, 600, 150);
        titleNamePanel.setBackground(Color.black);
        titleNameLabel = new JLabel("HOUSE ON A HAUNTED HILL");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);
        titleNamePanel.add(titleNameLabel);
        con.add(titleNamePanel);

        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(300, 375, 250, 150);
        startButtonPanel.setBackground(Color.black);
        startButtonPanel.setLayout(null);
        con.add(startButtonPanel);

        startButton = new JButton("ENTER");
        startButton.setBounds(25, 50, 150, 50);
        styleButton(startButton);
        startButton.addActionListener(e -> createGameScreen());
        startButtonPanel.add(startButton);

        window.setVisible(true);
    }

    public void createGameScreen() {
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(50, 30, 700, 310);
        mainTextPanel.setBackground(Color.black);
        mainTextPanel.setLayout(null);
        con.add(mainTextPanel);

        mainTextArea = new JTextArea();
        mainTextArea.setBounds(0, 0, 700, 310);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(normalFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(50, 360, 700, 180);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(2, 1, 0, 5));
        con.add(choiceButtonPanel);

        choice1 = new JButton();
        styleButton(choice1);
        choice1.setActionCommand("c1");
        choiceButtonPanel.add(choice1);

        choice2 = new JButton();
        styleButton(choice2);
        choice2.setActionCommand("c2");
        choiceButtonPanel.add(choice2);

        window.revalidate();
        window.repaint();
        entryWay();
    }

    // Styles a button with the game's standard black/white theme
    private void styleButton(JButton button) {
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFont(normalFont);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.white, 2),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
    }

    // Displays a standard scene with two labeled choices
    private void showScene(String pos, String text, String c1Text, String c2Text) {
        position = pos;
        mainTextArea.setText(text);
        configureButton(choice1, c1Text, "c1", choiceHandler);
        configureButton(choice2, c2Text, "c2", choiceHandler);
    }

    // Displays an ending scene with Exit/Play Again buttons
    private void showEnding(String pos, String text) {
        showScene(pos, text, "Exit Game", "Play again?");
    }

    // All Game Scenarios and Choices

    public void entryWay() {
        showScene("entryWay",
            "You enter the house.\n\nThere's a set of stairs to your right and a \ndark hallway to your left. \n\nWhich way do you go?",
            "Go up the stairs", "Go down the hallway");
    }

    public void upStairs() {
        showScene("upStairs",
            "You walk up the stairs and see a door.\n\nWhat will you do next?",
            "Open the door", "Return to entryway");
    }

    public void openAtticDoor() {
        showScene("openAtticDoor",
            "You open the door and enter the room.\nIt seems to be an old attic. \nThere's a box pushed into a corner of the room.\n\nOpen it?",
            "Open the box", "Go back down");
    }

    public void openBulletBox() {
        showScene("openBulletBox",
            "You open the box and find a set of bullets.\n\nTake the bullets?",
            "Take the bullets", "Go to the hallway");
    }

    public void takeBullets() {
        bullets = "regular";
        showScene("takeBullets",
            "You take the bullets and return to the entryway. \n\n What will you do next?",
            "Go down the hallway", "Leave the house");
    }

    public void darkHallway() {
        showScene("darkHallway",
            "In the hallway, there's a door to your left \nwhile the hallway continues further down.\n\nWhat will you do?",
            "Open the door", "Continue down the hallway");
    }

    public void openGunDoor() {
        showScene("openGunDoor",
            "You try the handle to find it's unlocked and you walk inside. \nYou're standing in an old bedroom. On the endtable is a gun.\n\nDo you take it?",
            "Take the gun", "Return to the hallway");
    }

    public void takeGun() {
        weapon = "gun";
        showScene("takeGun",
            "You take the gun and return to the hallway",
            "Continue down the hallway", "Leave the house");
    }

    public void leaveHouse() {
        position = "leaveHouse";
        mainTextArea.setText("You try to leave. \n\nBut the door slams shut in front of you. \nAnd then you realize.\n\nSomething is standing behind you.");
        String c1Text = weapon.equals("gun") ? "Turn around and shoot" : "Turn around and face it";
        configureButton(choice1, c1Text, "c1", choiceHandler);
        configureButton(choice2, "Try the door", "c2", choiceHandler);
    }

    public void faceIt() {
        showScene("faceIt",
            "You spin around to face whatever is behind you. A sweet pain blooms into your belly.\n\nAnd then your guts splatter to the floor.",
            "You Died", "Try again");
    }

    public void tryDoor() {
        showScene("tryDoor",
            "You try the door but it won't open. \n\nSomething slices into your neck.",
            "You Died", "Try again");
    }

    public void shootMonster() {
        String text = bullets.equals("none")
            ? "You turn around and shoot. \n But you don't have any bullets. \n\nSomething sharp pierces your heart."
            : "You turn around and shoot. \nBut the bullets have no effect. \n\nYour throat gets torn out.";
        showScene("shootMonster", text, "You Died", "Try again");
    }

    public void continueDownHallway() {
        showScene("continueDownHallway",
            "You continue down the hallway and \nhear a horrifying sound.\n\nInvestigate further?",
            "Investigate the sound", "Run away!");
    }

    public void runAway() {
        position = "runAway";
        mainTextArea.setText("You start your escape and something\n immediately starts running after you.");
        configureButton(choice1, "Run out the front door", "c1", choiceHandler);
        String c2Text = weapon.equals("gun") ? "Use the gun" : "Try to fight it";
        configureButton(choice2, c2Text, "c2", choiceHandler);
    }

    public void investigateSound() {
        position = "investigateSound";
        mainTextArea.setText("You quietly walk towards the source of \nthe noise and see a man lying on the floor\ncovered in blood. \nHe raises his head and you recognize him \nas a priest.\n\n'Please kill me.' He begs you.");
        configureButton(choice1, "Talk to him", "c1", choiceHandler);
        String c2Text = weapon.equals("gun") ? "Shoot him" : "Leave him to his fate";
        configureButton(choice2, c2Text, "c2", choiceHandler);
    }

    public void talkToPriest() {
        position = "talkToPriest";
        if (weapon.equals("gun")) {
            mainTextArea.setText("The priest explains that he was trying to\nexorcise the demon haunting the house.\nAnd had no choice but to seal the demon \ninside himself.\n\nHe hands you a box of silver bullets.\n'Here, use these.'");
            configureButton(choice1, "Take the silver bullets", "c1", choiceHandler);
        } else {
            mainTextArea.setText("The priest explains that he was trying to\nexorcise the demon haunting the house.\nAnd had no choice but to seal the demon \ninside himself.\n\nHe hands you a vial of holy water.\n'Here, use this.'");
            configureButton(choice1, "Take the holy water", "c1", choiceHandler);
        }
        configureButton(choice2, "Run away", "c2", choiceHandler);
    }

    public void takeSilverBullets() {
        bullets = "silver";
        showScene("takeSilverBullets",
            "You take the silver bullets\nand the priest exclaims,\n\n'Quick, I can't hold it back any longer!'",
            "Shoot the priest", "Leave him to his fate");
    }

    public void takeHolyWater() {
        weapon = "holyWater";
        showScene("takeHolyWater",
            "You take the holy water\nand the priest exclaims,\n\n'Quick, I can't hold it back any longer!'",
            "Throw holy water at him", "Leave him to his fate");
    }

    public void throwHolyWater() {
        showScene("throwHolyWater",
            "You throw the holy water at the priest\nand he screams in agony.\nYou watch in stunned horror.\n\nUntil a grotesque claw begins forcing its way out of his mouth.",
            "Throw more holy water", "Run for your life!");
    }

    public void throwMoreHolyWater() {
        showScene("throwMoreHolyWater",
            "You throw more holy water at the priest and you hear a shriek.\nBut you aren't sticking around to see what happens next.\nYou run for the exit and,\n thanks to your quick thinking,\n\nit buys you enough time to make it out of the house\nand down the hill to your car.",
            "You survived", "Play again?");
    }

    public void runForLife() {
        showScene("runForLife",
            "You sprint for the exit,\nfeet pounding as you cross the hallway,\nonly to realize a split second later that you were falling.\nYou had tripped over nothing, spilling the holy water.\n\nResulting in you being eaten alive.",
            "You Died", "Try again");
    }

    public void leaveHim() {
        String text;
        switch (weapon) {
            case "gun":
                text = "You can't bring yourself to fire the gun\nand the hesitation was all it needed.\nSuddenly, you're seeing the priest from upside-down,\na searing pain along your neck.\n\n You were decapitated.";
                break;
            case "holyWater":
                text = "You don't have faith that any of this will work\nand the hesitation was all it needed.\n\nA claw reaches out and tears your face off.";
                break;
            default:
                text = "You don't know how to help him\nand the hesitation was all it needed.\nThe priest is now standing directly\nin front of you.\n\n His bloody eyes are the last thing you ever see.";
        }
        showScene("leaveHim", text, "You Died", "Try again");
    }

    public void shootPriest() {
        switch (bullets) {
            case "silver":
                showScene("shootPriest",
                    "You shoot the priest and he falls to the ground,\nalong with light bursting forth from his wound.\n\nYou hear an otherwordly dying roar as\n the demon is vanquished.",
                    "You survived", "Play again?");
                break;
            case "regular":
                showScene("shootPriest",
                    "You shoot the priest and he falls to the ground.\n\nThen you hear a voice inside your head.\n\n'Finally, a proper host.'",
                    "You Died", "Try again");
                break;
            default:
                showScene("shootPriest",
                    "You move to shoot the priest\nbut realize that the gun is empty.",
                    "You Died", "Try again");
        }
    }

    // All Endings

    public void neutralEnd() {
        showEnding("neutralEnd",
            "Whatever that thing was, it couldn't leave the house,\nit couldn't leave the hill.\nBut one thing was for sure.\n\nIt would always be waiting.\n\n Thanks for playing!");
    }

    public void badEnd() {
        showEnding("badEnd",
            "The house remains haunted forever,\nawaiting its next victim.\n\nThanks for playing!");
    }

    public void goodEnd() {
        showEnding("goodEnd",
            "The house settles into a peaceful quiet\nAll is returned to as it should be.\n\nThanks for playing!");
    }

    public class ChoiceHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String yourChoice = event.getActionCommand();
            switch (position) {
                case "entryWay":
                    if (yourChoice.equals("c1")) upStairs();
                    else darkHallway();
                    break;

                case "upStairs":
                    if (yourChoice.equals("c1")) openAtticDoor();
                    else entryWay();
                    break;

                case "openAtticDoor":
                    if (yourChoice.equals("c1")) openBulletBox();
                    else entryWay();
                    break;

                case "openBulletBox":
                    if (yourChoice.equals("c1")) takeBullets();
                    else darkHallway();
                    break;

                case "takeBullets":
                    if (yourChoice.equals("c1")) continueDownHallway();
                    else leaveHouse();
                    break;

                case "darkHallway":
                    if (yourChoice.equals("c1")) openGunDoor();
                    else continueDownHallway();
                    break;

                case "openGunDoor":
                    if (yourChoice.equals("c1")) takeGun();
                    else darkHallway();
                    break;

                case "takeGun":
                    if (yourChoice.equals("c1")) continueDownHallway();
                    else leaveHouse();
                    break;

                case "continueDownHallway":
                    if (yourChoice.equals("c1")) investigateSound();
                    else runAway();
                    break;

                case "runAway":
                    if (yourChoice.equals("c1")) leaveHouse();
                    else if (weapon.equals("gun")) shootMonster();
                    else faceIt();
                    break;

                case "leaveHouse":
                    if (yourChoice.equals("c1")) {
                        if (weapon.equals("gun")) shootMonster();
                        else faceIt();
                    } else {
                        tryDoor();
                    }
                    break;

                case "investigateSound":
                    if (yourChoice.equals("c1")) talkToPriest();
                    else shootPriest();
                    break;

                case "talkToPriest":
                    if (yourChoice.equals("c1")) {
                        if (weapon.equals("gun")) takeSilverBullets();
                        else takeHolyWater();
                    } else {
                        runAway();
                    }
                    break;

                case "takeSilverBullets":
                    if (yourChoice.equals("c1")) shootPriest();
                    else badEnd();
                    break;

                case "takeHolyWater":
                    if (yourChoice.equals("c1")) throwHolyWater();
                    else leaveHim();
                    break;

                case "throwHolyWater":
                    if (yourChoice.equals("c1")) throwMoreHolyWater();
                    else runForLife();
                    break;

                case "shootPriest":
                    if (yourChoice.equals("c1") && bullets.equals("silver")) goodEnd();
                    else if (yourChoice.equals("c1")) badEnd();
                    else entryWay();
                    break;

                // Death scenes: c1 = bad end, c2 = restart
                case "faceIt":
                case "tryDoor":
                case "shootMonster":
                case "runForLife":
                case "leaveHim":
                    if (yourChoice.equals("c1")) badEnd();
                    else { weapon = "none"; bullets = "none"; entryWay(); }
                    break;

                // End screens: c1 = exit, c2 = play again
                case "throwMoreHolyWater":
                case "badEnd":
                case "goodEnd":
                case "neutralEnd":
                    if (yourChoice.equals("c1")) System.exit(0);
                    else { weapon = "none"; bullets = "none"; entryWay(); }
                    break;
            }
        }
    }

    // Reconfigures a button with new text, action command, and listener
    private void configureButton(JButton button, String text, String actionCommand, ActionListener listener) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
        button.setText(text);
        button.setActionCommand(actionCommand);
        button.addActionListener(listener);
    }
}
