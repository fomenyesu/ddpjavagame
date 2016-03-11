package GUI;

import javax.swing.*;

import GameCode.ManagerOfNumAndButton;
import GameCode.GameTimer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author winson
 * @mail 654874992@qq.com
 * @site http://hatustudio.com
 * ��Ϸ����Ϸ�����
 */
public class GamePanel extends JPanel {
    //ArrayList���¼Explode���Զ���İ�ť����
	public List<Explode> explodes = new ArrayList<Explode>();
	public List<MyButton> buttons = new ArrayList<MyButton>();
	//��Ϸ��Ҫ���ͼƬ��·��
	private static String frameImg = "images/frame.png";
	private static String backgroundImg = "images/image39.jpg";
	private static String imgURL3 = "images/right.png";
	private static String imgURL4 = "images/left.png";
	private static String imgPus = "images/pause.png";
	private static String imgOver = "images/timeOver.png";
	private static String imgTimeBar = "images/timeBar.png";
	//��Ϸ��ʼ����״̬
	private static boolean init = false;
	//ʹ��Image[]�ഢ��ͼƬ����ʹ��Toolkit��ȡͼƬ����MAP��ͼƬӳ��ΪString
	private static Image[] gameImages = null;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Map<String, Image> imgs = new HashMap<String, Image>();
	//��̬�����ȡͼƬ
	static {
		gameImages = new Image[] {
				tk.getImage(GamePanel.class.getClassLoader().getResource(frameImg)),
				tk.getImage(GamePanel.class.getClassLoader().getResource(backgroundImg)),
				tk.getImage(GamePanel.class.getClassLoader().getResource(imgURL3)),
				tk.getImage(GamePanel.class.getClassLoader().getResource(imgURL4)),
				tk.getImage(GamePanel.class.getClassLoader().getResource(imgPus)),
				tk.getImage(GamePanel.class.getClassLoader().getResource(imgOver)),
				tk.getImage(GamePanel.class.getClassLoader().getResource(imgTimeBar))
		};
		imgs.put("1" , gameImages[0]);
		imgs.put("2" , gameImages[1]);
		imgs.put("3" , gameImages[2]);
		imgs.put("4" , gameImages[3]);
		imgs.put("5" , gameImages[4]);
		imgs.put("6" , gameImages[5]);
		imgs.put("7" , gameImages[6]);
	}
	// ��Ϸ�е�GMFrameOld����
	APPMain gfo;
	//��Ϸ�Ŀ��ư�ť����ʼ��Ϸ��������ť
	ControlButton start,sound;
	//������ʱ���Զ��尴ť����ʵ�ְ�ť���ƶ�
	MyButton but1, but2;
	// ��Ϸ�е�GameTimer����
	GameTimer gt;
	// ��Ϸ�е�managerOfNumAndButton����
	ManagerOfNumAndButton MOB;
	// ��Ϸ�е�Score����
	Score score;
	// ��Ϸ�е�GameMusic����
	GameMusic gameMu;
	//��¼ʱ�����ֵ
	int gtGV, oldTime;
	//״̬����ť�Ƿ���Խ�������Ϸ��ʼ����������������
	boolean changeable,isStart,isStop,isSoundOn;
	
