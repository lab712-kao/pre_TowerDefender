package tw.edu.ttu.pre_towerdefender;

import java.util.ArrayList;

import tw.edu.ttu.pre_towerdefender.R.drawable;
import android.widget.ImageView;

public class GameInterface {
	
	public ImageView trytry;
	public ArrayList<ImageView> imageArray = new ArrayList<ImageView>();
	
	public GameInterface( ArrayList<ImageView>imaA ){
		
		imageArray = imaA;
		
		for (int i = 0; i < imageArray.size(); i++) {
			/*LayoutParams params = imageArray.get(2).getLayoutParams();
			params.width = 24;
	        params.height = 32;
	        imageArray.get(2).setLayoutParams(params);*/
			imageArray.get(7).setImageResource(drawable.number_eight);
			
		}
	}
	
	
	

}
