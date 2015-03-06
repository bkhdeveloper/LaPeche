package lapechealaqueue.episode1;


import lapechealaqueue.episode1.util.ScaleManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashScreen extends Activity {

	private static final int STOPSPLASH = 0;
	private static final long SPLASHTIME = 2000;
	private boolean scalingComplete = false;

	private Handler splashHandler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		Message msg = new Message();
		msg.what = STOPSPLASH;
		splashHandler = new Handler() {
			public void handleMessage(Message msg) {
				Intent intent = new Intent(SplashScreen.this, Home.class);
				SplashScreen.this.startActivity(intent);
				SplashScreen.this.finish();
				overridePendingTransition(R.anim.mainfadein_ep1,
						R.anim.splashfadeout_ep1);
				super.handleMessage(msg);
			}
		};
		splashHandler.sendMessageDelayed(msg, SPLASHTIME);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		if (!scalingComplete) {
			ScaleManager sm = new ScaleManager();
			sm.scaleContents(findViewById(R.id.contents),
					findViewById(R.id.container));
			scalingComplete = true;
		}
		super.onWindowFocusChanged(hasFocus);
	}
}