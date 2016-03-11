# ddpjavagame
JAVA GUI 实现的QQ游戏里 怪物对对碰游戏

这个游戏是我根据QQ校友的游戏应用中的怪物对对碰制作而成的

所属的包	说明	                                  对应的类
GameCode	核心数字类，生成数字	                  GameNo
GameCode	时间控制的线程类，包括分数的的记录等。	GameTimer
GameCode	消息类，传送UI跟核心的类	              managerOfNumAndButton
GUI	      游戏的主窗口	                          GMFrameOld
GUI	      自定义按钮移动的方向	                  Direction
GUI	      如果相同后产生的爆炸	                  Explode
GUI	      控制声音跟游戏开始的按钮	              ControlButton
GUI	      游戏的主要界面	                        GamePanel
GUI	      对”排行版.txt”文件的操作              	FileOper
GUI	      用于控制游戏声音的类	                  GameMusic
GUI	      自定义按钮的类，每个可移动的都属于自定义的类。	MyButton
GUI     	控制分数的类	                          Score

另外，为提高软件的响应速度，我还为不同的功能使用线程化操作，即用户核心数字的变化与图形用户界面在不同的线程中实现，声音跟时间也使用了不同的线程。这样游戏可以自由控制声音，转换为排行榜等操作。
