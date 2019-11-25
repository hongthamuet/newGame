package Tower_Defense.GameStage;

import Tower_Defense.GameStage.MainGame;

import java.io.File;
import java.util.Scanner;

public class LoadLevel {
    public void loadSave(File loadPath){
        try {
            Scanner scannerLoad = new Scanner(loadPath);
            while (scannerLoad.hasNext()){

                MainGame.killToWin = scannerLoad.nextInt();

                for(int y = 0; y< MainGame.room.block.length; y++){
                    for(int x = 0; x< MainGame.room.block[0].length; x++){
                        MainGame.room.block[y][x].groundId = scannerLoad.nextInt();
                    }
                }

                for(int y = 0; y< MainGame.room.block.length; y++){
                    for(int x = 0; x< MainGame.room.block[0].length; x++){
                        MainGame.room.block[y][x].airId = scannerLoad.nextInt();
                    }
                }
            }

            scannerLoad.close();

        }catch (Exception e){}
    }
}
