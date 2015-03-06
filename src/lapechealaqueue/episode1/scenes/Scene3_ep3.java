package lapechealaqueue.episode1.scenes;

import lapechealaqueue.episode1.R;
import lapechealaqueue.episode1.util.ActivityScene_ep2;
import lapechealaqueue.episode1.util.CustomAnim;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Scene3_ep3 extends ActivityScene_ep2 {
	protected boolean isPlay = false, isStop = false, isPause = false;
	ImageView neige, surf, prince, btn_pp;
	AnimationDrawable frameAnimation;
	private CustomAnim anim_surf, anim_nuage, anim_prince, anim_pp;

	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.scene3_ep3);
		scenePrecedente = "lapechealaqueue.episode1.scenes.Scene2_ep3";
		sceneSuivante = "lapechealaqueue.episode1.scenes.Scene4_ep3";
		sceneKeyback = "lapechealaqueue.episode1.scenes.Scene2_ep3";

		neige = (ImageView) findViewById(R.id.neige);
		surf= (ImageView) findViewById(R.id.surf);
		System.gc();

		contenu1 = " \n"
				+ " \n"
				+ " Mais il avait tellement faim qu’il était prêt à tout pour pêcher quelques malheureux poissons.";

		contenu2 = " \n"
				+ " – Au revoir mon grand ami, dit Renart.\n"
				+" Je dois aller mettre une bûche dans ma cheminée sinon mon feu va s’éteindre.";

		contenu3 = " \n"
				+ " Mais n’oublie pas, mon cher Isengrin, que, grâce à moi, tu mangeras à ta faim tout l’hiver\n"
				+" – Merci Renart, dit Isengrin qui grelottait.";

		raw_id = R.raw.sc3_ep3;
		font = Typeface.createFromAsset(getAssets(), "MorrisRomanBlack.ttf");

		
		anim_surf = new CustomAnim(AnimationUtils.loadAnimation(Scene3_ep3.this,
				R.anim.mainfadein1_ep3), true);
		anim_surf.setStartTime(10000);
		anim_surf.setStartOffset(10000);
		anim_surf.setFillAfter(true);
		anim_surf.setFillEnabled(true);
		surf.startAnimation(anim_surf);
		

		System.gc();
		
		super.onCreate(savedInstanceState);
	}

	public void onBouttunPlay() {
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
		anim_surf.resumeAnim();
	}

	private void startAnimations() {
		anim_surf = new CustomAnim(AnimationUtils.loadAnimation(Scene3_ep3.this,
				R.anim.mainfadein1_ep3), true);
		anim_surf.setStartTime(10000);
		anim_surf.setStartOffset(10000);
		anim_surf.setFillAfter(true);
		anim_surf.setFillEnabled(true);
		surf.startAnimation(anim_surf);
	}

	private void pauseAnimations() {
		anim_surf.pauseAnim();
	}

	private void clearAnimations() {
		surf.clearAnimation();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

	}

}