package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * @author winson
 * @mail 654874992@qq.com
 * @site http://hatustudio.com
 * ��ը�࣬��ͬ�İ�ť��ʧ�Ķ���Ч����
 */
public class Explode {
    // ��ը��x,y���꣬��ը��ͼ��ID
    int x, y, imgID;
    // ��ը�Ŀ�ʼ������
    private boolean live = true;
    // ����Ψһ��gf����
    GamePanel gf;
    // Toolkit����
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    // ʹ��Toolkit��ȡͼ��,imgsID�Զ��尴ť��ըǰ���ͣ�scoBoom��imgs��ը����
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
    int step = 0; // ����֡�ļ���
    int timeID = 0;// ʱ��
    int drawTime = 50; // ����ʱ��
    private static boolean init = false;

    // ��ʼ��
    public Explode(int x, int y, int imgID, GamePanel GF) {
	this.x = x;
	this.y = y;
	this.imgID = imgID;
	this.gf = GF;
    }

    // draw������ʹ�������Ļ���������ϻ�����ը�Ķ���Ч��
    public void draw(Graphics g) {
	// ���ڴ����ⲿ��һ�Σ���ΪͼƬ���صĻ���
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
	// �����ը�������꣬��gf���б���ɾ����ը����
	if (!live) {
	    gf.explodes.remove(this);
	    gf.gameMu.shortMid(); // ���ű�ը����
	    return;
	}
	// �����궯��ʱ������live״̬Ϊfalse
	if (step == imgs.length) {
	    live = false;
	    step = 0;
	    timeID = 0;
	    return;
	}
	// ������֡��û��ʾ��ɣ�����draw����
	if (imgID != 8) {
	    g.drawImage(scoBoom[timeID / 6], 275, 200, 200, 200, null);
	    if (drawTime > 1) {
		g.drawImage(imgsID[imgID], x, y, 50, 50, null);
		drawTime--;
	    }
	    g.drawImage(imgs[step], x - 20, y - 10, null);
	}
	// ����һ�κ󶯻�֡������һ
	step++;
	timeID++;
    }
}
