package model;

public class pojo1 {


private static int id;
private static int xwins;
private static int owins;
private static int draws;

public pojo1(int id,int xwins,int owins,int draws)
{
	this.id=id;
	this.xwins=xwins;
	this.owins=owins;
	this.draws=draws;
}



public  int getId() {
	return id;
}
public void setId(int id) {
	pojo1.id = id;
}
public  int getXwins() {
	return xwins;
}
public  void setXwins(int xwins) {
	pojo1.xwins = xwins;
}
public  int getOwins() {
	return owins;
}
public  void setOwins(int owins) {
	pojo1.owins = owins;
}
public  int getDraws() {
	return draws;
}
public  void setDraws(int draws) {
	pojo1.draws = draws;
}	

public String toString()
{
	return "Game{" +" id: "+id+" PlayerX_wins: "+xwins+" PlayerO_wins: "+owins+" Draws: "+draws+" }";
}


}
