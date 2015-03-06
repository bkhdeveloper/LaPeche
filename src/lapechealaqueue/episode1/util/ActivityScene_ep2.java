package lapechealaqueue.episode1.util;
import lapechealaqueue.episode1.Home;
import lapechealaqueue.episode1.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityScene_ep2 extends Activity implements OnClickListener {

	// Default Attributes
	DisplayMetrics dm;
	// controle
	public ImageView play, stop, pause, suivant, precedent, home, exit;

	// popup exit
	public ImageView oui, non;

	// text
	protected TextView tv, tvBase;

	// media player
	protected MediaPlayer mp;

	protected View exit_popup;

	protected boolean scalingComplete = false;

	protected String contenu1, contenu2 = "", contenu3 = "";

	protected boolean _myIsStop, _myIsPause;

	protected int counter;

	protected boolean firstTextFinished, secondTextFinished;

	protected Typeface font;

	protected WakeLock mWakeLock;

	protected String scenePrecedente, sceneSuivante, sceneKeyback;

	protected int raw_id;
	protected ScaleManager sm;
	private ActivityScene_ep2 act;
	protected boolean nextEpisodeInstalled = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// keep screen on
		PowerManager power = (PowerManager) getSystemService(Context.POWER_SERVICE);
		mWakeLock = power.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,
				"My Tag");
		mWakeLock.acquire();
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		// Buttons
		exit = (ImageView) findViewById(R.id.exit);
		home = (ImageView) findViewById(R.id.home);
		stop = (ImageView) findViewById(R.id.stop);
		pause = (ImageView) findViewById(R.id.pause);
		play = (ImageView) findViewById(R.id.play);
		suivant = (ImageView) findViewById(R.id.suivant);
		precedent = (ImageView) findViewById(R.id.precedent);

		exit_popup = (View) findViewById(R.id.exit_popup);
		oui = (ImageView) findViewById(R.id.btn_oui);
		non = (ImageView) findViewById(R.id.btn_non);

		exit.setOnClickListener(this);
		home.setOnClickListener(this);
		stop.setOnClickListener(this);
		pause.setOnClickListener(this);
		play.setOnClickListener(this);
		suivant.setOnClickListener(this);
		precedent.setOnClickListener(this);
		exit_popup.setOnClickListener(this);
		oui.setOnClickListener(this);
		non.setOnClickListener(this);

		
		act = this;
		
		// TextViews
		
