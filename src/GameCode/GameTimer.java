package GameCode;

import javax.swing.JOptionPane;

import GUI.FileOper;
import GUI.APPMain;
import GUI.GamePanel;

/**
 * @author winson
 * @mail 654874992@qq.com
 * @site http://hatustudio.com
 */
public class GameTimer extends Thread {
    // 接收游戏中唯一的gf对象
    GamePanel gf;
    // 接收游戏中唯一的gf对象
    APPMain gfo;
    // 游戏开始标志
    boolean isStart;
    // 时间条的大小
    int GTBarValue = 0;

    // 开始线程
    public void setStart(boolean isStart) {
	this.isStart = isStart;
    }

    // 取得时间条的大小
    public int getGTBarValue() {
	return GTBarValue;
    }

    // 设置时间条的大小
    public void setGTBarValue(int barValue) {
	GTBarValue = barValue;
    }

    // 初始化
    public GameTimer(GamePanel gf, APPMain gfo) {
	this.gf = gf;
	this.gfo = gfo;
    }

    // 添加时间
    public void addGTBarValue(int gtValue) {
	if (GTBarValue <= 298)
	    GTBarValue = GTBarValue + gtValue / 10;
    }

    // 线程运行方法
    public void run() {
	Thread ct = Thread.currentThread();
	while (true) {
	    // 当时间条小于等于0，什么都不做
	    while (GTBarValue <= 0) {
	    }
	    // 当时间条大于0时，开始游戏
	    while (GTBarValue > 0) {
		if (!(this.isStart))
		    continue;
		try {
		    Thread.sleep(100L);
		} catch (InterruptedException ie) {
		} catch (Exception e) {
		    e.printStackTrace();
		}
		// 游戏时间减少
		GTBarValue = GTBarValue - ManagerOfNumAndButton.gameSpeed;
		// 游戏分数达到一定的数值时，加快游戏时间的减少
		if (ManagerOfNumAndButton.score < 2000)
		    ManagerOfNumAndButton.gameSpeed = 5;
		else if (ManagerOfNumAndButton.score < 3000)
		    ManagerOfNumAndButton.gameSpeed = 6;
		else if (ManagerOfNumAndButton.score < 5000)
		    ManagerOfNumAndButton.gameSpeed = 7;
		else if (ManagerOfNumAndButton.score < 7000)
		    ManagerOfNumAndButton.gameSpeed = 8;
		else if (ManagerOfNumAndButton.score < 10000)
		    ManagerOfNumAndButton.gameSpeed = 9;
		else if (ManagerOfNumAndButton.score < 14000)
		    ManagerOfNumAndButton.gameSpeed = 10;
		else if (ManagerOfNumAndButton.score < 19000)
		    ManagerOfNumAndButton.gameSpeed = 10;
		// 游戏时间小于或等于0时，游戏结束，进入分数的处理
		if (GTBarValue <= 0) {
		    gf.setStart(false);// 设置游戏结束
		    int a = gfo.thirdS.indexOf(" ");
		    int ts = Integer.parseInt(gfo.thirdS.substring(a).trim());
		    String message1 = "哈哈，你输了，是否退出？";
		    String message2 = "<输了>";
		    if (ts < ManagerOfNumAndButton.score) {
			String name = JOptionPane.showInputDialog(null,
				"恭喜你破记录了!!\r\n请输入大名！");
			while ((name == null) || (name.trim().equals(""))) {
			    name = JOptionPane.showInputDialog(null,
				    "恭喜你破记录了!!\r\n请输入大名！");
			}

			FileOper.panScore(name, ManagerOfNumAndButton.score);
			message1 = "是否退出？";
			message2 = "<赢了>";
		    }
		    int state = JOptionPane.showConfirmDialog(null, message1,
			    message2, 0, 0);
		    if (state == 0) {
			System.exit(0);
		    }
		}
		continue;
	    }
	}
    }
}