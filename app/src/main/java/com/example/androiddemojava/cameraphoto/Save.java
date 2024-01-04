//package com.example.androiddemojava.cameraphoto;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.content.FileProvider;
//
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.ListView;
//
//import com.example.androiddemojava.Manifest;
//import com.example.androiddemojava.R;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.net.URI;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//public class PhotoListActivity extends AppCompatActivity {
//    private ImageView btn_camera;
//    private List<Photo> photos=new ArrayList<Photo>();
//    private ListView photoList;
//    private PhotoAdapter photoAdapter;
//    private final static int REQUEST_CAMERA=200;
//    private final static int IMAGE_REQUEST_CODE=300;
//    private String filePath;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_photo_list);
//        btn_camera=findViewById(R.id.btn_camera);
//        photoList=findViewById(R.id.photoList);
//        initPhoto("");
//        //    askPermission();
//        btn_camera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //            addPic();
//            }
//        });
//        photoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent=new Intent();
//                intent.setClass(PhotoListActivity.this, PhotoShowActivity.class);
//                intent.putExtra("position",i);
//                startActivity(intent);
//            }
//        });
//        photoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Photo p=photos.get(i);
//                AlertDialog.Builder myDialog=new AlertDialog.Builder(PhotoListActivity.this);
//                return false;
//            }
//        });
//    }
//
//    public void initPhoto(String key) {//从数据库取出以前保存的照片加载到ListView
//        photos.clear();
//        PhotoDAO dao=new PhotoDAO(this);
//        for(Photo p:dao.findPhoto(key)){
//            String uriS=p.getFilePath();
//
//            Bitmap b=imageZip(Uri.parse(uriS));
//            p.setBitmap(b);
//            photos.add(p);
//        }
//        photoAdapter=new PhotoAdapter(this,photos);
//        photoList.setAdapter(photoAdapter);
//    }
//
//    public Bitmap imageZip(Uri uri){
//        //压缩uri指向的图片，返回位图
//        Bitmap bitmap=null;
//        InputStream is=null;
//        try {
//            is=getContentResolver().openInputStream(uri);
//            BitmapFactory.Options options=new BitmapFactory.Options();
//            options.inJustDecodeBounds=false;
//            options.inSampleSize=5;
//            bitmap=BitmapFactory.decodeStream(is,null,options);
//            return bitmap;
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//
//        }
//
//    }
//    public void addPic(){//添加图片
//        CharSequence[] items={"拍照","图库"};
//        AlertDialog.Builder myDialog=new AlertDialog.Builder(this);
//        myDialog.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                switch (i){
//                    case 0:
//                        takeCamer();
//                        break;
//                    case 1:
//                        getPhoto();
//                        break;
//                }
//            }
//        });
//    }
//    public void askPermission(){
//        String[] permissions={
//                "android.permission.WRITE_EXTERNAL_STORAGE",
//        };
//        int requestCode=200;
//        requestPermissions(permissions,requestCode);
//    }
//    public void takeCamer(){
//        //打开相机
//        Intent intent=new Intent();
//        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//        String photo_path=getImageFile();
//        File file=new File(photo_path);
//        Uri uri= FileProvider.getUriForFile(this,"com.example.androiddemojava.provider",file);
//        filePath=uri.toString();
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
//        startActivityForResult(intent,REQUEST_CAMERA);
//    }
//    public void getPhoto(){
//        //跳转到手机相册
//        Intent intent=new Intent(Intent.ACTION_PICK,
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent,IMAGE_REQUEST_CODE);
//    }
//    public String getImageFile(){
//        //用时间生成照片名称
//        String path=getFilesDir().getPath();//得到APP私有存储路径
//        DateFormat sdf=new SimpleDateFormat("yyyyMMdd_hhmmss");
//        String mDate=sdf.format(new Date());
//        String mFilePath=path+"/"+mDate+".jpg";
//        return mFilePath;
//    }
//    public void delete(URI uri){
//        String path=uri.getPath();
//        File file=new File(uri.getPath());
//        file.delete();
//        if(file.exists()){
//            if(file.delete()){
//                System.out.println("file Deleted :"+uri.getPath());
//            }else{
//                System.out.println("file Not Deleted :"+uri.getPath());
//            }
//        }
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CAMERA) {//请求码是获得照片
//            if (resultCode == RESULT_OK) {//从URI中获得图片
//                View infoInputView = View.inflate(this, R.layout.mydialog, null);
//                final EditText name_edit = infoInputView.findViewById(R.id.pName);
//                final EditText info_edit = infoInputView.findViewById(R.id.pInfo);
//                File file = new File(filePath);
//                Uri uri = FileProvider.getUriForFile(CameraListActivity.this.getApplicationContext(),
//                        "com.cqjtu.chenhe.rememberpassword.provider", file);
//                try {
//                    final Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
//                    info_edit.setHint("信息");
//                    AlertDialog.Builder myDialog = new AlertDialog.Builder(CameraListActivity.this);
//                    myDialog.setTitle("描述一下");
//                    myDialog.setIcon(R.drawable.notes);
//                    myDialog.setView(infoInputView);
//                    myDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            String name = name_edit.getText().toString();
//                            String info = info_edit.getText().toString();
//                            Photo photo = new Photo();
//                            photo.setFileName(name);
//                            photo.setBitmap(bitmap);
//                            photo.setInfo(info);
//                            photo.setFilePath(filePath);
//                            long i = photoDAO.insert(photo);//保存图片信息到数据库
//                            //利用数据库数据刷新
//                            photos = photoDAO.findPhoto();
//                            initPhoto(photos);
//                        }
//                    });
//                    myDialog.setNegativeButton("取消", null);
//                    myDialog.show();
//
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}