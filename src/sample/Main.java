package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        // Stops printing messages from MongoDB in the console unless it has a priority level of 'WARNING' or higher
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.WARNING);

        System.out.println("Welcome to the Gym Management System");

        while (true) {
            try {
                MyGymManager.startUpMenu();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                System.out.println("------------------------------------");
            }
        }
    }

}
