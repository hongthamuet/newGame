package Tower_Defense.Other;

import Tower_Defense.GameEntity.Tower;
import Tower_Defense.GameStage.MainGame;

import java.awt.*;

public class Room {
    public int worldWidth = 17;
    public int worldHeight = 11;
    public int blockSize = 52;

    public Tower[][] block;

    public Room(){
        define();
    }
    public void define(){
        //chia thành các ô vuông, mỗi ô vuông kích thước 52
        block = new Tower[worldHeight][worldWidth];// khung có chiều dài ứng với 12 ô, chiều rộng ứng với 8 ô
        for(int y=0; y<block.length; y++){
            for(int x=0; x<block[0].length; x++){
                //thiết lập vị trí cho các ô vuông trong bảng
                block[y][x] = new Tower((MainGame.myWidth/2)-((worldWidth*blockSize)/2)+(x*blockSize),
                        y*blockSize, blockSize,blockSize, Value.groundGrass, Value.airAir);
            }
        }
    }
    public void physic(){
        for(int y=0; y<block.length; y++){
            for(int x=0; x<block[0].length; x++){
                block[y][x].physic();
            }
        }
    }
    public void draw(Graphics g){
        for(int y=0; y<block.length; y++){
            for(int x=0; x<block[0].length; x++){
                block[y][x].draw(g);
            }
        }
        for(int y=0; y<block.length; y++){
            for(int x=0; x<block[0].length; x++){
                block[y][x].fight(g);
            }
        }

    }
}
