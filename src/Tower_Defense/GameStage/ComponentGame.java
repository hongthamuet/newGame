package Tower_Defense.GameStage;

import Tower_Defense.Other.Room;
import Tower_Defense.Other.Value;

import java.awt.*;

public class ComponentGame {
    private Room room;

    public static int shopWidth = 8;//số lượng ô bên dưới(8 ô)
    public static int buttonSize = 52;
    public static int cellSpace = 2;
    public static int awayFromRoom = 29;//khoảng cách từ thanh chứa tháp bắn đến khung
    public static int iconSize = 20;
    public static int iconSpace = 6;
    public static int iconTextY = 15;
    public static int itemIn = 4;
    public static int heldID = -1;
    public static int realID = -1;

    //nếu là Value.airAir thì để trống(Image "cell")
    public static int[] buttonID = {Value.airTowerFrost, Value.airTowerLaser, Value.airAir, Value.airAir, Value.airAir, Value.airAir, Value.airAir, Value.airTrashCan};
    public static int[] buttonPrice = {25,50,0,0,0,0,0,0};//$10 xuất hiện trên tháp, chỗ nào bằng 0 thì không có

    public Rectangle[] button = new Rectangle[shopWidth];
    public Rectangle buttonHealth;
    public Rectangle buttonCoins;

    public boolean holdsItem = false;

    public ComponentGame(){
        define();
    }
    public void click(int mouseButton){
        if(mouseButton == 1){
            for(int i=0; i<button.length; i++){
                if(button[i].contains(MainGame.mse)){
                    if(buttonID[i]!= Value.airAir){
                        if(buttonID[i] == Value.airTrashCan){//Delete Item
                            holdsItem = false;
                        }else {
                            heldID = buttonID[i];
                            realID = i;
                            holdsItem = true;
                        }
                    }
                }
            }
            if  (holdsItem){
                if(MainGame.coinage >= buttonPrice[realID]){//if(số tiền hiện có >= giá của đồ cần mua)
                    for(int y = 0; y< MainGame.room.block.length; y++){
                        for(int x = 0; x< MainGame.room.block[0].length; x++){
                            if(MainGame.room.block[y][x].contains(MainGame.mse)){
                                if((MainGame.room.block[y][x].groundId != Value.groundRoad&&MainGame.room.block[y][x].groundId != Value.groundRoad1&&MainGame.room.block[y][x].groundId != Value.groundRoad2&&MainGame.room.block[y][x].groundId != Value.groundRoad3)&&
                                    MainGame.room.block[y][x].airId == Value.airAir){
                                    MainGame.room.block[y][x].airId = heldID;
                                    MainGame.coinage -= buttonPrice[realID];
                                    holdsItem = false;//đặt lại bằng false thì mob ở mũi tên sẽ mất
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public void define(){
        for(int i=0; i<button.length; i++){
            button[i] = new Rectangle((MainGame.myWidth/2)-(shopWidth*(buttonSize+cellSpace)/2) +(buttonSize+cellSpace)*i,
                    MainGame.room.block[MainGame.room.worldHeight-1][0].y + MainGame.room.blockSize +awayFromRoom,buttonSize,buttonSize);
        }
        buttonHealth = new Rectangle(MainGame.room.block[0][0].x-1, button[0].y, iconSize, iconSize);
        buttonCoins = new Rectangle(MainGame.room.block[0][0].x-1, button[0].y + button[0].height-iconSize, iconSize, iconSize);
    }



    public void draw(Graphics g){
        for(int i=0; i<button.length; i++){
            if(button[i].contains(MainGame.mse)){
                //Khi kích chuột vào đâu thì ở đó hiện lên màu mà setColor
                g.setColor(new Color(81,31,144));
                g.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
            }
            //Vẽ các ô chứa tháp bắn(8 ô bên dưới)
            g.drawImage(MainGame.tileset_res[0], button[i].x, button[i].y, button[i].width, button[i].height,null);

            if(buttonID[i]!= Value.airAir) {g.drawImage(MainGame.tileset_air[buttonID[i]], button[i].x + itemIn, button[i].y + itemIn,
                    button[i].width - (itemIn*2), button[i].height - itemIn*2,null);}
            if(buttonPrice[i]>0) {
                g.setColor(new Color(255,255,255));
                g.setFont(new Font("Courier New ", Font.BOLD, 14));
                g.drawString("$" + buttonPrice[i],button[i].x + itemIn + 8, button[i].y - 2);
            }
        }
        g.drawImage(MainGame.tileset_res[1],buttonHealth.x, buttonHealth.y, buttonHealth.width, buttonHealth.height,null);
        g.drawImage(MainGame.tileset_res[2],buttonCoins.x, buttonCoins.y, buttonCoins.width, buttonCoins.height,null);
        g.setFont(new Font("Courier New", Font.BOLD, 14));
        g.setColor(new Color(255,255,255));
        g.drawString("" + MainGame.health, buttonHealth.width + buttonHealth.x + iconSpace, buttonHealth.y+iconTextY);
        g.drawString("" + MainGame.coinage, buttonCoins.width + buttonCoins.x + iconSpace, buttonCoins.y+iconTextY);

        if(holdsItem){
            g.drawImage(MainGame.tileset_air[heldID], MainGame.mse.x - ((button[0].width - (itemIn*2))/2) + itemIn,
                    MainGame.mse.y - ((button[0].width - (itemIn*2))/2) + itemIn,
                    button[0].width - (itemIn*2), button[0].height - itemIn*2, null);
        }
    }
}
