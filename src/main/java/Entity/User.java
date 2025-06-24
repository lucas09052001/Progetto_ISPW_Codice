package Entity;

public class User {
    String username;
    String password;
    int rating;
    int points;

    public User(){
    }

    public User(String username, String password, int rating, int points) {
        this.username = username;
        this.password = password;
        this.rating = rating;
        this.points = points;
    }

    public String getUsername() {
        return username;
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

}
