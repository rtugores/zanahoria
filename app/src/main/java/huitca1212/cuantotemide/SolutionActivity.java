package huitca1212.cuantotemide;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class SolutionActivity extends OptionsActivity implements View.OnClickListener {
	private static final String FINAL_SIZE_ARG = "FinalSizeArg";
	private ImageView image;
	private TextView sizeText;
	private TextView solutionText;
	private Button homeButton;
	private Button shareButton;
	private float sizeShare;

	public static void startActivity(Activity activity, String countrySelected) {
		Intent intent = new Intent(activity, SolutionActivity.class);
		Bundle b = new Bundle();
		b.putString(FINAL_SIZE_ARG, countrySelected);
		intent.putExtras(b);
		activity.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_solution);

		image = (ImageView)findViewById(R.id.final_photo);
		sizeText = (TextView)findViewById(R.id.size_text);
		solutionText = (TextView)findViewById(R.id.solution_text);
		homeButton = (Button)findViewById(R.id.home_button);
		shareButton = (Button)findViewById(R.id.share_button);

		homeButton.setOnClickListener(this);
		shareButton.setOnClickListener(this);
		Utils.setAnalytics(this);

		sizeShare = Float.parseFloat(getIntent().getExtras().getString(FINAL_SIZE_ARG));
		sizeText.setText(String.format(getString(R.string.solution_size_text), sizeShare));

		if (sizeShare >= 13.79) {
			setSolutionParameters(R.drawable.solution_bigger, R.string.solution_bigger_text, R.raw.applause);
		} else {
			setSolutionParameters(R.drawable.solution_smaller, R.string.solution_smaller_text, R.raw.boo);
		}
	}

	private void setSolutionParameters(int solutionDrawableId, int solutionTextId, int solutionRawId) {
		MediaPlayer mpRes;
		image.setImageResource(solutionDrawableId);
		solutionText.setText(solutionTextId);
		mpRes = MediaPlayer.create(getApplicationContext(), solutionRawId);
		mpRes.start();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.home_button) {
			onHomeButtonClicked();
		} else if (id == R.id.share_button) {
			onShareButtonClicked();
		}
	}

	private void onHomeButtonClicked() {
		MainActivity.startActivity(SolutionActivity.this);
		finish();
	}

	private void onShareButtonClicked() {
		final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
		intent.setType("text/plain");
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
		} else {
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
		}
		intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject));
		intent.putExtra(Intent.EXTRA_TEXT, String.format(Locale.getDefault(), getString(R.string.share_text), sizeShare));
		startActivity(Intent.createChooser(intent, getString(R.string.share_chooser)));
	}
}