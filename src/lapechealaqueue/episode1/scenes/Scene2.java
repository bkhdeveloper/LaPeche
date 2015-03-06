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

public class Scene2 extends ActivityScene {
	protected boolean isPlay = false, isStop = false, isPause = false;
	ImageView neige, renard, bucheron;
	private CustomAnim anim_renard, anim_bucheron;
	AnimationDrawable frameAnimation;
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.scene2);
		scenePrecedente = "lapechealaqueue.episode1.scenes.Scene1";
		sceneSuivante = "lapechealaqueue.episode1.scenes.Scene3";
		System.gc();

		neige = (ImageView) findViewById(R.id.neige);
		renard = (ImageView) findViewById(R.id.renard);
		bucheron = (ImageView) findViewById(R.id.bucheron);

		contenu1 = " \n"
				+ " \n"
				+ " Seul, dans sa maison bien chauffée, Renart mangeait du soir au matin.\n"
				+ " Renart était en effet très malin et parvenait à déjouer les pièges de ses ennemis.";
		contenu2 = " \n"
				+" Des bûcherons voulurent un jour lui fracasser le crâne en abattant un grand chêne, alors Renart leur vola tout leur bois.";
		
		raw_id = R.raw.sc2_ep1;
		font = Typeface.createFromAsset(getAssets(), "MorrisRomanBlack.ttf");

		

		anim_renard = new CustomAnim(AnimationUtils.loadAnimation(
				Scene2.this, R.anim.rotate_renard_sc2_ep1), true);
		anim_renard.setStartOffset(1000);
		renard.startAnimation(anim_renard);

		anim_bucheron = new CustomAnim(AnimationUtils.loadAnimation(
				Scene2.this, R.anim.scale_bouttun_ep1), true);
		bucheron.startAnimation(anim_bucheron);

		super.onCreate(savedInstanceState);

	}

	public void onBouttunPlay() {
		neige.post(new Runnable() {
			public void run() {
				frameAnimation.start();
			}
		});
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
		anim_renard.resumeAnim();
		anim_bucheron.resumeAnim();
	}

	private void startAnimations() {
		anim_renard = new CustomAnim(AnimationUtils.loadAnimation(
				Scene2.this, R.anim.rotate_renard_sc2_ep1), true);
		anim_renard.setStartOffset(1000);
		renard.startAnimation(anim_renard);

		anim_bucheron = new CustomAnim(AnimationUtils.loadAnimation(
				Scene2.this, R.anim.scale_bouttun_ep1), true);
		bucheron.startAnimation(anim_bucheron);


	}

	private void pauseAnimations() {
		anim_renard.pauseAnim();
		anim_bucheron.pauseAnim();
	}

	private void clearAnimations() {
		renard.clearAnimation();
		bucheron.clearAnimation();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			Intent i = new Intent(getApplicationContext(), Scene1.class);

			startActivity(i);
			overridePendingTransition(R.anim.view_transition_in_right,
					R.anim.view_transition_out_right);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}