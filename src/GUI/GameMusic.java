package GUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * @author winson
 * @mail 654874992@qq.com
 * @site http://hatustudio.com
 * GameMusic类继承并扩展系统的Thread类，控制游戏的声音
 */
class GameMusic extends Thread {
    // 接收游戏中唯一的gf对象
    GamePanel gf;
    // File对象读取文件
    File f1 = new File("musics/mfs.mid");
    File f2 = new File("musics/yxyl.mid");
    static File f3 = new File("musics/boom.wav");
    static File f4 = new File("musics/end.wav");
    // 音乐的播放状态
    boolean isStart;

    // 初始化
    GameMusic(GamePanel gf) {
	this.gf = gf;
    }

    // 发出短的爆炸声音方法，使用AudioPlayer
    public static void shortMid() {
	try {
	    InputStream is = new FileInputStream(f3);
	    AudioStream as = new AudioStream(is);
	    AudioPlayer.player.start(as);
	    is = new FileInputStream(f3);
	    as = new AudioStream(is);
	    AudioPlayer.player.start(as);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    // 结束声音
    public static void endMid() {
	try {
	    InputStream is = new FileInputStream(f4);
	    AudioStream as = new AudioStream(is);
	    AudioPlayer.player.start(as);
	    is = new FileInputStream(f4);
	    as = new AudioStream(is);
	    AudioPlayer.player.start(as);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void run() {
	Thread ct = Thread.currentThread();
	int flag = 0;// 两个声音文件的循环
	while (true)
	    try {
		Sequence currentSong2;
		Sequencer player = MidiSystem.getSequencer();

		if (flag == 0) {
		    currentSong2 = MidiSystem.getSequence(this.f2);
		    flag = 1;
		} else {
		    currentSong2 = MidiSystem.getSequence(this.f1);
		    flag = 0;
		}
		player.open();
		player.setSequence(currentSong2);
		player.start();
		while (player.isRunning()) {
		    if (!(gf.isStart)) {
			player.stop();
		    }
		    if (!(gf.isSoundOn)) {
			player.stop();
		    }
		    Thread.sleep(1000L);
		}

		player.close();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
    }
}