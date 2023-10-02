import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MoviePosterFrame(arrangement : Arrangement.Vertical, img : String){
    Column(
        verticalArrangement = arrangement,
        modifier = Modifier.fillMaxHeight(),

        ) {

        Card(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .size(width = 106.dp, height = 161.dp)

        ){
            AsyncImage(
                model = img,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}