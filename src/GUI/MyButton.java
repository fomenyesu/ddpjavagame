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
    // ��ť���ԣ��Ƿ���ڣ��Ƿ�ѡ�У������ͼƬID
    private boolean live = true;
    private boolean isChoosed = false;
    private int x, y, ImgID;
    // ��ԭ�ȷ����ƶ����ٶ�
    private int speed = 11;
    // ��ť��ID
    int ButID;
    // �ƶ��Ĵ����������ƶ��Ĵ������ƶ���ȥ
    private int moveT, downMove, moveB;
    // ��ť�ƶ��ķ���
    private Direction dir = Direction.STOP;
    // ��ȡͼƬ����ӦͼƬ��String
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

    // һЩ���ԵĻ�ȡ������
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

    // ��ť��ʼ�������꣬��ťID��ͼƬID
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

    // �Զ��尴ť��ͼ�ν������ʾ
    public void draw(Graphics g) {
	// ��ӦͼƬid��ʾ��Ӧ��ͼƬ
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
	// ��ťѡ��ʱ������ѡ�е�ͼ��
	if (isChoosed) {
	    g.drawImage(imgBorder, x, y, 44, 44, null);
	}
	// �����ƶ���ʾ
	if (y <= downMove - speed) {
	    moveT = 0;
	    moveB = 0;
	    moveDown();
	}
	// �ƶ���ʾ
	if (moveT > 0) {
	    downMove = 0;
	    moveTo();
	    moveT--;
	}
	// �ƶ���ȥ����ʾ
	if (moveB > 0 && moveT == 0 && downMove == 0) {
	    moveB--;
	    moveBack(moveB);
	}
    }

    // ��鰴ť���ƶ�����
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

    // �����е��ƶ����
    void clearMove() {
	moveB = 0;
	moveT = 0;
    }

    // �õ������ƶ���������Ϣ
    void setMoveDown(int md) {
	dir = Direction.STOP;
	downMove = md;
    }

    // �����ƶ���downMoveΪ�����ƶ��İ�ť
    void moveDown() {
	moveT = 0;
	if (y <= downMove - speed) {
	    y = y + speed;
	}
    }

    // ��ȥ��ť�����ƶ����°�ť��ʵ������ԭ����ͬ�İ�ť��ֻ�����¸�ֵ���ѡ�
    void newMove(int x, int y, int newY) {
	this.x = x;
	this.y = y;
	setMoveDown(newY);
    }

    // �����ƶ���������Ϣ��
    void setMoveT(int mt) {
	moveT = mt;
    }

    // ���շ��������ƶ�һ��
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

    // �ƶ����飬��������������ͽ���ť�ƻ�ԭλ
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
