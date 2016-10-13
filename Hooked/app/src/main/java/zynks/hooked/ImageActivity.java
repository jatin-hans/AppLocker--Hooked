package zynks.hooked;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ImageActivity extends AppCompatActivity {
    public static final int DONE=1;
    public static final int NEXT=2;
    public static final int PERIOD=1000;
    private Camera camera;
    private int cameraId = 0;
    private ImageView display;
    private Timer1 timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        display=(ImageView)findViewById(R.id.imageView1);
        // we have a camera
        if (!getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Toast.makeText(this, "No camera on this device", Toast.LENGTH_LONG)
                    .show();
        } else {
            cameraId = findFrontFacingCamera();
            if (cameraId < 0) {
                Toast.makeText(this, "No front facing camera found.",
                        Toast.LENGTH_LONG).show();
            } else {
                safeCameraOpen(cameraId);
            }
        }

        //FAKE SURFACE
        SurfaceView view = new SurfaceView(this);
        try {
            camera.setPreviewDisplay(view.getHolder());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        camera.startPreview();
        Camera.Parameters params = camera.getParameters();
        params.setJpegQuality(100);
        camera.setParameters(params);
        // trigger
        timer=new Timer1(getApplicationContext(),threadHandler);
        timer.execute();
    }

    ////////////////////////////////////thread Handler///////////////////////////////////////
    private Handler threadHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch(msg.what){
                case DONE:
                    // Trigger
                    camera.takePicture(null, null, mCall);
                    break;
                case NEXT:
                    timer=new Timer1(getApplicationContext(),threadHandler);
                    timer.execute();
                    break;
            }
        }
    };


    private int findFrontFacingCamera() {
        int cameraId = -1;

        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            CameraInfo info = new CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
                Log.v("MyActivity", "Camera found");
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }
    @Override
    protected void onPause() {
        if (timer!=null){
            timer.cancel(true);
        }
        releaseCamera();
        super.onPause();
    }

    private boolean safeCameraOpen(int id) {
        boolean qOpened = false;
        try {
            releaseCamera();
            camera = Camera.open(id);
            qOpened = (camera != null);
        } catch (Exception e) {
            Log.e(getString(R.string.app_name), "failed to open Camera");
            e.printStackTrace();
        }
        return qOpened;
    }
    private void releaseCamera() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    Camera.PictureCallback mCall = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] data, Camera camera) {


            if (data != null) {
                Bitmap bitmapPicture
                        = BitmapFactory.decodeByteArray(data, 0, data.length);
                display.setImageBitmap(bitmapPicture);


                if(bitmapPicture!=null){
                    File file=new File(Environment.getExternalStorageDirectory()+"/Theif");
                    if(!file.isDirectory()){
                        file.mkdir();
                    }
                    else{
                        file=new File(Environment.getExternalStorageDirectory()+"/Theif",System.currentTimeMillis()+".jpg");
                    }

                    try
                    {
                        FileOutputStream fileOutputStream=new FileOutputStream(file);
                        bitmapPicture.compress(Bitmap.CompressFormat.JPEG,100, fileOutputStream);

                        fileOutputStream.flush();
                        fileOutputStream.close();
                        Toast.makeText(getApplicationContext(), "Picture Saved", Toast.LENGTH_LONG).show();
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                    catch(Exception exception)
                    {
                        exception.printStackTrace();
                    }

                }
                camera.startPreview();

                finish();

            }

        }
    };

}
