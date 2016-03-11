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
    // ������Ϸ��Ψһ��gf����
    GamePanel gf;
    // ������Ϸ��Ψһ��gameNo����
    GameNo gameNo;
    // �����
    private static Random r = new Random();
    // ������Ϸ�Ĵ�С
    private int gpX = 190;
    private int ygpY = 103;
    // ��Ϸ����
    static int score = 0;
    // ��Ϸ����ʱ��
    static int gameSpeed = 2;
    // ��־
    private boolean changeState, state;

    // ��ʼ��
    public ManagerOfNumAndButton(GamePanel gf) {
	this.gf = gf;
	gameNo = new GameNo();
	initBut();
    }

    // ��ȡ��������Ϸ����
    public int getScore() {
	return score;
    }

    public static void setScore(int score) {
	ManagerOfNumAndButton.score = score;
    }

    // ��ʼ���Զ��尴ť
    public void initBut() {
	// �Ƚ��а�ť�����
	gameNo.processNum();
	// ���Դ�ӡ
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		System.out.print(gameNo.getANum(i, j) + " ");
	    }
	    System.out.println("");
	}
	// ���ǵ�����������������壬��һ��һ�еĳ�ʼ����ť���
	for (int i = 0; i < 64; i++) {
	    int col = (i / 8); // ÿcol��
	    int row = i - col * 8; // ÿrow��
	    int ImgID = gameNo.getANum(row, col);
	    gf.buttons.add(new MyButton(gpX + 44 * col, ygpY + 44 * row, i,
		    ImgID));
	}
    }

    // �Ѱ�ť��ImaIDͬ������Ϸ�ĺ�����ֵ
    void sendImgIDToNum() {
	for (int i = 0; i < 8; i++)
	    for (int j = 0; j < 8; j++) {
		gameNo.setANum(i, j, gf.buttons.get(i + j * 8).getImgID());
	    }
    }

    // ����Ϸ�ĺ�����ֵͬ������ť��ImaID
    void sendNumToImgID() {
	for (int i = 0; i < 8; i++)
	    for (int j = 0; j < 8; j++) {
		gf.buttons.get(i + j * 8).setImgID(gameNo.getANum(i, j));
	    }
    }

    // �ƶ�����̨��ֵ�����ʱ���ƶ��ⲿ���Զ��尴ť
    public boolean moveBut() {
	changeState = false;
	sendImgIDToNum();
	isSome();
	sendNumToImgID();
	// �ں�̨��ӡ���Ե�����
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		System.out.print(gameNo.getANum(i, j) + " ");
	    }
	    System.out.println("");
	}
	return changeState;
    }

    // ��鰴ť�Ƿ���ͬ��ʹ����ֵ�����ƶ���ť
    public void isSome() {
	// �����ֵ
	gameNo.checkNum();
	state = false;
	int a;
	// ʹ����ֵ�����ƶ���ť
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
	    isSome(); // �ݹ顣
	}
    }

    // ��Ϸ���³�ʼ��
    public void shuffle() {
	gameNo.initNum();
	gameNo.processNum();
	sendNumToImgID();
    }
}
