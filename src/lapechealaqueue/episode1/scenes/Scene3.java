package lapechealaqueue.episode1.scenes;

import lapechealaqueue.episode1.R;
import lapechealaqueue.episode1.util.ActivityScene;
import lapechealaqueue.episode1.util.CustomAnim;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Scene3 extends ActivityScene {
	protected boolean isPlay = false, isStop = false, isPause = false;
	ImageView neige, perso, bonh3, bonh4, bonh5, bonh6;
	private CustomAnim anim_perso, anim_poissons, anim_pp;
	AnimationDrawable frameAnimation;
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.scene3);
		scenePrecedente = "lapechealaqueue.episode1.scenes.Scene2";
		sceneSuivante = "lapechealaqueue.episode1.scenes.Scene4";
		
		neige = (ImageView) findViewById(R.id.neige);
		perso = (ImageView) findViewById(R.id.perso);

		contenu1 = " \n"
				+ " \n"
				+ " – Au voleur ! Au voleur ! crièrent les bûcherons.\n"
				+ " – Voler qui veut m’assassiner n’est pas un grand crime ! s’était exclamé Renart en riant.";
		
		raw_id = R.raw.sc3_ep1;
		font = Typeface.createFromAsset(getAssets(), "MorrisRomanBlack.ttf");

		
		anim_perso = new CustomAnim(AnimationUtils.loadAnimation(
				Scene3.this, R.anim.scale_perso_ep1), true);
		perso.startAnimation(anim_perso);

		super.onCreate(savedInstanceState);


		System.gc();
	}

	public void onBouttunPlay() {
//		neige.post(new Runnable() {
//			public void run() {
//				frameAnimation.start();
//			}
//		});
		if (isPause && !isStop) {
			resumeAnimations();
			isPause = false;

		} else if (isPause || isStop) {
			startAnimations();
			isStop = false;
		}

	}

	public void onBouttunPause() {
//		frameAnimation.stop();
		pauseAnimations();
		isPause = true;
	}

	public void onBouttunStop() {
//		frameAnimation.stop();
		clearAnimations();
		isPause = false;
		isStop = true;
	}

	public void onBouttunExit() {
//		frameAnimation.stop();
		pauseAnimations();
		isPause = true;
	}

	public void onBouttunExit_Non() {
//		neige.post(new Runnable() {
//			public void run() {
//				frameAnimation.start();
//			}
//		});
		resumeAnimations();
		isPause = false;
	}

	/** Control Animations **/

	private void resumeAnimations() {
		anim_perso.resumeAnim();
	}

	private void startAnimations() {
		anim_perso = new CustomAnim(AnimationUtils.loadAnimation(
				Scene3.this, R.anim.scale_perso_ep1), true);
		perso.startAnimation(anim_perso);

	}

	private void pauseAnimations() {
		anim_perso.pauseAnim();
		anim_poissons.pauseAnim();
	}

	private void clearAnimations() {
		perso.clearAnimation();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			Intent i = new Intent(getApplicationContext(), Scene3.class);

			startActivity(i);
			overridePendingTransition(R.anim.view_transition_in_right,
					R.anim.view_transition_out_right);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
