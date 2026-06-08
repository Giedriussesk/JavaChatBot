import javax.swing.SwingUtilities; //get the tool to launch windows safely

public class ChatBotApp {
    //this main method is the starting point of any java application
    public static void main(String[] args) {

        //invokeLater is used to make sure the window opens smoothly
        //and doesnt freeze the computers main logic thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                //this line creates the window and makes it appear
                new ChatGUI();
            }

        });
    }

}
