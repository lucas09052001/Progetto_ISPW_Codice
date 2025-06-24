package entity;

public class Discount {
    String ownerId;
    int percentage;
    String location;
    int pointsToRedeem;
    boolean used;

    public Discount() {
    }

    public Discount(String ownerId, int percentage, String location, int pointsToRedeem, boolean used) {
        this.ownerId = ownerId;
        this.percentage = percentage;
        this.location = location;
        this.pointsToRedeem = pointsToRedeem;
        this.used = used;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPointsToRedeem() {
        return pointsToRedeem;
    }

    public void setPointsToRedeem(int pointsToRedeem) {
        this.pointsToRedeem = pointsToRedeem;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
