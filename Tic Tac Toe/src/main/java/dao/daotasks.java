package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dbconnection.db;
import model.pojo;
import model.pojo1;




public class daotasks {
	
	
		  public static int startGame()
		  {
			  String sql="INSERT INTO game(player_x_wins,player_o_wins,draws)VALUES(0,0,0)";
			  try(Connection con=db.getConnection();
					  PreparedStatement st=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){
				 
				 
				  st.executeUpdate();
				  ResultSet rs=st.getGeneratedKeys();
				  if(rs.next())
				  {
					 int  currentGameid=rs.getInt(1);
					  
					  
					  System.out.println("New Game Starts..!Game Id:"+currentGameid);
					  return currentGameid;
				  }
				  
			  }
			  catch(Exception e)
			  {
				  e.printStackTrace();
			  }
			  return -1;
			  
		  }
		  
		  public static void recordMove(pojo details)
		  {
			  String sql="INSERT INTO moves(game_id,player,row_index,col_index)VALUES(?,?,?,?)";
			  try(Connection con=db.getConnection();
					  PreparedStatement st=con.prepareStatement(sql))
			  {
				  st.setInt(1,details.getGameid());
				  st.setString(2,String.valueOf(details.getPlayer()));
				  st.setInt(3, details.getRow());
				  st.setInt(4, details.getCol());
				  st.executeUpdate();
				  
				  
			  }
			  catch(Exception e)
			  {
				  e.printStackTrace();
		  }
		  }
			  
			  public static void updateGameStats(int currentGameid,Character winner)
			  {
				  String sql="";
				  if(winner==null)
				  {
					  sql="UPDATE game SET draws=draws+1 WHERE id=?";
				  }
				  else if(winner=='X')
				  {
					  sql="UPDATE game SET player_x_wins=player_x_wins+1 WHERE id=?";
				  }
				  else if(winner=='O')
				  {
					  sql="UPDATE game SET player_o_wins=player_o_wins+1 WHERE id=?";
				  }
				  
				  try(Connection con=db.getConnection();
						  PreparedStatement st=con.prepareStatement(sql))
				  {
					  st.setInt(1, currentGameid);
					  st.executeUpdate();
				  }
				  catch(Exception e)
				  {
					  e.printStackTrace();
					  
				  }
			  }
			  
			  public List<pojo> movesByid(int gameId)
			  {
				  List<pojo> moves=new ArrayList<>();
				  String sql="SELECT * FROM   moves WHERE game_id=?";
				  try(Connection con=db.getConnection();
						  PreparedStatement st=con.prepareStatement(sql))
				  {
					  st.setInt(1, gameId);
					  ResultSet rs=st.executeQuery();
					  while(rs.next())
					  {
						  pojo p=new pojo(
								  rs.getInt("id"),
								  rs.getInt("game_id"),
								  rs.getString("player").charAt(0),
								  rs.getInt("row_index"),
								  rs.getInt("col_index")
								  );
						  moves.add(p);
						  
					  }
				  }
				  catch(Exception e)
				  {
					  e.printStackTrace();
				  }
				  return moves;
			  }
			  
			  
			  public pojo1 gameByid(int gameid)
			  
			  {
				  String sql="SELECT * FROM game WHERE id=?";
				  try(Connection con=db.getConnection();
						  PreparedStatement st=con.prepareStatement(sql))
				  {
					  st.setInt(1, gameid);
					 ResultSet rs=st.executeQuery();
					 while(rs.next())
					 {
						 pojo1 p1=new pojo1(
								 rs.getInt("id"),
								 rs.getInt("player_x_wins"),
								 rs.getInt("player_o_wins"),
								 rs.getInt("draws")
								 );
						 return p1;
								 
					 }
				  }
				  catch(Exception e)
				  {
					  e.printStackTrace();
				  }
				  return null;
			  }

	

}
