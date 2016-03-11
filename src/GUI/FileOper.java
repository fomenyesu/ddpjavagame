package GUI;

import java.io.File;
import java.io.FileWriter;
import javax.swing.JLabel;

/**
 * @author winson
 * @mail 654874992@qq.com
 * @site http://hatustudio.com
 * 游戏的文件控制，负责打开文件"排行榜.txt"，进行写入跟读取操作
 */
public class FileOper {
    // 分数排行榜读取
    public static void panScore(String name, int score) {
	// 第一名的分数
	int l1Score = stringToInt(APPMain.firstS);
	// 第二名的分数
	int l2Score = stringToInt(APPMain.secondS);
	if (score > l1Score) {
	    APPMain.thirdS = APPMain.secondS.replace("第二名", "第三名");
	    APPMain.secondS = APPMain.firstS.replace("第一名", "第二名");
	    APPMain.firstS = "第一名" + name + "  " + score;
	} else if (score > l2Score) {
	    APPMain.thirdS = APPMain.secondS.replace("第二名", "第三名");
	    APPMain.secondS = "第二名" + name + "  " + score;
	} else {
	    APPMain.thirdS = "第三名" + name + "  " + score;
	}
	String snew = APPMain.firstS + "\r\n" + APPMain.secondS + "\r\n"
		+ APPMain.thirdS;
	write(snew);
	resetScore();
    }

    // 刷新分数
    public static void resetScore() {
	APPMain.p1Label.setText(APPMain.firstS);
	APPMain.p2Label.setText(APPMain.secondS);
	APPMain.p3Label.setText(APPMain.thirdS);
    }

    // String到Int的转换
    public static int stringToInt(String s) {
	int a = s.indexOf(" ");
	int reI = Integer.parseInt(s.substring(a).trim());
	return reI;
    }

    // 把分数写进文件中
    public static void write(String s) {
	File f = new File("排行榜.txt"); // File对象打开文件
	try {
	    FileWriter fw = new FileWriter(f);// 使用FileWriter对象写文件
	    fw.append(s);
	    fw.close();
	} catch (Exception e) { // 异常处理
	    e.printStackTrace();
	}
    }
}