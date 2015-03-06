package lapechealaqueue.episode1.scenes;

import lapechealaqueue.episode1.Episodes;
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

public class Scene1 extends ActivityScene {
	protected boolean isPlay = false, isStop = false, isPause = false;
	ImageView pers1, pers2, pers3, pers4, pers5,neige;
	private CustomAnim anim_pers1, anim_pers2, anim_pers3,anim_pers4,anim_pers5;
	AnimationDrawable frameAnimation;
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.scene1);
		scenePrecedente = "";
		sceneSuivante = "lapechealaqueue.episode1.scenes.Scene2";
		System.gc();

		pers1 = (ImageView) findViewById(R.id.pers1);
		pers2 = (ImageView) findViewById(R.id.pers2);
		pers3 = (ImageView) findViewById(R.id.pers3);
		pers4 = (ImageView) findViewById(R.id.pers4);
		pers5 = (ImageView) findViewById(R.id.pers5);
		
		neige = (ImageView) findViewById(R.id.neige);
		
		contenu1 = " \n"
				+ " \n"
				+" Cet hiver-là était glacial, si glacial que tous les animaux de la forêt avaient très froid et très faim.";
				
		
		contenu2 = " \n"
				+ " Même Isengrin, le loup, avait très froid et très faim.";
		
		raw_id = R.raw.sc1_ep1;
		font = Typeface.createFromAsset(getAssets(), "MorrisRomanBlack.ttf");

		

		anim_pers1 = new CustomAnim(AnimationUtils.loadAnimation(
				Scene1.this, R.anim.scale_bouttun_ep1), true);
		pers1.startAnimation(anim_pers1);
		
		anim_pers2 = new CustomAnim(AnimationUtils.loadAnimation(
				Scene1.this, R.anim.scale_bouttun_ep1), true);
		pers2.startAnimation(anim_pers2);
		
		anim_pers3 = new CustomAnim(AnimationUtils.loadAnimation(
				Scene1.this, R.anim.scale_bouttun_ep1), true);
		pers3.startAnimation(anim_pers3);
		
		anim_pers4 = new CustomAnim(AnimationUtils.loadAnimation(
				Scene1.this, R.anim.scale_bouttun_ep1), true);
		pers4.startAnimation(anim_pers4);
		
		anim_pers5 = new CustomAnim(AnimationUtils.loadAnimation(
				Scene1.this, R.anim.scale_bouttun_ep1), true);
		pers5.startAnimation(anim_pers5);
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
		//frameAnimation.stop();
		pauseAnimations();
		isPause = true;
	}

	public void onBouttunStop() {
		//frameAnimation.stop();
		clearAnimations();
		isPause = false;
		isStop = true;
	}

	public void onBouttunExit() {
		//frameAnimation.stop();
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
 		anim_pers1.resumeAnim();
 		anim_pers2.resumeAnim();
 		anim_pers3.resumeAnim();
 		anim_pers4.resumeAnim();
 		anim_pers5.resumeAnim();
	}

	private void startAnimations() {
		anim_pers1 = new CustomAnim(AnimationUtils.loadAnimation(
				Scene1.this, R.anim.scale_bouttun_ep1), true);
		pers1.startAnimation(anim_pers1);
		
		anim_pers2 = new CustomAnim(AnimationUtils.loadAnimation(
				Scene1.this, R.anim.scale_bouttun_ep1), true);
		pers2.startAnimation(anim_pers2);
		
		anim_pers3 = new CustomAnim(AnimationUtils.loadAnimation(
				Scene1.this, R.anim.scale_bouttun_ep1), true);
		pers3.startAnimation(anim_pers3);
		
		anim_pers4 = new CustomAnim(AnimationUtils.loadAnimation(
				Scene1.this, R.anim.scale_bouttun_ep1), true);
		pers4.startAnimation(anim_pers4);
		
		anim_pers5 = new CustomAnim(AnimationUtils.loadAnimation(
				Scene1.this, R.anim.scale_bouttun_ep1), true);
		pers5.startAnimation(anim_pers5);

	}

	private void pauseAnimations() {
		anim_pers1.pauseAnim();
		anim_pers2.pauseAnim();
		anim_pers3.pauseAnim();
		anim_pers4.pauseAnim();
		anim_pers5.pauseAnim();
	}

	private void clearAnimations() {
		pers1.clearAnimation();
		pers2.clearAnimation();
		pers3.clearAnimation();
		pers4.clearAnimation();
		pers5.clearAnimation();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			Intent i = new Intent(getApplicationContext(), Episodes.class);

			startActivity(i);
			overridePendingTransition(R.anim.view_transition_in_right,
					R.anim.view_transition_out_right);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}