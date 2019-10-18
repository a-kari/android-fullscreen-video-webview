package jp.neechan.samplewebkit.utils

object SampleUtils {

    fun getSampleHtml(): String {
        return "<body>\n" +
                "\t\t<h1>Good morning!</h1>\n" +
                "\n" +
                "\t\t<img src = \"https://media.wired.com/photos/5c1ae77ae91b067f6d57dec0/master/pass/Comparison-City-MAIN-ART.jpg\" width = \"90%\" />\n" +
                "\n" +
                "\t\t<p>It's friday morning of <b>October 18, 2019</b> in Almaty, the weather is 11°C, cloudy.</p>\n" +
                "\t\t<p>The day promises to be good.</p>\n" +
                "\t</body>"
    }

    fun styleHtml(html: String): String {
        return "<link rel=\"stylesheet\" type=\"text/css\" href=\"default.css\" />$html"
    }
}