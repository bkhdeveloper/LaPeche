package lapechealaqueue.episode1;

import lapechealaqueue.episode1.util.ScaleManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class Home extends Activity implements OnClickListener {

	protected PowerManager.WakeLock mWakeLock;
	ImageView activites, episodes, about, exit, oui, non, renard1, texte1,
			texte2,renard2;
	View exit2, about2;
	View layout;
	AnimationDrawable frameAnimation;
	private TranslateAnimation trans2;
	private boolean scalingComplete = false;
	private MediaPlayer mp;

	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);

		final PowerManager power = (PowerManager) getSystemService(Context.POWER_SERVICE);
		this.mWakeLock = power.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,
				"My Tag");
		this.mWakeLock.acquire();

		episodes = (ImageView) findViewById(R.id.btn_episode);
		activites = (ImageView) findViewById(R.id.btn_activite);
		exit = (ImageView) findViewById(R.id.exit);
		about = (ImageView) findViewById(R.id.btn_about);
		texte1 = (ImageView) findViewById(R.id.texte1);
		renard1 = (ImageView) findViewById(R.id.renard1);
		renard2 = (ImageView) findViewById(R.id.renard2);

		oui = (ImageView) findViewById(R.id.btn_oui);
		non = (ImageView) findViewById(R.id.btn_non);
		exit2 = (View) findViewById(R.id.exit_popup);

		episodes.setOnClickListener(this);
		activites.setOnClickListener(this);

		exit.setOnClickListener(this);

		oui.setOnClickListener(this);
		non.setOnClickListener(this);

		Animation scale = AnimationUtils.loadAnimation(this,
				R.anim.scale_bouttun_ep1);
		episodes.startAnimation(scale);
		activites.startAnimation(scale);

		Animation scale1 = AnimationUtils.loadAnimation(this,
				R.anim.scale_loup_ep1);
		renard2.startAnimation(scale1);
		
		Animation scale2 = AnimationUtils.loadAnimation(this,
				R.anim.rotate_renard_ep1);
		renard1.startAnimation(scale2);

		Animation fadein1 = AnimationUtils.loadAnimation(this,
				R.anim.mainfadein1_ep1);
		fadein1.setFillAfter(true);
		fadein1.setFillEnabled(true);
		
		texte1.startAnimation(fadein1);
		
		about.setBackgroundResource(R.drawable.anim_about);
		frameAnimation = (AnimationDrawable) about.getBackground();
		frameAnimation.setCallback(about);
		frameAnimation.setOneShot(false);
		frameAnimation.setVisible(false, true);
		about.setBackgroundDrawable(frameAnimation);

		about.post(new Runnable() {
			public void run() {
				frameAnimation.start();
			}
		});
		
		// anim_bateau = new CustomAnim(AnimationUtils.loadAnimation(this,
		// R.anim.anim_sc7), true);
		// bateau.startAnimation(anim_bateau);

		mp = new MediaPlayer();
		mp = MediaPlayer.create(this, R.raw.fond_sonore);
		mp.start();
		mp.setLooping(true);

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
		if (!scalingComplete) {
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
		case R.id.btn_episode:
			i2 = new Intent(getApplicationContext(), Episodes.class);
			startActivity(i2);
			finish();
			break;
		case R.id.btn_activite:
			i2 = new Intent(getApplicationContext(), Activities.class);
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
