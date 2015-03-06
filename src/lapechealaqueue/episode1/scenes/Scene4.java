package lapechealaqueue.episode1.scenes;

import java.util.List;

import lapechealaqueue.episode1.R;
import lapechealaqueue.episode1.util.ActivityScene;
import lapechealaqueue.episode1.util.CustomAnim;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class Scene4 extends ActivityScene {
	protected boolean isPlay = false, isStop = false, isPause = false;
	private boolean testEpisode2 = false;
	ImageView btn_telecharger;
	private View telechargement;
	ImageView neige, pois2, pois3, pois4, p1, p2, p3, p4, p5,p6, bulle,btn_pp;
	private CustomAnim anim_personnage1, anim_personnage2,
			anim_poissons1, anim_poissons2, anim_poissons3, anim_poissons4,anim_pp;
	AnimationDrawable frameAnimation;
	private ActivityScene act;
	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.scene4);
		
		
		scenePrecedente = "lapechealaqueue.episode1.scenes.Scene3";
		sceneSuivante = "lapechealaqueue.episode1.scenes.Scene1_ep2";
		neige = (ImageView) findViewById(R.id.neige);
		
		
		act = this;
		
		contenu1 = " \n"
				+ " \n"
				+ " Des pêcheurs voulurent le tuer pour vendre sa peau à la foire, alors Renart leur vola tous leurs poissons.";

		contenu2 = " \n"
				+" – Au voleur ! Au voleur ! hurlèrent les pêcheurs.\n"
				+" – Voler qui veut me tuer n’est pas un grand crime ! s’exclama Renart en éclatant de rire.";
		
		raw_id = R.raw.sc4_ep1;
		font = Typeface.createFromAsset(getAssets(), "MorrisRomanBlack.ttf");
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

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			Intent i = new Intent(getApplicationContext(), Scene3.class);

			startActivity(i);
			overridePendingTransition(R.anim.view_transition_in_right,
					R.anim.view_transition_out_right);
			act.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}