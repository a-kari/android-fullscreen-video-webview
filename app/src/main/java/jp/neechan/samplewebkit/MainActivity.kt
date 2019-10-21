package jp.neechan.samplewebkit

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import jp.neechan.samplewebkit.utils.SampleUtils
import jp.neechan.samplewebkit.webclients.FullscreenVideoChromeClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), FullscreenVideoChromeClient.ToggleFullscreenCallback {

    private lateinit var chromeClient: FullscreenVideoChromeClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupWebView()
    }

    /** Setup WebView and load some HTML there. */
    private fun setupWebView() {
        chromeClient            = FullscreenVideoChromeClient(webView, fullscreenContainer, R.layout.view_progress, this)
        webView.webChromeClient = chromeClient

        val html = SampleUtils.styleHtml(SampleUtils.getSampleHtml())
        webView.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "UTF-8", null)
    }

    /**
     * Fullscreen mode: force landscape orientation, hide status bar & action bar.
     * Default mode:    force portrait  orientation, show status bar & action bar.
     */
    override fun toggleFullscreen(isFullscreen: Boolean) {
        if (isFullscreen) {
            requestedOrientation                = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            val windowAttributes                = window.attributes
            windowAttributes.flags              = windowAttributes.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
            windowAttributes.flags              = windowAttributes.flags or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
            window.attributes                   = windowAttributes
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE
            supportActionBar?.hide()

        } else {
            requestedOrientation                = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            val windowAttributes                = window.attributes
            windowAttributes.flags              = windowAttributes.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN.inv()
            windowAttributes.flags              = windowAttributes.flags and WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON.inv()
            window.attributes                   = windowAttributes
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            supportActionBar?.show()
        }
    }

    /** Release the chromeClient's resources. */
    override fun onDestroy() {
        super.onDestroy()
        chromeClient.destroy()
    }
}
