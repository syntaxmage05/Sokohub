import android.content.Context
import dev.hotwire.turbo.views.TurboWebView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
fun TurboWebView.runJavaScript(script: String) {
    // Evaluate JavaScript on the main thread
    GlobalScope.launch(Dispatchers.Main) {
        evaluateJavascript(script, null)
    }
}
fun TurboWebView.attachWebBridge(context: Context) {
    val script = context.assets.open("js/bridge.js").use {
        String(it.readBytes())
    }
    runJavaScript(script)
}