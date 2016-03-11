package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

/**
 * @author winson
 * @mail 654874992@qq.com
 * @site http://hatustudio.com
 * 在面板上画出图形分数的类
 */

public class Score {
    private static boolean init = false;
    private static String s;
    // 接收并缓存图片，使用MAP映射图片跟String
    private static Image[] scoreImages = null;
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Map<String, Image> imgs = new HashMap<String, Image>();
    static {
	scoreImages = new Image[] {
		tk.getImage(Score.class.getClassLoader().getResource(
			"images/1.png")),
		tk.getImage(Score.class.getClassLoader().getResource(
			"images/2.png")),
		tk.getImage(Score.class.getClassLoader().getResource(
			"images/3.png")),
		tk.getImage(Score.class.getClassLoader().getResource(
			"images/4.png")),
		tk.getImage(Score.class.getClassLoader().getResource(
			"images/5.png")),
		tk.getImage(Score.class.getClassLoader().getResource(
			"images/6.png")),
		tk.getImage(Score.class.getClassLoader().getResource(
			"images/7.png")),
		tk.getImage(Score.class.getClassLoader().getResource(
			"images/8.png")),
		tk.getImage(Score.class.getClassLoader().getResource(
			"images/9.png")),
		tk.getImage(Score.class.getClassLoader().getResource(
			"images/0.png")),

	};
	imgs.put("0", scoreImages[9]);
	imgs.put("1", scoreImages[0]);
	imgs.put("2", scoreImages[1]);
	imgs.put("3", scoreImages[2]);
	imgs.put("4", scoreImages[3]);
	imgs.put("5", scoreImages[4]);
	imgs.put("6", scoreImages[5]);
	imgs.put("7", scoreImages[6]);
	imgs.put("8", scoreImages[7]);
	imgs.put("9", scoreImages[8]);
    }

    // 显示图形分数的画笔方法
    public void draw(Graphics g, int imgID) {
	// 缓冲图片
	if (!init) {
	    for (int i = 0; i < scoreImages.length; i++) {
		g.drawImage(scoreImages[i], -100, -100, null);
	    }
	    init = true;
	}
	// 接收图片的ID
	s = String.valueOf(imgID);
	// System.out.print(s);
	// 检查图片的ID，在MAP中映射出来
	for (int i = 0; i <= s.length() - 1; i++) {
	    switch (s.charAt(i)) {
	    case '0':
		g.drawImage(imgs.get("0"), 45 + i * 15, 320, 12, 20, null);
		break;
	    case '1':
		g.drawImage(imgs.get("1"), 45 + i * 15, 320, 12, 20, null);
		break;
	    case '2':
		g.drawImage(imgs.get("2"), 45 + i * 15, 320, 12, 20, null);
		break;
	    case '3':
		g.drawImage(imgs.get("3"), 45 + i * 15, 320, 12, 20, null);
		break;
	    case '4':
		g.drawImage(imgs.get("4"), 45 + i * 15, 320, 12, 20, null);
		break;
	    case '5':
		g.drawImage(imgs.get("5"), 45 + i * 15, 320, 12, 20, null);
		break;
	    case '6':
		g.drawImage(imgs.get("6"), 45 + i * 15, 320, 12, 20, null);
		break;
	    case '7':
		g.drawImage(imgs.get("7"), 45 + i * 15, 320, 12, 20, null);
		break;
	    case '8':
		g.drawImage(imgs.get("8"), 45 + i * 15, 320, 12, 20, null);
		break;
	    case '9':
		g.drawImage(imgs.get("9"), 45 + i * 15, 320, 12, 20, null);
		break;
	    }
	    // System.out.print("Char"+s.charAt(i));
	}
    }
}