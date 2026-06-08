import javax.swing.*; //get tools for windows, text areas and buttons
import java.awt.*;  //get tools for arranging where things sit on the screen
import java.awt.event.ActionEvent; //get the tools that detects when enter is pressed

public class ChatGUI extends JFrame { //extends means this class is a window
    private JTextArea chatArea; //the big box that shows old messages
    private JTextField inputField;  //the small box where you type
    private ResponseEngine engine;  //the link to ResponseEngine

    public ChatGUI() {
        //link this GUI to a new instance of our logic engine
        engine = new ResponseEngine();
        //run the method to build the window appearance
        setupWindow();
    }

    private void setupWindow() {
        setTitle("JavaChatBot");  //the text in the top bar of the window
        setSize(400, 500); //the width and height in pixels
        setDefaultCloseOperation(EXIT_ON_CLOSE); //stop the program when X is clicked
        setLayout(new BorderLayout()); //use a layout with north, south, east and west sections

        //define custom dark theme colours
        Color darkBackground = new Color(30, 30, 30);  //soft dark charcoal grey
        Color inputBackground = new Color(45, 45, 45);  //slightly lighter grey for input
        Color brightTextColor = new Color(240, 240, 240);  //off white text
        Color accentColor = new Color(70, 130, 180);  //steel blue for accents
        Font customFont = new Font("Monospaced", Font.PLAIN, 12); //clean developer font


        chatArea = new JTextArea(); //create the big message box
        chatArea.setLineWrap(true); //enables text wrapping
        chatArea.setWrapStyleWord(true);  //wraps at word boundaries, not characters
        chatArea.setEditable(false); //stop the user from deleting bot messages
        chatArea.setLineWrap(true); //wrap text to the next line auto
        chatArea.setBackground(darkBackground); //apply dark background
        chatArea.setForeground(brightTextColor); //apply light text colour
        chatArea.setFont(customFont); //apply monospace font to input
        chatArea.setCaretColor(brightTextColor); //typing cursor colour white

        //this wraps the chat area in a scroll box and styles the scrollpane border
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(darkBackground, 1));
        add(scrollPane, BorderLayout.CENTER);


        //set up the input box at the bottom
        inputField = new JTextField(); //create the typing box
        inputField.addActionListener(this::handleInput); //tell the program to run 'handleInput' when the user presses enter
        inputField.setBackground(inputBackground); //apply dark background to input
        inputField.setForeground(brightTextColor); //apply light text colour to input
        inputField.setFont(customFont); //apply monospace font to input
        inputField.setCaretColor(brightTextColor); //makes typing cursor white

        //add a subtle coloured border to separate input field cleanly from history area
        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(2, 0, 0, 0, accentColor), //top steel blue border
                BorderFactory.createEmptyBorder(5, 5, 5, 5) //inner padding
        ));


        add(inputField, BorderLayout.SOUTH); //put the typing box at the bottom

        setVisible(true); //makes the window pop up on your screen
    }

    private void handleInput(ActionEvent e) {
        //get the text from the box and trim off extra spaces at the ends
        String userText = inputField.getText().trim();

        if (!userText.isEmpty()) { //only do something if the box is not empty
            chatArea.append("You: " + userText + "\n");  //show your text in the window
            //ask the engine for an answer based on what you typed
            String response = engine.generateResponse(userText);
            chatArea.append("Bot: " + response + "\n\n"); //shows the bots answer
            inputField.setText(""); //cleat the typing box for your next message
        }


    }



}
