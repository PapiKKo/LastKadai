import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

import org.opencv.core.Mat;

public class PinBallGame extends JFrame {
	final static int Width=640;//画面の幅
	final static int Height=382;//画面の高さ
	static boolean gameover=false;
	int xspeed=1;
	int yspeed=1;
	static int barwidth=80;//フリッパーの幅
	static int eyebox=50;
	int ax[]=null;
	int ay[]=null;
	
	BufferedImage image=null;
	MyKeyAdapter mka=new MyKeyAdapter();
	Ball ball=new Ball();//ボール生成
	Flipper flipper=new Flipper(Width / 2, 372,barwidth, 5);//フリッパー生成

	public PinBallGame() {//コンストラクタ引数なし
		ax=new int[2];
		ay=new int[2];
		ax[0]=300;
		ax[1]=340;
		ay[0]=100;
		ay[1]=100;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("PinBall");
		this.setSize(Width, Height);
		
		this.setUndecorated(false);//タイトルバーとかtrue-off
		// キーリスナー
		MyKeyAdapter myKeyAdapter = new MyKeyAdapter();
		addKeyListener(myKeyAdapter);

		// タイマー
		Timer timer = new java.util.Timer();
		timer.schedule(task, 1l, 10l);
		setLayout(new BorderLayout()); 

	     
		// 表示
		this.setVisible(true);
	}
	public PinBallGame(int[] ax0,int[] ay0,Mat img) {//コンストラクタ引数あり
		
		
		int type = BufferedImage.TYPE_BYTE_GRAY; 
		if (img.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
		ax=ax0;
		ay=ay0;
		for(int i=0;i<ay.length;i++) {
			ay[i]=ay[i]+22;
		}
		int bufferSize = img.channels() * img.cols() * img.rows();
		byte[] b = new byte[bufferSize];
		img.get(0, 0, b);//全てのピクセルを取得
		image = new BufferedImage(img.cols(), img.rows(), type);
		final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("PinBall");
		this.setSize(Width, Height);
		this.setUndecorated(false);//タイトルバーとかtrue-off
		
		// キーリスナー
		MyKeyAdapter myKeyAdapter = new MyKeyAdapter();
		addKeyListener(myKeyAdapter);

		// タイマー
		Timer timer = new java.util.Timer();
		
		timer.schedule(task, 1l, 10l);
		// 表示
		this.setVisible(true);
	}

	public void paint(Graphics gc) {
	    gc.drawImage(getScreen(), 0, 0, this);
	}
	
	private Image getScreen() {
		Image screen = createImage(Width, Height);
	    Graphics2D g = (Graphics2D)screen.getGraphics();
	    
	    if(image!=null) {
	    g.drawImage(image,0,22,this);}
		g.setColor(Color.green);
		for(int i=0;i<ax.length;i++) {//目の数だけ四角を描く
			g.drawRect(ax[i], ay[i], eyebox, eyebox);
		}
		ball.drawBall(g);
	    // ターゲット描画
		g.setColor(Color.green);
		flipper.drawFlipper(g);
		//g.draw(player);
		if(gameover==true) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, Width, Height);
			g.setColor(Color.black);
			g.drawString("Game Over", Width/2-20, Height/2);
			
		}

		return screen;
	}
	
	TimerTask task=new TimerTask() {
		@Override
		public void run() {
			int barx=flipper.x;
			int bary=flipper.y;
			while (true) {			
				ball.x = ball.x + xspeed;
				ball.y=ball.y+yspeed;
				if(ball.x >= Width-5){//右の枠に当たったら
					ball.x = Width - 10;
					xspeed *= -1;
				}
				if(ball.x <= 0){//左の枠に当たったら
					ball.x = 5;
					xspeed *= -1;
				}
				if(ball.y >= Height-5){//下の枠に当たったら
					ball.y = Height - 10;
					yspeed *= -1;
					gameover=true;
					
					//ゲームオーバーして全てを終わらせる時
				}
				if(ball.y <= 17){//上の枠に当たったら
					ball.y = 22;
					yspeed *= -1;
				}
				for(int i=0;i<ax.length;i++) {//目の四角に当たったとき
					if(ax[i]<=ball.x+5&&ball.x-5<=ax[i]+eyebox&&ay[i]<=ball.y+10&&ball.y+10<=ay[i]+2&&yspeed>0) {						
						yspeed *= -1;
					}
					if(ax[i]<=ball.x+5&&ball.x-5<=ax[i]+eyebox&&ay[i]+eyebox-2<=ball.y&&ball.y<=ay[i]+eyebox&&yspeed<0) {				
						yspeed *= -1;
					}
					if(ax[i]<=ball.x+10&&ball.x+10<=ax[i]+2&&ay[i]<=ball.y&&ball.y<=ay[i]+eyebox&&xspeed>0) {	
						xspeed *= -1;
					}
					if(ax[i]+eyebox-2<=ball.x&&ball.x<=ax[i]+eyebox&&ay[i]<=ball.y&&ball.y<=ay[i]+eyebox&&xspeed<0) {	
						xspeed *= -1;
					}
				}
				if(ball.y==bary-5&&barx<ball.x+2.5&&ball.x+2.5<barx+barwidth) {//バーに当たったら
					ball.y=bary-10;
					yspeed *= -1;
				}
				if (mka.keyFlgs[mka.INDEX_OF_LEFT_KEY]&&barx>=0) {//左キーが押されたら
					barx -= 1;
				}

				if (mka.keyFlgs[mka.INDEX_OF_RIGHT_KEY]&&barx<=Width-barwidth) {//右キーが押されたら
					barx += 1;
				}
				flipper.setLocation(barx, bary);
				
				repaint();	// 画面更新
				try {
					Thread.sleep(6); // 画面更新間隔
				} catch (Exception e) { }
			}
		}

	};

	
}