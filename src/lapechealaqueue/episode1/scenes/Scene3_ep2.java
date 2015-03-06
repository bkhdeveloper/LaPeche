package lapechealaqueue.episode1.scenes;

import lapechealaqueue.episode1.R;
import lapechealaqueue.episode1.util.ActivityScene;
import lapechealaqueue.episode1.util.ActivityScene_ep2;
import lapechealaqueue.episode1.util.CustomAnim;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Scene3_ep2 extends ActivityScene_ep2 implements AnimationListener{
	protected boolean isPlay = false, isStop = false, isPause = false;
	ImageView neige, tete, poisson, btn_pp;
	private CustomAnim anim_tete, anim_poisson;
	AnimationDrawable frameAnimation;
	int testAnimation = 0;
	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.scene3_ep2);
		scenePrecedente = "lapechealaqueue.episode1.scenes.Scene2_ep2";
		sceneSuivante = "lapechealaqueue.episode1.scenes.Scene4_ep2";
		sceneKeyback = "lapechealaqueue.episode1.scenes.Scene2_ep2";
		
		neige = (ImageView) findViewById(R.id.neige);
		tete = (ImageView) findViewById(R.id.tete);
		poisson = (ImageView) findViewById(R.id.poisson);
		

		System.gc();

		contenu1 = " \n"
				+ " \n"
				+ " – Je ne voulais pas te manger, protesta Isengrin. Je voulais juste t’embrasser.\n"
				+" Tu m’as mal compris !";

		contenu2 = " \n"
				+ " Ouvre-moi ta porte, nous allons partager tes poissons grillés et il n’y aura plus jamais de dispute entre nous.\n"
				+" Renart entrouvrit sa porte.";

		contenu3 = " \n"
				+ " – J’ai tout mangé mais je vais te montrer comment te nourrir pendant tout l’hiver, déclara Renart qui dut se mordre la langue pour ne pas rire.";

		raw_id = R.raw.sc3_ep2;
		font = Typeface.createFromAsset(getAssets(), "MorrisRomanBlack.ttf");

		
		anim_poisson = new CustomAnim(AnimationUtils.loadAnimation(Scene3_ep2.this,
				R.anim.mainfadein1_ep2), true);
		anim_poisson.setStartTime(5000);
		anim_poisson.setStartOffset(5000);
		anim_poisson.setFillAfter(true);
		anim_poisson.setFillEnabled(true);
		poisson.startAnimation(anim_poisson);
		anim_poisson.setAnimationListener(this);
		
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
		if (testAnimation == 0) {
			anim_poisson.resumeAnim();

		} else if (testAnimation == 1) {
			anim_tete.resumeAnim();

		}
	}

	private void startAnimations() {
		anim_poisson = new CustomAnim(AnimationUtils.loadAnimation(Scene3_ep2.this,
				R.anim.mainfadein1_ep2), true);
		anim_poisson.setStartTime(5000);
		anim_poisson.setStartOffset(5000);
		anim_poisson.setFillAfter(true);
		anim_poisson.setFillEnabled(true);
		poisson.startAnimation(anim_poisson);
		anim_poisson.setAnimationListener(this);
	}

	private void pauseAnimations() {
		if (testAnimation == 0) {
			anim_poisson.pauseAnim();

		} else if (testAnimation == 1) {
			anim_tete.pauseAnim();

		}
	}

	private void clearAnimations() {
		poisson.clearAnimation();
		tete.clearAnimation();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if (animation == anim_poisson) {

			tete.setVisibility(View.VISIBLE);
			testAnimation = 1;

		}
		
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}

}