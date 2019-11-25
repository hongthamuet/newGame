package Tower_Defense.GameEntity;

import Tower_Defense.Main.Menu;
import Tower_Defense.GameStage.MainGame;
import Tower_Defense.Other.Value;

import java.awt.*;

public class Enemy extends Rectangle {
    public int xC, yC;
    public int health;
    public int healthSpace = 3, healthHeight = 6;
    public int mobSize = 52;
    public int mobWalk = 0;
    public int upward = 0, downward = 1, right = 2, left = 3;
    public int direction = right;//phương hướng
    public int mobID = Value.mobAir;
    public boolean inGame = false;
    public boolean hasUpward = false;
    public boolean hasDownward = false;
    public boolean hasLeft = false;
    public boolean hasRight = false;


    public int walkFrame = 0, walkSpeed = 40; //Walking Speed of mobs
    public boolean slowed = false;
    public int towerDamage = 1;

    public Menu frame;

    public Enemy() {
    }

    //
    public void spawnMob(int mobID) {
        for (int y = 0; y < MainGame.room.block.length; y++) {
            if (MainGame.room.block[y][0].groundId == Value.groundRoad ||
                    MainGame.room.block[y][0].groundId == Value.groundRoad1||
                    MainGame.room.block[y][0].groundId == Value.groundRoad2||
                    MainGame.room.block[y][0].groundId == Value.groundRoad3) {
                setBounds(MainGame.room.block[y][0].x, MainGame.room.block[y][0].y, mobSize, mobSize);
                xC = 0;
                yC = y;
            }
        }

        this.mobID = mobID;
        this.health = mobSize;
        inGame = true;
    }

    public int check_health = health;

    public void deleteMob() {
        inGame = false;
        direction = right;
        mobWalk = 0;
        MainGame.killCount += 1;
        MainGame.room.block[0][0].getMoney(mobID);
    }

    public void looseHealth() {
        MainGame.health -= 50;
    }

    public void physic() {
        if (walkFrame >= walkSpeed) {
            if (direction == right) {
                x++;
            } else if (direction == left) {
                x--;
            } else if (direction == upward) {
                y--;
            } else if (direction == downward) {
                y++;
            }
            mobWalk++;
            if (mobWalk == MainGame.room.blockSize) {
                if (direction == right) {
                    xC++;
                    hasRight = true;
                } else if (direction == upward) {
                    yC--;
                    hasUpward = true;
                } else if (direction == downward) {
                    yC++;
                    hasDownward = true;
                } else if (direction == left) {
                    xC--;
                    hasLeft = true;
                }
                if (!hasUpward) {
                    try {
                        if (MainGame.room.block[yC + 1][xC].groundId == Value.groundRoad ||
                                MainGame.room.block[yC + 1][xC].groundId == Value.groundRoad1 ||
                                MainGame.room.block[yC + 1][xC].groundId == Value.groundRoad2 ||
                                MainGame.room.block[yC + 1][xC].groundId == Value.groundRoad3) {
                            direction = downward;
                        }
                    } catch (Exception e) {
                    }
                }

                if (!hasDownward) {
                    try {
                        if (MainGame.room.block[yC - 1][xC].groundId == Value.groundRoad ||
                                MainGame.room.block[yC - 1][xC].groundId == Value.groundRoad1 ||
                                MainGame.room.block[yC - 1][xC].groundId == Value.groundRoad2 ||
                                MainGame.room.block[yC - 1][xC].groundId == Value.groundRoad3) {
                            direction = upward;
                        }
                    } catch (Exception e) {
                    }
                }
                if (!hasLeft) {
                    try {
                        if (MainGame.room.block[yC][xC + 1].groundId == Value.groundRoad ||
                                MainGame.room.block[yC][xC + 1].groundId == Value.groundRoad1 ||
                                MainGame.room.block[yC][xC + 1].groundId == Value.groundRoad2 ||
                                MainGame.room.block[yC][xC + 1].groundId == Value.groundRoad3) {
                            direction = right;
                        }
                    } catch (Exception e) {
                    }
                }

                if (!hasRight) {
                    try {
                        if (MainGame.room.block[yC][xC - 1].groundId == Value.groundRoad ||
                                MainGame.room.block[yC][xC - 1].groundId == Value.groundRoad1 ||
                                MainGame.room.block[yC][xC - 1].groundId == Value.groundRoad2 ||
                                MainGame.room.block[yC][xC - 1].groundId == Value.groundRoad3) {
                            direction = left;
                        }
                    } catch (Exception e) {
                    }
                }

                if (MainGame.room.block[yC][xC].airId == Value.airCave) {
                    deleteMob();
                    looseHealth();
                }
                hasUpward = false;
                hasDownward = false;
                hasLeft = false;
                hasRight = false;
                mobWalk = 0;
            }
            walkFrame = 0;
        } else walkFrame++;
    }

    public void loseHealth() {
        health -= towerDamage;
        checkDeath();
    }

    public void slow() {
        health -= 3;
        checkDeath();
    }

    public void checkDeath() {
        if (health <= 1) {
            deleteMob();
        }
    }

    public boolean isDeath() {
        if (inGame) {

            return false;
        }
        return true;
    }

    //Tạo phần healthy cho mob
    public void draw(Graphics g) {

        g.drawImage(MainGame.tileset_mob[mobID], x, y, width, height, null);

        g.setColor(new Color(150, 50, 50));
        g.fillRect(x, y - (healthSpace + healthHeight), width, healthHeight);

        g.setColor(new Color(50, 180, 50));
        g.fillRect(x, y - (healthSpace + healthHeight), health, healthHeight);

        //Tạo đường viền ngoài của healthy
        g.setColor(new Color(50, 0, 0));
        g.drawRect(x, y - (healthSpace + healthHeight), health - 1, healthHeight - 1);

    }
}
