package Entity;

public class Discount {
    String owner_id;
    int percentage;
    String location;
    int points_to_redeem;
    boolean used;

    public Discount() {
    }

    public Discount(String owner_id, int percentage, String location, int points_to_redeem, boolean used) {
        this.owner_id = owner_id;
        this.percentage = percentage;
        this.location = location;
        this.points_to_redeem = points_to_redeem;
        this.used = used;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
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

    public int getPoints_to_redeem() {
        return points_to_redeem;
    }

    public void setPoints_to_redeem(int points_to_redeem) {
        this.points_to_redeem = points_to_redeem;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
