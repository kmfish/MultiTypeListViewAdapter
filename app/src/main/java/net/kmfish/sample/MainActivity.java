package net.kmfish.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnListView).setOnClickListener(this);
        findViewById(R.id.btnRecyclerView).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Class clz = null;
        switch (v.getId()) {
            case R.id.btnListView:
                clz = ListViewActivity.class;
                break;
            case R.id.btnRecyclerView:
                clz = RecyclerViewActivity.class;
                break;
            default:
                break;
        }

        startActivity(new Intent(this, clz));
    }
}
