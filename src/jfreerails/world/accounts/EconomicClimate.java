package jfreerails.world.accounts;

final public class EconomicClimate {
    private final int climateInterestRate;
    private final String climateName;

    public int getClimateInterestRate() {
        return climateInterestRate;
    }

    public String getClimateName() {
        return climateName;
    }

    public EconomicClimate(int r, String name) {
        climateName = name;
        climateInterestRate = r;
    }
}