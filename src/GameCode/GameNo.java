package GameCode;

import java.util.*;



/**
 * @author winson
 * @mail 654874992@qq.com
 * @site http://hatustudio.com
 *对对碰的主要算法：
 *
 *功能描述：
 *生成随机数1~7，添加进数组中，使用递归循环遍历，查询数组中是否
 *有3个连续相同的数字，如果有则除去连续数，再在其中加进随机数字。
 *在控制台中显示信息。
 */
public class GameNo {
    // 随机生成随机数
    private static Random r = new Random();
    // 是8行8列的数组
    private int[][] num = new int[8][8];
    // 缓存消除的真值表CleanButID
    private boolean[][] CleanButID = new boolean[8][8];
    // 删除的数值
    int delnumber = 0;

    // 取得或设置第i行第j列的数
    public int getANum(int i, int j) {
	return num[i][j];
    }

    public void setANum(int i, int j, int num) {
	this.num[i][j] = num;
    }

    // 取得整个数组
    public int[][] getNum() {
	return this.num;
    }

    // 设置真值表第i个值
    public void setCleanButID(int i, int j, boolean bool) {
	CleanButID[i][j] = bool;
    }

    // 取得真值表第i行j列的值
    public boolean getCleanButID(int i, int j) {
	return CleanButID[i][j];
    }

    // 初始化
    public GameNo() {
	initNum();
    }

    // 初始化num,其中i是列数，而j是行数
    public void initNum() {
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		num[i][j] = r.nextInt(7);// 读为i行j列
	    }
	}
    }

    // 把缓存真值表清空，全部设置为false
    public void resetCleanID() {
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		CleanButID[i][j] = false;// 读为i行j列
	    }
	}
    }

    // 递归遍历，检查是否有连续三个一样的数字。使用真值表存储需要消除的真值
    public boolean[][] checkNum() {
	resetCleanID();// 清空真值
	int temp;// 行或列，用一个boolean CleanButID[]存储，返回true;
	// 列向对比。
	for (int i = 0; i < 8; i++) {
	    for (int j = 1; j < 7; j++) {
		temp = getANum(i, j);
		if (temp == getANum(i, j - 1) && temp == getANum(i, j + 1)) { // 分析列向
									      // 1-6
		    CleanButID[i][j] = true;
		    CleanButID[i][(j - 1)] = true;
		    CleanButID[i][(j + 1)] = true;

		    System.out.println("行数字:" + temp + "在第" + (j + 1) + "列，第"
			    + (i + 1) + "行相等");
		}
	    }
	}

	// 横向对比。
	for (int j = 0; j < 8; j++) {
	    for (int i = 1; i < 7; i++) {
		temp = getANum(i, j);
		if (temp == getANum(i - 1, j) && temp == getANum(i + 1, j)) { // 分析横向
									      // 1-6
		    CleanButID[i][j] = true;
		    CleanButID[(i + 1)][j] = true;
		    CleanButID[(i - 1)][j] = true;

		    System.out.println("列数字:" + temp + "在第" + (j + 1) + "列，第"
			    + (i + 1) + "行相等");
		}
	    }
	}
	// 在打印台测试真值表的正确性
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		System.out.print(CleanButID[i][j] + "  ");
	    }
	    System.out.println("");
	}
	return CleanButID;

    }

    // 清除相同的数据，并重新加入随机数。返回真值表，使用递归循环实现。
    public void processNum() {
	// 在后台输出测试的数值列表
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		System.out.print(getANum(i, j) + " ");
	    }
	    System.out.println("");
	}
	checkNum();// 检查相同数据的方法
	boolean state = false;// 状态，数值是否相同
	int a;
	// 清除相同的数据，并重新加入随机数的算法。
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		a = i;
		if (CleanButID[i][j]) {
		    while (a > 0) {
			num[a][j] = num[a - 1][j];
			a--;
		    }
		    num[a][j] = r.nextInt(7);
		    state = true;
		}
	    }
	}
	if (state) {
	    processNum(); // 递归。
	    // 添加分数算法。
	}
    }

    // 查找真值表的每一列有多少个真值
    public int ckCleanID(int col) {
	int count = 0;
	for (int j = 0; j < 8; j++) {
	    if (CleanButID[col][j])
		count++;
	}
	return count;
    }

    // 用于测试的main函数
    public static void main(String args[]) {
	GameNo fu = new GameNo();
	fu.processNum();
	// fu.getCleanID();
	// fu.checkNum();
	// fu.getCleanID();
    }
}