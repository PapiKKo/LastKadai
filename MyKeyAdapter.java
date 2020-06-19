import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyAdapter extends KeyAdapter{//キーが押されたかを判断するクラス
	static boolean keyFlgs[] = new boolean[2];
	static final int LEFT_KEY  = 0;
	static final int RIGHT_KEY = 1;
	
	@Override
	public void keyPressed(KeyEvent e) {//押されているとき
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				keyFlgs[LEFT_KEY] = true;
				break;
			case KeyEvent.VK_RIGHT:
				keyFlgs[RIGHT_KEY] = true;
				break;		
		}	
	}
	@Override
	public void keyReleased(KeyEvent e) {//押されていないとき
		keyFlgs[LEFT_KEY]  = false;
		keyFlgs[RIGHT_KEY] = false;	
	}
}
