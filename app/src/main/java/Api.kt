import dev.hotwire.turbo.BuildConfig

class Api {
    companion object {
        private val baseUrl = "http://10.0.2.2:3000"
        val rootUrl = "$baseUrl/"
        val loginUrl = "$baseUrl/login"
    }
}
