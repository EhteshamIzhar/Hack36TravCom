package com.android.parii.travcom;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.IOException;
import java.io.InputStream;


public class VrActivity extends AppCompatActivity {

    private VrPanoramaView mVrPanoramaView;
    //ImageButton b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr);


        mVrPanoramaView = (VrPanoramaView) findViewById(R.id.pano_view);

        loadPhotoSphere();
    }

    private void loadPhotoSphere() {
        //This could take a while. Should do on a background thread, but fine for current example
        VrPanoramaView.Options options = new VrPanoramaView.Options();
        InputStream inputStream = null;

        AssetManager assetManager = getAssets();

        try {
            inputStream = assetManager.open("room.jpg");
            options.inputType = VrPanoramaView.Options.TYPE_MONO;
            mVrPanoramaView.loadImageFromBitmap(BitmapFactory.decodeStream(inputStream), options);
            inputStream.close();
        } catch (IOException e) {
            Log.e("Tuts+", "Exception in loadPhotoSphere: " + e.getMessage() );
        }
    }


    @Override
    protected void onPause() {
        mVrPanoramaView.pauseRendering();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVrPanoramaView.resumeRendering();
    }

    @Override
    protected void onDestroy() {
        mVrPanoramaView.shutdown();
        super.onDestroy();
    }


}
