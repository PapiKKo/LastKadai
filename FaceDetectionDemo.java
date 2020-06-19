
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;


public class FaceDetectionDemo {//画像処理

	public static void  FaceDetection(){


		//*****************************
		// モード選択をキーボードからの入力で選択する
		//*****************************
		System.out.println("モード選択をしてください。(s: 動画保存)");

		String buf = "n";
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		try {
			buf = br.readLine();
			System.out.println("input key is " + buf);
		}
		catch(IOException e) {
			System.out.println("Quit program");
			System.exit(0);
		}

		//*****************************
		//処理開始
		//*****************************

		//OpenCVを使うために必ず入れる一行
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		//映像取得モジュールのインスタンス化
		VideoCaptureModule vcm = new VideoCaptureModule(buf);



//--------------------------------------------------------------------
// ビデオ処理の例
//--------------------------------------------------------------------

		//映像取得開始
		//カメラが開いている間処理を続ける
		while(vcm.isCameraOpened())
		{
			Mat image = vcm.getFrameFromCamera(); //カメラ映像から画像を一枚取り出す

			//---------------------------
			//カメラ画像の縮小（処理高速化のため）
			//---------------------------
			double ratio = 0.5; //入力画像を縮小する割合
			Imgproc.resize(image, image, new Size(0, 0), ratio, ratio);

			vcm.showImage(image); //取り込んだ画像を表示

			vcm.saveVideo(image); //ビデオ保存（一枚ずつビデオに追加していく）

			//キーボード入力の取得
			//表示している画面をアクティブにするとキー入力を受け付ける
			int key = vcm.getInputKey();

			//key==81 というのは、'q'を押したことを意味（参考：アスキーコード）
			//qが押されたらwhileループを抜ける
			if(key == 81)
				break;

			else if (key == 73) //"i"が押されたら画像保存
				vcm.saveImage(image);	
		}
		//映像取得を終了する（プログラムを終了する）メソッド
		vcm.stopVideoCapture();
	}
	
}