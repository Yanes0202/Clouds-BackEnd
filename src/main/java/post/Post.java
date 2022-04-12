package post;

public class Post {
    private String title;
    private String image;
    private String description;
    private int id;

    public Post(int id, String title, String image, String description){
        this.id =id;
        this.title=title;
        this.image=image;
        this.description=description;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
