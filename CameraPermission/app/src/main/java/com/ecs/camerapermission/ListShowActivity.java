package com.ecs.camerapermission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ListShowActivity extends AppCompatActivity {

    private static final int OPEN_CAMERA_PERMISSON = 100 ;
    private static final int REQUEST_IMAGE_CAPTURE = 200;
    private Button btn_Next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_show);
        btn_Next = (Button)findViewById(R.id.btn_next);
    }


    public void captureCameraImage(View view)
    {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
          //  openCamera();
            return;
        }

        int permissionCheck = ContextCompat.checkSelfPermission(ListShowActivity.this, Manifest.permission.CAMERA);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED)
        {
            boolean flag = ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA);
            if(flag)
            {
                openCamera();
            }else
            {
                Toast.makeText(this, "OPEN CAMERA PERMISSION ALREADY ENABLED", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(ListShowActivity.this,new String[]{Manifest.permission.CAMERA},OPEN_CAMERA_PERMISSON);
            }
        }else
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},OPEN_CAMERA_PERMISSON);
        }
    }

    public void openCamera()
    {
        Toast.makeText(this, "CAMERA PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager())!= null)
        {
            startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode)
        {
            case OPEN_CAMERA_PERMISSON:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    openCamera();
                }
                else
                {
                    Toast.makeText(this, "CAMERA PERMISSON DENIED", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case REQUEST_IMAGE_CAPTURE:
                if(resultCode == RESULT_OK)
                {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = null;
                    if(extras!= null)
                    {
                        imageBitmap = (Bitmap)extras.get("data");
                    }
                    ImageView mImageView = (ImageView) findViewById(R.id.captureImage);
                    mImageView.setImageBitmap(imageBitmap);
                    mImageView.setVisibility(View.VISIBLE);
                }else
                {
                    Toast.makeText(this, "Not able to Capture Image", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
