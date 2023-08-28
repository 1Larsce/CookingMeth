public class Methamphetamine {
    private String chemicalComposition;
    private double purity;
    private String color;
    private double productionCost;

    public Methamphetamine(String composition, double purity, String color, double cost) {
        this.chemicalComposition = composition;
        this.purity = purity;
        this.color = color;
        this.productionCost = cost;
    }

    public String getChemicalComposition() {
        return chemicalComposition;
    }

    public double getPurity() {
        return purity;
    }

    public String getColor() {
        return color;
    }

    public double calculateProductionCost() {
        return productionCost;
    }
}
