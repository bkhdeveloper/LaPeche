package lapechealaqueue.episode1.scenes;

import lapechealaqueue.episode1.R;
import lapechealaqueue.episode1.util.ActivityScene_ep2;
import lapechealaqueue.episode1.util.CustomAnim;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class Scene4_ep3 extends ActivityScene_ep2 {
	protected boolean isPlay = false, isStop = false, isPause = false;
	ImageView neige, nuage, prince, btn_pp;
	AnimationDrawable frameAnimation;
	private CustomAnim anim_boule, anim_nuage, anim_prince, anim_pp;

	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.scene4_ep3);
		scenePrecedente = "lapechealaqueue.episode1.scenes.Scene3_ep3";
		sceneSuivante = "lapechealaqueue.episode1.scenes.Scene5_ep3";
		sceneKeyback = "lapechealaqueue.episode1.scenes.Scene3_ep3";

		System.gc();
		
		neige = (ImageView) findViewById(R.id.neige);
		
	
		contenu1 = " \n"
				+ " \n"
				+ " Renart s’éloigna. Mais, au lieu de rentrer chez lui, il se dissimula derrière un buisson.\n"
				+ " Il n’eut pas à attendre longtemps.";

		contenu2 = " \n"
				+ " Un bruit de cavalcade résonna bientôt sous les arbres et des chasseurs apparurent au bord de l’étang. Isengrin voulut fuir.\n"
				+" Impossible ! Sa queue était prise dans la glace.";

		contenu3 = " \n"
				+ " – Ce loup terrorise tous les villages alentour, déclara un chasseur en descendant de cheval.\n"
				+" Il faut nous en débarrasser.";

		raw_id = R.raw.sc4_ep3;
		font = Typeface.createFromAsset(getAssets(), "MorrisRomanBlack.ttf");

	


		System.gc();
		super.onCreate(savedInstanceState);
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
		frameAnimation.stop();
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