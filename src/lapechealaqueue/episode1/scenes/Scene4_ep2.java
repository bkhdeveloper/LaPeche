package lapechealaqueue.episode1.scenes;

import lapechealaqueue.episode1.R;
import lapechealaqueue.episode1.util.ActivityScene;
import lapechealaqueue.episode1.util.ActivityScene_ep2;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class Scene4_ep2 extends ActivityScene_ep2 {
	protected boolean isPlay = false, isStop = false, isPause = false;

	ImageView btn_telecharger;

	AnimationDrawable frameAnimation;
	private ActivityScene_ep2 act;
	ImageView neige, pois2, pois3, pois4, p1, p2, p3, p4, p5,p6, bulle,btn_pp;

	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.scene4_ep2);
		scenePrecedente = "lapechealaqueue.episode1.scenes.Scene3_ep2";
		sceneSuivante = "lapechealaqueue.episode1.scenes.Scene1_ep3";
		sceneKeyback =  "lapechealaqueue.episode1.scenes.Scene3_ep2";

		
		neige = (ImageView) findViewById(R.id.neige);
		

		contenu1 = " \n"
				+ " \n"
				+ " Puis il sortit de chez lui et dit au loup de le suivre. Ainsi Renart emmena Isengrin au bord d’un étang gelé.";

		contenu2 = " \n"
				+ " Des pêcheurs avaient découpé un trou dans la glace pour tenter de prendre des poissons.";
		
		contenu3 = " \n"
				+ " Mais, lassés d’attendre, ils étaient partis en laissant leur grand seau vide";
		
		
		raw_id = R.raw.sc4_ep2;
		super.onCreate(savedInstanceState);
		System.gc();
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
	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}


}