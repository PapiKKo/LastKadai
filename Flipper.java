import java.awt.Graphics2D;

public class Flipper {//フリッパークラス
	public int x;//x座標
	public int y;//y座標
	public int width;//フリッパーの幅
	public int height;//フリッパーの高さ
	Flipper(int x,int y, int width,int height){//コンストラクタ
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}
	void drawFlipper(Graphics2D g) {//描画
		g.drawRect(x, y, width, height);
	}
	void setLocation(int x1,int y1) {//座標の設定
		this.x=x1;
		this.y=y1;
	}
}
