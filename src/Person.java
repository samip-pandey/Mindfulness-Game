public class Person {
    private String emotion;
    private String image;

    public static final String ANGRY = "Angry";
    public static final String HAPPY = "Happy";

    public Person(String emotion, String image){
        this.emotion = emotion;
        this.image = image;
    }

    public void setEmotion(String emotion){
        if((emotion == HAPPY) || (emotion == ANGRY)){
            this.emotion = emotion;
        }
    }

    public String getEmotion(){
        return emotion;
    }

    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image = image;
    }

    public String toString(){
        return ("Emotion: " + emotion + " , " + "Image: " + image);
    }


}
