package lapechealaqueue.episode1;

import java.util.List;

import lapechealaqueue.episode1.scenes.Scene1;
import lapechealaqueue.episode1.scenes.Scene1_ep2;
import lapechealaqueue.episode1.scenes.Scene1_ep3;
import lapechealaqueue.episode1.util.ScaleManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Episodes extends Activity implements OnClickListener {
	ImageView episode1, episode2, episode3,home, exit,
			oui, non,episode,renard1,neige;
	private boolean testEpisode2 = false, testEpisode3 = false;
	private String packageName;
	private List<PackageInfo> packs;
	protected PowerManager.WakeLock mWakeLock;
	View exit2;
	View layout;
	private boolean scalingComplete = false;
	private MediaPlayer mp;
	AnimationDrawable frameAnimation, frameAnimation2,frameAnimation1;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.episodes);

		final PowerManager power = (PowerManager) getSystemService(Context.POWER_SERVICE);
		this.mWakeLock = power.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,
				"My Tag");
		this.mWakeLock.acquire();

		exit = (ImageView) findViewById(R.id.exit);
		home = (ImageView) findViewById(R.id.home);

		oui = (ImageView) findViewById(R.id.btn_oui);
		non = (ImageView) findViewById(R.id.btn_non);
		exit2 = (View) findViewById(R.id.exit_popup);

		episode1 = (ImageView) findViewById(R.id.episode1);
		episode2 = (ImageView) findViewById(R.id.episode2);
		episode3 = (ImageView) findViewById(R.id.episode3);
		episode = (ImageView) findViewById(R.id.episode);
		renard1 = (ImageView) findViewById(R.id.renard1);
		neige = (ImageView) findViewById(R.id.neige);
		

		episode1.setOnClickListener(this);
		episode2.setOnClickListener(this);
		episode3.setOnClickListener(this);

		home.setOnClickListener(this);
		exit.setOnClickListener(this);
		oui.setOnClickListener(this);
		non.setOnClickListener(this);
		mp = new MediaPlayer();
		mp = MediaPlayer.create(Episodes.this, R.raw.fond_sonore);
		mp.start();
		mp.setLooping(true);
		
		Animation scale = AnimationUtils.loadAnimation(this,
				R.anim.scale_bouttun_ep1);
		episode1.startAnimation(scale);
		episode2.startAnimation(scale);
		episode3.startAnimation(scale);


		Animation scale1 = AnimationUtils.loadAnimation(this,
				R.anim.scale_bouttun_ep1);
		episode.startAnimation(scale1);
		
		Animation scale2 = AnimationUtils.loadAnimation(this,
				R.anim.rotate_renard_ep_ep1);
		renard1.startAnimation(scale2);
		

	}

	@Override
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
		case R.id.episode1:
			i2 = new Intent(getApplicationContext(), Scene1.class);
			startActivity(i2);
			finish();
			break;
		case R.id.episode2:

			i2 = new Intent(getApplicationContext(), Scene1_ep2.class);
			startActivity(i2);
			finish();
			
			break;
		case R.id.episode3:
			i2 = new Intent(getApplicationContext(), Scene1_ep3.class);
			startActivity(i2);
			finish();

			break;
		
		case R.id.home:
			i2 = new Intent(getApplicationContext(), Home.class);
			startActivity(i2);
			finish();
			break;
		case R.id.exit:
			exit2.setVisibility(View.VISIBLE);
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
