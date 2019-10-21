package jp.neechan.samplewebkit.webclients

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView

/** An inheritor of the default WebChromeClient, which allows to play a video in the fullscreen mode. */
class FullscreenVideoChromeClient(
        private var webView:                  WebView?,                  // A WebView which shows whole web content in the default mode.
        private var fullscreenContainer:      ViewGroup?,                // A video container which will be shown in the fullscreen mode.
        private val progressViewResource:     Int?,                      // A resource of progress view.
        private var toggleFullscreenCallback: ToggleFullscreenCallback?) // Activity's callback, where fullscreen toggling can be handled.
        : WebChromeClient() {

    private var progressView:            View?               = null // ProgressBar to show the progress.
    private var fullscreenVideoView:     View?               = null // A video player, will be initialized on video showing in the fullscreen mode.
    private var fullscreenVideoCallback: CustomViewCallback? = null // Video player's callback.

    /** A callback to handle toggling of the fullscreen mode. E.g. your activity can show/hide the action bar here. */
    interface ToggleFullscreenCallback {
        fun toggleFullscreen(isFullscreen: Boolean)
    }

    init {
        if (progressViewResource != null) {
            progressView = LayoutInflater.from(webView!!.context).inflate(progressViewResource, null)
        }
    }

    override fun onShowCustomView(view: View?, requestedOrientation: Int, callback: CustomViewCallback?) {
        onShowCustomView(view, callback)
    }

    /** Called when the user clicks a video player's fullscreen button. Here we're switching to the fullscreen mode. */
    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        super.onShowCustomView(view, callback)

        // Do not switch to the fullscreen mode again if already done.
        if (fullscreenVideoView != null) {
            callback?.onCustomViewHidden()
            return
        }

        fullscreenVideoView     = view
        fullscreenVideoCallback = callback
        fullscreenContainer!!.addView(fullscreenVideoView)

        webView!!.visibility             = GONE
        fullscreenContainer!!.visibility = VISIBLE

        toggleFullscreenCallback?.toggleFullscreen(true)
    }

    /** Called when the user clicks a video player's exit fullscreen button. Here we're returning back to the default mode. */
    override fun onHideCustomView() {
        super.onHideCustomView()
        if (fullscreenVideoView != null) {
            fullscreenVideoCallback?.onCustomViewHidden()
            fullscreenContainer!!.removeView(fullscreenVideoView)

            fullscreenContainer!!.visibility = GONE
            webView!!.visibility             = VISIBLE
            fullscreenVideoView              = null
            fullscreenVideoCallback          = null
        }

        toggleFullscreenCallback?.toggleFullscreen(false)
    }

    /** Get progress view, which will be shown on loading a video. */
    override fun getVideoLoadingProgressView(): View? {
        return progressView
    }

    /** Set all fields to null. Call this method in your activity's onDestroy() to avoid memory leaks. */
    fun destroy() {
        webView                 = null
        fullscreenContainer     = null
        progressView            = null
        fullscreenVideoView     = null
        fullscreenVideoCallback = null
    }
}