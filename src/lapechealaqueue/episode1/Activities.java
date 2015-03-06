package lapechealaqueue.episode1;

import java.util.List;

import lapechealaqueue.episode1.activities.Activitie1;
import lapechealaqueue.episode1.activities.Activitie2;
import lapechealaqueue.episode1.activities.Activitie3;
import lapechealaqueue.episode1.util.ScaleManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Activities extends Activity implements OnClickListener {

	ImageView activitie1, activitie2, activitie3,activitetitre, home, exit, oui, non;
	private boolean testEpisode2 = false, testEpisode3 = false;
	PowerManager.WakeLock mWakeLock;
	View exit2;
	AnimationDrawable frameAnimation, frameAnimation2,frameAnimation1;
	View layout;
	private boolean scalingComplete = false;
	private MediaPlayer mp;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activites);

		final PowerManager power = (PowerManager) getSystemService(Context.POWER_SERVICE);
		this.mWakeLock = power.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,
				"My Tag");
		this.mWakeLock.acquire();

		exit = (ImageView) findViewById(R.id.exit);
		home = (ImageView) findViewById(R.id.home);
		oui = (ImageView) findViewById(R.id.btn_oui);
		non = (ImageView) findViewById(R.id.btn_non);
		exit2 = (View) findViewById(R.id.exit_popup);

		activitie1 = (ImageView) findViewById(R.id.activitie1);
		activitie2 = (ImageView) findViewById(R.id.activitie2);
		activitie3 = (ImageView) findViewById(R.id.activitie3);
		//activitetitre = (ImageView) findViewById(R.id.activitetitre);

		activitie1.setOnClickListener(this);
		activitie2.setOnClickListener(this);
		activitie3.setOnClickListener(this);

		home.setOnClickListener(this);
		exit.setOnClickListener(this);
		oui.setOnClickListener(this);
		non.setOnClickListener(this);

		
	
		mp = new MediaPlayer();
		mp = MediaPlayer.create(this, R.raw.fond_sonore);
		mp.start();
		mp.setLooping(true);
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			Intent i2 = new Intent(getApplicationContext(), Home.class);
			startActivity(i2);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	protected void onPause() {
		super.onPause();
		if (mp != null) {
			mp.stop();
		}
		finish();
	}

	public void onDestroy() {
		this.mWakeLock.release();
		super.onDestroy();
		mp.release();

	}
	protected void onRestart() {

		super.onRestart();
	}
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		if (!scalingComplete) // only do this once
		{
			ScaleManager sm = new ScaleManager();
			sm.scaleContents(findViewById(R.id.contents),
					findViewById(R.id.container));
			scalingComplete = true;
		}
		super.onWindowFocusChanged(hasFocus);
	}

	@Override
	public void onClick(View v) {
		Intent i2 = null;
		switch (v.getId()) {
		case R.id.activitie1:
			 i2 = new Intent(getApplicationContext(), Activitie1.class);
			 startActivity(i2);
			 finish();
			break;
		case R.id.activitie2:
				 i2 = new Intent(getApplicationContext(), Activitie2.class);
				 startActivity(i2);
				 finish();
			break;
		case R.id.activitie3:
				 i2 = new Intent(getApplicationContext(), Activitie3.class);
				 startActivity(i2);
				 finish();
			break;
		case R.id.exit:
			exit2.setVisibility(View.VISIBLE);
			break;
		case R.id.home:
			i2 = new Intent(getApplicationContext(), Home.class);
			startActivity(i2);
			finish();
			break;
		case R.id.btn_oui:
			finish();
			break;
		case R.id.btn_non:
			exit2.setVisibility(View.INVISIBLE);
			break;
		default:
			break;
		}
	}
}
