import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage

@Composable
fun MoviePosterFrame(arrangement : Arrangement.Vertical, img : String, offsetX : Dp = 0.dp, offsetY : Dp = 0.dp, zindex : Float = 0f){
    Column(
        verticalArrangement = arrangement,
        modifier = Modifier.fillMaxHeight().offset(x=offsetX, y=offsetY).zIndex(zindex),

        ) {

        Card(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .size(width = 105.dp, height = 158.dp)

        ){
            AsyncImage(
                model = img,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}