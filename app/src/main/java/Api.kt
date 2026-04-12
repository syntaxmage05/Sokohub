import dev.hotwire.turbo.BuildConfig

class Api {
    companion object {
        private val baseUrl = if(BuildConfig.DEBUG)
            "http://10.0.2.2:3000"
        else
            "https://extravagantly-kindredless-lavonne.ngrok-free.dev"
        val rootUrl = "$baseUrl/"
        val profileUrl = "$baseUrl/profile"
        val loginUrl = "$baseUrl/login"
        val myAdsUrl = "$baseUrl/my_listings"
    }
}
