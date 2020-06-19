import java.awt.Graphics2D;

public class Ball {
	int x = 0;//ボールのx座標のスタート地点
	int y=22;//ボールのy座標のスタート地点
	
	void drawBall(Graphics2D g) {//ボールを描画する
		g.fillOval(x ,y ,10,10);	
	}
	
}
