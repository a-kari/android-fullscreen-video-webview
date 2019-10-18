package jp.neechan.samplewebkit

import android.os.Bundle
import android.webkit.WebChromeClient
import androidx.appcompat.app.AppCompatActivity
import jp.neechan.samplewebkit.utils.SampleUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupWebView()
    }

    private fun setupWebView() {
        webView.webChromeClient       = WebChromeClient()
        val settings                  = webView.settings
        settings.loadWithOverviewMode = true
        settings.useWideViewPort      = true

        val html = SampleUtils.styleHtml(SampleUtils.getSampleHtml())
        webView.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "UTF-8", null)
    }
}
