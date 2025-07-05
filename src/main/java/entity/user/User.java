package entity.user;

public class User {
    String username;
    String password;
    float rating;
    int points;

    public User(){
    }

    public User(String username, String password, float rating, int points) {
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

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) { this.rating = rating; }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points){
        this.points += points;
    }

}
