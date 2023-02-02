package com.zhy.myapplication;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements View.OnClickListener {
    private TextView down;
    private TextView progress;
    private TextView file_name;
    private ProgressBar pb_update;
    private DownloadManager downloadManager;
    private DownloadManager.Request request;
    public static String downloadUrl = "http://ucdl.25pp.com/fs08/2017/01/20/2/2_87a290b5f041a8b512f0bc51595f839a.apk";
    Timer timer;
    long id;
    TimerTask task;
    @SuppressLint("HandlerLeak")
    Handler handler =new Handler(){
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int pro = bundle.getInt("pro");
            String name  = bundle.getString("name");
            pb_update.setProgress(pro);
            progress.setText(String.valueOf(pro)+"%");
            file_name.setText(name);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        down = (TextView) findViewById(R.id.down);
        progress = (TextView) findViewById(R.id.progress);
        file_name = (TextView) findViewById(R.id.file_name);
        pb_update = (ProgressBar) findViewById(R.id.pb_update);
        down.setOnClickListener(this);
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        request = new DownloadManager.Request(Uri.parse(downloadUrl));

        request.setTitle("大象投教");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        request.setAllowedOverRoaming(false);
        request.setMimeType("application/vnd.android.package-archive");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //创建目录
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdir() ;

        //设置文件存放路径
        request.setDestinationInExternalPublicDir(  Environment.DIRECTORY_DOWNLOADS  , "app-release.apk" ) ;
        pb_update.setMax(100);
        final  DownloadManager.Query query = new DownloadManager.Query();
        timer = new Timer();
        task = new TimerTask() {
            @SuppressLint("Range")
            @Override
            public void run() {
                Cursor cursor = downloadManager.query(query.setFilterById(id));
                if (cursor != null && cursor.moveToFirst()) {
                    if (cursor.getInt(
                            cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                        pb_update.setProgress(100);
                        install(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/app-release.apk" );
                        task.cancel();
                    }
                    String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
                    String address = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    int bytes_downloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                    int bytes_total = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                    int pro =  (bytes_downloaded * 100) / bytes_total;
                    Message msg =Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putInt("pro",pro);
                    bundle.putString("name",title);
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                }
                assert cursor != null;
                cursor.close();
            }
        };
        timer.schedule(task, 0,1000);
    }

    @Override
    public void onClick(View v) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == 0) {
            id = downloadManager.enqueue(request);
        }

        task.run();
        down.setClickable(false);
        down.setText("正在下载中");
        down.setEnabled(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                task.run();
                down.setClickable(false);
                down.setText("正在下载中");
                down.setEnabled(false);
            } else {
                Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void install(String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + path), "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//4.0以上系统弹出安装成功打开界面
        startActivity(intent);
    }
}