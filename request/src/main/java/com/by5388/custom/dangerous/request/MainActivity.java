package com.by5388.custom.dangerous.request;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_DANGEROUS_PERMISSION = 100;
    private static final String ACTION_TO_NORMAL = "com.by5388.custom.permission.action.normal";
    private static final String ACTION_TO_DANGEROUS = "com.by5388.custom.permission.action.dangerous";
    private static final String CUSTOM_PERMISSION_DANGEROUS = "com.by5388.custom.permission.dangerous";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_to_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNormalActivity();
            }
        });

        findViewById(R.id.button_to_dangerous).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDangerousActivity();
            }
        });
    }

    private void toNormalActivity() {
        try {
            final Intent intent = new Intent(ACTION_TO_NORMAL);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "没有找到该应用", Toast.LENGTH_SHORT).show();
        }
    }


    private void toDangerousActivity() {
        final int permission = checkSelfPermission(CUSTOM_PERMISSION_DANGEROUS);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            try {
                Intent intent = new Intent(ACTION_TO_DANGEROUS);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "没有找到该应用", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        requestPermissions(new String[]{CUSTOM_PERMISSION_DANGEROUS}, REQUEST_CODE_DANGEROUS_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_DANGEROUS_PERMISSION) {
            boolean getPermission = true;
            for (int result : grantResults) {
                if (PackageManager.PERMISSION_GRANTED != result) {
                    getPermission = false;
                    break;
                }
            }

            if (getPermission) {
                toDangerousActivity();
            } else {
                Toast.makeText(this, "没有获得相应的权限,请重试", Toast.LENGTH_SHORT).show();
                // TODO: 2019/9/23 打开该应用设置相关的界面
            }
        }
    }
}
