import csstype.Color
import csstype.HtmlAttributes
import csstype.rgb

fun hex(value: String) : Color {
    val r: Int = value.substring(1, 3).toInt(16)
    val g: Int = value.substring(3, 5).toInt(16)
    val b: Int = value.substring(5, 7).toInt(16)

    return rgb(r, g, b)
}