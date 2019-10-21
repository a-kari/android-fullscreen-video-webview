package jp.neechan.samplewebkit.utils

object SampleUtils {

    fun getSampleHtml(): String {
        return "<body>\n" +
                "\t\t<h1>Good morning!</h1>\n" +
                "\n" +
                "\t\t<img src = \"https://i.ibb.co/zhPyc52/clouds.jpg\" />\n" +
                "\n" +
                "\t\t<p>It's friday morning of <b>October 18, 2019</b> in Almaty, the weather is 11°C, cloudy.</p>\n" +
                "\t\t<p>The day promises to be good.</p>\n" +
                "\t\t<hr />\n" +
                "\t\t<p>It's <b>5:30 PM</b>, evening in Almaty.</p>\n" +
                "\n" +
                "\t\t<video class=\"videoplayer\" controlslist=\"nodownload\" poster=\"https://cdn.videvo.net/videvo_files/images/preview_TimelapsephotographySunsetF24JamaJamon.jpg\">\n" +
                "\t\t\t<source src=\"https://www.videvo.net/videvo_files/converted/2013_10/videos/TimelapsephotographySunsetF24JamaJamon.mov70745.mp4\" type=\"video/mp4\">\n" +
                "\t\t</video>" +
                "\t</body>"
    }

    fun styleHtml(html: String): String {
        return "<link rel=\"stylesheet\" type=\"text/css\" href=\"default.css\" />$html"
    }
}