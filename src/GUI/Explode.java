package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * @author winson
 * @mail 654874992@qq.com
 * @site http://hatustudio.com
 * 爆炸类，相同的按钮消失的动画效果类
 */
public class Explode {
    // 爆炸的x,y坐标，爆炸的图像ID
    int x, y, imgID;
    // 爆炸的开始跟结束
    private boolean live = true;
    // 接收唯一的gf对象
    GamePanel gf;
    // Toolkit对象
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    // 使用Toolkit读取图像,imgsID自定义按钮爆炸前膨胀，scoBoom，imgs爆炸动画
    private static Image[] imgsID = {
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/image2.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/image4.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/image6.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/image8.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/image10.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/image12.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/image14.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/image16.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource("")), };
    private static Image[] scoBoom = {
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/scoBoom1.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/scoBoom2.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/scoBoom3.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/scoBoom4.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/scoBoom5.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/scoBoom6.png")) };
    private static Image[] imgs = {
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom1.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom1.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom1.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom1.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom1.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom2.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom2.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom2.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom2.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom2.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom3.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom3.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom3.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom3.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom3.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom4.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom4.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom4.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom4.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom4.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom5.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom5.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom5.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom5.png")),
	    tk.getImage(Explode.class.getClassLoader().getResource(
		    "images/boom5.png")) };
    int step = 0; // 动画帧的计数
    int timeID = 0;// 时间
    int drawTime = 50; // 动画时间
    private static boolean init = false;

    // 初始化
    public Explode(int x, int y, int imgID, GamePanel GF) {
	this.x = x;
	this.y = y;
	this.imgID = imgID;
	this.gf = GF;
    }

    // draw方法，使用主面板的画笔在面板上画出爆炸的动画效果
    public void draw(Graphics g) {
	// 先在窗口外部画一次，作为图片加载的缓冲
	if (!init) {
	    for (int i = 0; i < imgs.length; i++) {
		g.drawImage(imgs[i], -100, -100, null);
	    }
	    for (int i = 0; i < imgsID.length; i++) {
		g.drawImage(imgsID[i], -100, -100, null);
	    }
	    for (int i = 0; i < scoBoom.length; i++) {
		g.drawImage(scoBoom[i], -100, -100, null);
	    }
	    init = true;
	}
	// 如果爆炸动画画完，在gf的列表里删除爆炸对象
	if (!live) {
	    gf.explodes.remove(this);
	    gf.gameMu.shortMid(); // 播放爆炸声音
	    return;
	}
	// 当画完动画时，设置live状态为false
	if (step == imgs.length) {
	    live = false;
	    step = 0;
	    timeID = 0;
	    return;
	}
	// 当动画帧还没显示完成，继续draw方法
	if (imgID != 8) {
	    g.drawImage(scoBoom[timeID / 6], 275, 200, 200, 200, null);
	    if (drawTime > 1) {
		g.drawImage(imgsID[imgID], x, y, 50, 50, null);
		drawTime--;
	    }
	    g.drawImage(imgs[step], x - 20, y - 10, null);
	}
	// 画完一次后动画帧计数加一
	step++;
	timeID++;
    }
}
