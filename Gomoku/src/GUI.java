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



public class GUI extends Application
{
    private board board;
    private String outputBoard;
    
    private static final int [] location ={15,54,95,136,178,219,260,300,341,
                                            383,424,464,505,547,588};
    private static final int REC_WIDTH = 40;
    private static final Color COLOR_EMPTY = Color.rgb(238, 228, 218, 0.35);
    
    private GridPane pane; // pane used to store text and rectangle cells
    private HBox title;
    private Pane piecePane;
    private BorderPane borderPane;
    private Text[][] text; // array storing all the pieces
    private StackPane sPane; // pane is used to store game over text
    private Rectangle[][] cells; // array storing all rectangle tilt
    private final Text name = new Text("Gomoku"); // name of the game
    private Text turn; // Turn section
    private boolean isGameover = false; // Check if the game is over
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
        board = new board();
        piecePane = new Pane();
        pane = new GridPane();
        sPane = new StackPane();
        borderPane = new BorderPane();
        title = new HBox();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(12,20,20,20));
        title.setPadding(new Insets(12,20,20,20));
        title.setSpacing(380);
        title.setStyle("-fx-background-color: rgb(187, 173, 160)");
        pane.setStyle("-fx-background-color: rgb(187, 173, 160)");
        // Set the spacing between the Tiles
        pane.setHgap(1);
        pane.setVgap(1);
        pane.setGridLinesVisible(true);
        cells = new Rectangle[board.boardSize][];
        for (int i = 0; i < board.boardSize; i++)
        {
           cells[i] = new Rectangle[board.boardSize];
        }
        // sets color and size of the rectangular board and add them
        // onto the board.
        for (int i = 0; i < board.boardSize-1; i++)
        {
           for (int j = 0; j < board.boardSize-1; j++)
           {
              int size = REC_WIDTH;
              cells[i][j] = new Rectangle(size, size, COLOR_EMPTY);
              pane.add(cells[i][j], j, i + 1, 1, 1);
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
        title.fillHeightProperty();
        borderPane.setTop(title);
        sPane = new StackPane();
        sPane.getChildren().addAll(pane,piecePane);
        borderPane.setCenter(sPane);
        borderPane.setOnMouseClicked(new myMouseHandler());
        scene = new Scene(borderPane);
    }
    
    public void update()
    {
        borderPane.getChildren().clear();
        title.getChildren().clear();
        sPane.getChildren().clear();
        cells = new Rectangle[board.boardSize][];
        for (int i = 0; i < board.boardSize; i++)
        {
           cells[i] = new Rectangle[board.boardSize];
        }
        // sets color and size of the rectangular board and add them
        // onto the board.
        for (int i = 0; i < board.boardSize-1; i++)
        {
           for (int j = 0; j < board.boardSize-1; j++)
           {
              int size = REC_WIDTH;
              cells[i][j] = new Rectangle(size, size, COLOR_EMPTY);
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
                    int col = location[j]+7;
                    Circle piece = new Circle(col,row,15,Color.BLACK);
                    piecePane.getChildren().add(piece);
                }else if(grid[i][j] == 2)
                {
                    int row = location[i];
                    int col = location[j]+7;
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
        borderPane.setTop(title);
        sPane.getChildren().addAll(pane,piecePane);
        borderPane.setCenter(sPane);
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
    
    private class myMouseHandler implements EventHandler<MouseEvent>
    {
        @Override
        public void handle(MouseEvent e)
        {
            if(!isGameover)
            {
                int row = getLoc((int)(e.getY()-48));
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
       Rectangle rect = new Rectangle(sPane.getWidth(), sPane.getHeight(),
           Color.rgb(238, 228, 218, 0.73));
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