//		tv = (TextView) findViewById(R.id.ka);
//		tvBase = (TextView) findViewById(R.id.baseTxt);

		tv = (TextView) findViewById(R.id.ka);
		font = Typeface.createFromAsset(getAssets(), "MorrisRomanBlack.ttf");
		tv.setTypeface(font);

		tvBase = (TextView) findViewById(R.id.baseTxt);
		tvBase.setTypeface(font);
		tvBase.setText(contenu1);
		
		
		sm = new ScaleManager();
		sm.setTextSize(this, tvBase, tv);

		
		
		tv.setTypeface(font);
		tvBase.setTypeface(font);
		tvBase.setText(contenu1);

		
		
		try {
			startKaroke();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.mWakeLock.release();
		super.onDestroy();
		mp.release();
	}

	@Override
    public void onLowMemory() {
        super.onLowMemory();
    }
	
	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mp != null) {
			mp.stop();
		}
		finish();
	}

	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		View view = super.onCreateView(name, context, attrs);
		return view;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Intent intent = null;
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			if (scenePrecedente.equals("precedentEpisode")) {
				loadPrecedentEpisode();
			} else {
				try {
					intent = new Intent(getApplicationContext(),
							Class.forName(scenePrecedente));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (intent != null) {
					startActivity(intent);
					overridePendingTransition(R.anim.view_transition_in_right,
							R.anim.view_transition_out_right);
					act.finish();
				}
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		if (!scalingComplete) {
			ScaleManager sm = new ScaleManager();
			sm.scaleContents(findViewById(R.id.contents),
					findViewById(R.id.container));
			scalingComplete = true;
		}
		super.onWindowFocusChanged(hasFocus);
		super.onWindowFocusChanged(hasFocus);
	}

	@Override
	public void onClick(View view) {
		Intent intent = null;

		switch (view.getId()) {
		case R.id.exit:
			onBouttunExit();
			exit_popup.setVisibility(View.VISIBLE);
			mp.pause();
			_myIsPause = true;
			oui.setOnClickListener(this);
			non.setOnClickListener(this);
			break;

		case R.id.btn_oui:
			this.finish();
			break;

		case R.id.btn_non:
			onBouttunExit_Non();
			exit_popup.setVisibility(View.INVISIBLE);
			mp.start();
			_myIsPause = false;
			break;

		case R.id.exit_popup:
			break;

		case R.id.home:
			intent = new Intent(getApplicationContext(), Home.class);
			startActivity(intent);
			overridePendingTransition(R.anim.view_transition_in_right,
					R.anim.view_transition_out_right);
			finish();
			break;

		case R.id.precedent:
			if (scenePrecedente.equals("precedentEpisode")) {
				loadPrecedentEpisode();
			} else {
				try {
					intent = new Intent(getApplicationContext(),
							Class.forName(scenePrecedente));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (intent != null) {
					startActivity(intent);
					overridePendingTransition(R.anim.view_transition_in_right,
							R.anim.view_transition_out_right);
					act.finish();
				}
			}
			break;

		case R.id.suivant:
			if (!sceneSuivante.equals("nextEpisode")) {
				try {
					intent = new Intent(getApplicationContext(),
							Class.forName(sceneSuivante));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				if (intent != null) {
					startActivity(intent);
					overridePendingTransition(R.anim.view_transition_in_left,
							R.anim.view_transition_out_left);
					act.finish();
				}
			} else {
				loadNextEpisode();
			}
			break;

		case R.id.stop:
			onBouttunStop();
			if (mp.isPlaying() || _myIsPause || firstTextFinished) {
				mp.stop();
				_myIsStop = true;
				tv.setText("");
				tvBase.setText(contenu1);
				firstTextFinished = false;
			}
			break;

		case R.id.pause:
			onBouttunPause();
			if (mp.isPlaying()) {
				mp.pause();
				_myIsPause = true;
			}
			break;

		case R.id.play:
			
			onBouttunPlay();
			if (_myIsStop) {
				tv.setText("");
				tvBase.setText(contenu1);
				_myIsPause = false;
				_myIsStop = false;
				startKaroke();
			} else if (_myIsPause && !_myIsStop) {
				mp.start();
				_myIsPause = false;
			}
			break;
			
		}
	}

	public void loadPrecedentEpisode() {
		// TODO Auto-generated method stub
		
	}

	public void onBouttunExit_Non() {
		// TODO Auto-generated method stub
		
	}

	public void onBouttunExit() {
		// TODO Auto-generated method stub
		
	}

	public void onBouttunStop() {
		// TODO Auto-generated method stub
		
	}

	public void onBouttunPause() {
		// TODO Auto-generated method stub
		
	}

	public void onBouttunPlay() {
		// TODO Auto-generated method stub
		
	}

	public void startKaroke() {
		final Handler handler = new Handler();
		counter = 0;
		final String[] spContenu1 = contenu1.split(" ");
		final String[] spContenu2 = contenu2.split(" ");
		final String[] spContenu3 = contenu3.split(" ");
		mp = MediaPlayer.create(ActivityScene_ep2.this, raw_id);
		try {
			mp.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mp.start();
		final int time = mp.getDuration()
				/ (spContenu1.length + spContenu2.length + spContenu3.length);

		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				firstTextFinished = false;
				secondTextFinished = false;
				while (counter < (spContenu1.length + spContenu2.length + spContenu3.length)
						&& !_myIsStop) {
					try {
						while (_myIsPause) {
							if (_myIsStop)
								break;
						}
						Thread.sleep(time);
						handler.postDelayed(new Runnable() {

							@Override
							public void run() {
								if (!_myIsStop) {
									if (counter < spContenu1.length
											&& !firstTextFinished) {
										tvBase.setText(contenu1);
										tv.append(spContenu1[counter] + " ");
									} else if (counter < spContenu2.length
											&& firstTextFinished
											&& !secondTextFinished) {
										tvBase.setText(contenu2);
										tv.append(spContenu2[counter] + " ");
									} else if (counter < spContenu3.length
											&& secondTextFinished) {
										tvBase.setText(contenu3);
										tv.append(spContenu3[counter] + " ");
									} else if ((!firstTextFinished)
											&& (spContenu2.length > 1)) {
										tv.setText("");
										counter = 0;
										firstTextFinished = true;
									} else if ((!secondTextFinished)
											&& (spContenu3.length > 1)) {
										tv.setText("");
										counter = 0;
										secondTextFinished = true;
									}
								}
							}
						}, 100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					counter++;
				}
			}
		};
		new Thread(runnable).start();
	}


	public void loadNextEpisode() {

	}
}
