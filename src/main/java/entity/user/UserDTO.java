package entity.user;

import entity.GenericDTO;

public class UserDTO extends GenericDTO {

    public String username;
    public String password;
    public float rating;
    public int points;

    public UserDTO(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.rating = user.getRating();
        this.points = user.getPoints();
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

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