	//������Ϸ��ʼ
	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}
	//���췽��
	public GamePanel(APPMain gfo) {
		this.gfo = gfo;
		try {
		addMouseListener(new GameMouse());
		LauchGame();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
	}
	//������ʼ������
	public void LauchGame() {
	    setForeground(Color.green);

	    gt = new GameTimer(this,gfo);
	    MOB = new ManagerOfNumAndButton(this);
	    gameMu = new GameMusic(this);
	    isStop = true;
		isStart = false;
		isSoundOn = false;
		sound = new ControlButton("images/button2_3.png","images/button2_4.png","images/button2_1.png","images/button2_2.png",194, 468);
		start = new ControlButton("images/button1_1.png","images/button1_2.png","images/button1_3.png","images/button1_4.png",234, 468);
		//start.setLocation(234, 468);
		this.setLayout(null);
		score = new Score();
		new Thread(new MainThread()).start();
		gameMu.start();
	}
	//����
	public void paint(Graphics g) {
		if(!init) {
			for (int i = 0; i < gameImages.length; i++) {
				g.drawImage(gameImages[i], -100, -100, null);
			}
			init = true;
		}

		g.drawImage(gameImages[1], 13, 59, 550, 450, null);
		g.drawImage(gameImages[0], 4, 26, 570, 553, null);
		//������buttons��explodes��CTvalue�Ķ��������
		//�����ʼ���ͻ������÷�������Ϸʱ������
		if(isStart){
			//g.drawString("score:" + MOB.getScore(), 100, 300);
			g.drawImage(gameImages[6], 237, 70, gtGV, 21, null) ;
		}
		//���button��ʼ����ϣ���������ϻ���button
		for (int i = 0; i < buttons.size(); i++) {
			MyButton t = buttons.get(i);
			t.draw(g);
		}
		//���explode������֣��ͻ���explode
		for (int i = 0; i < explodes.size(); i++) {
			Explode e = explodes.get(i);
			gt.addGTBarValue(100);//��ը����������ʱ��ֵ
			e.draw(g);			
		}
		//���ֹͣ��������Ϸ����
		if(isStop){
			g.drawImage(gameImages[3], 190, 103, 195, 352, null);
			g.drawImage(gameImages[2], 347, 103, 195, 352, null);
			g.drawImage(gameImages[4], 250, 353, 176, 52, null);			
		}
		//���ʱ��������ر���Ϸ���ر�����
		if(gt.getGTBarValue() <= 0){
			g.drawImage(gameImages[3], 190, 103, 195, 352, null);
			g.drawImage(gameImages[2], 347, 103, 195, 352, null);
			g.drawImage(gameImages[5], 250, 353, 236, 50, null);
			sound.setStart(false);
			start.setStart(false);
		}
		start.draw(g);
		sound.draw(g);
		score.draw(g,MOB.getScore());
	}
	void stop(){
		if(gt.isAlive()){
		gt.setGTBarValue(oldTime);
		gt.interrupt();
		}
	}
	//�ҵ���������Զ��尴ť
	public MyButton findBUT(int locateX, int locateY) {
		MyButton find;
		if (locateX < 542 && locateX > 190 && locateY > 105 && locateY < 455 && isStart == true && isStop == false) {
			int IdX = (int) (locateX - 190) / 44;
			int IdY = (int) (locateY - 105) / 44;
			int ID = IdY + IdX * 8;
//System.out.println(ID+"x"+IdX+"y"+IdY);
			find = buttons.get(ID);
		}
		else if(locateX > 194 && locateY > 468 && locateX < 224 && locateY <498){
			isSoundOn = !sound.mouseCli();
			return null;
		}
		else if(locateX > 234 && locateY > 468 && locateX < 264 && locateY <498 && isStart == false){
			MOB.shuffle();
			start.mouseCli();
			isSoundOn = true;
			isStart = true;
			if(!gt.isAlive()){
			gt.start();
			}
			gt.setStart(true);
			gt.setGTBarValue(298);
			isStop = false;		
			return null;
		}
		else if(locateX > 234 && locateY > 468 && locateX < 264 && locateY <498 && isStart == true){
			isStop = !start.mouseCli();
	    	if(isStop){
			oldTime = gt.getGTBarValue();
			stop();
			gt.setStart(false);}
	    	else{
	    	gt.setStart(true);
	    	}
			return null;
		}
		else{
			return null;
		}
		return find;
	}
	
	public void mouseOnCon(int lx,int ly){
		if(lx > 185 && ly > 435 && lx < 215 && ly <465){
			start.mouseOn = true;
		}else{
			start.mouseOn = false;
		}
	}
	
	public void ChangeBut(int locateX, int locateY) {
		MyButton tempBut = findBUT(locateX, locateY);
		boolean isNear = false;
		if (but1 != null && tempBut != null) {
			isNear = tempBut.ButID - but1.ButID == 1
					|| tempBut.ButID - but1.ButID == -1
					|| tempBut.ButID - but1.ButID == 8
					|| tempBut.ButID - but1.ButID == -8;
		}
		if (but1 == null && tempBut != null){
			but1 = findBUT(locateX, locateY);
			but1.setChoosed(true);
		}else if(but1 != null && tempBut != null && isNear) {
			but2 = findBUT(locateX, locateY);
			butExchange(but1,but2);
			but2 = null;
			but1.setChoosed(false);
			but1 = null;
		} 
		else if(but1 != null && tempBut != null && !isNear) {
			but1.setChoosed(false);
			but1 = findBUT(locateX, locateY);
			but1.setChoosed(true);
//System.out.println("but1"+but1.ButID);
		}else if ( but1 != null && tempBut == null){
			but1.setChoosed(false);
			}
	}
//����������ť��Ϣ��
	public void butInfoEX(MyButton but1 , MyButton but2){
		MyButton butTemp = new MyButton(-50, -50, -1, 0);
		butTemp = but1;
		buttons.set(but1.ButID, buttons.get(but2.ButID));
		buttons.set(but2.ButID, butTemp);
		int a = but2.ButID;
		but2.ButID = but1.ButID;
		but1.ButID = a;
	}
//������ť�Ķ���
	public void butExMove(MyButton but1 , MyButton but2){
		but1.locateDirection(but1.ButID, but2.ButID);
		but2.locateDirection(but2.ButID, but1.ButID);
		but1.setMoveT(4);
		but2.setMoveT(4);
	}
	
//������ť�����붯��Ч�������ң����ƶ�������ʱ���Ѱ�ť��������
	public void butExchange(MyButton but1 , MyButton but2) {
		
		this.but1 = but1;
		this.but2 = but2;
		
		if (but2 != null) {
		butExMove(but1,but2);
		butInfoEX(but1,but2);
		changeable = MOB.moveBut();		
		if(!changeable){
			but1.moveBack(4);
			but2.moveBack(4);
			butInfoEX(but1,but2);
		}
		}
	}
	//����һ���ƶ���ť
	public void butDown(int id1,int id2){
		buttons.get(id1).setMoveDown(id2);
	}
//�°�ť�����ƶ�
	public void newBut(int x,int y,int id,int newY){
		buttons.get(id).newMove(x,y,newY);
	}
//�߳��࣬������Ϸ����
	private class MainThread implements Runnable {
		public void run() {
			while (true) {
				//gtGV����ʱ�����ϵ���Ϣ��
				gtGV =  gt.getGTBarValue();
				repaint();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//����������չMouseAdapter
	private class GameMouse extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			// if(e.getX())
			// e.getButton() = but1;
			System.out.println("mouseclick");
			System.out.println(e.getX());
			System.out.println(e.getY());
			ChangeBut(e.getX(), e.getY());
			// but1=buttons.get(0);
			// but2=buttons.get(8);
		}
		}
	}
