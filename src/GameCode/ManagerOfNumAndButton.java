package GameCode;

import java.util.*;

import GUI.Explode;
import GUI.GamePanel;
import GUI.MyButton;

/**
 * @author winson
 * @mail 654874992@qq.com
 * @site http://hatustudio.com
 */
public class ManagerOfNumAndButton {
    // 接收游戏中唯一的gf对象
    GamePanel gf;
    // 接收游戏中唯一的gameNo对象
    GameNo gameNo;
    // 随机数
    private static Random r = new Random();
    // 设置游戏的大小
    private int gpX = 190;
    private int ygpY = 103;
    // 游戏分数
    static int score = 0;
    // 游戏进行时间
    static int gameSpeed = 2;
    // 标志
    private boolean changeState, state;

    // 初始化
    public ManagerOfNumAndButton(GamePanel gf) {
	this.gf = gf;
	gameNo = new GameNo();
	initBut();
    }

    // 获取跟设置游戏分数
    public int getScore() {
	return score;
    }

    public static void setScore(int score) {
	ManagerOfNumAndButton.score = score;
    }

    // 初始化自定义按钮
    public void initBut() {
	// 先进行按钮的清空
	gameNo.processNum();
	// 测试打印
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		System.out.print(gameNo.getANum(i, j) + " ");
	    }
	    System.out.println("");
	}
	// 考虑到组件将从上面进入面板，先一列一列的初始化按钮组件
	for (int i = 0; i < 64; i++) {
	    int col = (i / 8); // 每col列
	    int row = i - col * 8; // 每row行
	    int ImgID = gameNo.getANum(row, col);
	    gf.buttons.add(new MyButton(gpX + 44 * col, ygpY + 44 * row, i,
		    ImgID));
	}
    }

    // 把按钮的ImaID同步到游戏的核心数值
    void sendImgIDToNum() {
	for (int i = 0; i < 8; i++)
	    for (int j = 0; j < 8; j++) {
		gameNo.setANum(i, j, gf.buttons.get(i + j * 8).getImgID());
	    }
    }

    // 把游戏的核心数值同步到按钮的ImaID
    void sendNumToImgID() {
	for (int i = 0; i < 8; i++)
	    for (int j = 0; j < 8; j++) {
		gf.buttons.get(i + j * 8).setImgID(gameNo.getANum(i, j));
	    }
    }

    // 移动当后台真值表完成时，移动外部的自定义按钮
    public boolean moveBut() {
	changeState = false;
	sendImgIDToNum();
	isSome();
	sendNumToImgID();
	// 在后台打印测试的数字
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		System.out.print(gameNo.getANum(i, j) + " ");
	    }
	    System.out.println("");
	}
	return changeState;
    }

    // 检查按钮是否相同，使用真值表来移动按钮
    public void isSome() {
	// 检查数值
	gameNo.checkNum();
	state = false;
	int a;
	// 使用真值表来移动按钮
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		a = i;
		if (gameNo.getCleanButID(i, j)) {
		    gf.explodes.add(new Explode(gpX + 44 * j, ygpY + 44 * i,
			    gf.buttons.get(i + j * 8).getImgID(), gf));
		    changeState = true;
		    while (a > 0) {
			gameNo.setANum(a, j, gameNo.getANum(a - 1, j));
			gf.butDown(((a - 1) + j * 8), ygpY + 44 * a);
			gf.butInfoEX(gf.buttons.get(a + j * 8), gf.buttons
				.get((a - 1) + j * 8));
			a--;
		    }
		    score = score + 10;
		    gameNo.setANum(a, j, r.nextInt(7));
		    gf.newBut(gpX + 44 * j, ygpY + 44 * a - 44, (a + j * 8),
			    ygpY + 44 * a);
		    state = true;
		}
	    }
	}
	if (state) {
	    isSome(); // 递归。
	}
    }

    // 游戏重新初始化
    public void shuffle() {
	gameNo.initNum();
	gameNo.processNum();
	sendNumToImgID();
    }
}
