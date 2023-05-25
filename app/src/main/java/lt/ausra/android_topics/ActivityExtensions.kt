package lt.ausra.android_topics

import android.app.Activity
import android.content.Intent
import android.os.Build

fun Activity.getExtraFromParcelable(result: Intent?, keyName: String): Item? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        result?.getParcelableExtra(
            keyName,
            Item::class.java
        )
    } else {
        result?.getParcelableExtra(keyName)
    }