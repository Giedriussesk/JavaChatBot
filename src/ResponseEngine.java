import java.util.HashMap;  //get the tool for storing word pairs (key and value)
import java.util.Map;  //get the general map tool that hashmap uses
import java.io.BufferedWriter; //tool to write text to a file efficiently
import java.io.FileWriter; //tool to connect to a specific file on your computer
import java.io.IOException; //tool to handle file input/output errors
import java.time.LocalDateTime; //tool to get the current date and time
import java.time.format.DateTimeFormatter; //tool to format the date neatly

public class ResponseEngine {
    //a private storage for words and answers (encapsulation)
    private final Map<String, String> knowledgeBase;

    public ResponseEngine() {
        //create the actual storage object
        knowledgeBase = new HashMap<>();
        //run the setup method below to fill the storage
        initializeResponses();

    }

    private void initializeResponses() {
        //workout greetings and motivation
        knowledgeBase.put("yo", "Hi, I am here to help you reach your fitness goals.  Type 'workout' or 'macros' to start.");
        knowledgeBase.put("hi there", "Hi, I am here to help you reach your fitness goals.  Type 'workout' or 'macros' to start.");
        knowledgeBase.put("well boss", "Hi, I am here to help you reach your fitness goals.  Type 'workout' or 'macros' to start.");
        knowledgeBase.put("whats the craic", "Hi, I am here to help you reach your fitness goals.  Type 'workout' or 'macros' to start.");
        knowledgeBase.put("whats up", "Hi, I am here to help you reach your fitness goals.  Type 'workout' or 'macros' to start.");
        knowledgeBase.put("well", "Hi, I am here to help you reach your fitness goals.  Type 'workout' or 'macros' to start.");
        knowledgeBase.put("hi", "Hi, I am here to help you reach your fitness goals.  Type 'workout' or 'macros' to start.");
        knowledgeBase.put("hello", "Hi, I am here to help you reach your fitness goals.  Type 'workout' or 'macros' to start.");
        knowledgeBase.put("bye", "Fantastic session! Make sure to eat clean, get you rest and recover properly.");
        knowledgeBase.put("see you later", "Fantastic session! Make sure to eat clean, get you rest and recover properly.");
        knowledgeBase.put("bye bye", "Fantastic session! Make sure to eat clean, get you rest and recover properly.");
        knowledgeBase.put("thank you bye", "Fantastic session! Make sure to eat clean, get you rest and recover properly.");
        knowledgeBase.put("thank you for your help", "Fantastic session! Make sure to eat clean, get you rest and recover properly.");
        knowledgeBase.put("farewell", "Fantastic session! Make sure to eat clean, get you rest and recover properly.");

        //calisthenics and training knowledge
        knowledgeBase.put("workout", "Today we will focus on calisthenics.  Aim for 4 sets of pull-ups, sit-ups, dips and squats.");
        knowledgeBase.put("calisthenics", "Bodyweight training is great for beginners it will build a solid foundation.");

        //nutrition and muscle maintenance
        knowledgeBase.put("macros", "To maintain and buld muscle, aim for a high protein target (around 2g per kg of bodyweight) and stay in a caloric surplus.");
        knowledgeBase.put("protein", "Here are some great protein sources: chicken breast, turkey, eggs, greek yogurt, beef mince and whey protein powder.  Aim to hit your daily target!");

    }

    //helper method to save the data to a local file
    private void saveLogToFile(double weight, double target) {
        //set up the filename
        String fileName = "workout_log.txt";

        //get the current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDate = now.format(formatter);

        //prepare the text line we want to save
        String logLine = "[" + formattedDate + "] Weight: " + weight + "kg -> Protein Target: " + target + "g\n";

        //try to open the file and append the text to the end of it
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(logLine); //write the line into the file
        } catch (IOException e) {
            System.out.println("Could not save log to file: " + e.getMessage());
        }
    }

    public String generateResponse(String input) {
        //make the users text lowercase so its not case-sensitive
        String lowerInput = input.toLowerCase().trim();


        // dynamic feature - if the user types 'protein 80' , this will calculate the intake
        if (lowerInput.startsWith("protein ")) {
            try {
                //split the sentence by space to isolate thee number after 'protein'
                String[] parts = lowerInput.split(" ");
                // extract the number string (like "80") and convert it to a decimal number
                double weight = Double.parseDouble(parts[1]);

                //calculate protein target: 2 grams per kilogram of bodyweight
                double proteinTarget = weight * 2.0;

                //call helper method to log this calculation to the file
                saveLogToFile(weight, proteinTarget);

                //return a custom calculated response
                return "for a body weight of " + weight + "kg, your daily target is " + proteinTarget + "g of protein to maximise muscle maintenance";
            } catch (Exception e) {
                //if the user typed something like 'protein hello' instead of a number, this will handle the error
                return "Invalid weight, please provide a valid number for your weight...for example 'protein 85'";

            }

        }

        //go through every keyword in our storage one by one
        for (String key : knowledgeBase.keySet()) {
            //check if the users sentence contains our keyword
            if (lowerInput.contains(key)) {
                //if yes, give back the matching answer
                return knowledgeBase.get(key);
            }
        }
        //if the loop finishes with no match, return this default text
        return "I'm still learning! Could you try asking about java or OOP?";

    }


}
