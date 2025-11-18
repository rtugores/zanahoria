package huitca1212.cuantotemide.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

/**
 * A lifecycle-aware Composable wrapper for Google Mobile Ads Banner.
 * Properly handles pause/resume/destroy lifecycle events to prevent memory leaks.
 *
 * @param adUnitId The AdMob ad unit ID
 * @param modifier Modifier to be applied to the ad view
 */
@Composable
fun BannerAdView(
    adUnitId: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val adView = remember {
        AdView(context).apply {
            setAdSize(AdSize.BANNER)
            this.adUnitId = adUnitId
        }
    }

    AndroidView(
        factory = { adView },
        modifier = modifier,
        update = { view ->
            // Load ad on initial composition
            if (view.adUnitId == adUnitId) {
                view.loadAd(AdRequest.Builder().build())
            }
        }
    )

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    adView.resume()
                }

                Lifecycle.Event.ON_PAUSE -> {
                    adView.pause()
                }

                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            adView.destroy()
        }
    }
}
