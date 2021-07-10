import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    val heightMap: BufferedImage = ImageIO.read(File("hm.png"))
    val diffuseMap: BufferedImage = ImageIO.read(File("dm.jpg"))

    val width = heightMap.width
    val height = heightMap.height
    val pixels = ArrayList<PixelInfo>()

    for(x in 0 until width){
        for(y in 0 until height){
            val rgb = heightMap.getRGB(x,y)
            val b = Color(rgb).blue
            if(b < 2){
                val color = Color(2,5,20)
                diffuseMap.setRGB(x,y,color.rgb)
            }else{
                pixels.add(PixelInfo(x,y,rgb))
            }
        }
    }

    for(wl in 0 until 255 step 5){
        for(i in pixels.indices){
            if(i < pixels.size){
                if(Color(pixels[i].rgb).blue < wl){
                    diffuseMap.setRGB(pixels[i].x, pixels[i].y, Color(2,5,20).rgb)
                    pixels.remove(pixels[i])
                }
            }
        }
        println("Saving $wl" + "th image...")
        ImageIO.write(diffuseMap,"png", File("image_$wl.png"))
    }
    println("Done!")
}