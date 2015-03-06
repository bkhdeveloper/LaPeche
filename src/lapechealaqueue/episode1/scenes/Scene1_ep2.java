package lapechealaqueue.episode1.scenes;

import java.util.ArrayList;
import java.util.List;

import lapechealaqueue.episode1.R;
import lapechealaqueue.episode1.util.ActivityScene;
import lapechealaqueue.episode1.util.ActivityScene_ep2;
import lapechealaqueue.episode1.util.CustomAnim;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Scene1_ep2 extends ActivityScene_ep2 implements AnimationListener{
	protected boolean isPlay = false, isStop = false, isPause = false;
	private String packageName;
	private List<PackageInfo> packs;
	private boolean testEpisode1 = false;
	private CustomAnim anim_loup,anim_loup1,anim_loup2,anim_loup3,anim_odeur;
	AnimationDrawable frameAnimation;
	ImageView neige, loup, loup1, loup2, loup3,odeur;
	int testAnimation = 0;

	public void onCreate(Bundle savedInstanceState) {

		setContentView(R.layout.scene1_ep2);
		scenePrecedente = "lapechealaqueue.episode1.scenes.Scene4";
		sceneSuivante = "lapechealaqueue.episode1.scenes.Scene2_ep2";
		sceneKeyback = "previousEpisode";

		neige = (ImageView) findViewById(R.id.neige);
		loup = (ImageView) findViewById(R.id.loup);
		loup1 = (ImageView) findViewById(R.id.loup1);
		loup2 = (ImageView) findViewById(R.id.loup2);
		loup3 = (ImageView) findViewById(R.id.loup3);
		odeur = (ImageView) findViewById(R.id.odeur);
		
		contenu1 = " \n"
				+ " \n"
				+ " Voilà pourquoi un grand feu de bois ronflait dans sa cheminée tandis qu’il se léchait les babines en mangeant des anguilles grillées.";

		contenu2 = " \n"
				+ " Isengrin, le loup, avait parcouru plusieurs fois la grande forêt de long en large sans rien trouver à se mettre sous la dent, "
				+"pas le moindre petit lapin, pas le plus minuscule oiseau. Rien.";
		
		contenu3 = " \n"
				+" Il se traînait, à bout de force, quand il flaira une bonne odeur de poisson grillé.\n"
				+" Il suivit l’odeur et arriva bientôt devant la maison de Renart.";

	
				
		ArrayList<PackageInfo> res = new ArrayList<PackageInfo>();
		PackageManager mm = getPackageManager();
		packs = mm.getInstalledPackages(0);
		activate();

		raw_id = R.raw.sc1_ep2;
		font = Typeface.createFromAsset(getAssets(), "MorrisRomanBlack.ttf");

		
		anim_loup1 = new CustomAnim(AnimationUtils.loadAnimation(Scene1_ep2.this,
				R.anim.mainfadein1_ep2), true);
		anim_loup1.setStartOffset(1000);
		loup1.startAnimation(anim_loup1);
		anim_loup1.setAnimationListener(this);
		
		super.onCreate(savedInstanceState);

		System.gc();

	}

	// ///////////////////////Scaling//////////////////////////////////////////////////
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

	}

	public void loadPrecedentEpisode() {
		super.loadPrecedentEpisode();
		if (testEpisode1 == true) {
			Intent ep2 = new Intent(Intent.ACTION_MAIN);
			final ComponentName cn = new ComponentName(
					"lapechealaqueue.episode1",
					"lapechealaqueue.episode1.scenes.Scene4");
			ep2.setComponent(cn);
			ep2.addCategory(Intent.CATEGORY_LAUNCHER);
			ep2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			overridePendingTransition(R.anim.view_transition_in_left,
					R.anim.view_transition_out_left);
			startActivity(ep2);
			finish();
		} else {

		}
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

	private void resumeAnimations() {
		if (testAnimation == 0) {
			anim_loup1.resumeAnim();

		} else if (testAnimation == 1) {
			anim_loup2.resumeAnim();

		} else if (testAnimation == 2) {
			anim_loup3.resumeAnim();

		} else if (testAnimation == 3) {
			anim_loup.resumeAnim();

		}
	}

	private void startAnimations() {
		anim_loup1 = new CustomAnim(AnimationUtils.loadAnimation(Scene1_ep2.this,
				R.anim.mainfadein1_ep2), true);
		anim_loup1.setStartOffset(1000);
		loup1.startAnimation(anim_loup1);
		anim_loup1.setAnimationListener(this);
	}

	private void pauseAnimations() {
		if (testAnimation == 0) {
			anim_loup1.pauseAnim();

		} else if (testAnimation == 1) {
			anim_loup2.pauseAnim();

		} else if (testAnimation == 2) {
			anim_loup3.pauseAnim();

		} else if (testAnimation == 3) {
			anim_loup.pauseAnim();

		}
	}

	private void clearAnimations() {
		loup.clearAnimation();
		loup1.clearAnimation();
		loup2.clearAnimation();
		loup3.clearAnimation();
		odeur.clearAnimation();
	}

	public void activate() {

		for (int i = 0; i < packs.size(); i++) {
			PackageInfo p = packs.get(i);
			packageName = p.packageName;
			if (packageName.equals("lapechealaqueue.episode1")) {
				testEpisode1 = true;
			}
		}
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if (animation == anim_loup1) {

			anim_loup1 = new CustomAnim(AnimationUtils.loadAnimation(
					Scene1_ep2.this, R.anim.mainfadeout1_ep2), true);

			anim_loup1.setFillAfter(true);
			anim_loup1.setFillEnabled(true);
			
			anim_loup2 = new CustomAnim(AnimationUtils.loadAnimation(Scene1_ep2.this,
					R.anim.mainfadeout1_ep2), true);

			anim_loup2.setFillAfter(true);
			anim_loup2.setFillEnabled(true);
			loup2.startAnimation(anim_loup2);
			anim_loup2.setAnimationListener(this);
			testAnimation = 1;

		}
		if (animation == anim_loup2) {
			anim_loup2 = new CustomAnim(AnimationUtils.loadAnimation(
					Scene1_ep2.this, R.anim.mainfadeout1_ep2), true);

			anim_loup2.setFillAfter(true);
			anim_loup2.setFillEnabled(true);
			
			anim_loup3 = new CustomAnim(AnimationUtils.loadAnimation(Scene1_ep2.this,
					R.anim.mainfadein1_ep2), true);

			anim_loup3.setFillAfter(true);
			anim_loup3.setFillEnabled(true);
			loup3.startAnimation(anim_loup3);
			anim_loup3.setAnimationListener(this);
			
			anim_odeur = new CustomAnim(AnimationUtils.loadAnimation(Scene1_ep2.this,
					R.anim.mainfadein1_ep2), true);

			anim_odeur.setFillAfter(true);
			anim_odeur.setFillEnabled(true);
			odeur.startAnimation(anim_odeur);
			testAnimation = 2;
		}
		if (animation == anim_loup3) {
			anim_loup3 = new CustomAnim(AnimationUtils.loadAnimation(
					Scene1_ep2.this, R.anim.mainfadeout1_ep2), true);

			anim_loup3.setFillAfter(true);
			anim_loup3.setFillEnabled(true);
			loup3.startAnimation(anim_loup3);
			
			anim_odeur = new CustomAnim(AnimationUtils.loadAnimation(
					Scene1_ep2.this, R.anim.mainfadeout1_ep2), true);

			anim_odeur.setFillAfter(true);
			anim_odeur.setFillEnabled(true);
			odeur.startAnimation(anim_odeur);
			
			
			anim_loup = new CustomAnim(AnimationUtils.loadAnimation(Scene1_ep2.this,
					R.anim.mainfadein1_ep2), true);

			anim_loup.setFillAfter(true);
			anim_loup.setFillEnabled(true);
			loup.startAnimation(anim_loup);
			testAnimation = 3;
		}
		
	
		
	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		
	}


}