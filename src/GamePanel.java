import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class GamePanel extends JPanel {

    private BufferedImage bg, angryFace, happyFace;
    private int bgWidth, bgHeight;
    private int aFWidth, aFHeight;
    private int hFWidth, hFHeight;
    private Rectangle aFRect, hFRect;
    private JMenu happyCountM, angryCountM;
    private int happyCount = 0;
    private int angryCount = 0;
    private int happyX, happyY;
    private int angryX, angryY;

    private Person hPerson = new HappyPerson();
    private Person aPerson = new AngryPerson();

    Random random = new Random();

    public GamePanel(JMenu happyCountM, JMenu angryCountM){
        this.happyCountM = happyCountM;
        this.angryCountM = angryCountM;
        loadGameImages();
        init();
        setPreferredSize(new Dimension(bgWidth, bgHeight));
        addMouseListener(new mouseHandler());

    }

    private void init(){
        bgWidth = bg.getWidth();
        bgHeight = bg.getHeight();
        aFWidth = angryFace.getWidth();
        aFHeight = angryFace.getHeight();
        hFWidth = happyFace.getWidth();
        hFHeight = happyFace.getHeight();
        happyX = random.nextInt(bgWidth - hFWidth/6);
        happyY = random.nextInt(bgHeight - hFHeight/6);
        angryX = random.nextInt(bgWidth - aFWidth);
        angryY = random.nextInt(bgHeight - aFHeight);

    }


    @Override
    public void paintComponent(Graphics g){
        System.out.println("in method paintComponent");
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, bgWidth, bgHeight, null);

        g.drawImage(happyFace, happyX, happyY, hFWidth/6, hFHeight/6, null);
        hFRect = new Rectangle(happyX, happyY, hFWidth/6, hFHeight/6);

        g.drawImage(angryFace, angryX, angryY, aFWidth, aFHeight, null);
        aFRect = new Rectangle(angryX, angryY, aFWidth, aFHeight);

    }

    private void loadGameImages(){
        try{
            bg = ImageIO.read(new File("src/Img3.jpeg"));
            angryFace = ImageIO.read(new File("src/"  + aPerson.getImage()));
            happyFace = ImageIO.read(new File("src/" + hPerson.getImage()));

        }
        catch(IOException ioe){
            System.out.println("Unable to load image");
            System.out.println(ioe);

        }

    }
//Choose a random x and y position for the happy and angry faces.
    private void moveFaces(){
        happyX = random.nextInt(bgWidth - hFWidth/6);
        happyY = random.nextInt(bgHeight - hFHeight/6);
        angryX = random.nextInt(bgWidth - aFWidth);
        angryY = random.nextInt(bgHeight - aFHeight);
    }


    class mouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e){
            Point p = e.getPoint();
            if(hFRect.contains(p)){
                System.out.println("Happy face clicked");
                happyCount++;
                happyCountM.setText("Happy Count: " + happyCount);
                moveFaces();
                //call paintComponent with new coordinates for happy and sad faces
                repaint();
            }
            else if(aFRect.contains(p)){
                System.out.println("Angry face clicked");
                angryCount++;
                angryCountM.setText("Angry Count: " + angryCount);
                moveFaces();
                repaint();
            }
        }

    }
}
