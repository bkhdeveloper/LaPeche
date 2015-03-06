package lapechealaqueue.episode1.activities;

import java.io.IOException;
import java.util.List;

import lapechealaqueue.episode1.Activities;
import lapechealaqueue.episode1.Home;
import lapechealaqueue.episode1.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class Activitie2 extends Activity {
	protected PowerManager.WakeLock mWakeLock;

	MediaPlayer mpFaux, mpVrai, mpIncomplet;

	private boolean scalingComplete = false;
	Activity act;
	ImageView exit, home, oui, non, rep_1, rep_2, rep_3, valider, repeat, vrai,
			incomplet, faux;
	Boolean _rep_1 = false, _rep_2 = false, _rep_3 = false;
	final Context context = this;
	AnimationSet animation;
	private static final long active = 2000;
	private static final int STOP = 0;
	private static final int STOP2 = 1;
	View layout_activite;
	private ImageView popup_suivant, popup_acceuil, popup_quiz;
	private boolean testEpisode = false;
	private String packageName;
	private List<PackageInfo> packs;
	View exit2;
	View layout;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activitie2);
		act = this;

		mpFaux = new MediaPlayer();
		mpVrai = new MediaPlayer();
		mpIncomplet = new MediaPlayer();

		layout_activite = (View) findViewById(R.id.layout_activite);
		popup_acceuil = (ImageView) findViewById(R.id.activite);
		popup_quiz = (ImageView) findViewById(R.id.quiz);
		popup_suivant = (ImageView) findViewById(R.id.suivant);

		exit = (ImageView) findViewById(R.id.exit);
		home = (ImageView) findViewById(R.id.home);

		oui = (ImageView) findViewById(R.id.btn_oui);
		non = (ImageView) findViewById(R.id.btn_non);
		exit2 = (View) findViewById(R.id.exit_popup);

		rep_1 = (ImageView) findViewById(R.id.rep_1);
		rep_2 = (ImageView) findViewById(R.id.rep_2);
		rep_3 = (ImageView) findViewById(R.id.rep_3);

		vrai = (ImageView) findViewById(R.id.vrai);
		faux = (ImageView) findViewById(R.id.faux);
		incomplet = (ImageView) findViewById(R.id.incomplet);

		repeat = (ImageView) findViewById(R.id.repeat);
		valider = (ImageView) findViewById(R.id.valider);

		vrai = (ImageView) findViewById(R.id.vrai);
		faux = (ImageView) findViewById(R.id.faux);
		incomplet = (ImageView) findViewById(R.id.incomplet);
		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setInterpolator(new DecelerateInterpolator());
		fadeIn.setDuration(2000);

		Animation fadeOut = new AlphaAnimation(1, 0);
		fadeOut.setInterpolator(new AccelerateInterpolator());
		fadeOut.setStartOffset(2500);
		fadeOut.setDuration(2000);

		animation = new AnimationSet(true);
		animation.addAnimation(fadeIn);
		animation.addAnimation(fadeOut);

		PackageManager pm2 = getPackageManager();
		packs = pm2.getInstalledPackages(0);

		testEpisode();

		repeat.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i2 = getIntent();
				startActivity(i2);
				act.finish();
			}
		});

		rep_1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (_rep_1 == false) {
					rep_1.setBackgroundResource(R.drawable.choix1_s_act2);
					_rep_1 = true;
				} else {
					rep_1.setBackgroundResource(R.drawable.choix1_act2);
					_rep_1 = false;
				}
			}
		});

		rep_2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (_rep_2 == false) {
					rep_2.setBackgroundResource(R.drawable.choix2_s_act2);
					_rep_2 = true;
				} else {
					rep_2.setBackgroundResource(R.drawable.choix2_act2);
					_rep_2 = false;
				}
			}
		});

		rep_3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (_rep_3 == false) {
					rep_3.setBackgroundResource(R.drawable.choix3_s_act2);
					_rep_3 = true;
				} else {
					rep_3.setBackgroundResource(R.drawable.choix3_act2);
					_rep_3 = false;
				}
			}
		});

		valider.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				faux.setBackgroundDrawable(null);
				vrai.setBackgroundDrawable(null);
				incomplet.setBackgroundDrawable(null);
				KillMedia();

				if (_rep_1 == true && _rep_2 == true && _rep_3 == false) {
					mpVrai = MediaPlayer.create(Activitie2.this, R.raw.vrai);
					mediaVrais();

					vrai.setVisibility(1);
					vrai.setBackgroundResource(R.drawable.bulle_vrai);
					vrai.startAnimation(animation);
					vrai.setVisibility(View.INVISIBLE);

					rep_2.setBackgroundResource(R.drawable.choix2_v_act2);
					rep_1.setBackgroundResource(R.drawable.choix1_v_act2);

					rep_1.setEnabled(false);
					rep_2.setEnabled(false);
					rep_3.setEnabled(false);

					Message msg = new Message();
					msg.what = STOP2;
					HandlerActivite.sendMessageDelayed(msg, active);
				}

				else if (_rep_1 == false && _rep_2 == true && _rep_3 == false) {

					incomplet.setVisibility(1);
					incomplet.setBackgroundResource(R.drawable.bulle_incomplet);
					incomplet.startAnimation(animation);
					incomplet.setVisibility(View.INVISIBLE);
					mediaIncomp();

				}
				else if (_rep_1 == true && _rep_2 == false && _rep_3 == false) {

					incomplet.setVisibility(1);
					incomplet.setBackgroundResource(R.drawable.bulle_incomplet);
					incomplet.startAnimation(animation);
					incomplet.setVisibility(View.INVISIBLE);

				} else if (_rep_1 == false && _rep_2 == false && _rep_3 == false) {
					incomplet.setVisibility(1);
					incomplet.setBackgroundResource(R.drawable.bulle_incomplet);
					incomplet.startAnimation(animation);
					incomplet.setVisibility(View.INVISIBLE);

				}

				else if (_rep_1 == true && _rep_2 == true && _rep_3 == true) {
					_rep_1 = false;
					_rep_2 = false;
					_rep_3 = false;
					rep_1.setBackgroundResource(R.drawable.choix1_act2);
					rep_2.setBackgroundResource(R.drawable.choix2_act2);
					rep_3.setBackgroundResource(R.drawable.choix3_act2);
					mpFaux = MediaPlayer.create(Activitie2.this, R.raw.faux);

					faux.setVisibility(1);
					faux.setBackgroundResource(R.drawable.bulle_faux);
					faux.startAnimation(animation);
					faux.setVisibility(View.INVISIBLE);

					mediaFaux();
					
					Message msg = new Message();
					msg.what = STOP;
					Handler.sendMessageDelayed(msg, active);

				} else if ((_rep_2 == true || _rep_1 == true) && _rep_3 == true) {
					mpFaux = MediaPlayer.create(Activitie2.this, R.raw.faux);
					faux.setVisibility(1);
					faux.setBackgroundResource(R.drawable.bulle_faux);
					faux.startAnimation(animation);
					faux.setVisibility(View.INVISIBLE);
					rep_3.setBackgroundResource(R.drawable.choix3_f_act2);	
					mediaFaux();
					
					Message msg = new Message();
					msg.what = STOP;
					Handler.sendMessageDelayed(msg, active);

				} else if (_rep_3 == true) {
					rep_3.setBackgroundResource(R.drawable.choix3_f_act2);	
					mpFaux = MediaPlayer.create(Activitie2.this, R.raw.faux);
					faux.setVisibility(1);
					faux.setBackgroundResource(R.drawable.bulle_faux);
					faux.startAnimation(animation);
					faux.setVisibility(View.INVISIBLE);

					mediaFaux();
					
					Message msg = new Message();
					msg.what = STOP;
					Handler.sendMessageDelayed(msg, active);


				} else {
					_rep_1 = false;
					_rep_2 = false;
					_rep_3 = false;
					rep_1.setBackgroundResource(R.drawable.choix1_act2);
					rep_2.setBackgroundResource(R.drawable.choix2_act2);
					rep_3.setBackgroundResource(R.drawable.choix3_act2);
					mpFaux = MediaPlayer.create(Activitie2.this, R.raw.faux);

					faux.setVisibility(1);
					faux.setBackgroundResource(R.drawable.bulle_faux);
					faux.startAnimation(animation);
					faux.setVisibility(View.INVISIBLE);

					mediaFaux();
					
					Message msg = new Message();
					msg.what = STOP;
					Handler.sendMessageDelayed(msg, active);


				}
			}
		});

		exit.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				exit2.setVisibility(View.VISIBLE);

				oui.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						finish();
					}
				});
				non.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {

						exit2.setVisibility(View.INVISIBLE);

					}
				});
				exit2.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {

					}
				});

			}
		});
		home.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Intent i2 = new Intent(getApplicationContext(), Home.class);
				startActivity(i2);
				act.finish();
			}
		});

		final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		this.mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,
				"My Tag");
		this.mWakeLock.acquire();

	}

	/******************************************/
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			Intent i = new Intent(getApplicationContext(), Activities.class);

			startActivity(i);
			overridePendingTransition(R.anim.view_transition_in_right,
					R.anim.view_transition_out_right);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	/******************************************/

	protected void onRestart() {

		super.onRestart();

	}

	protected void onPause() {
		super.onPause();
		KillMedia();

		finish();
	}

	public void onDestroy() {
		this.mWakeLock.release();
		super.onDestroy();

	}

	private Handler Handler = new Handler() {
		public void handleMessage(Message msg) {

			_rep_3 = false;

			rep_3.setBackgroundResource(R.drawable.choix3_act2);
			super.handleMessage(msg);
		}

	};

	private Handler HandlerActivite = new Handler() {
		public void handleMessage(Message msg) {

			popupActivite();

			super.handleMessage(msg);
		}
	};

	// *********

	private void mediaFaux() {

		if (mpVrai.isPlaying() || mpFaux.isPlaying() || mpIncomplet.isPlaying()) {
			try {
				mpVrai.stop();
				mpFaux.stop();
				mpIncomplet.stop();
				mpFaux.prepare();
				mpFaux.start();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {

			mpFaux.start();
		}
	}

	private void mediaIncomp() {

		if (mpVrai.isPlaying() || mpFaux.isPlaying() || mpIncomplet.isPlaying()) {
			try {

				mpVrai.stop();
				mpFaux.stop();
				mpIncomplet.stop();
				mpIncomplet.prepare();
				mpIncomplet.start();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {

			mpIncomplet.start();
		}
	}

	private void mediaVrais() {

		if (mpVrai.isPlaying() || mpFaux.isPlaying() || mpIncomplet.isPlaying()) {
			try {
				mpVrai.stop();
				mpFaux.stop();
				mpIncomplet.stop();
				mpVrai.prepare();
				mpVrai.start();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {

			mpVrai.start();
		}
	}

	private void KillMedia() {
		Handler.removeMessages(STOP);
		if (mpVrai.isPlaying() || mpFaux.isPlaying() || mpIncomplet.isPlaying()) {
			try {
				mpVrai.stop();
				mpFaux.stop();
				mpIncomplet.stop();
				mpVrai.prepare();
				mpFaux.prepare();
				mpIncomplet.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// ********
		public void popupActivite() {

			layout_activite.setVisibility(View.VISIBLE);

			if (testEpisode == true) {
				popup_suivant.setVisibility(View.VISIBLE);
			}
			popup_acceuil.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

					Intent i2 = new Intent(getApplicationContext(), Home.class);
					startActivity(i2);
					act.finish();

				}
			});

			popup_quiz.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

					Intent i2 = new Intent(getApplicationContext(), Activities.class);
					startActivity(i2);
					act.finish();

				}
			});

			popup_suivant.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

					Intent i2 = new Intent(getApplicationContext(), Activitie3.class);
					startActivity(i2);
					act.finish();
				}
			});
			
			// bg_popup_activite.setOnClickListener(new OnClickListener() {
			// public void onClick(View v) {
			//
			// layout_activite.setVisibility(View.INVISIBLE);
			//
			// }
			// });
			//

			layout_activite.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

					// layout_activite.setVisibility(View.INVISIBLE);

				}
			});
		}
		// *****************

	public void onWindowFocusChanged(boolean hasFocus) {
		if (!scalingComplete) // only do this once
		{
			scaleContents(findViewById(R.id.contents),
					findViewById(R.id.container));
			scalingComplete = true;
		}
		super.onWindowFocusChanged(hasFocus);
	}

	/**
	 * Called when the views have been created. We override this in order to
	 * scale the UI, which we can't do before this.
	 */
	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		View view = super.onCreateView(name, context, attrs);
		return view;
	}

	/**
	 * Scales the contents of the given view so that it completely fills the
	 * given container on one axis (that is, we're scaling isotropically).
	 * 
	 * @param rootView
	 *            The view that contains the interface elements
	 * @param container
	 *            The view into which the interface will be scaled
	 */
	private void scaleContents(View rootView, View container) {
		// Compute the scaling ratio. Note that there are all kinds of games you
		// could
		// play here - you could, for example, allow the aspect ratio to be
		// distorted
		// by a certain percentage, or you could scale to fill the *larger*
		// dimension
		// of the container view (useful if, for example, the container view can
		// scroll).
		float xScale = (float) container.getWidth() / rootView.getWidth();
		float yScale = (float) container.getHeight() / rootView.getHeight();
		float scale = Math.min(xScale, yScale);

		// Scale our contents
		scaleViewAndChildren(rootView, scale);
	}

	/**
	 * Scale the given view, its contents, and all of its children by the given
	 * factor.
	 * 
	 * @param root
	 *            The root view of the UI subtree to be scaled
	 * @param scale
	 *            The scaling factor
	 */
	public static void scaleViewAndChildren(View root, float scale) {
		// Retrieve the view's layout information
		ViewGroup.LayoutParams layoutParams = root.getLayoutParams();

		// Scale the view itself
		if (layoutParams.width != ViewGroup.LayoutParams.FILL_PARENT
				&& layoutParams.width != ViewGroup.LayoutParams.WRAP_CONTENT) {
			layoutParams.width *= scale;
		}
		if (layoutParams.height != ViewGroup.LayoutParams.FILL_PARENT
				&& layoutParams.height != ViewGroup.LayoutParams.WRAP_CONTENT) {
			layoutParams.height *= scale;
		}

		// If this view has margins, scale those too
		if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
			ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) layoutParams;
			marginParams.leftMargin *= scale;
			marginParams.rightMargin *= scale;
			marginParams.topMargin *= scale;
			marginParams.bottomMargin *= scale;
		}

		root.setLayoutParams(layoutParams);

		root.setPadding((int) (root.getPaddingLeft() * scale),
				(int) (root.getPaddingTop() * scale),
				(int) (root.getPaddingRight() * scale),
				(int) (root.getPaddingBottom() * scale));

		if (root instanceof TextView) {
			TextView textView = (TextView) root;
			textView.setTextSize(textView.getTextSize() * scale);
		}

		// If the root view is a ViewGroup, scale all of its children
		// recursively
		if (root instanceof ViewGroup) {
			ViewGroup groupView = (ViewGroup) root;
			for (int cnt = 0; cnt < groupView.getChildCount(); ++cnt)
				scaleViewAndChildren(groupView.getChildAt(cnt), scale);
		}
	}
	public void testEpisode() {

		for (int i = 0; i < packs.size(); i++) {
			PackageInfo p = packs.get(i);
			packageName = p.packageName;
			if (packageName.equals("lapechealaqueue.episode3")) {
				testEpisode = true;

			}
			
		}

	}

}