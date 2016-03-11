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
 * 控制按钮的类，可以加进特别的按钮，例如开始跟声音的控制按钮
 */
public class ControlButton extends Label {
    // 控制按钮的图形
    String imgState;
    // 按钮的x,y坐标
    int x, y;
    // 按钮是否打开的标志
    boolean isStart = false, mouseOn = false;
    private static boolean init = false;

    // 设置跟获取按钮是否开始的标志
    public boolean isStart() {
	return isStart;
    }

    public void setStart(boolean isStart) {
	this.isStart = isStart;
    }

    // 使用MAP对象，用String对应相应的图片
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

    // draw方法
    public void draw(Graphics g) {
	// 先在窗口外部画一次，作为图片加载的缓冲
	if (!init) {
	    for (int i = 0; i < 4; i++) {
		g.drawImage(imgs.get(i), -100, -100, null);
	    }
	    init = true;
	}
	// 检查按钮状态，再画相应的图形
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

    // 暂停跟开始状态的改变
    public boolean mouseCli() {
	if (isStart) {
	    isStart = false;
	} else {
	    isStart = true;
	}
	return isStart;
    }
}
