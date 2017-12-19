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

	private int itemSize;
	private int itemMargin;

	private boolean fillPrevious;

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
			fillPrevious = attributes.getBoolean(R.styleable.InputProgressView_fill_previous, false);
			progress = attributes.getInteger(R.styleable.InputProgressView_progress, 0);
			itemSize = attributes.getDimensionPixelSize(R.styleable.InputProgressView_item_size, -1);
			itemMargin = attributes.getDimensionPixelSize(R.styleable.InputProgressView_item_margin, -1);

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

		if (itemSize == -1) {
			itemSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, dm);
		}
		if (itemMargin == -1) {
			itemMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, dm);
		}

		circles = new View[maxProgress];

		for (int i = 0; i < maxProgress; i++) {
			View circle = new View(context);
			LayoutParams params = new LayoutParams(itemSize, itemSize);
			params.setMargins(itemMargin, 0, itemMargin, 0);
			circle.setLayoutParams(params);
			addView(circle);
			circles[i] = circle;
		}

		setProgress(progress);
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

	public void setProgress(int currentProgress) {
		if (currentProgress < 0) {
			throw new ArrayIndexOutOfBoundsException("current progress must be >= 0");
		}
		for (View v: circles) {
			v.setBackground(emptyDrawable);
		}
		if (currentProgress == 0) {
			return;
		}
		progress = currentProgress - 1;
		if (progress >= maxProgress) {
			progress = maxProgress - 1;
		}
		if (fillPrevious) {
			for (int i = 0; i <= progress; i++) {
				circles[i].setBackground(filledDrawable);
			}
		} else {
			circles[progress].setBackground(filledDrawable);
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