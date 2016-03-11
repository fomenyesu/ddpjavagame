package GameCode;

import java.util.*;



/**
 * @author winson
 * @mail 654874992@qq.com
 * @site http://hatustudio.com
 *�Զ�������Ҫ�㷨��
 *
 *����������
 *���������1~7����ӽ������У�ʹ�õݹ�ѭ����������ѯ�������Ƿ�
 *��3��������ͬ�����֣���������ȥ���������������мӽ�������֡�
 *�ڿ���̨����ʾ��Ϣ��
 */
public class GameNo {
    // ������������
    private static Random r = new Random();
    // ��8��8�е�����
    private int[][] num = new int[8][8];
    // ������������ֵ��CleanButID
    private boolean[][] CleanButID = new boolean[8][8];
    // ɾ������ֵ
    int delnumber = 0;

    // ȡ�û����õ�i�е�j�е���
    public int getANum(int i, int j) {
	return num[i][j];
    }

    public void setANum(int i, int j, int num) {
	this.num[i][j] = num;
    }

    // ȡ����������
    public int[][] getNum() {
	return this.num;
    }

    // ������ֵ���i��ֵ
    public void setCleanButID(int i, int j, boolean bool) {
	CleanButID[i][j] = bool;
    }

    // ȡ����ֵ���i��j�е�ֵ
    public boolean getCleanButID(int i, int j) {
	return CleanButID[i][j];
    }

    // ��ʼ��
    public GameNo() {
	initNum();
    }

    // ��ʼ��num,����i����������j������
    public void initNum() {
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		num[i][j] = r.nextInt(7);// ��Ϊi��j��
	    }
	}
    }

    // �ѻ�����ֵ����գ�ȫ������Ϊfalse
    public void resetCleanID() {
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		CleanButID[i][j] = false;// ��Ϊi��j��
	    }
	}
    }

    // �ݹ����������Ƿ�����������һ�������֡�ʹ����ֵ��洢��Ҫ��������ֵ
    public boolean[][] checkNum() {
	resetCleanID();// �����ֵ
	int temp;// �л��У���һ��boolean CleanButID[]�洢������true;
	// ����Աȡ�
	for (int i = 0; i < 8; i++) {
	    for (int j = 1; j < 7; j++) {
		temp = getANum(i, j);
		if (temp == getANum(i, j - 1) && temp == getANum(i, j + 1)) { // ��������
									      // 1-6
		    CleanButID[i][j] = true;
		    CleanButID[i][(j - 1)] = true;
		    CleanButID[i][(j + 1)] = true;

		    System.out.println("������:" + temp + "�ڵ�" + (j + 1) + "�У���"
			    + (i + 1) + "�����");
		}
	    }
	}

	// ����Աȡ�
	for (int j = 0; j < 8; j++) {
	    for (int i = 1; i < 7; i++) {
		temp = getANum(i, j);
		if (temp == getANum(i - 1, j) && temp == getANum(i + 1, j)) { // ��������
									      // 1-6
		    CleanButID[i][j] = true;
		    CleanButID[(i + 1)][j] = true;
		    CleanButID[(i - 1)][j] = true;

		    System.out.println("������:" + temp + "�ڵ�" + (j + 1) + "�У���"
			    + (i + 1) + "�����");
		}
	    }
	}
	// �ڴ�ӡ̨������ֵ�����ȷ��
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		System.out.print(CleanButID[i][j] + "  ");
	    }
	    System.out.println("");
	}
	return CleanButID;

    }

    // �����ͬ�����ݣ������¼����������������ֵ��ʹ�õݹ�ѭ��ʵ�֡�
    public void processNum() {
	// �ں�̨������Ե���ֵ�б�
	for (int i = 0; i < 8; i++) {
	    for (int j = 0; j < 8; j++) {
		System.out.print(getANum(i, j) + " ");
	    }
	    System.out.println("");
	}
	checkNum();// �����ͬ���ݵķ���
	boolean state = false;// ״̬����ֵ�Ƿ���ͬ
	int a;
	// �����ͬ�����ݣ������¼�����������㷨��
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
	    processNum(); // �ݹ顣
	    // ��ӷ����㷨��
	}
    }

    // ������ֵ���ÿһ���ж��ٸ���ֵ
    public int ckCleanID(int col) {
	int count = 0;
	for (int j = 0; j < 8; j++) {
	    if (CleanButID[col][j])
		count++;
	}
	return count;
    }

    // ���ڲ��Ե�main����
    public static void main(String args[]) {
	GameNo fu = new GameNo();
	fu.processNum();
	// fu.getCleanID();
	// fu.checkNum();
	// fu.getCleanID();
    }
}