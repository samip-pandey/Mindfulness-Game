public class Test {
    public static void main(String[] args){
        Person hP = new HappyPerson();
        Person aP = new AngryPerson();

        System.out.println(hP.getEmotion());
        System.out.println(hP);
        System.out.println(aP);

    }
}
