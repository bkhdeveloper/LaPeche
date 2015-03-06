package lapechealaqueue.episode1.scenes;

import lapechealaqueue.episode1.R;
import lapechealaqueue.episode1.util.ActivityScene_ep2;
import lapechealaqueue.episode1.util.CustomAnim;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class Scene2_ep3 extends ActivityScene_ep2 {
	protected boolean isPlay = false, isStop = false, isPause = false;
	ImageView neige, nuage, prince, btn_pp;
	AnimationDrawable frameAnimation;
	private CustomAnim anim_boule, anim_nuage, anim_prince, anim_pp;

	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.scene2_ep3);
		scenePrecedente = "lapechealaqueue.episode1.scenes.Scene1_ep3";
		sceneSuivante = "lapechealaqueue.episode1.scenes.Scene3_ep3";
		sceneKeyback = "lapechealaqueue.episode1.scenes.Scene1_ep3";

		System.gc();

		neige = (ImageView) findViewById(R.id.neige);

		contenu1 = " \n"
				+ " \n"
				+ " – C’est glacé ! protesta le loup au contact de l’eau.\n"
				+" Tu vas me faire mourir de froid !";

		contenu2 = " \n"
				+ " – Tu veux manger ou pas ? demanda Renart.\n"
				+ " Alors ne te plains pas ! Maintenant ne bouge surtout pas, sinon tu vas effrayer les poissons.";

		contenu3 = " \n"
				+ " Attends deux heures et tire le seau.\n"
				+ " – Deux heures ! soupira Isengrin.";

		raw_id = R.raw.sc2_ep3;
		font = Typeface.createFromAsset(getAssets(), "MorrisRomanBlack.ttf");

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

	}

	private void startAnimations() {

	}

	private void pauseAnimations() {

	}

	private void clearAnimations() {

	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

	}

}