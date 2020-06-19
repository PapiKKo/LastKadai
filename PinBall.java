import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;

public class PinBall {
	int x, y, width, height;
	static int[] ax;
	static int[] ay;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 FaceDetectionDemo.FaceDetection();//画像保存
		 System.loadLibrary(Core.NATIVE_LIBRARY_NAME);//OpenCVを使うために必ず入れる一行
	
		 Mat input = Imgcodecs.imread("savedImage.jpg"); //画像を読み込む処理。引数はファイルパス
		 EyeDetection ed=new EyeDetection();//目の検出器
		 try {
			 MatOfRect eye=ed.execEyeDetection(input);//目の検出
			 Rect a[]=eye.toArray();//目の情報を取り出す
			 ax=new int[a.length];//目のx座標用の配列を目の個数作る
			 ay=new int[a.length];//目のy座標用の配列を目の個数作る
			 for(int i=0;i<a.length;i++) {
				 ax[i]=a[i].x;//目のx座標の情報を取り出す
				 ay[i]=a[i].y;//目のy座標の情報を取り出す
			 }
			 PinBallGame pinball=new PinBallGame(ax,ay,input);//引数あり
		 }catch(Exception e) {//画像がないとき
			 PinBallGame pinball=new PinBallGame();//引数なし
		 }
		
	}

}
