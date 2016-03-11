package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author winson
 * @mail 654874992@qq.com
 * @site http://hatustudio.com
 */
public class MyButton {
    // 按钮属性，是否存在，是否选中，坐标跟图片ID
    private boolean live = true;
    private boolean isChoosed = false;
    private int x, y, ImgID;
    // 向原先方向移动的速度
    private int speed = 11;
    // 按钮的ID
    int ButID;
    // 移动的次数，向下移动的次数，移动回去
    private int moveT, downMove, moveB;
    // 按钮移动的方向
    private Direction dir = Direction.STOP;
    // 读取图片并对应图片跟String
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] butImages = null;
    private static Image imgBorder = tk.getImage(MyButton.class
	    .getClassLoader().getResource("images/imgBorder.png"));
    private static Map<String, Image> imgs = new HashMap<String, Image>();
    static {
	butImages = new Image[] {
		tk.getImage(MyButton.class.getClassLoader().getResource(
			"images/image1.png")),
		tk.getImage(MyButton.class.getClassLoader().getResource(
			"images/image3.png")),
		tk.getImage(MyButton.class.getClassLoader().getResource(
			"images/image5.png")),
		tk.getImage(MyButton.class.getClassLoader().getResource(
			"images/image7.png")),
		tk.getImage(MyButton.class.getClassLoader().getResource(
			"images/image9.png")),
		tk.getImage(MyButton.class.getClassLoader().getResource(
			"images/image11.png")),
		tk.getImage(MyButton.class.getClassLoader().getResource(
			"images/image13.png")),
		tk.getImage(MyButton.class.getClassLoader().getResource(
			"images/image15.png")) };

	imgs.put("0", butImages[0]);
	imgs.put("1", butImages[1]);
	imgs.put("2", butImages[2]);
	imgs.put("3", butImages[3]);
	imgs.put("4", butImages[4]);
	imgs.put("5", butImages[5]);
	imgs.put("6", butImages[6]);
	imgs.put("7", butImages[7]);
    }

    // 一些属性的获取跟设置
    public void setChoosed(boolean isChoosed) {
	this.isChoosed = isChoosed;
    }

    public void setY(int y) {
	this.y = y;
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }

    public boolean isLive() {
	return live;
    }

    public void setLive(boolean live) {
	this.live = live;
    }

    public void setImgID(int img) {
	this.ImgID = img;
    }

    public int getImgID() {
	return ImgID;
    }

    void setDir(Direction dir) {
	this.dir = dir;
    }

    public Direction getDir() {
	return dir;
    }

    // 按钮初始化，坐标，按钮ID，图片ID
    public MyButton(int x, int y, int ButID, int ImgID) {
	// String image1, String image2, String image3) {
	this.x = x;
	this.y = y;
	this.ImgID = ImgID;
	this.ButID = ButID;
	moveT = 0;
	downMove = 0;
	moveB = 0;
    }

    // 自定义按钮在图形界面的显示
    public void draw(Graphics g) {
	// 对应图片id显示相应的图片
	switch (ImgID) {
	case 0:
	    g.drawImage(imgs.get("0"), x, y, 44, 44, null);
	    break;
	case 1:
	    g.drawImage(imgs.get("1"), x, y, 44, 44, null);
	    break;
	case 2:
	    g.drawImage(imgs.get("2"), x, y, 44, 44, null);
	    break;
	case 3:
	    g.drawImage(imgs.get("3"), x, y, 44, 44, null);
	    break;
	case 4:
	    g.drawImage(imgs.get("4"), x, y, 44, 44, null);
	    break;
	case 5:
	    g.drawImage(imgs.get("5"), x, y, 44, 44, null);
	    break;
	case 6:
	    g.drawImage(imgs.get("6"), x, y, 44, 44, null);
	    break;
	case 7:
	    g.drawImage(imgs.get("7"), x, y, 44, 44, null);
	    break;
	case 8:
	    break;
	}
	// 按钮选中时，加上选中的图框
	if (isChoosed) {
	    g.drawImage(imgBorder, x, y, 44, 44, null);
	}
	// 向下移动显示
	if (y <= downMove - speed) {
	    moveT = 0;
	    moveB = 0;
	    moveDown();
	}
	// 移动显示
	if (moveT > 0) {
	    downMove = 0;
	    moveTo();
	    moveT--;
	}
	// 移动回去的显示
	if (moveB > 0 && moveT == 0 && downMove == 0) {
	    moveB--;
	    moveBack(moveB);
	}
    }

    // 检查按钮的移动方向
    void locateDirection(int butID1, int butID2) {
	if (butID2 - butID1 == 1) {
	    dir = Direction.D;
	} else if (butID2 - butID1 == -1) {
	    dir = Direction.U;
	} else if (butID2 - butID1 == 8) {
	    dir = Direction.R;
	} else if (butID2 - butID1 == -8) {
	    dir = Direction.L;
	}
    }

    // 把所有的移动清除
    void clearMove() {
	moveB = 0;
	moveT = 0;
    }

    // 得到向下移动步数的信息
    void setMoveDown(int md) {
	dir = Direction.STOP;
	downMove = md;
    }

    // 向下移动，downMove为向下移动的按钮
    void moveDown() {
	moveT = 0;
	if (y <= downMove - speed) {
	    y = y + speed;
	}
    }

    // 消去按钮向下移动的新按钮，实际上是原先相同的按钮，只是重新赋值而已。
    void newMove(int x, int y, int newY) {
	this.x = x;
	this.y = y;
	setMoveDown(newY);
    }

    // 接收移动步数的信息。
    void setMoveT(int mt) {
	moveT = mt;
    }

    // 按照方向设置移动一步
    void moveTo() {
	if (moveT > 0) {
	    switch (dir) {
	    case L:
		x = x - speed;
		break;
	    case U:
		y = y - speed;
		break;
	    case R:
		x = x + speed;
		break;
	    case D:
		y = y + speed;
		break;
	    }
	}
    }

    // 移动后检查，如果不能消除，就将按钮移回原位
    void moveBack(int mov) {
	moveB = mov;
	if (moveB > 0) {
	    switch (dir) {
	    case L:
		x = x + speed;
		break;
	    case U:
		y = y + speed;
		break;
	    case R:
		x = x - speed;
		break;
	    case D:
		y = y - speed;
		break;
	    }
	}
    }
}
