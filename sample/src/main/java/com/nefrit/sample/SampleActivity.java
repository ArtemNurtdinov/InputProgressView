package com.nefrit.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nefrit.inputprogressview.gui.InputProgressView;

public class SampleActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener,
		View.OnClickListener {

	private InputProgressView inputProgressView1;
	private InputProgressView inputProgressView2;
	private InputProgressView inputProgressView3;
	private InputProgressView inputProgressView4;

	private TextView tvProgress;

	private SeekBar seekBar;

	private Button btnStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		inputProgressView1 = findViewById(R.id.ipv_1);
		inputProgressView2 = findViewById(R.id.ipv_2);
		inputProgressView3 = findViewById(R.id.ipv_3);
		inputProgressView4 = findViewById(R.id.ipv_4);
		seekBar = findViewById(R.id.seek_bar);
		tvProgress = findViewById(R.id.tv_progress);
		btnStart = findViewById(R.id.btn_start);

		seekBar.setOnSeekBarChangeListener(this);

		btnStart.setOnClickListener(this);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
		inputProgressView1.setProgress(progress);
		inputProgressView2.setProgress(progress);
		inputProgressView3.setProgress(progress);
		String progressStr = "progress = " +progress;
		tvProgress.setText(progressStr);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onClick(View view) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <= 5; i++) {
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					final int progress = i;
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							inputProgressView4.setProgress(progress);
						}
					});
				}
			}
		}).start();
	}
}