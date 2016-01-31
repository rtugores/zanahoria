package huitca1212.cuantotemide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends Activity {
	
	Splash splash_ct = new Splash(); 
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
			// thread for displaying the SplashScreen
		Thread splashTread = new Thread() {
			@Override
			public void run() {
				try {
					int waited = 0;
					while(splash_ct._active && (waited < splash_ct._splashTime)) {
						sleep(100);
						if(splash_ct._active) {
							waited += 100;
						}
					}
				} catch(InterruptedException e) {
					// do nothing
				} finally {
					finish();
					startActivity(new Intent(MainActivity.this, MainActivity2.class)); //pantalla de inicio
					finish();
				}
			}
		};
		splashTread.start();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				splash_ct._active = false;
			}
			return true;
	}
}
