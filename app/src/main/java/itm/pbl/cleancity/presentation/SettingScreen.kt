package itm.pbl.cleancity.presentation

import android.graphics.drawable.Icon
import android.provider.CalendarContract.Colors
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShieldMoon
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material.icons.rounded.HeartBroken
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import itm.pbl.cleancity.ui.theme.CardViewColor
import itm.pbl.cleancity.ui.theme.GreenLightWala
import itm.pbl.cleancity.ui.theme.backgroundColormain
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun SettingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            color = backgroundColormain,
            modifier = Modifier.fillMaxWidth()
        ) {
            Settings()
        }
    }
}


@Composable
fun Settings() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 75.dp, bottom = 90.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {


////////////////////////////////////
        Card(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(55.dp),
            colors = CardDefaults.cardColors(
                containerColor = CardViewColor
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 10.dp, start = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                androidx.compose.material3.Icon(
                    Icons.Filled.Person,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )

                Text(text = "Profile Settings", textAlign = TextAlign.Center, fontSize = 20.sp)
                Spacer(modifier = Modifier.weight(0.7f))
                androidx.compose.material3.Icon(
                    Icons.Filled.ArrowForwardIos,
                    contentDescription = "",
                    modifier = Modifier.size(22.dp)
                )
            }
        }

///////////////////////////////////////
        Card(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(55.dp),
            colors = CardDefaults.cardColors(
                containerColor = CardViewColor
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 10.dp, start = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                androidx.compose.material3.Icon(
                    Icons.Filled.ShieldMoon,
                    contentDescription = "",
                    modifier = Modifier.size(22.dp)
                )
                Text(text = "Change Theme", textAlign = TextAlign.Center, fontSize = 20.sp)
                Spacer(modifier = Modifier.weight(0.7f))
                SwitchCase()
            }
        }

///////////////////////////////////
        Card(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(55.dp),
            colors = CardDefaults.cardColors(
                containerColor = CardViewColor
            )

        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 10.dp, start = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                androidx.compose.material3.Icon(
                    Icons.Rounded.ErrorOutline,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
                Text(text = "About Us", textAlign = TextAlign.Center, fontSize = 20.sp)
                Spacer(modifier = Modifier.weight(0.7f))
                androidx.compose.material3.Icon(
                    Icons.Filled.ArrowForwardIos,
                    contentDescription = "",
                    modifier = Modifier.size(22.dp),

                    )
            }
        }

//////////////////////////////////////
        Card(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(55.dp),
            colors = CardDefaults.cardColors(
                containerColor = CardViewColor
            )

        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 10.dp, start = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                androidx.compose.material3.Icon(
                    Icons.Filled.ExitToApp,
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
                Text(text = "Logout", textAlign = TextAlign.Center, fontSize = 20.sp)
                Spacer(modifier = Modifier.weight(0.7f))
                androidx.compose.material3.Icon(
                    Icons.Filled.ArrowForwardIos,
                    contentDescription = "",
                    modifier = Modifier.size(22.dp)
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.8f))
        Row {
            androidx.compose.material3.Icon(imageVector = Icons.Rounded.HeartBroken, contentDescription = "", tint = GreenLightWala)
            Text(text = "   Made For The Hackathon")
        }


    }
}


//////////////////////       FOR SWITCH CASE       /////////////////////////
@Composable
fun SwitchCase() {
    var checked by remember {
        mutableStateOf(true)
    }

    Switch(checked = checked, onCheckedChange = {
        checked = it
    })
}
