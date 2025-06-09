import java.util.*;
import java.sql.SQLException;
class TicTacToe
{
    public static void main(String[] args) {
        String playagain=" ";

 do{       
    Scanner s=new Scanner(System.in);

       char[][] game=new char[3][3];
     
       for(int i=0;i<game.length;i++)
       {
        for( int j=0;j<game[0].length;j++)
        {
            game[i][j]=' ';
            System.out.print( game[i][j]);
            if(j<2)
            {
                System.out.print(" |");
            }
        }
        System.out.println();
        if(i<2)

        {
        System.out.println("--------");
        }
       }

       DBHelper.startGame();

      char Currentplayer='X';
      for(int turn=0;turn<9;turn++)
      {
        int row,col;
        while(true)
        {

        System.out.println("Player "+Currentplayer+ " enter your move(row and col(0-2)): ");
        row=s.nextInt();
        col=s.nextInt();

        if(row<0 || row>2 || col<0 ||col>2)
        {
            System.out.println("You enter wrong input! row and coloumn between 0-2.");
            continue;
        }

        if(game[row][col] != ' ')
        {
            System.out.println("Cell already taken,Try different one");
            continue;
        }
        break;
    }
    System.out.println();

     game[row][col]=Currentplayer;   

      DBHelper.recordMove(Currentplayer, row, col);//////

     if(isWin(game,Currentplayer))
     {
       for(int i=0;i<game.length;i++)
       {
        for(int j=0;j<game[0].length;j++)
        {
                
                System.out.print(game[i][j]);
            
            
            if(j<2)
            {
                System.err.print(" | ");
            }
        }
        System.out.println();
        if(i<2)
        {
        System.out.println("----------");
        }
       }
       System.out.println("Player "+Currentplayer +" wins!\nThe game over!");
       DBHelper.updateGameStats(Currentplayer);
       return;
 }


    for(int i=0;i<game.length;i++)
       {
        for(int j=0;j<game[0].length;j++)
        {
                
                System.out.print(game[i][j]);
            
            
            if(j<2)
            {
                System.err.print(" | ");
            }
        }
        System.out.println();
        if(i<2)
        {
        System.out.println("------------");
        }
       }


       if(Currentplayer=='X')
       {
        Currentplayer='O';
       }
       else{
        Currentplayer='X';
       } 
    }

    System.out.println("it is Draw!");
     DBHelper.updateGameStats(null); 
    System.out.println("Do you play again(yes/no):");
    s.nextLine(); 
    playagain=s.nextLine().toLowerCase();
}while(playagain.equals("yes"));
    
      
    }
    public static boolean isWin(char[][] game, char player)
    {
        for(int i=0;i<game.length;i++)
        {
            if(game[i][0]==player && game[i][1]==player && game[i][2]==player)
            {
                return true;
            }
        }

        for(int j=0;j<game[0].length;j++)
        {
            if(game[0][j]==player && game[1][j]==player && game[2][j]==player)
            {
                return true;
            }
        }

        if(game[0][0]==player && game[1][1]==player && game[2][2]==player)
        {
            return true;
        }

        if(game[0][2]==player && game[1][1]==player && game[2][0]==player)
        {
            return true;
        }
        return false;
    }
}