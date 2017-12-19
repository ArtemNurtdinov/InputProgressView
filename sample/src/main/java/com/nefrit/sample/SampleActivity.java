package com.nefrit.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nefrit.inputprogressview.gui.InputProgressView;

public class SampleActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		InputProgressView inputProgressView = findViewById(R.id.input_progress);

		inputProgressView.setProgress(3);
	}
}