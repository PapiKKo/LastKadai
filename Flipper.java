import java.awt.Graphics2D;

public class Flipper {
	int x;//x座標
	int y;//y座標
	int width;//フリッパーの幅
	int height;//フリッパーの高さ
	Flipper(int x,int y, int width,int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	void drawFlipper(Graphics2D g) {
		g.drawRect(x, y, width, height);
	}
	void setLocation(int x1,int y1) {
		this.x=x1;
		this.y=y1;
	}
}
