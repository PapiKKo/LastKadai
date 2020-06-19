import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyAdapter extends KeyAdapter{//キーが押されたかを判断するクラス
	static boolean keyFlgs[] = new boolean[3];
	static final int INDEX_OF_LEFT_KEY  = 0;
	static final int INDEX_OF_RIGHT_KEY = 1;
	static final int SPACE_KEY = 2;
	@Override
	public void keyPressed(KeyEvent e) {//押されているとき
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			keyFlgs[INDEX_OF_LEFT_KEY] = true;
			break;
		case KeyEvent.VK_RIGHT:
			keyFlgs[INDEX_OF_RIGHT_KEY] = true;
			break;
		case KeyEvent.VK_SPACE:
			keyFlgs[SPACE_KEY] = true;
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {//押されていないとき
		keyFlgs[INDEX_OF_LEFT_KEY]  = false;
		keyFlgs[INDEX_OF_RIGHT_KEY] = false;
		keyFlgs[SPACE_KEY]=false;
	}

}
