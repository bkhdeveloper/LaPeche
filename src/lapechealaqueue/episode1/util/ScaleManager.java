package lapechealaqueue.episode1.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ScaleManager {

	public void scaleContents(View rootView, View container) {

		float xScale = (float) container.getWidth() / rootView.getWidth();
		float yScale = (float) container.getHeight() / rootView.getHeight();
		float scale = Math.min(xScale, yScale);

		scaleViewAndChildren(rootView, scale);
	}

	@SuppressWarnings("deprecation")
	private static void scaleViewAndChildren(View root, float scale) {
		ViewGroup.LayoutParams layoutParams = root.getLayoutParams();

		if (layoutParams.width != ViewGroup.LayoutParams.FILL_PARENT
				&& layoutParams.width != ViewGroup.LayoutParams.WRAP_CONTENT) {
			layoutParams.width *= scale;
		}
		if (layoutParams.height != ViewGroup.LayoutParams.FILL_PARENT
				&& layoutParams.height != ViewGroup.LayoutParams.WRAP_CONTENT) {
			layoutParams.height *= scale;
		}

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

		if (root instanceof ViewGroup) {
			ViewGroup groupView = (ViewGroup) root;
			for (int cnt = 0; cnt < groupView.getChildCount(); ++cnt)
				scaleViewAndChildren(groupView.getChildAt(cnt), scale);
		}
	}

	// //////////////////////////////////////////////////////////////////////

	public void setTextSize(Activity context, TextView tvBase, TextView tv) {
		final DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);

		// **********************************************//

		// galaxy HTC et ...
		if (dm.densityDpi >= 400 && dm.densityDpi <= 500) {
			tvBase.setTextSize(4);
			tv.setTextSize(4);
		}

		else if (dm.densityDpi >= 318 && dm.densityDpi <= 330) {
			tvBase.setTextSize(11);
			tv.setTextSize(11);
		}
		
		// galaxy s4 et ...
		else if (dm.widthPixels == 1920 && dm.heightPixels == 1080) {
			tvBase.setTextSize(5);
			tv.setTextSize(5);
		}

		// new test
		else if (dm.widthPixels <= 340 && dm.heightPixels <= 280
				&& dm.densityDpi <= 140) {
			tvBase.setTextSize(75);
			tv.setTextSize(75);
		}
		// galaxy y et ...
		else if (dm.widthPixels <= 320 && dm.heightPixels <= 240
				&& (dm.densityDpi > 120 && dm.densityDpi < 160)) {
			tvBase.setTextSize(11);
			tv.setTextSize(11);
		}
		// galazy s2
		else if (dm.widthPixels <= 800 && dm.heightPixels <= 480
				&& dm.densityDpi > 180) {
			tvBase.setTextSize(20);
			tv.setTextSize(20);
		}
		// Nexsus 7
		else if (dm.widthPixels == 1280 && dm.heightPixels == 736
				&& dm.densityDpi == 213) {
			tvBase.setTextSize(25);
			tv.setTextSize(25);
		}

		// galaxy Note 2 // 320dpi
		else if (dm.widthPixels == 1280 && dm.heightPixels == 720
				&& (dm.densityDpi == 320)) {
			tvBase.setTextSize(12);
			tv.setTextSize(12);
		}

		// galaxy note 1 // 320dpi
		else if (dm.widthPixels == 1280 && dm.heightPixels == 800
				&& (dm.densityDpi == 320)) {
			tvBase.setTextSize(11);
			tv.setTextSize(11);
		}

		// galaxy s3
		else if (dm.widthPixels == 1280 && dm.heightPixels == 720
				&& (dm.densityDpi > 300 && dm.densityDpi <= 320)) {
			tvBase.setTextSize(10);
			tv.setTextSize(10);
		}

		// galaxy Note 2
		else if (dm.widthPixels <= 1280 && dm.heightPixels <= 720
				&& (dm.densityDpi > 220 && dm.densityDpi < 270)) {
			tvBase.setTextSize(20);
			tv.setTextSize(20);
		}

		// galaxy note 1
		else if (dm.widthPixels <= 1280 && dm.heightPixels <= 800
				&& (dm.densityDpi < 310) && (dm.densityDpi > 270)) {
			tvBase.setTextSize(15);
			tv.setTextSize(15);
		}
				
	}
}
