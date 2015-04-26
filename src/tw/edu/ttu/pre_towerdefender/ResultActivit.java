package tw.edu.ttu.pre_towerdefender;


import android.app.Activity;
import android.app.AliasActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ResultActivit extends Activity {

	private TextView whoWon;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		whoWon = (TextView)findViewById(R.id.resultTextView);
		
		Bundle bundle;
		bundle = this.getIntent().getExtras();
		if(bundle.getString("KEY_WIN").equals("Player")) {
			whoWon.setText("Player won!");
		}
		else {
			whoWon.setText("Enermy won!");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
