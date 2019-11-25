package Tower_Defense.GameStage;

import Tower_Defense.GameEntity.Enemy;
import Tower_Defense.Main.Menu;
import Tower_Defense.Main.Music;
import Tower_Defense.Other.Control;
import Tower_Defense.Other.Room;
import Tower_Defense.Other.Value;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;

public class MainGame extends JPanel implements Runnable {
    //tạo một thread bằng cách thực hiện từ interface Runnable

    public Thread thread = new Thread(this);
    // Thread trong java: https://vietjack.com/java/multithread_trong_java.jsp
    //https://viblo.asia/p/multithreading-cac-cach-khoi-tao-va-su-dung-java-thread-5y8Rr7n0Mob3

    public static Image[] tileset_ground = new Image[100];
    public static Image[] tileset_air = new Image[100];
    public static Image[] tileset_res = new Image[100];
    public static Image[] tileset_mob = new Image[100];
    public static Image[] backGround = new Image[10];

    public static Image[] tileset_tree = new Image[100];

    public static int myWidth, myHeight;
    public static int coinage = 50, health = 100;
    public static int killed = 0, killToWin = 100, level = 1, maxlevel = 4;
    public static int winTime = 4000, winFrame = 0;// winTime: thời gian dừng giữa các màn

    public static int killCount = 0;
    public int[] mobNumber = {3, 5, 7, 10, 13, 16, 17, 19, 23, 24, 25, 28, 31, 34, 37, 38, 40, 43, 47, 48, 50, 55};
    public int mobSpawned = 0;

    public static boolean isFirst = true;
    //public static boolean isDebug = false;
    public static boolean isWin = false;

    public static Point mse = new Point(0, 0);

    public static Room room;
    public static LoadLevel save;
    public static ComponentGame store;
    public static Tower_Defense.Main.Menu frame;


    public static Enemy[] mobs = new Enemy[100];// số lượng quân địch

    public MainGame(Menu frame) {
        frame.addMouseListener(new Control());
        frame.addMouseMotionListener(new Control());
        thread.start();//Khởi chạy thread, bắt đầu thực hiện các thao tác trong hàm run()
    }

    public static void hasWon() {
        if (killed == killToWin) {
            isWin = true;
            killed = 0;
            //coinage = 0;
        }
    }

    public void define() {// define: định nghĩa
        room = new Room();
        save = new LoadLevel();
        store = new ComponentGame();

        for (int i = 0; i < tileset_ground.length; i++) {
            tileset_ground[i] = new ImageIcon("Image_MainGame/tileset_Ground.png").getImage();
            tileset_ground[i] = createImage(new FilteredImageSource(tileset_ground[i].getSource(),
                    new CropImageFilter(0, 26 * i, 26, 26)));
        }

        //vẽ đích đến và tháp bắn
        for (int i = 0; i < tileset_air.length; i++) {
            tileset_air[i] = new ImageIcon("Image_MainGame/tileset_air.png").getImage();
            tileset_air[i] = createImage(new FilteredImageSource(tileset_air[i].getSource(),
                    new CropImageFilter(0, 26 * i, 26, 26)));
        }
        //"./src/res/Music/nhacnen.wav"
        tileset_air[4] = new ImageIcon("./src/Game_Image/Tree_Image/peach_tree.png").getImage();
        tileset_air[5] = new ImageIcon("./src/Game_Image/Tree_Image/kumquat_tree.png").getImage();
        tileset_air[6] = new ImageIcon("./src/Game_Image/Tree_Image/tree.png").getImage();
        tileset_air[7] = new ImageIcon("./src/Game_Image/Tree_Image/tree1.png").getImage();
        tileset_air[8] = new ImageIcon("./src/Game_Image/Tree_Image/tree2.png").getImage();
        tileset_air[9] = new ImageIcon("./src/Game_Image/Tree_Image/tree3.png").getImage();
        tileset_air[10] = new ImageIcon("./src/Game_Image/Tree_Image/pineTree.png").getImage();
        tileset_air[11] = new ImageIcon("./src/Game_Image/Tree_Image/flower.png").getImage();
        tileset_air[12] = new ImageIcon("./src/Game_Image/Tree_Image/flower1.png").getImage();
        tileset_air[13] = new ImageIcon("./src/Game_Image/Tree_Image/flower2.png").getImage();
        tileset_air[14] = new ImageIcon("./src/Game_Image/Tree_Image/flower3.png").getImage();
        tileset_air[15] = new ImageIcon("./src/Game_Image/Tower_Image/tower.png").getImage();
        tileset_air[16] = new ImageIcon("./src/Game_Image/Tower_Image/tower1.png").getImage();
        tileset_air[17] = new ImageIcon("./src/Game_Image/Tower_Image/tower2.png").getImage();
        tileset_air[18] = new ImageIcon("./src/Game_Image/House_Image/house.png").getImage();
        tileset_air[19] = new ImageIcon("./src/Game_Image/House_Image/house1.png").getImage();
        tileset_air[20] = new ImageIcon("./src/Game_Image/House_Image/house2.png").getImage();
        tileset_air[21] = new ImageIcon("./src/Game_Image/House_Image/housetree.png").getImage();
        tileset_air[22] = new ImageIcon("./src/Game_Image/Scenery_Image/rock.png").getImage();
        tileset_air[23] = new ImageIcon("./src/Game_Image/Scenery_Image/straw.png").getImage();
        tileset_air[24] = new ImageIcon("./src/Game_Image/Scenery_Image/snowman.png").getImage();
        tileset_air[25] = new ImageIcon("./src/Game_Image/Scenery_Image/mountain.png").getImage();
        tileset_air[26] = new ImageIcon("./src/Game_Image/Scenery_Image/well.png").getImage();
        tileset_air[27] = new ImageIcon("./src/Game_Image/Scenery_Image/fountain.png").getImage();
        tileset_air[28] = new ImageIcon("./src/Game_Image/Tree_Image/tree4.png").getImage();
        tileset_air[29] = new ImageIcon("./src/Game_Image/Scenery_Image/fence.png").getImage();
        tileset_air[30] = new ImageIcon("./src/Game_Image/Tree_Image/rose.png").getImage();

        tileset_res[0] = new ImageIcon("Image_MainGame/cell.png").getImage();
        tileset_res[1] = new ImageIcon("Image_MainGame/heart.png").getImage();
        tileset_res[2] = new ImageIcon("Image_MainGame/coin.png").getImage();

        tileset_mob[0] = new ImageIcon("./src/Game_Image/Enemy_Image/mob.png").getImage();
        tileset_mob[1] = new ImageIcon("./src/Game_Image/Enemy_Image/boss1.png").getImage();

        save.loadSave(new File("Map_Game/map" + level));

        Music music = new Music("./src/Tower_Defense/Music/MangChung.wav");

        try {
            music.play();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < mobs.length; i++) {
            mobs[i] = new Enemy();
        }
    }

