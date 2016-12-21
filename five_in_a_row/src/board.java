
import java.util.*;
public class board
{
    public final int boardSize = 15;
    public final String p1 = "Player 1";
    public final String p2 = "Player 2";
    public final int empty = 0;
    public final int player1 = 1;
    public final int player2 = 2;
    
    
    private int turn;
    private int[][] grid;
    
    public board()
    {
        this.turn = this.player1;
        this.grid = new int [boardSize][boardSize];
    }
    public board(int turn, int [][]input)
    {
        this.turn = turn;
        for(int i =0;i<input.length;i++)
        {
            for(int j=0;i<input[i].length;j++)
            {
                this.grid[i][j]=input[i][j];
            }
        }
    }

    public int getTurn()
    {
        if(turn%2==1)
        {
            return 1;
        }else
        {
            return 2;
        }
    }
    
    public void setTurn(int turn)
    {
        this.turn = turn;
    }
    
    public int[][] getGrid()
    {
        return grid;
    }
    
    public void setGrid(int[][] grid)
    {
        this.grid = grid;
    }
    
    public boolean emptyPos(int row, int col)
    {
        if(this.getGrid()[row][col]==empty)
        {
            return true;
        }
        return false;
    }
    
    public void addPiece(int row,int col)
    {
        if(this.getTurn()%2==1)
        {
            if(this.emptyPos(row, col))
            {
                this.getGrid()[row][col]=player1;
                if(!(this.checkWin()))
                {
                    this.setTurn(this.getTurn()+1);
                }
            }else
            {
                this.occupied();
                this.printboard();
            }
        }else
        {
            if(this.emptyPos(row, col))
            {
                this.getGrid()[row][col]=player2;
                if(!(this.checkWin()))
                {
                    this.setTurn(this.getTurn()+1);
                }
            }else
            {
                this.occupied();
                this.printboard();
            }
        }
    }
    
    public boolean full()
    {
        for(int i=0;i<boardSize;i++)
        {
            for(int j=0;j<boardSize;j++)
            {
                if(this.getGrid()[i][j]==0)
                {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean checkWin()
    {
        for(int i =0;i<boardSize;i++)
        {
            for(int j=0;j<=boardSize-5;j++)
            {
                if(this.getGrid()[i][j]!=0 &&
                    this.getGrid()[i][j]==this.getGrid()[i][j+1] &&
                    this.getGrid()[i][j+1]==this.getGrid()[i][j+2] &&
                    this.getGrid()[i][j+2]==this.getGrid()[i][j+3] &&
                    this.getGrid()[i][j+3]==this.getGrid()[i][j+4])
                {
                    return true;
                }
            }
        }
        for(int i =0;i<=boardSize-5;i++)
        {
            for(int j=0;j<boardSize;j++)
            {
                if(this.getGrid()[i][j]!=0 &&
                    this.getGrid()[i][j]==this.getGrid()[i+1][j] &&
                    this.getGrid()[i+1][j]==this.getGrid()[i+2][j] &&
                    this.getGrid()[i+2][j]==this.getGrid()[i+3][j] &&
                    this.getGrid()[i+3][j]==this.getGrid()[i+4][j])
                {
                    return true;
                }
            }
        }
        for(int i =0;i<boardSize;i++)
        {
            for(int j=0;j<boardSize;j++)
            {
                if(this.getGrid()[i][j]!=0 &&
                    this.getGrid()[i][j]==this.getGrid()[i+1][j+1] &&
                    this.getGrid()[i+1][j+1]==this.getGrid()[i+2][j+2] &&
                    this.getGrid()[i+2][j+2]==this.getGrid()[i+3][j+3] &&
                    this.getGrid()[i+3][j+3]==this.getGrid()[i+4][j+4])
                {
                    return true;
                }
            }
        }
        return false;
        
    }
    
    public void printboard()
    {
        System.out.println(this.toString());
    }
    
    public void occupied()
    {
        System.out.println("The is already a piece here");
    }
    
    
    @Override
    public String toString()
    {
        StringBuilder outputString = new StringBuilder();
        outputString.append("Player"+this.getTurn()+ "'s turn \n");
        for(int i = 0;i<boardSize;i++)
        {
            for(int j=0;j<boardSize;j++)
            {
                if(j==0)
                {
                    outputString.append(grid[i][j] == 0 ? "-" :
                        String.format("%1d", grid[i][j]));
                }else
                {
                    outputString.append(grid[i][j] == 0 ? "    -" :
                        String.format("%5d", grid[i][j]));
                }
            }
            outputString.append("\n");
        }
        return outputString.toString();
    }
    
    
}
