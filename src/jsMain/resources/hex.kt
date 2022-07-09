import csstype.Color
import csstype.rgb

fun hex(value: String) : Color {
    val firstHex = listOf(value[1], value[2])
    val secondHex = listOf(value[3], value[4])
    val thirdHex = listOf(value[5], value[6])
    val r: Int = firstHex.joinToString().toInt()
    val g: Int = secondHex.joinToString().toInt()
    val b: Int = thirdHex.joinToString().toInt()

    return rgb(r, g, b)
}