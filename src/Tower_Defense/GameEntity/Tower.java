package Tower_Defense.GameEntity;

import Tower_Defense.GameStage.MainGame;
import Tower_Defense.Other.Value;

import java.awt.*;

public class Tower extends Rectangle {
    public Rectangle towerSquare;//Tháp bắn
    public int towerSquareSize = 130;// kích cỡ của vùng bắn
    public int groundId;
    public int airId;
    public int loseTime = 270, loseFrame = 0;

    public int shotMob = -1;
    public boolean shoting = false;

    public Tower(){}
    public Tower(int x, int y, int width, int height, int groundId, int airId) {
        setBounds(x, y, width, height);
        //Tạo tháp bắn
        towerSquare = new Rectangle(x - towerSquareSize / 2, y - towerSquareSize / 2, width + towerSquareSize,
                height + towerSquareSize);
        this.groundId = groundId;
        this.airId = airId;
    }

    //drawImage(Image img, int x, int y, ImageObserver observer): được sử dụng để vẽ hình ảnh đã cho.
    public void draw(Graphics g) {
        g.drawImage(MainGame.tileset_ground[groundId], x, y, width, height, null);//vẽ đường đi và cỏ xanh
        //Vẽ tháp bắn
        if (airId != Value.airAir) {
            g.drawImage(MainGame.tileset_air[airId], x, y, width, height, null);
        }
    }

    public void physic() {
        // phương thức intersects()của lớp Rectangle kiểm tra va chạm giữa hai hình chữ nhật với nhau
        // trả về true nếu chúng chạm nhau
        if (shotMob != -1 && towerSquare.intersects(MainGame.mobs[shotMob])) {
            shoting = true;
        } else shoting = false;
        if (!shoting) {
            if (airId == Value.airTowerLaser || airId == Value.airTowerFrost) {
                for (int i = 0; i < MainGame.mobs.length; i++) {
                    if (MainGame.mobs[i].inGame) {
                        if (towerSquare.intersects(MainGame.mobs[i])) {
                            shoting = true;
                            shotMob = i;
                        }
                    }
                }
            }
        }
        if (shoting) {
            if (loseFrame >= loseTime) {
                if(airId == Value.airTowerLaser) {
                    MainGame.mobs[shotMob].slow();
                }
                else if(airId == Value.airTowerFrost) {
                    MainGame.mobs[shotMob].loseHealth();
                }
                loseFrame = 0;
            } else {
                loseFrame++;
            }

            if (MainGame.mobs[shotMob].isDeath() && !MainGame.mobs[shotMob].inGame) {
                shoting = false;
                shotMob = -1;
                MainGame.killed++;
                MainGame.hasWon();
            }
        }
    }

    public void getMoney(int mobID) {
        MainGame.coinage += Value.deathReward[mobID];
    }

    public void fight(Graphics g) {
        if (MainGame.store.holdsItem) {
            if (airId == Value.airTowerLaser || airId == Value.airTowerFrost) {
                g.drawRect(towerSquare.x, towerSquare.y, towerSquare.width, towerSquare.height);
            }
        }
        // Tạo tia bắn cho tháp
        if (shoting) {
            if(airId == Value.airTowerLaser) {
                g.setColor(new Color(255, 255, 0));
                // Vẽ đường bắn, căn chỉnh tọa độ phù hợp với vật bị bắn
                g.drawLine(x + width / 2, y + height / 2, MainGame.mobs[shotMob].x + MainGame.mobs[shotMob].width / 2,
                        MainGame.mobs[shotMob].y + MainGame.mobs[shotMob].height / 2);
            }else if(airId == Value.airTowerFrost){
                g.setColor(new Color(223, 0, 0));
                // Vẽ đường bắn, căn chỉnh tọa độ phù hợp với vật bị bắn
                g.drawLine(x + width / 2, y+ height / 2, MainGame.mobs[shotMob].x + MainGame.mobs[shotMob].width / 2,
                        MainGame.mobs[shotMob].y + MainGame.mobs[shotMob].height / 2);
            }
        }

    }
}
