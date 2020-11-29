package activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.hfe.R;

import java.io.File;

public class HomePageActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_GALLERY = 10;//图库选取图片标识请求码
    private static final int CROP_PHOTO = 12;// 裁剪图片
    private static final int STORAGE_PERMISSION = 13;// 动态申请存储权限标识请求码

    private Button btn_chooseProfile;
    private Button btn_back;
    private ImageView img_userProfile;
    private TextView tex_userRealName;
    private TextView tex_userAccount;

    private Uri imageUri = null;// 拍照时的图片uri
    private File imageFile = null;//File 对象
    private String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        hide();

        init();

        requestStoragePermission();
    }

    private void gallery() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // 以startActivityForResult的方式启动一个activity用来获取返回的结果
        startActivityForResult(intent, REQUEST_CODE_GALLERY);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case REQUEST_CODE_GALLERY:// 图库选择图片

                    Uri uri = data.getData();// 获取图片的uri

                    Intent intent_gallery_crop = new Intent("com.android.camera.action.CROP");
                    intent_gallery_crop.setDataAndType(uri, "image/*");

                    // 设置裁剪
                    intent_gallery_crop.putExtra("crop", "true");
                    intent_gallery_crop.putExtra("scale", true);
                    // aspectX aspectY 是宽高的比例
                    intent_gallery_crop.putExtra("aspectX", 1);
                    intent_gallery_crop.putExtra("aspectY", 1);
                    // outputX outputY 是裁剪图片宽高
                    intent_gallery_crop.putExtra("outputX", 350);
                    intent_gallery_crop.putExtra("outputY", 350);

                    intent_gallery_crop.putExtra("return-data", false);

                    // 创建文件保存裁剪的图片
                    createImageFile();
                    imageUri = Uri.fromFile(imageFile);

                    if (imageUri != null) {
                        intent_gallery_crop.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                        intent_gallery_crop.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                    }

                    startActivityForResult(intent_gallery_crop, CROP_PHOTO);

                    break;

                case CROP_PHOTO:// 裁剪图片

                    try {

                        if (imageUri != null) {
                            displayImage(imageUri);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
    }


    private void requestStoragePermission() {

        int hasCameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        Log.e("TAG","开始" + hasCameraPermission);
        if (hasCameraPermission == PackageManager.PERMISSION_GRANTED){
            // 拥有权限，可以执行涉及到存储权限的操作
            Log.e("TAG", "你已经授权了该组权限");
        }else {
            // 没有权限，向用户申请该权限
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Log.e("TAG", "向用户申请该组权限");
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == STORAGE_PERMISSION){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // 用户同意，执行相应操作
                Log.e("TAG","用户已经同意了存储权限");
            }else {
                // 用户不同意，向用户展示该权限作用
            }
        }
    }

    private void createImageFile() {

        try{

            if (imageFile != null && imageFile.exists()){
                imageFile.delete();
            }
            // 新建文件
            imageFile = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + "galleryDemo.jpg");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void displayImage(Uri imageUri) {
        try{
            // glide根据图片的uri加载图片
            Glide.with(this)
                    .load(imageUri)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.mipmap.ic_launcher_round)// 占位图设置：加载过程中显示的图片
                    .error(R.mipmap.ic_launcher_round)// 异常占位图
                    .transform(new CenterCrop(this))
                    .into(img_userProfile);
        }catch (Exception e){
            e.printStackTrace();
        }

    }



    //    private void SelectPicture(){
//        // 创建Intent，用于打开手机本地图库选择图片
//        Intent intent1 = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        // 启动intent打开本地图库
//        startActivityForResult(intent1,LOCAL_CROP);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == LOCAL_CROP && resultCode == RESULT_OK){
//            // 创建intent用于裁剪图片
//            Intent intent1 = new Intent("com.android.camera.action.CROP");
//            // 获取图库所选图片的uri
//            Uri uri = data.getData();
//            intent1.setDataAndType(uri,"image/*");
//            //  设置裁剪图片的宽高
//            intent1.putExtra("outputX", 350);
//            intent1.putExtra("outputY", 350);
//            // 裁剪后返回数据
//            intent1.putExtra("return-data", true);
//            // 启动intent，开始裁剪
//            startActivityForResult(intent1, CROP_PHOTO);
//        }
//
//        if(requestCode == CROP_PHOTO && resultCode == RESULT_OK){
//            try{
//                if(data != null){
//                    // 根据返回的data，获取Bitmap对象
//                    Bitmap bitmap = data.getExtras().getParcelable("data");
//                    // 展示图片
//                    img_userProfile.setImageBitmap(bitmap);
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//
//    }

    private void hide(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
            actionBar.hide();
    }

    private void init(){
        btn_chooseProfile = findViewById(R.id.button_HomePage_profileCovered);
        img_userProfile = findViewById(R.id.image_HomePage_profile);
        tex_userAccount = findViewById(R.id.text_homaPage_userAccountAsPhoneNumber);
        tex_userRealName = findViewById(R.id.text_homaPage_userRealName);
        btn_back = findViewById(R.id.button_homePage_navigationBar_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_chooseProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gallery();
            }
        });
    }

}