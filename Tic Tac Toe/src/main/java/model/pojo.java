package model;



public class pojo {
	

private int id;
private int gameid;
private char player;
private int row;
private int col;


public pojo(int id, int gameid, char player, int row, int col) {
		
		this.id = id;
		this.gameid = gameid;
		this.player = player;
		this.row = row;
		this.col = col;
	}

public pojo(int gameid, char player, int row, int col) {
	
		this.gameid = gameid;
		this.player = player;
		this.row = row;
		this.col = col;
	}




public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getGameid() {
	return gameid;
}
public void setGameid(int gameid) {
	this.gameid = gameid;
}
public char getPlayer() {
	return player;
}
public void setPlayer(char player) {
	this.player = player;
}
public int getRow() {
	return row;
}
public void setRow(int row) {
	this.row = row;
}
public int getCol() {
	return col;
}
public void setCol(int col) {
	this.col = col;
}

public String toString() {
    return "pojo [id=" + id + ", gameId=" + gameid + ", player=" + player + ", row=" + row + ", col=" + col + "]";
}

}
