import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.text.*;
import javafx.geometry.*;
import java.util.*;



public class GUI extends Application
{
    private board board;
    private String outputBoard;
    private Button undo = new Button("Undo");
    private Button redo = new Button("Redo");
    private Button restart = new Button("Restart");
    private Button save = new Button("Save");
    
    private static final int [] location ={21,61,102,144,184,226,266,307,348,
                                            389,431,471,512,554,594};
    private static final int REC_WIDTH = 40;
    
    private GridPane pane;
    private HBox title;
    private Pane piecePane;
    private BorderPane borderPane;
    private VBox actions;
    private StackPane sPane;
    private Rectangle[][] cells; // array storing all rectangle tilt
    private final Text name = new Text("Gomoku"); // name of the game
    private Text turn; // Turn section
    private boolean isGameover = false; // Check if the game is over
    private boolean canUndo = true;
    private boolean canRedo = false;
    Scene scene;

    @Override
    public void start(Stage primaryStage)
    {
        this.setUpBoard();
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gomoku");
        primaryStage.show();
    }
    public void setUpBoard()
    {
        actions = new VBox();
        board = new board();
        piecePane = new Pane();
        pane = new GridPane();
        sPane = new StackPane();
        actions = new VBox();
        borderPane = new BorderPane();
        title = new HBox();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(20,20,20,20));
        title.setPadding(new Insets(12,20,0,20));
        actions.setPadding(new Insets(0,20,0,0));
        title.setSpacing(380);
        borderPane.setStyle("-fx-background-color: rgb(222, 184, 135)");
        // Set the spacing between the Tiles
        pane.setHgap(1);
        pane.setVgap(1);
        pane.setGridLinesVisible(true);
        cells = new Rectangle[15][];
        for (int i = 0; i < 15; i++)
        {
           cells[i] = new Rectangle[15];
        }
        // sets color and size of the rectangular board and add them
        // onto the board.
        for (int i = 0; i < 15-1; i++)
        {
           for (int j = 0; j < 15-1; j++)
           {
              int size = REC_WIDTH;
              cells[i][j] = new Rectangle(size, size, Color.BURLYWOOD);
              pane.add(cells[i][j], j, i + 1, 1, 1);
           }
        }
        undo.setMinHeight(40);
        undo.setMinWidth(120);
        redo.setMinHeight(40);
        redo.setMinWidth(120);
        restart.setMinHeight(40);
        restart.setMinWidth(120);
        save.setMinHeight(40);
        save.setMinWidth(120);
        name.setFont(Font.font("Times New Roman", 
            FontWeight.BOLD, 
            FontPosture.ITALIC, 20));
        title.getChildren().add(name);
        turn = new Text("Player "+board.whoseTurn()+"'s turn");
        turn.setFont(Font.font("Times New Roman", 
            FontWeight.BOLD, 
            FontPosture.ITALIC, 20));
        title.getChildren().add(turn);
        title.fillHeightProperty();
        actions.getChildren().addAll(undo,redo,save,restart);
        actions.setSpacing(30);
        actions.setAlignment(Pos.CENTER);
        sPane.getChildren().addAll(pane,piecePane);
        borderPane.setTop(title);
        borderPane.setCenter(sPane);
        borderPane.setRight(actions);
        sPane.setOnMouseClicked(new myMouseHandler());
        undo.setOnAction(new undoButtonHandler());
        redo.setOnAction(new redoButtonHandler());
        restart.setOnAction(new restartButtonHandler());
        scene = new Scene(borderPane);
    }
    
    public void update()
    {
        borderPane.getChildren().clear();
        title.getChildren().clear();
        sPane.getChildren().clear();
        piecePane.getChildren().clear();
        actions.getChildren().clear();
        undo.setMinHeight(40);
        undo.setMinWidth(120);
        redo.setMinHeight(40);
        redo.setMinWidth(120);
        restart.setMinHeight(40);
        restart.setMinWidth(120);
        save.setMinHeight(40);
        save.setMinWidth(120);
        for (int i = 0; i < board.boardSize; i++)
        {
           cells[i] = new Rectangle[board.boardSize];
        }
        borderPane.setStyle("-fx-background-color: rgb(222, 184, 135)");
        // sets color and size of the rectangular board and add them
        // onto the board.
        for (int i = 0; i < board.boardSize-1; i++)
        {
           for (int j = 0; j < board.boardSize-1; j++)
           {
              int size = REC_WIDTH;
              cells[i][j] = new Rectangle(size, size, Color.BURLYWOOD);
              pane.add(cells[i][j], j, i + 1, 1, 1);
           }
        }
        int [][] grid = board.getGrid();
        for(int i =0;i<board.boardSize;i++)
        {
            for(int j = 0;j<board.boardSize;j++)
            {
                if(grid[i][j] == 1)
                {
                    int row = location[i];
                    int col = location[j];
                    Circle piece = new Circle(col,row,15,Color.BLACK);
                    piecePane.getChildren().add(piece);
                }else if(grid[i][j] == 2)
                {
                    int row = location[i];
                    int col = location[j];
                    Circle piece = new Circle(col,row,15,Color.WHITE);
                    piecePane.getChildren().add(piece);
                }
            }
        }
        name.setFont(Font.font("Times New Roman", 
            FontWeight.BOLD, 
            FontPosture.ITALIC, 20));
        title.getChildren().add(name);
        turn = new Text("Player "+board.whoseTurn()+"'s turn");
        turn.setFont(Font.font("Times New Roman", 
            FontWeight.BOLD, 
            FontPosture.ITALIC, 20));
        title.getChildren().add(turn);
        sPane.getChildren().addAll(pane,piecePane);
        actions.getChildren().addAll(undo,redo,save,restart);
        actions.setSpacing(30);
        actions.setAlignment(Pos.CENTER);
        borderPane.setTop(title);
        borderPane.setCenter(sPane);
        borderPane.setRight(actions);
        canUndo = true;
        sPane.setOnMouseClicked(new myMouseHandler());
        undo.setOnAction(new undoButtonHandler());
        redo.setOnAction(new redoButtonHandler());
        restart.setOnAction(new restartButtonHandler());
    }
    
    public static int getLoc(int x)
    {
        int result = -1;
        for(int i = 0;i<location.length;i++)
        {
            if(Math.abs(x-location[i])<13)
            {
                return i;
            }
        }
        return result;
    }
    
    private class undoButtonHandler implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event)
        {
            if(board.getTurn()>2 && canUndo == true && board.undo()!=null)
            {
                board = board.undo();
                update();
                canUndo = false;
                canRedo = true;
            }
        }
    }
    
    private class redoButtonHandler implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event)
        {
            if(canRedo == true && board.redo()!=null)
            {
                board = board.redo();
                update();
                canRedo  =false;
            }
        }
    }
    private class restartButtonHandler implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent event)
        {
            board.resetBoard();
            update();
        }
    }
    
    private class myMouseHandler implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent e)
        {
            if(!isGameover)
            {
                int row = getLoc((int)(e.getY()));
                int col = getLoc((int)(e.getX()));
                board.addPiece(row, col);
                update();
            if(board.checkWin())
            {
                isGameover = true;
                endGame();
            }
            }
        }
    }
    
    private void endGame()
    {
        undo.setDisable(true);
        redo.setDisable(true);
        save.setDisable(true);
       // set the text to game over !
       Text gameOver = new Text("Player "+board.whoseTurn()+" Won!"
           + "");
       // set font
       gameOver.setFont(Font.font("Times New Roman", 
                                  FontWeight.BOLD, 
                                  55));
       // set color
       gameOver.setFill(Color.BLACK);
       // set the rectangle that is the size of the pane
       Rectangle rect = new Rectangle(pane.getWidth(), pane.getHeight(),
           Color.rgb(238, 228, 218, 0.35));
       // add rect to the sPane
       sPane.getChildren().addAll(rect);
       // add text to the sPane
       sPane.getChildren().addAll(gameOver);
       // center the text
       StackPane.setAlignment(gameOver, Pos.CENTER);
    }
    public static void main(String [] arg)
    {
        launch();
    }

}
