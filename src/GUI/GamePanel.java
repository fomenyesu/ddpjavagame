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
 * 游戏的游戏面板类
 */
public class GamePanel extends JPanel {
    //ArrayList类记录Explode跟自定义的按钮对象
	public List<Explode> explodes = new ArrayList<Explode>();
	public List<MyButton> buttons = new ArrayList<MyButton>();
	//游戏主要框架图片的路径
	private static String frameImg = "images/frame.png";
	private static String backgroundImg = "images/image39.jpg";
	private static String imgURL3 = "images/right.png";
	private static String imgURL4 = "images/left.png";
	private static String imgPus = "images/pause.png";
	private static String imgOver = "images/timeOver.png";
	private static String imgTimeBar = "images/timeBar.png";
	//游戏初始化的状态
	private static boolean init = false;
	//使用Image[]类储存图片，并使用Toolkit读取图片，用MAP把图片映射为String
	private static Image[] gameImages = null;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Map<String, Image> imgs = new HashMap<String, Image>();
	//静态代码读取图片
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
	// 游戏中的GMFrameOld对象
	APPMain gfo;
	//游戏的控制按钮，开始游戏、声音按钮
	ControlButton start,sound;
	//两个临时的自定义按钮用来实现按钮的移动
	MyButton but1, but2;
	// 游戏中的GameTimer对象
	GameTimer gt;
	// 游戏中的managerOfNumAndButton对象
	ManagerOfNumAndButton MOB;
	// 游戏中的Score对象
	Score score;
	// 游戏中的GameMusic对象
	GameMusic gameMu;
	//记录时间的数值
	int gtGV, oldTime;
	//状态：按钮是否可以交换，游戏开始，结束，声音控制
	boolean changeable,isStart,isStop,isSoundOn;
	
	//设置游戏开始
	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}
	//构造方法
	public GamePanel(APPMain gfo) {
		this.gfo = gfo;
		try {
		addMouseListener(new GameMouse());
		LauchGame();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
	}
	//主面板初始化方法
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
	//画笔
	public void paint(Graphics g) {
		if(!init) {
			for (int i = 0; i < gameImages.length; i++) {
				g.drawImage(gameImages[i], -100, -100, null);
			}
			init = true;
		}

		g.drawImage(gameImages[1], 13, 59, 550, 450, null);
		g.drawImage(gameImages[0], 4, 26, 570, 553, null);
		//下面是buttons、explodes、CTvalue的对象个数。
		//如果开始，就画出所得分数与游戏时间条。
		if(isStart){
			//g.drawString("score:" + MOB.getScore(), 100, 300);
			g.drawImage(gameImages[6], 237, 70, gtGV, 21, null) ;
		}
		//如果button初始化完毕，就在面板上画出button
		for (int i = 0; i < buttons.size(); i++) {
			MyButton t = buttons.get(i);
			t.draw(g);
		}
		//如果explode对象出现，就画出explode
		for (int i = 0; i < explodes.size(); i++) {
			Explode e = explodes.get(i);
			gt.addGTBarValue(100);//爆炸奖励，增加时间值
			e.draw(g);			
		}
		//如果停止，关上游戏界面
		if(isStop){
			g.drawImage(gameImages[3], 190, 103, 195, 352, null);
			g.drawImage(gameImages[2], 347, 103, 195, 352, null);
			g.drawImage(gameImages[4], 250, 353, 176, 52, null);			
		}
		//如果时间结束，关闭游戏，关闭声音
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
	//找到鼠标点击的自定义按钮
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
//交换两个按钮信息。
	public void butInfoEX(MyButton but1 , MyButton but2){
		MyButton butTemp = new MyButton(-50, -50, -1, 0);
		butTemp = but1;
		buttons.set(but1.ButID, buttons.get(but2.ButID));
		buttons.set(but2.ButID, butTemp);
		int a = but2.ButID;
		but2.ButID = but1.ButID;
		but1.ButID = a;
	}
//两个按钮的对移
	public void butExMove(MyButton but1 , MyButton but2){
		but1.locateDirection(but1.ButID, but2.ButID);
		but2.locateDirection(but2.ButID, but1.ButID);
		but1.setMoveT(4);
		but2.setMoveT(4);
	}
	
//交换按钮，加入动画效果，并且，当移动不符合时，把按钮交换回来
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
	//向下一下移动按钮
	public void butDown(int id1,int id2){
		buttons.get(id1).setMoveDown(id2);
	}
//新按钮向下移动
	public void newBut(int x,int y,int id,int newY){
		buttons.get(id).newMove(x,y,newY);
	}
//线程类，控制游戏画笔
	private class MainThread implements Runnable {
		public void run() {
			while (true) {
				//gtGV接收时间类上的信息。
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
	//鼠标监听类扩展MouseAdapter
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
