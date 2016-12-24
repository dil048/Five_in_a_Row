import java.util.Random;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;
import java.io.*;



public class GUI extends Application
{
    private board board;
    private String outputBoard;
    
    private GridPane pane; // pane used to store text and rectangle cells
    private StackPane sPane; // pane is used to store game over text
    private Text[][] text; // array storing all the number
    private Rectangle[][] cells; // array storing all rectangle tilt
    private final Text name = new Text("Five_in_a_Row"); // name of the game
    private Text turn; // Turn section
    private boolean isGameover = false; // Check if the game is over
    Scene scene;

    @Override
    public void start(Stage primaryStage)
    {
        
        
    }

}
