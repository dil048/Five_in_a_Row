/*import java.util.ArrayList;
import java.util.Scanner;

public class tester
{
    
    public static void main(String [] args)
    {
        board a = new board();
        do
        {
            Scanner sc = new Scanner(System.in);
            System.out.println(a);
            System.out.println("please make a move. Enter row and col");
            String s = sc.next();
            if(s.equals("undo"))
            {
                if(a.undo()==null)
                {
                    System.out.println("Cannot undo");
                }else
                {
                    System.out.println("Undo commanded");
                    a = a.undo();
                }
                continue;
            }else if(s.equals("redo"))
            {
                if(a.redo()==null)
                {
                    System.out.println("Cannot redo");
                }else
                {
                    System.out.println("redo commanded");
                    a = a.redo();
                }
                continue;
            }else
            {
                int row = Integer.parseInt(s);
                int col = sc.nextInt();
                
                a.addPiece(row, col);
            }
        }while(!a.checkWin()&&!a.full());
        if(a.checkWin())
        {
            System.out.println("Player "+ a.getTurn()+ " won!");
        }
        if(a.full())
        {
            System.out.println("Game over. It is a tie.");
        }
    }
    }*/


