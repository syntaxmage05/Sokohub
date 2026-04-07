import android.webkit.JavascriptInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import android.util.Log
import com.example.sokohub.SessionNavHostFragment

class WebBridgeMessageHandler(
    private val fragment: SessionNavHostFragment
) {
    @JavascriptInterface
    fun post(message: String) {
        val json = JSONObject(message)
        val webBridgeMessage = WebBridgeMessage(
            json["type"] as String,
            json["data"] as JSONObject
        )
        if (webBridgeMessage.type == "render") {
            GlobalScope.launch(Dispatchers.Main) {
                render(webBridgeMessage.data)
            }
        }
    }
    private fun render(element: JSONObject) {
        if (element["component"] == "menu_item") {
            this.fragment.currentNavDestination
                .toolbarForNavigation()?.menu?.removeItem(
                    (element["id"] as String).toInt()
                )
            this.fragment.currentNavDestination
                .toolbarForNavigation()?.menu?.add(
                    0,
                    (element["id"] as String).toInt(),
                    (element["order"] as String).toInt(),
                    element["title"] as String
                )
        } else {
            Log.d("Web Bridge", "Unknown component: $element")
        }
    }
}