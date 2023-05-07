package lt.ausra.android_topics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import timber.log.Timber

class MainActivity : ActivityLifeCycles() {

    lateinit var openButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timber("onCreate")

        openButton = findViewById(R.id.openButton)

        openButton.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}