/**
 * 
 */
package lapechealaqueue.episode1.activities;


import java.util.Vector;

import android.app.Activity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class DragWithFleche extends Activity {

	protected Vector<ViewGroup> zone_depart;
	protected Vector<ViewGroup> zone_arrivee;
	protected Vector<View> images;
	protected Vector<View> rep_images;
	LineView[] lines;
	protected ViewGroup content_line;
	boolean[] reponse;
	int nbr_question;
	private int x = 0;
	int i;
	protected Vector<Image> image_id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		nbr_question = zone_depart.size();
		reponse = new boolean[nbr_question];
		lines = new LineView[nbr_question];
		for (int j = 0; j < nbr_question; j++)
			reponse[i] = false;
		setImagesListener();
		zoneArriveeListeer();
		super.onCreate(savedInstanceState);
	}

	public void setImagesListener() {
		for (int i = 0; i < nbr_question; i++) {
			images.get(i).setOnTouchListener(new OnTouchListener() {
				public boolean onTouch(View view, MotionEvent motionEvent) {
					if ((motionEvent.getAction() == MotionEvent.ACTION_DOWN)) {
						DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
								view);
						view.startDrag(null, shadowBuilder, view, 0);
						view.setVisibility(View.VISIBLE);
						return true;
					} else {
						images.get(images.indexOf(view)).setVisibility(
								View.VISIBLE);
						return false;
					}
				}
			});
		}
	}

	public void zoneArriveeListeer() {
		for (int i = 0; i < nbr_question; i++) {
			zone_arrivee.get(i).setOnDragListener(new OnDragListener() {
				public boolean onDrag(View v, DragEvent event) {
					switch (event.getAction()) {
					case DragEvent.ACTION_DRAG_STARTED:
						break;
					case DragEvent.ACTION_DRAG_ENTERED:
						break;
					case DragEvent.ACTION_DRAG_EXITED:
						break;
					case DragEvent.ACTION_DROP:
						View view = (View) event.getLocalState();
						if (((ViewGroup) v).getChildCount() != 0) {
							((ViewGroup) v).removeAllViews();
						}
						ImageView view2 = new ImageView(getApplicationContext());
						((ViewGroup) v).addView(view2);
						view2.setImageResource(image_id.get(0).rep_defaut);
						view2.setVisibility(View.VISIBLE);

						RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) ((View) view
								.getParent()).getLayoutParams();
						RelativeLayout.LayoutParams lp2 = (RelativeLayout.LayoutParams) ((View) view2
								.getParent()).getLayoutParams();
						LineView line = new LineView(getApplicationContext(),
								view2, (int) lp.leftMargin, (int) lp.topMargin,
								(int) lp2.leftMargin, (int) lp2.topMargin);
						line.setVisibility(View.VISIBLE);

						lines[zone_arrivee.indexOf(v)] = line;

						content_line.addView(line);
						x++;

						if (zone_arrivee.indexOf(v) == rep_images.indexOf(view)) {
							reponse[zone_arrivee.indexOf(v)] = true;
						}

						view.setEnabled(false);
						v.setEnabled(false);
						break;
					case DragEvent.ACTION_DRAG_ENDED:
					default:
						break;
					}
					return true;
				}
			});
		}
	}

	public void reset() {
		for (LineView line : lines)
			content_line.removeView(line);
		for (int i = 0; i < reponse.length; i++)
			reponse[i] = false;
		x = 0;
		for (View v : images)
			v.setEnabled(true);
		for (View v : zone_arrivee) {
			((ViewGroup) v).removeAllViews();
			v.setEnabled(true);
		}
	}

	// Verifier les reponse
	public int verifierReponse() {
		int nbr_rep_juste = 0;
		if (x < nbr_question) {
			onReponseNotComplete();
			return 0;
		}
		if (x == nbr_question) {
			for (int i = 0; i < nbr_question; i++) {
				if (reponse[i] == true) {
					nbr_rep_juste++;
					((ImageView) zone_arrivee.get(i).getChildAt(0))
							.setImageResource(image_id.get(0).rep_vrai);
				} else {
					((ImageView) zone_arrivee.get(i).getChildAt(0))
							.setImageResource(image_id.get(0).rep_faux);
				}
			}
		}
		if ((x == nbr_question) && (nbr_rep_juste < nbr_question)) {
			for (int i = 0; i < reponse.length; i++) {
				if (!reponse[i]) {
					x--;
					content_line.removeView(lines[i]);
					images.get(images.indexOf(rep_images.get(i))).setEnabled(
							true);
					zone_arrivee.get(i).setEnabled(true);
				}
			}
			onReponsesFausses();
			return 1;
		} else {
			onReponsesJustes();
			return 2;
		}
	}

	public void onReponsesJustes() {
		// TODO
	}

	public void onReponsesFausses() {
		// TODO
	}

	public void onReponseNotComplete() {
		// TODO
	}
}
