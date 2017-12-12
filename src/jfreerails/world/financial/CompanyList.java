/**
 * Java class generated from Poseidon UML diagram. 
 * Poseidon is developed by <A HREF="http://www.gentleware.com">Gentleware</A>.
 * Generated with <A HREF="http://jakarta.apache.org/velocity/">velocity</A> template engine.
 *
 * 
 */
package jfreerails.world.financial;


import jfreerails.world.GameElementsList;

/**
 * Represents ...
 * 
 * @see OtherClasses
 * @author lindsal
 */

public class CompanyList {

   ///////////////////////////////////////
   // associations

    public GameElementsList gameElementsList;
    public Company company;


   ///////////////////////////////////////
   // access methods for associations


    public GameElementsList getGameElementsList() {
        return gameElementsList;
    }
    public void setGameElementsList(GameElementsList gameElementsList) {
        if (this.gameElementsList != gameElementsList) {
            this.gameElementsList = gameElementsList;
            if (gameElementsList != null)
                gameElementsList.setCompanyList(this);  
        }      
    } 

    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
            this.company = company;
    } 



}





