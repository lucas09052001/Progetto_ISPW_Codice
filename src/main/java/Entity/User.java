package Entity;

public class User {
    String username;
    String password;
    int rating;
    int points;
    Discount[] owned_Discounts;

    public User(){
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Discount[] getOwned_Discounts() {
        return owned_Discounts;
    }

    public void setOwned_Discounts(Discount[] owned_Discounts) {
        this.owned_Discounts = owned_Discounts;
    }

    public String getUsername() {
        return username;
    }
}
