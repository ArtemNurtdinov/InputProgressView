package com.nefrit.inputprogressview.gui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.nefrit.inputprogressview.R;

public class InputProgressView extends LinearLayout {

	private static final int DEFAULT_MAX_PROGRESS = 4;

	private int progress;
	private int maxProgress;

	private Drawable emptyDrawable;
	private Drawable filledDrawable;

	private View[] circles;

	private ProgressChangeListener progressChangeListener;

	public InputProgressView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);

		if (attrs != null) {
			TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.InputProgressView);
			maxProgress = attributes.getInteger(R.styleable.InputProgressView_max_progress, DEFAULT_MAX_PROGRESS);
			emptyDrawable = attributes.getDrawable(R.styleable.InputProgressView_background_empty);
			filledDrawable = attributes.getDrawable(R.styleable.InputProgressView_background_filled);

			attributes.recycle();
		}

		if (emptyDrawable == null) {
			emptyDrawable = ContextCompat.getDrawable(context, R.drawable.background_ipv_empty);
		}

		if (filledDrawable == null) {
			filledDrawable = ContextCompat.getDrawable(context, R.drawable.background_ipv_filled);
		}

		init(context);
	}

	private void init(Context context) {

		if (maxProgress < 1) {
			maxProgress = DEFAULT_MAX_PROGRESS;
		}

		DisplayMetrics dm = context.getResources().getDisplayMetrics();

		int size = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, dm);
		int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, dm);

		circles = new View[maxProgress];

		for (int i = 0; i < maxProgress; i++) {
			View circle = new View(context);
			LayoutParams params = new LayoutParams(size, size);
			params.setMargins(margin, 0, margin, 0);
			circle.setLayoutParams(params);
			circle.setBackground(emptyDrawable);
			addView(circle);
			circles[i] = circle;
		}
	}

	public int getProgress() {
		return progress;
	}

	public int getMaxProgress() {
		return maxProgress;
	}

	public void setProgressChangeListener(ProgressChangeListener progressChangeListener) {
		this.progressChangeListener = progressChangeListener;
	}

	public void setProgress(int progress) {
		if (progress > maxProgress) {
			return;
		}
		this.progress = progress;
		for (int i = 0; i < progress; i++) {
			circles[i].setBackground(filledDrawable);
		}

		for (int i = progress; i < maxProgress; i++) {
			circles[i].setBackground(emptyDrawable);
		}

		if (progressChangeListener != null) {
			progressChangeListener.progressChanged(progress);
		}
	}

	public void setMaxProgress(int progress) {
		this.maxProgress = progress;
		removeAllViews();
		init(getContext());
	}

	public interface ProgressChangeListener {
		void progressChanged(int progress);
	}
}