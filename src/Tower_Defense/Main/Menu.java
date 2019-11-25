package Tower_Defense.Main;

import Tower_Defense.GameStage.MainGame;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Menu extends JFrame {
    private Button[] arrayBtn = new Button[5];
    public static String title = " Tower Defense ";
    //700 va 550
    public static Dimension size = new Dimension(1000, 700);

//    File file1 = new File("sound/Lac-Troi-Masew-Mix-Son-Tung-M-TP-Masew.mp3");
//    MP3Player player = new MP3Player(file1);

    ImageIcon background;
    ImageIcon button1, button2;
    JPanel jpanel;

    public void testFrame() {

        background = null;
        this.setSize(size);
        jpanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (background != null) {
                    g.drawImage(background.getImage(),
                            0, 0, getWidth(), getHeight(), null);
                }
            }
        };
        setContentPane(jpanel);

    }

    public void setBackground(ImageIcon img) {
        this.background = img;
    }

    public Menu() {
        //player.play();
        setTitle(title);
        setSize(size);
        setResizable(true);
        setLocationRelativeTo(null);//hiển thị cửa sổ lên vị trí chính giữa
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);


        this.setLayout(null);

        Music music = new Music("./src/Tower_Defense/Music/nhacnen.wav");

        try {
            music.play();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        button1 = new ImageIcon("Image_MainGame/StartGameButton.png");
        button2 = new ImageIcon("Image_MainGame/EditMapButton.png");
        arrayBtn[0] = new Button(240, 300, 200, 35, button1);
        arrayBtn[1] = new Button(240, 350, 200, 35, button2);

        arrayBtn[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                remove(arrayBtn[0]);
                remove(arrayBtn[1]);
                init();
                try {
                    music.stop();
                } catch (UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                }
            }
        });

        arrayBtn[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });
        testFrame();
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBackground(new ImageIcon("Image_MainGame/StartGame.jpg"));
        this.setVisible(true);
        for (int i = 0; i < 2; i++) {
            arrayBtn[i].setVisible(true);
            this.add(arrayBtn[i]);
        }

    }

    public void init() {
        setLayout(new GridLayout(1, 1, 0, 0));

        MainGame screen = new MainGame(this);
        add(screen);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Menu();
    }
}
