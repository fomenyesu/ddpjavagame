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
 * 程序的主入口
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
	  static Panel gutpnl = new Panel();//内容容器
		
		  public static void readFile(String row) {
			    File phbFile = new File("排行榜.txt");
			    try
			    {
			      if (row.equals("read")) {
			        String s;
			        FileReader fr = new FileReader(phbFile);
			        BufferedReader br = new BufferedReader(fr);

			        while ((s = br.readLine()) != null) {
			          if (s.startsWith("第一名"))
			          {
			            firstS = s;
			          } else if (s.startsWith("第二名"))
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
		
	  
 //构造函数
 public APPMain(String winname,int width,int height)
 {
  readFile("read");
  GridBagConstraints gbc = new GridBagConstraints();
  GridBagLayout gbl = new GridBagLayout();
  this.setTitle(winname);
  this.setUndecorated(true);
  this.setSize(width,height);
  this.setLayout(gbl);//重要，必须与后面的gbl.setConstraints是同一个对象，否则无法约束其中的组件
  this.setBackground(new Color(200,200,100));
  //添加主panel
  Panel pnl = new Panel();
  //设置布局约束
  gbc.fill=GridBagConstraints.BOTH;    
  gbc.weightx=1.0;
  gbc.weighty=1.0;
  gbc.insets = new Insets(3,3,3,3);  
  //添加布局约束
  gbl.setConstraints(pnl,gbc);
  add(pnl);
  pnl.setLayout(gbl);
  //----------标题栏-------------------
  gbc.insets = new Insets(0,0,0,0);
  gbc.gridx=0;//GridBagConstraints.REMAINDER;
  gbc.gridy=0;//GridBagConstraints.RELATIVE;
  gbc.fill=GridBagConstraints.HORIZONTAL;
  gbc.anchor=GridBagConstraints.NORTH;
  Panel titlebar = new Panel();//标题栏
  gbl.setConstraints(titlebar,gbc);
  
  titlebar.setBackground(new Color(37,37,37));
  titlebar.addMouseMotionListener(new MoveWinPos());
  pnl.add(titlebar);
  //添加标题
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
  
  //最小化，最大化，关闭按钮panel
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
  //按钮
  LabelMListener lml = new LabelMListener();
  Label btnclose = new Label("×",Label.CENTER);
  btnclose.setSize(10,10);
  btnclose.addMouseListener(lml);
 // JLabel btnmax = new JLabel("□",Label.CENTER);
  Label btnmax = new Label("排行榜",Label.CENTER);
  btnmax.addMouseListener(lml);
  //JLabel btnmin = new JLabel("-",Label.CENTER);
  Label btnmin = new Label("游戏",Label.CENTER);
  btnmin.addMouseListener(lml);
  ope.add(btnmin);
  ope.add(btnmax);
  ope.add(btnclose);
  //---------正文内容容器------------------
  gbc.gridx=0;//GridBagConstraints.REMAINDER;
  gbc.gridy=GridBagConstraints.REMAINDER;
  gbc.fill=GridBagConstraints.BOTH;
  
  gbl.setConstraints(gutpnl,gbc);
  gutpnl.add(GP);
  gutpnl.setLayout(null);
  GP.setBounds(-5,-26,578, 583);
  gutpnl.setBackground(new Color(255,255,255));
  pnl.add(gutpnl);
  //为排行榜添加标签

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
	 APPMain myWin=new APPMain("JAVA对对碰游戏",573,557);
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
  if(sourceLabel.getText().equals("×"))
  {
   System.exit(0);
  }
  else if(sourceLabel.getText().equals("排行榜"))//最大化
  {
  /* //顶层容器
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
  /* else if(sourceLabel.getText().equals("排行榜⊙"))//还原
  {
   //顶层容器
	  
	  
   Component cp=(Component)e.getSource();
   while(cp.getParent()!=null)
   {
    cp=cp.getParent();
   } 
   ((Frame)cp).setExtendedState(Frame.NORMAL); 
   sourceLabel.setText("排行榜");  
  }  */
  else if(sourceLabel.getText().equals("游戏"))
  {/*
   //顶层容器
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
 public Point tcpt = null;//鼠标相对于顶层容器的坐标
 public Component topc = null;//顶层容器
 public Point mspt = new Point();//鼠标的屏幕坐标
 //获取顶层容器
 private Component getTopComponent(MouseEvent e)
 {
  Component cp=(Component)e.getSource();
  while(cp.getParent()!=null)
  {
   cp=cp.getParent();
  }
  return cp;
 }
 //获取鼠标的屏幕坐标
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
 //获取鼠标相对于顶层容器的坐标
 public void mouseMoved(MouseEvent e)
 {
  //获取鼠标相对于顶层容器的坐标
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
