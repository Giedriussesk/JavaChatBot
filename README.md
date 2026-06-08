# JavaChatBot - Simple Fitness ChatBot

This is a lightweight desktop chatbot designed to act as a simple fitness and nutrition coach. It was built completely from scratch using standard Java, without relying on build tools like Maven or Gradle, and without external frameworks. 

I created this project as a milestone in my self-study journey to practice core coding principles, building a desktop user interface, and saving data to local files.

---

## Project Attribution & Changes

This project was originally inspired by the step-by-step guide found in the Stackademic Java Chatbot tutorial (https://stackademic.com/blog/building-a-java-chatbot-a-step-by-step-guide-with-code-examples#setting-up-your-java-chatbot-project).

While the original guide provides a great starting point for a baseline chatbot using Maven, I wanted to challenge myself to write this in pure, vanilla Java. I adapted the core concepts from the guide and added several features of my own:
* **No Build Tools:** Kept the project entirely clean by skipping Maven/Gradle dependencies, writing it purely in standard Java.
* **Dynamic Calculations:** Instead of just responding with matching text, the bot can parse through a sentence, pull out numbers (like body weight), and run math equations to calculate personalized fitness targets.
* **Error Handling:** Added code to catch input mistakes gracefully so the window doesn't crash if someone types letters instead of a number.
* **File Saving & Themes:** Built in a feature that automatically logs calculations to a text file on my machine and redesigned the layout with a dark theme.

---

## Key Features

* **Organized Code Layout:** The project splits the visual setup (the window and text fields) from the core logic (the bot's brain) to keep the codebase clean and manageable.
* **Input Parsing:** Scans incoming user messages, checks for specific keywords, and splits text apart to read input numbers.
* **Crash Protection:** Uses standard try-catch blocks to handle typos or invalid inputs safely.
* **File Logging:** Automatically tracks calculations by writing a formatted line with a timestamp straight to a local file called `workout_log.txt`.
* **Custom Dark Theme:** Replaces the default system window style with a dark gray background, monospace font, and subtle colored border accents.
---

## Computer Science Concepts Applied

### Data Structures

* **HashMap (Key-Value Pairs):** Used in the `ResponseEngine` to map user keywords to specific bot answers. This allows the bot to quickly look up static responses based on user input.
* **Arrays (`String[]`):** Used when parsing dynamic inputs. The `.split()` method breaks a sentence into an array of separate words so the bot can isolate numbers.

### Algorithmic Logic
* **Linear Search:** The bot runs a loop through the keys of the response map to check if the user's input contains any matching phrases.
* **String Parsing and Data Conversion:** The program uses logic to identify specific command patterns (like `protein `), isolate the text representing the user's weight, and convert that string into a decimal number (`double`) to perform calculations.

---

## How the Code is Structured

The application uses three files to keep things organized:

1. **`ChatBotApp.java`**: The main starting point that launches the window safely.
2. **`ChatGUI.java`**: Handles the visual interface (the window, typing field, and chat history) and watches for when you press the Enter key.
3. **`ResponseEngine.java`**: The core logic engine that holds the bot's standard answers and handles the math for calculating targets.

---
