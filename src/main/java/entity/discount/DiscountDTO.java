package entity.discount;

public class DiscountDTO {
    String name;
    String pathToImage;
    int percentage;
    int cost;
    String ownerUsername;

    public DiscountDTO(){}

    public DiscountDTO(Discount discount) {
        this.name = discount.getName();
        this.pathToImage = discount.getPathToImage();
        this.percentage = discount.getPercentage();
        this.cost = discount.getCost();
        this.ownerUsername = discount.getOwnerUsername();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }
}
