/**
 *
 *
 *
 *
 *
 */
package jfreerails.world.financial;

import jfreerails.world.misc.Money;


/**
 *
 *
 *
 * @author lindsal
 */

public class CurrentAccount {




/**
 *

 */

    private Money balance;


   // associations

    public RRCompany rRCompany;



  //access methods for attributes

    public Money getBalance() {
        return balance;
    }
    public void setBalance(Money balance) {
        this.balance = balance;
    }


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





