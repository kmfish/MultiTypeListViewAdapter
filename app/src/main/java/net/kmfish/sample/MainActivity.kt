package net.kmfish.sample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById(R.id.btnListView).setOnClickListener(this)
        findViewById(R.id.btnRecyclerView).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        var clz: Class<*>? = null
        when (v.id) {
            R.id.btnListView -> clz = ListViewActivity::class.java
            R.id.btnRecyclerView -> clz = RecyclerViewActivity::class.java
            else -> {
            }
        }

        startActivity(Intent(this, clz))
    }
}
