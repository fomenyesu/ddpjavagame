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
    // ������Ϸ��Ψһ��gf����
    GamePanel gf;
    // ������Ϸ��Ψһ��gf����
    APPMain gfo;
    // ��Ϸ��ʼ��־
    boolean isStart;
    // ʱ�����Ĵ�С
    int GTBarValue = 0;

    // ��ʼ�߳�
    public void setStart(boolean isStart) {
	this.isStart = isStart;
    }

    // ȡ��ʱ�����Ĵ�С
    public int getGTBarValue() {
	return GTBarValue;
    }

    // ����ʱ�����Ĵ�С
    public void setGTBarValue(int barValue) {
	GTBarValue = barValue;
    }

    // ��ʼ��
    public GameTimer(GamePanel gf, APPMain gfo) {
	this.gf = gf;
	this.gfo = gfo;
    }

    // ���ʱ��
    public void addGTBarValue(int gtValue) {
	if (GTBarValue <= 298)
	    GTBarValue = GTBarValue + gtValue / 10;
    }

    // �߳����з���
    public void run() {
	Thread ct = Thread.currentThread();
	while (true) {
	    // ��ʱ����С�ڵ���0��ʲô������
	    while (GTBarValue <= 0) {
	    }
	    // ��ʱ��������0ʱ����ʼ��Ϸ
	    while (GTBarValue > 0) {
		if (!(this.isStart))
		    continue;
		try {
		    Thread.sleep(100L);
		} catch (InterruptedException ie) {
		} catch (Exception e) {
		    e.printStackTrace();
		}
		// ��Ϸʱ�����
		GTBarValue = GTBarValue - ManagerOfNumAndButton.gameSpeed;
		// ��Ϸ�����ﵽһ������ֵʱ���ӿ���Ϸʱ��ļ���
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
		// ��Ϸʱ��С�ڻ����0ʱ����Ϸ��������������Ĵ���
		if (GTBarValue <= 0) {
		    gf.setStart(false);// ������Ϸ����
		    int a = gfo.thirdS.indexOf(" ");
		    int ts = Integer.parseInt(gfo.thirdS.substring(a).trim());
		    String message1 = "�����������ˣ��Ƿ��˳���";
		    String message2 = "<����>";
		    if (ts < ManagerOfNumAndButton.score) {
			String name = JOptionPane.showInputDialog(null,
				"��ϲ���Ƽ�¼��!!\r\n�����������");
			while ((name == null) || (name.trim().equals(""))) {
			    name = JOptionPane.showInputDialog(null,
				    "��ϲ���Ƽ�¼��!!\r\n�����������");
			}

			FileOper.panScore(name, ManagerOfNumAndButton.score);
			message1 = "�Ƿ��˳���";
			message2 = "<Ӯ��>";
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