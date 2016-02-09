package printtest;

public class PrintJob {
    private int blackPages;
    private int colourPages;
    private double blackAmount;
    private double colourAmount;
    private double jobAmount;
    private boolean doubleSided;

    public int getBlackPages() {
        return blackPages;
    }

    public void setBlackPages(int blackPages) {
        this.blackPages = blackPages;
    }

    public int getColourPages() {
        return colourPages;
    }

    public void setColourPages(int colourPages) {
        this.colourPages = colourPages;
    }

    public double getBlackAmount() {
        return blackAmount;
    }

    public void setBlackAmount(double blackAmount) {
        this.blackAmount = blackAmount;
    }

    public double getColourAmount() {
        return colourAmount;
    }

    public void setColourAmount(double colourAmount) {
        this.colourAmount = colourAmount;
    }

    public double getJobAmount() {
        return jobAmount;
    }

    public void setJobAmount(double jobAmount) {
        this.jobAmount = jobAmount;
    }

    public boolean isDoubleSided() {
        return doubleSided;
    }

    public void setDoubleSided(boolean doubleSided) {
        this.doubleSided = doubleSided;
    }
    
}
