package lapechealaqueue.episode1.scenes;

import lapechealaqueue.episode1.R;
import lapechealaqueue.episode1.util.ActivityScene;
import lapechealaqueue.episode1.util.ActivityScene_ep2;
import lapechealaqueue.episode1.util.CustomAnim;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class Scene2_ep2 extends ActivityScene_ep2 {
	protected boolean isPlay = false, isStop = false, isPause = false;
	ImageView neige, nuage, prince, btn_pp;
	AnimationDrawable frameAnimation;
	private CustomAnim anim_boule, anim_nuage, anim_prince, anim_pp;

	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.scene2_ep2);
		scenePrecedente = "lapechealaqueue.episode1.scenes.Scene1_ep2";
		sceneSuivante = "lapechealaqueue.episode1.scenes.Scene3_ep2";
		sceneKeyback = "lapechealaqueue.episode1.scenes.Scene1_ep2";

		
		neige = (ImageView) findViewById(R.id.neige);
		
		System.gc();

		contenu1 = " \n"
				+ " \n"
				+ " Renart et Isengrin ne s’aimaient guère.\n"
				+ "Plusieurs fois le loup avait essayé de manger le renard et ce dernier avait souvent joué de mauvais tours au loup.";

		contenu2 = " \n"
				+ " Néanmoins, Isengrin frappa à la porte de son vieil ennemi.\n"
				+ " – Toc, toc, toc !"
				+" – Qui est là ? demanda Renart.";

		contenu3 = " \n"
				+ " – C’est moi, ton grand ami Isengrin, répondit le loup.\n"
				+ " – Mon grand ami ! ricana Renart. C’est sans doute par amitié que tu as si souvent voulu me manger !";

		raw_id = R.raw.sc2_ep2;
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