package GUI;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author winson
 * @mail 654874992@qq.com
 * @site http://hatustudio.com
 * ����������
 */
public class APPMain extends Frame
{
	  public static String firstS;
	  public static String secondS;
	  public static String thirdS;
	  static JLabel p1Label = new JLabel("1231233");
	  static JLabel p2Label = new JLabel("123123");
	  static JLabel p3Label = new JLabel("12312312");
	  static JPanel phb = new JPanel();
	  GamePanel GP = new GamePanel(this);
	  static File f = new File("images\\");
	  static Panel gutpnl = new Panel();//��������
		
		  public static void readFile(String row) {
			    File phbFile = new File("���а�.txt");
			    try
			    {
			      if (row.equals("read")) {
			        String s;
			        FileReader fr = new FileReader(phbFile);
			        BufferedReader br = new BufferedReader(fr);

			        while ((s = br.readLine()) != null) {
			          if (s.startsWith("��һ��"))
			          {
			            firstS = s;
			          } else if (s.startsWith("�ڶ���"))
			            secondS = s;
			          else {
			            thirdS = s;
			          }
			        }
			        fr.close();
			        br.close(); return;
			      }
			      FileWriter fw = new FileWriter(f);
			      fw.append(firstS + "\r\n");
			      fw.append(secondS + "\r\n");
			      fw.append(thirdS + "\r\n");
			      fw.close();
			    }
			    catch (Exception e) {
			      e.printStackTrace();
			    }
			  }
		
	  
 //���캯��
 public APPMain(String winname,int width,int height)
 {
  readFile("read");
  GridBagConstraints gbc = new GridBagConstraints();
  GridBagLayout gbl = new GridBagLayout();
  this.setTitle(winname);
  this.setUndecorated(true);
  this.setSize(width,height);
  this.setLayout(gbl);//��Ҫ������������gbl.setConstraints��ͬһ�����󣬷����޷�Լ�����е����
  this.setBackground(new Color(200,200,100));
  //�����panel
  Panel pnl = new Panel();
  //���ò���Լ��
  gbc.fill=GridBagConstraints.BOTH;    
  gbc.weightx=1.0;
  gbc.weighty=1.0;
  gbc.insets = new Insets(3,3,3,3);  
  //��Ӳ���Լ��
  gbl.setConstraints(pnl,gbc);
  add(pnl);
  pnl.setLayout(gbl);
  //----------������-------------------
  gbc.insets = new Insets(0,0,0,0);
  gbc.gridx=0;//GridBagConstraints.REMAINDER;
  gbc.gridy=0;//GridBagConstraints.RELATIVE;
  gbc.fill=GridBagConstraints.HORIZONTAL;
  gbc.anchor=GridBagConstraints.NORTH;
  Panel titlebar = new Panel();//������
  gbl.setConstraints(titlebar,gbc);
  
  titlebar.setBackground(new Color(37,37,37));
  titlebar.addMouseMotionListener(new MoveWinPos());
  pnl.add(titlebar);
  //��ӱ���
  titlebar.setLayout(gbl);
  titlebar.setForeground(new Color(255,255,255));
  Label title = new Label(winname,Label.LEFT);
  title.addMouseMotionListener(new MoveWinPos());
  gbc.gridx=0;//GridBagConstraints.RELATIVE;
  gbc.gridy=0;
  gbc.anchor=GridBagConstraints.WEST;
  gbc.fill=GridBagConstraints.EAST;
  gbl.setConstraints(title,gbc);
  titlebar.add(title);
  
  //��С������󻯣��رհ�ťpanel
  Panel ope = new Panel();
  gbc.gridx=1;//GridBagConstraints.RELATIVE;
  gbc.gridy=0;
  gbc.anchor=GridBagConstraints.EAST;
  gbc.fill=GridBagConstraints.WEST;
  gbl.setConstraints(ope,gbc);
  titlebar.add(ope);
  ope.setLayout(new FlowLayout(FlowLayout.RIGHT));
  ope.setForeground(new Color(255,255,255));
  ope.addMouseMotionListener(new MoveWinPos());
  //��ť
  LabelMListener lml = new LabelMListener();
  Label btnclose = new Label("��",Label.CENTER);
  btnclose.setSize(10,10);
  btnclose.addMouseListener(lml);
 // JLabel btnmax = new JLabel("��",Label.CENTER);
  Label btnmax = new Label("���а�",Label.CENTER);
  btnmax.addMouseListener(lml);
  //JLabel btnmin = new JLabel("-",Label.CENTER);
  Label btnmin = new Label("��Ϸ",Label.CENTER);
  btnmin.addMouseListener(lml);
  ope.add(btnmin);
  ope.add(btnmax);
  ope.add(btnclose);
  //---------������������------------------
  gbc.gridx=0;//GridBagConstraints.REMAINDER;
  gbc.gridy=GridBagConstraints.REMAINDER;
  gbc.fill=GridBagConstraints.BOTH;
  
  gbl.setConstraints(gutpnl,gbc);
  gutpnl.add(GP);
  gutpnl.setLayout(null);
  GP.setBounds(-5,-26,578, 583);
  gutpnl.setBackground(new Color(255,255,255));
  pnl.add(gutpnl);
  //Ϊ���а���ӱ�ǩ

  phb.setLayout(null);
  phb.setBackground(new Color(255,255,255));
  p1Label.setForeground(new Color(255,0,0));
  p2Label.setForeground(new Color(255,0,0));
  p3Label.setForeground(new Color(255,0,0));
  p1Label.setBounds(250,100,300,50);
  p2Label.setBounds(250,150,300,50);
  p3Label.setBounds(250,200,300,50);
  p1Label.setText(firstS);
  p2Label.setText(secondS);
  p3Label.setText(thirdS);
  phb.add(p1Label);
  phb.add(p2Label);
  phb.add(p3Label);
 }
 //main
 public static void main(String arg[])
 {
	 APPMain myWin=new APPMain("JAVA�Զ�����Ϸ",573,557);
	 myWin.setVisible(true);
 }
 

class LabelMListener implements MouseListener
{
 Label sourceLabel=null;
 Color oldColor=null;
 public void mouseClicked(MouseEvent e){};
 public void mouseEntered(MouseEvent e)
 {
  sourceLabel=(Label)e.getSource();
  oldColor=sourceLabel.getForeground(); 
  sourceLabel.setBackground(new Color(255,255,255));
  sourceLabel.setForeground(new Color(255,0,0));
  sourceLabel.setForeground(new Color(255,0,0));
  sourceLabel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
 }
 public void mouseExited(MouseEvent e)
 {
  sourceLabel=(Label)e.getSource();
  sourceLabel.setBackground(null);
  sourceLabel.setForeground(oldColor);
 } 
 public void mousePressed(MouseEvent e){};
 public void mouseReleased(MouseEvent e)
 {
  sourceLabel=(Label)e.getSource();
  if(sourceLabel.getText().equals("��"))
  {
   System.exit(0);
  }
  else if(sourceLabel.getText().equals("���а�"))//���
  {
  /* //��������
   Component cp=(Component)e.getSource();
   while(cp.getParent()!=null)
   {
    cp=cp.getParent();
   } 
   ((Frame)cp).setExtendedState(Frame.MAXIMIZED_BOTH); */
   GP.setBounds(-1, -1, 1, 1);
   gutpnl.remove(GP);
   gutpnl.add(phb);
   phb.setBounds(-5,-26,578, 583);
  }
  /* else if(sourceLabel.getText().equals("���а��"))//��ԭ
  {
   //��������
	  
	  
   Component cp=(Component)e.getSource();
   while(cp.getParent()!=null)
   {
    cp=cp.getParent();
   } 
   ((Frame)cp).setExtendedState(Frame.NORMAL); 
   sourceLabel.setText("���а�");  
  }  */
  else if(sourceLabel.getText().equals("��Ϸ"))
  {/*
   //��������
   Component cp=(Component)e.getSource();
   while(cp.getParent()!=null)
   {
    cp=cp.getParent();
   } 
   ((Frame)cp).setState(Frame.ICONIFIED);   */
	   gutpnl.remove(phb);
	   gutpnl.add(GP);
	   GP.setBounds(-5,-26,578, 583);
  }
 }
}
class MoveWinPos implements MouseMotionListener
{
 public Point tcpt = null;//�������ڶ�������������
 public Component topc = null;//��������
 public Point mspt = new Point();//������Ļ����
 //��ȡ��������
 private Component getTopComponent(MouseEvent e)
 {
  Component cp=(Component)e.getSource();
  while(cp.getParent()!=null)
  {
   cp=cp.getParent();
  }
  return cp;
 }
 //��ȡ������Ļ����
 private Point getMspt(MouseEvent e)
 {
  Point pt1 = ((Component)e.getSource()).getLocationOnScreen();
  Point pt2 = e.getPoint();
  mspt.x=pt1.x+pt2.x;
  mspt.y=pt1.y+pt2.y;
  return mspt;
 }
 public void mouseDragged(MouseEvent e)
 {
  if(topc == null)
  {
   topc = getTopComponent(e);
  }
  getMspt(e);
  topc.setLocation(mspt.x-tcpt.x,mspt.y-tcpt.y);
 }
 //��ȡ�������ڶ�������������
 public void mouseMoved(MouseEvent e)
 {
  //��ȡ�������ڶ�������������
  tcpt=e.getPoint();
  Component cp=(Component)e.getSource();
  cp.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
  Rectangle rec=null;
  while(cp.getParent()!=null)
  {
   rec=cp.getBounds();
   tcpt.x+=rec.x;
   tcpt.y+=rec.y;
   cp=cp.getParent();
  } 
  //////////////////////////////
 }
}
}