    //https://vietjack.com/java_swing/lop_jcolorchooser_trong_java_swing.jsp
    public void paintComponent(Graphics g) {
        if (isFirst) {
            myWidth = getWidth();
            myHeight = getHeight();

            define();
            isFirst = false;
        }

        g.setColor(new Color(70, 70, 70));// màu xám bên ngoài
        g.fillRect(0, 0, getWidth(), getHeight());

        room.draw(g);//Drawing the room

        // Vẽ địch
        for (int i = 0; i < mobs.length; i++) {
            if (mobs[i].inGame) {
                mobs[i].draw(g);
            }

        }
        store.draw(g);

        //https://vietjack.com/java_swing/lop_graphics_trong_java_swing.jsp
        if (health < 1) {
            backGround[0] = new ImageIcon("Image_MainGame/GameOver.jpg").getImage();
            g.drawImage(backGround[0], 0, 0, getWidth(), getHeight(), null);
        }
        if (isWin) {
            if (level == maxlevel) {
                backGround[1] = new ImageIcon("Image_MainGame/GameWin.jpg").getImage();
                g.drawImage(backGround[1], 0, 0, getWidth(), getHeight(), null);
            } else {
                backGround[2] = new ImageIcon("Image_MainGame/NextLevel.jpg").getImage();
                g.drawImage(backGround[2], 0, 0, getWidth(), getHeight(), null);
            }
        }
    }

    public int spawnTime = 2800, spawnFrame = 0;

    //spawnTime: khoảng thời gian giữa 2 lần xuất hiện
    public int j = 0;

    public void mobSpawner() {
        if (spawnFrame >= spawnTime) {
            for (int i = 0; i < mobs.length; i++) {
                if (!mobs[i].inGame) {
                    if (mobNumber[j] == mobSpawned) {
                        mobs[mobNumber[j]].towerDamage = 1;
                        mobs[mobNumber[j]].spawnMob(Value.mobBoss);
                        if (j < 21) j++;
                    } else {
                        mobs[i].towerDamage = 2;
                        mobs[i].spawnMob(Value.mobGreeny);
                    }
                    mobSpawned += 1;
                    break;
                }
            }
            spawnFrame = 0;
        } else {
            spawnFrame++;
        }
    }

    //override lại phương thức run()
    public void run() {
        while (true) {
            if (!isFirst && health > 0 && !isWin) {
                room.physic();
                mobSpawner();
                for (int i = 0; i < mobs.length; i++) {
                    if (mobs[i].inGame) {
                        mobs[i].physic();
                    }
                }
            } else {
                if (isWin) {
                    if (winFrame >= winTime) {
                        if (level >= maxlevel) {
                            System.exit(0);

                        } else {
                            level++;
                            define();
                            isWin = false;
                            mobSpawned = 0;
                            j = 0;
                            ComponentGame.buttonPrice[0] = 25 * level;
                            ComponentGame.buttonPrice[1] = 100 * level;
                            coinage += level * 100;
                        }
                        winFrame = 0;
                    } else {
                        winFrame++;
                    }
                }
            }

            repaint();// vẽ lại đối tượng mà JPanel đang có
            try {
                Thread.sleep(1);//tạm ngưng một thread đang hoạt động trong một khoảng thời gian nhất định
            } catch (Exception e) {
            }
        }
    }
}
