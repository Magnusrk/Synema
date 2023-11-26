import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.synema.MainActivity
import io.cucumber.junit.WithJunitRule
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
public class ComposeRuleHolder {
        @get:Rule
        val composeRule = createAndroidComposeRule<MainActivity>()
}