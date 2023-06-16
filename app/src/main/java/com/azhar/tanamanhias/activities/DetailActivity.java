package com.azhar.tanamanhias.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhar.tanamanhias.R;
import com.azhar.tanamanhias.model.ModelMain;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    public static final String DETAIL_TANAMAN = "DETAIL_TANAMAN";
    String strNamaTanaman, strManfaatTanaman;
    ModelMain modelMain;
    Toolbar toolbar;
    ImageView imageTanaman;
    TextView tvNamaTanaman, tvManfaatTanaman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //set transparent statusbar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        toolbar = findViewById(R.id.toolbar);
        imageTanaman = findViewById(R.id.imageTanaman);
        tvNamaTanaman = findViewById(R.id.tvNamaTanaman);
        tvManfaatTanaman = findViewById(R.id.tvManfaatTanaman);

        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //get data intent
        modelMain = (ModelMain) getIntent().getSerializableExtra(DETAIL_TANAMAN);
        if (modelMain != null) {
            strNamaTanaman = modelMain.getNama();
            strManfaatTanaman = modelMain.getDeskripsi();

            Glide.with(this)
                    .load(modelMain.getImage())
                    .into(imageTanaman);

            tvNamaTanaman.setText(strNamaTanaman);
            tvManfaatTanaman.setText(strManfaatTanaman);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (on) {
            layoutParams.flags |= bits;
        } else {
            layoutParams.flags &= ~bits;
        }
        window.setAttributes(layoutParams);
    }

}