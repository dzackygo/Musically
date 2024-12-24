package com.example.musicfy.ui.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musically.R
import com.example.musically.model.Musically

@Composable
fun MusicallyItem(
    musically: Musically,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        modifier = modifier.fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row {
            Column(modifier = Modifier.padding(8.dp)) {
                Image(
                    painter = painterResource(musically.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(120.dp)
                )
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Row {
                    Text(
                        text = musically.title,
                        maxLines = 2,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Row {
                    Text(
                        text = musically.singers
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = UI_MODE_NIGHT_YES)
fun MusicallyItemPreview() {
    MaterialTheme {
        MusicallyItem(
            musically = Musically(2,"Sleep Well",
                "d4vd", R.drawable.d4vd, "30.964.242 Pendengar", "Singer and songwriter d4vd (pronounced David) is known for his moody, bedroom-style indie pop and R&B."),
            modifier = Modifier.padding(8.dp)
        )
    }
}