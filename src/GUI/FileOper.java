package GUI;

import java.io.File;
import java.io.FileWriter;
import javax.swing.JLabel;

/**
 * @author winson
 * @mail 654874992@qq.com
 * @site http://hatustudio.com
 * ��Ϸ���ļ����ƣ�������ļ�"���а�.txt"������д�����ȡ����
 */
public class FileOper {
    // �������а��ȡ
    public static void panScore(String name, int score) {
	// ��һ���ķ���
	int l1Score = stringToInt(APPMain.firstS);
	// �ڶ����ķ���
	int l2Score = stringToInt(APPMain.secondS);
	if (score > l1Score) {
	    APPMain.thirdS = APPMain.secondS.replace("�ڶ���", "������");
	    APPMain.secondS = APPMain.firstS.replace("��һ��", "�ڶ���");
	    APPMain.firstS = "��һ��" + name + "  " + score;
	} else if (score > l2Score) {
	    APPMain.thirdS = APPMain.secondS.replace("�ڶ���", "������");
	    APPMain.secondS = "�ڶ���" + name + "  " + score;
	} else {
	    APPMain.thirdS = "������" + name + "  " + score;
	}
	String snew = APPMain.firstS + "\r\n" + APPMain.secondS + "\r\n"
		+ APPMain.thirdS;
	write(snew);
	resetScore();
    }

    // ˢ�·���
    public static void resetScore() {
	APPMain.p1Label.setText(APPMain.firstS);
	APPMain.p2Label.setText(APPMain.secondS);
	APPMain.p3Label.setText(APPMain.thirdS);
    }

    // String��Int��ת��
    public static int stringToInt(String s) {
	int a = s.indexOf(" ");
	int reI = Integer.parseInt(s.substring(a).trim());
	return reI;
    }

    // �ѷ���д���ļ���
    public static void write(String s) {
	File f = new File("���а�.txt"); // File������ļ�
	try {
	    FileWriter fw = new FileWriter(f);// ʹ��FileWriter����д�ļ�
	    fw.append(s);
	    fw.close();
	} catch (Exception e) { // �쳣����
	    e.printStackTrace();
	}
    }
}