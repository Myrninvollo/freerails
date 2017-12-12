/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.world.financial;


import jfreerails.world.type.CreditTransactionType;
import jfreerails.world.type.DebitTransactionType;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class CurrentAccount {

  ///////////////////////////////////////
  //attributes

/**
 * Represents ...

 */

    private Money balance;

   ///////////////////////////////////////
   // associations

    public RRCompany rRCompany;


  ///////////////////////////////////////
  //access methods for attributes

    public Money getBalance() {
        return balance;
    }
    public void setBalance(Money balance) {
        this.balance = balance;
    }

   ///////////////////////////////////////
   // access methods for associations


    public RRCompany getRRCompany() {
        return rRCompany;
    }

    public void creditAccount(Money amount, CreditTransactionType type) {
    }
/**
 * Does ...
 * 
 * @param amount ...
 * @param type ...
 */

    public void debitAccount(Money amount, DebitTransactionType type) {
    }



}





