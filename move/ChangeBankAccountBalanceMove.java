/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.move;

import java.util.*;

import jfreerails.massnoun.Money;
import jfreerails.type.DebitTransactionType;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class ChangeBankAccountBalanceMove extends AbstractFinancialMove {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    public Money oldBalance;
/**
 * Represents ...

 */

    public Money newBalance;
/**
 * Represents ...

 */

    public DebitTransactionType type;

  ///////////////////////////////////////
  //access methods for attributes

    public Money getOldBalance() {
        return oldBalance;
    }
    public void setOldBalance(Money oldBalance) {
        this.oldBalance = oldBalance;
    }
    public Money getNewBalance() {
        return newBalance;
    }
    public void setNewBalance(Money newBalance) {
        this.newBalance = newBalance;
    }
    public DebitTransactionType getType() {
        return type;
    }
    public void setType(DebitTransactionType type) {
        this.type = type;
    }


}





