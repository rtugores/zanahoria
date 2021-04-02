package huitca1212.cuantotemide

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent

class SplashActivity : Activity() {

    private var active = true
    private var splashTime = 2000

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashTread: Thread = object : Thread() {
            override fun run() {
                try {
                    var waited = 0
                    while (active && waited < splashTime) {
                        sleep(100)
                        if (active) {
                            waited += 100
                        }
                    }
                } catch (e: InterruptedException) {
                    // do nothing
                } finally {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
        splashTread.start()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            active = false
        }
        return true
    }
}
