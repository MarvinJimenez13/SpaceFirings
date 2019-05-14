package mx.unam;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import mx.unam.MainGame;
import mx.unam.States.GameOver;
import mx.unam.States.Menu;
import mx.unam.States.PlayState;

public class AndroidLauncher extends AndroidApplication{

	AdView mAdView;
	private InterstitialAd interstitialAd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MobileAds.initialize(this, "ca-app-pub-4695862902323733/7738561477");
		final RelativeLayout layout = new RelativeLayout(this);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
	    View gameView = initializeForView(new MainGame(), config);

		layout.addView(gameView);

		final AdView adView = new AdView(this);
		adView.setAdSize(AdSize.SMART_BANNER);
		adView.setAdUnitId("ca-app-pub-4695862902323733/7738561477");

		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);


		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
		);

		layout.addView(adView, adParams);

		setContentView(layout);


		adView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				Log.i("AndroidLauncher", "Ad Loaded...");

			}

			@Override
			public void onAdFailedToLoad(int errorCode) {

			}

			@Override
			public void onAdOpened() {
				// Code to be executed when an ad opens an overlay that
				// covers the screen.
			}

			@Override
			public void onAdLeftApplication() {
				// Code to be executed when the user has left the app.
			}

			@Override
			public void onAdClosed() {
                  layout.removeView(adView);
                  adView.destroy();
			}
		});






	}
}
