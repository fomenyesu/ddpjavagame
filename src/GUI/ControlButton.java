package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
/**
 * @author winson
 * @mail 654874992@qq.com
 * @site http://hatustudio.com
 * ���ư�ť���࣬���Լӽ��ر�İ�ť�����翪ʼ�������Ŀ��ư�ť
 */
public class ControlButton extends Label {
    // ���ư�ť��ͼ��
    String imgState;
    // ��ť��x,y����
    int x, y;
    // ��ť�Ƿ�򿪵ı�־
    boolean isStart = false, mouseOn = false;
    private static boolean init = false;

    // ���ø���ȡ��ť�Ƿ�ʼ�ı�־
    public boolean isStart() {
	return isStart;
    }

    public void setStart(boolean isStart) {
	this.isStart = isStart;
    }

    // ʹ��MAP������String��Ӧ��Ӧ��ͼƬ
    private Map<String, Image> imgs = new HashMap<String, Image>();

    public ControlButton(String imgURL1, String imgURL2, String imgURL3,
	    String imgURL4, int x, int y) {
	URL landUrl = this.getClass().getResource(imgURL1);
	Image img1 = new ImageIcon(landUrl).getImage();
	URL landUr2 = this.getClass().getResource(imgURL2);
	Image img2 = new ImageIcon(landUr2).getImage();
	URL landUr3 = this.getClass().getResource(imgURL3);
	Image img3 = new ImageIcon(landUr3).getImage();
	URL landUr4 = this.getClass().getResource(imgURL4);
	Image img4 = new ImageIcon(landUr4).getImage();
	this.x = x;
	this.y = y;
	imgs.put("0", img1);
	imgs.put("1", img2);
	imgs.put("2", img3);
	imgs.put("3", img4);
    }

    // draw����
    public void draw(Graphics g) {
	// ���ڴ����ⲿ��һ�Σ���ΪͼƬ���صĻ���
	if (!init) {
	    for (int i = 0; i < 4; i++) {
		g.drawImage(imgs.get(i), -100, -100, null);
	    }
	    init = true;
	}
	// ��鰴ť״̬���ٻ���Ӧ��ͼ��
	if (isStart) {
	    if (mouseOn) {
		g.drawImage(imgs.get("1"), x, y, 30, 30, null);

	    } else {
		g.drawImage(imgs.get("0"), x, y, 30, 30, null);
		// g.drawImage(imgs.get("4"), 0, 0, 30, 30, null);
		// g.drawImage(imgs.get("5"), 0, 0, 30, 30, null);
	    }
	} else {
	    if (mouseOn) {
		g.drawImage(imgs.get("3"), x, y, 30, 30, null);
	    } else {
		g.drawImage(imgs.get("2"), x, y, 30, 30, null);

	    }
	}
    }

    // ��ͣ����ʼ״̬�ĸı�
    public boolean mouseCli() {
	if (isStart) {
	    isStart = false;
	} else {
	    isStart = true;
	}
	return isStart;
    }
}
