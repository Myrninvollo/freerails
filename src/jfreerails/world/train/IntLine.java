package jfreerails.world.train;

public class IntLine {
	
	public int x1, x2, y1, y2;
	
	public double getLength(){		
		int sumOfSquares=(x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);
		return Math.sqrt((double)sumOfSquares);
	}
    
    public IntLine(int xx1, int yy1,int  xx2,int yy2){
        x1=xx1;
        y1=yy1;
        x2=xx2;
        y2=yy2;                
    }
    
    public IntLine(){
        
    }

}
