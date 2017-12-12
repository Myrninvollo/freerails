package jfreerails.world.misc;
import java.text.DecimalFormat;



final public class Money {
    
    private static DecimalFormat df= new DecimalFormat("#,###"); 
    
    private final long amount;
    
    public long getAmount() {
        return amount;
    }
    
    
    
    public String toString() {
        return df.format(amount);
    }
    
    public Money(long amount){
        this.amount=amount;
    }
    
}





