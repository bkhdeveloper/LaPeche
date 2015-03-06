package lapechealaqueue.episode1.scenes;

import lapechealaqueue.episode1.Home;
import lapechealaqueue.episode1.R;
import lapechealaqueue.episode1.util.ActivityScene_ep2;
import lapechealaqueue.episode1.util.ActivityScene_ep3;
import lapechealaqueue.episode1.util.CustomAnim;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Scene5_ep3 extends ActivityScene_ep2 implements AnimationListener {
	protected boolean isPlay = false, isStop = false, isPause = false;
	private boolean testEpisode2 = false;
	ImageView btn_telecharger, popup_acceuil;
	private View telechargement;
	int testAnimation = 0;
	private ActivityScene_ep3 act;
	AnimationDrawable frameAnimation, frameAnimation1;
	ImageView neige, chasseur, loup, loup1, loup2, p2, p3, p4, p5, p6, bulle,
			btn_pp;
	private CustomAnim anim_loup, anim_loup1, anim_loup2;

	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.scene5_ep3);
		scenePrecedente = "lapechealaqueue.episode1.scenes.Scene4_ep3";
		sceneSuivante = "nextEpisode";
		sceneKeyback = "lapechealaqueue.episode1.scenes.Scene4_ep3";

		neige = (ImageView) findViewById(R.id.neige);
		loup = (ImageView) findViewById(R.id.loup);
		loup1 = (ImageView) findViewById(R.id.loup1);
		loup2 = (ImageView) findViewById(R.id.loup2);
		chasseur = (ImageView) findViewById(R.id.chasseur);
		popup_acceuil = (ImageView) findViewById(R.id.activite);

		contenu1 = " \n"
				+ " \n"
				+ " Puis il s’aventura sur la glace et s’approcha d’Isengrin qui sentit sa dernière heure arrivée.\n"
				+ " Le chasseur leva son épée et l’abaissa d’un geste vif sur le crâne du loup.";

		contenu2 = " \n"
				+ " D’un bond de côté, Isengrin évita le coup mortel.\n"
				+ " Mais il poussa un hurlement de souffrance car l’épée lui avait coupé la queue.";

		contenu3 = " \n"
				+ " Ainsi réussit-il à s’enfuir, fou de douleur et de rage en se jurant de ne plus jamais croire à ce que lui raconterait ce méchant renard.\n"
				+ " Quant à ce dernier, il rentra chez lui en riant du bon tour qu’il venait de jouer à son vieil ennemi.";

		raw_id = R.raw.sc5_ep3;
		telechargement = (View) findViewById(R.id.download_popup);
		btn_telecharger = (ImageView) findViewById(R.id.btn_telecharger);

		anim_loup = new CustomAnim(AnimationUtils.loadAnimation(Scene5_ep3.this,
				R.anim.mainfadeout1_ep3), true);
		anim_loup.setStartOffset(1000);
		loup.startAnimation(anim_loup);
		anim_loup.setAnimationListener(this);




		chasseur.setBackgroundResource(R.drawable.anim_chasseur);
		frameAnimation1 = (AnimationDrawable) chasseur.getBackground();
		frameAnimation1.setCallback(chasseur);
		frameAnimation1.setOneShot(false);
		frameAnimation1.setVisible(false, true);
		chasseur.setBackgroundDrawable(frameAnimation1);

		chasseur.post(new Runnable() {
			public void run() {
				frameAnimation1.start();
			}
		});

		super.onCreate(savedInstanceState);
	}

	@Override
	public void loadNextEpisode() {
		super.loadNextEpisode();

		telechargement.setVisibility(View.VISIBLE);
		mp.pause();
		pauseAnimations();
		_myIsPause = true;
		frameAnimation1.stop();

		popup_acceuil.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent ep = new Intent(getApplicationContext(), Home.class);
				startActivity(ep);
				overridePendingTransition(R.anim.view_transition_in_right,
						R.anim.view_transition_out_right);
				finish();
			}
		});

		btn_telecharger.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
			}
		});
		telechargement.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				telechargement.setVisibility(View.INVISIBLE);
				resumeAnimations();
				mp.start();
				_myIsPause = false;
			}
		});

	}

	public void onBouttunPlay() {
		// neige.post(new Runnable() {
		// public void run() {
		// frameAnimation.start();
		// }
		// });
		chasseur.post(new Runnable() {
			public void run() {
				frameAnimation1.start();
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
		// frameAnimation.stop();
		frameAnimation1.stop();
		pauseAnimations();
		isPause = true;
	}

	public void onBouttunStop() {
		// frameAnimation.stop();
		frameAnimation1.stop();
		clearAnimations();
		isPause = false;
		isStop = true;
	}

	public void onBouttunExit() {
		// frameAnimation.stop();
		frameAnimation1.stop();
		pauseAnimations();
		isPause = true;
	}

	public void onBouttunExit_Non() {
		// neige.post(new Runnable() {
		// public void run() {
		// frameAnimation.start();
		// }
		// });
		chasseur.post(new Runnable() {
			public void run() {
				frameAnimation1.start();
			}
		});
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
	public void onAnimationEnd(Animation animation) {
		if (animation == anim_loup) {

			anim_loup = new CustomAnim(AnimationUtils.loadAnimation(
					Scene5_ep3.this, R.anim.mainfadeout1_ep3), true);

			anim_loup.setFillAfter(true);
			anim_loup.setFillEnabled(true);
			loup.startAnimation(anim_loup);

			anim_loup1 = new CustomAnim(AnimationUtils.loadAnimation(
					Scene5_ep3.this, R.anim.mainfadein1_ep3), true);
			anim_loup1.setFillAfter(true);
			anim_loup1.setFillEnabled(true);
			loup1.startAnimation(anim_loup1);
			anim_loup1.setAnimationListener(this);
			testAnimation = 1;

		}
		if (animation == anim_loup1) {
			anim_loup1 = new CustomAnim(AnimationUtils.loadAnimation(
					Scene5_ep3.this, R.anim.mainfadeout1_ep3), true);

			anim_loup1.setFillAfter(true);
			anim_loup1.setFillEnabled(true);
			loup1.startAnimation(anim_loup1);

			anim_loup2 = new CustomAnim(AnimationUtils.loadAnimation(
					Scene5_ep3.this, R.anim.mainfadein1_ep3), true);

			anim_loup2.setFillAfter(true);
			anim_loup2.setFillEnabled(true);
			loup2.startAnimation(anim_loup2);
			anim_loup2.setAnimationListener(this);
			testAnimation = 2;
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
	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	

}