package gameviews.view;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class PictureLoadUtili {
	// �Y�����Ϥ�����k
				public static Bitmap scaleToFit(Bitmap bm, float ratio)// �Y��Ϥ�����k
				{
					float width = bm.getWidth(); // �Ϥ��e��
					float height = bm.getHeight();// �Ϥ�����

					Matrix m1 = new Matrix();
					m1.postScale(ratio, ratio);
					// �ŧi�I�}��
					Bitmap bmResult = Bitmap.createBitmap
					(
							bm, 
							0, 0, 
							(int) width, (int) height, 
							m1, 
							true
					);
					return bmResult;
				}

				// �Y�����Ϥ�����k,�ϹϤ����ù�,�������Y��
				public static Bitmap scaleToFitFullScreen(Bitmap bm, float wRatio,
						float hRatio)// �Y��Ϥ�����k
				{
					float width = bm.getWidth(); // �Ϥ��e��
					float height = bm.getHeight();// �Ϥ�����

					Matrix m1 = new Matrix();
					m1.postScale(wRatio, hRatio);
					// �ŧi�I�}��
					Bitmap bmResult = Bitmap.createBitmap
					(
							bm, 
							0, 0, 
							(int) width, (int) height, 
							m1, 
							true
					);
					return bmResult;
				}

}
