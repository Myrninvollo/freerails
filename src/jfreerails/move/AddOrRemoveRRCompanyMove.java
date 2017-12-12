package jfreerails.move;

import java.util.Vector;

import jfreerails.world.financial.Company;

/**
 *
 *
 *
 * @author lindsal
 */

final public class AddOrRemoveRRCompanyMove {

	private final Company owner;

	private final String name;

	private final int noBankruptcies;

	private final Vector addOrRemoveBondMove = new Vector();
	private final Vector addOrRemoveStockMove = new Vector();

	public Company getOwner() {
		return owner;
	}

	public String getName() {
		return name;
	}

	public int getNoBankruptcies() {
		return noBankruptcies;
	}

	public Vector getAddOrRemoveBondMove() {
		return addOrRemoveBondMove;
	}

	public Vector getAddOrRemoveStockMove() {
		return addOrRemoveStockMove;
	}

	public AddOrRemoveRRCompanyMove(
		Company owner,
		String name,
		int noBankruptcies) {
		this.owner = owner;
		this.name = name;
		this.noBankruptcies = noBankruptcies;
	}
}