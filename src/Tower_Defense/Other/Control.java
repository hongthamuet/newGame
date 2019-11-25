package Tower_Defense.Other;

import Tower_Defense.GameStage.MainGame;
import Tower_Defense.Main.Menu;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//https://www.hocvietnam.com/xu-ly-su-kien-chuot-trong-java/

public class Control implements MouseListener, MouseMotionListener {


    @Override
    public void mouseClicked(MouseEvent e) {
//        Screen.mse = new Point(e.getX() - (Frame.size.width - Screen.myWidth) / 2,
//                e.getY() - ((Frame.size.height - (Screen.myHeight))- (Frame.size.width-Screen.myWidth)/2));
    }

    @Override
    public void mousePressed(MouseEvent e) {//được gọi khi nút chuột được nhấn và con trỏ chuột ở component
        MainGame.store.click(e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // Di chuyển các đối tượng sử dụng chuột(Thao tác với chuột để click lựa chọn các thàn phần)
    @Override
    //mouseDragged:Được sử dụng khi chuột được nhấn trên một thành phần và sau đó được kéo(drag)
    public void mouseDragged(MouseEvent e) {
        MainGame.mse = new Point(e.getX() - (Menu.size.width - MainGame.myWidth) / 2,
                e.getY() - ((Menu.size.height - (MainGame.myHeight))- (Menu.size.width- MainGame.myWidth)/2));
    }

    @Override
    // mouseMoved: Được sử dụng khi chuột đã di chuyển trên màn hình và không có nút nào được nhấn
    public void mouseMoved(MouseEvent e) {
        MainGame.mse = new Point(e.getX() - (Menu.size.width - MainGame.myWidth) / 2,
                e.getY() - ((Menu.size.height - (MainGame.myHeight)) - (Menu.size.width- MainGame.myWidth)/2));
    }
}
