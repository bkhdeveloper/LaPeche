package lapechealaqueue.episode1.scenes;

import lapechealaqueue.episode1.R;
import lapechealaqueue.episode1.util.ActivityScene_ep2;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;

public class Scene1_ep3 extends ActivityScene_ep2 {
	protected boolean isPlay = false, isStop = false, isPause = false;
	ImageView neige, perso, n2, n3, n4, btn_pp;

	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.scene1_ep3);
		scenePrecedente = "lapechealaqueue.episode1.scenes.Scene4_ep2";
		sceneSuivante = "lapechealaqueue.episode1.scenes.Scene2_ep3";
		sceneKeyback = "lapechealaqueue.episode1.scenes.Scene4_ep2";

		perso = (ImageView) findViewById(R.id.perso);

		contenu1 = " \n"
				+ " \n"
				+ " – Si tu veux attraper des poissons, c’est très simple, expliqua Renart à Isengrin.";

		contenu2 = " \n"
				+ " Je vais attacher ce seau à ta queue, tu plongeras le seau dans l’eau et, tu verras, les poissons vont se précipiter dans le seau.";

		contenu3 = " \n"
				+ " – Les poissons sont décidément bien bêtes ! ricana Isengrin tandis que le renard lui attachait le seau à la queue.";

		raw_id = R.raw.sc1_ep3;
		font = Typeface.createFromAsset(getAssets(), "MorrisRomanBlack.ttf");

		super.onCreate(savedInstanceState);
		System.gc();

	}

	public void onBouttunPlay() {
		// neige.post(new Runnable() {
		// public void run() {
		// frameAnimation.start();
		// }
		// });
		if (isPause && !isStop) {
			resumeAnimations();
			isPause = false;

		} else if (isPause || isStop) {
			startAnimations();
			isStop = false;
		}

	}

	public void onBouttunPause() {
		// frameAnimation.stop();
		pauseAnimations();
		isPause = true;
	}

	public void onBouttunStop() {
		// frameAnimation.stop();
		clearAnimations();
		isPause = false;
		isStop = true;
	}

	public void onBouttunExit() {
		// frameAnimation.stop();
		pauseAnimations();
		isPause = true;
	}

	public void onBouttunExit_Non() {
		// neige.post(new Runnable() {
		// public void run() {
		// frameAnimation.start();
		// }
		// });
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
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

	}

}