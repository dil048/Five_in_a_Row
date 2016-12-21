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
            int row = sc.nextInt();
            int col = sc.nextInt();
            a.addPiece(row, col);
        }while(!a.checkWin()&&!a.full());
        if(a.checkWin())
        {
            System.out.println(a);
            System.out.println("Player "+ a.getTurn()+ " won!");
        }
        if(a.full())
        {
            System.out.println(a);
            System.out.println("Game over. It is a tie.");
        }
    }

}
