package lapechealaqueue.episode1.util;

import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Transformation;

public class CustomAnim extends AnimationSet {

	private long mElapsedAtPause = 0;
	private boolean mPaused = false;
	
	public CustomAnim(Animation animation, boolean sharedInterpolator) {
		super(sharedInterpolator);
		addAnimation(animation);
	}
	
	@Override
	public boolean getTransformation(long currentTime,
			Transformation outTransformation) {
		if (mPaused && mElapsedAtPause == 0) {
			mElapsedAtPause = currentTime - getStartTime();
		}
		if (mPaused)
			setStartTime(currentTime - mElapsedAtPause);
		return super.getTransformation(currentTime, outTransformation);
	}

	public void pauseAnim() {
		if(mPaused == false){
			mElapsedAtPause = 0;
			mPaused = true;
		}
		
	}

	public void resumeAnim() {
		mPaused = false;
	} 

	public void clearAnimation() {		
		cancel();
		reset();
	}
}