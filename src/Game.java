import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;


public class Game {
    private JFrame frame;

    public static void main(String[] args){
        Game game1 = new Game();
    }

    public Game(){
        initialize();
    }

    private void initialize(){

        //Initialize frame
        frame = new JFrame("Mindfulness Game");
        frame.setSize(500,400);
        frame.setLocation(200,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Initialize Menu
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);


        JButton reStart = new JButton("Restart");
        menuBar.add(reStart);
        JMenuItem about = new JMenuItem("About");
        JMenu help = new JMenu("Help");
        menuBar.add(help);
        help.add(about);

        menuBar.add(Box.createHorizontalStrut(100));
        JMenu happyCount = new JMenu("Happy Count: " + 0);
        menuBar.add(happyCount);
        JMenu angryCount = new JMenu("Angry Count: " + 0);
        menuBar.add(angryCount);

        class aboutAction implements ActionListener{
            public void actionPerformed(ActionEvent e){
                System.out.println("aboutAction performed");
                JOptionPane.showMessageDialog(frame,
                        "This application is a \n" +
                                "mindfullness game that \n" +
                        "encourages the user \n" +
                                "towards taking positive \n" +
                        "actions. The game will \n" +
                                "present the user with \n " +
                        "a positive and a negative \n" +
                                "action choice. It's the \n" +
                        "user's job to take positive action \n" +
                        "and avoid negative action.\n");
            }
        }
        about.addActionListener(new aboutAction());

        class reStartAction implements ActionListener{
            public void actionPerformed(ActionEvent f){
                happyCount.setText("Happy Count: " + 0);
                angryCount.setText("Angry Count: " + 0);
                System.out.println("startAction performed");
            }
        }
        reStart.addActionListener(new reStartAction());

        //setBackgroundImage();
        frame.add(new GamePanel(happyCount, angryCount));
        frame.pack();
        frame.setVisible(true);
        try {
            playSound();
        }
        catch(Exception e){
            System.out.println("can't play sound");
            e.printStackTrace();
        }
    }
/*
    private void setBackgroundImage(){
        try {
            frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("src/Img3.jpeg")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
    private void playSound()
            throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        //creating an AudioInputStream object
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream (new File("src/meditation-relaxation-1.wav").getAbsoluteFile());

        //Creating a reference to Clip
        Clip clip = AudioSystem.getClip();

        //opening AudioInputStream to the clip and looping continuously
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);

        clip.start();
    }

}
