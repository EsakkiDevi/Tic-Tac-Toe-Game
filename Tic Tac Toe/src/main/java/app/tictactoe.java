package app;



import java.util.*;
import dao.daotasks;
import model.pojo;
import model.pojo1;


public class tictactoe {

	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		
		
		Scanner sc=new Scanner(System.in);
		
		String playagain="yes";
		
		char[][] board=new char[3][3];
		
		do 
		{
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[0].length;j++)
			{
				board[i][j]=' ';
			}
		}
		
		int currentGameId = daotasks.startGame();
		
		char currentplayer='X';
		boolean win=false;
		
		for(int  turn=0;turn<9;turn++)
		{
			printboard(board);
			int row,col;
			
			while(true)
			{
				System.out.println("Player "+currentplayer+" enter the move row and col(0-2):");
				row=sc.nextInt();
				col=sc.nextInt();
				
				if(row<0 || row>2 ||col<0 || col>2 || board[row][col]!=' ')
				{
					System.out.println("Invalid Move..!Try Again..");
					
				}
				else
				{
					break;
				}
			}
			
			board[row][col]=currentplayer;
			
			  pojo move = new pojo(currentGameId, currentplayer, row, col);
              daotasks.recordMove(move);

			
			
			if(iswin(currentplayer,board))
			{
				printboard(board);
				System.out.println("Player "+currentplayer+" Wins.....!");
				
				daotasks.updateGameStats(currentGameId, currentplayer);
				win=true;
				break;
			}
			
			currentplayer=currentplayer=='X'?'O':'X';
		}
			if(!win)
			{
				printboard(board);
				System.out.println("It is draww....!");
			    daotasks.updateGameStats(currentGameId, null);
			}
			
			System.out.println("If You want playAgain?(yes/no):");
			sc.nextLine();
		    playagain=sc.nextLine().toLowerCase();
			
		
		
		}while(playagain.equals("yes"));
		
		
		

	}
	public static void printboard(char[][]board)
	{
		for(int i=0;i<board.length;i++)
		{
			
				System.out.println(" "+board[i][0]+" | "+board[i][1]+" | "+board[i][2]);
				if(i<2)
					System.out.println("---*---*---|");
			
		}
	}
	public static boolean iswin(char p,char[][] b)
	{
	for(int i=0;i<3;i++)
	{
		if(b[i][0]==p && b[i][1]==p && b[i][2]==p)
			return true;
		
	}
	
	for(int i=0;i<3;i++)
	{
		if(b[0][i]==p && b[1][i]==p & b[2][i]==p)
		
	       return true;
	}
	
	return (b[0][0]==p && b[1][1]==p && b[2][2]==p) || (b[0][2]==p && b[1][1]==p && b[2][0]==p); 
	}
	
	

}
