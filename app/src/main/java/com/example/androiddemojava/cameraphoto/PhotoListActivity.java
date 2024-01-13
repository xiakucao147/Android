package com.example.androiddemojava.cameraphoto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androiddemojava.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

public class PhotoListActivity extends AppCompatActivity {
    private List<Photo> photos=new ArrayList<Photo>();
    private ListView photoList;
    private PhotoAdapter photoAdapter;
    private ImageButton btnAddPhoto;
    private final static int REQUEST_CAMERA=200;
    private final static int IMAGE_REQUEST_CODE=300;
    private String photoPath,bitmapPath;
    PhotoDAO photoDAO=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);
        photoList=findViewById(R.id.photoList);
        btnAddPhoto=findViewById(R.id.btn_add_photo);
        photoDAO=new PhotoDAO(this);
        initPhoto();
        btnAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPic();
            }
        });
        photoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Photo photo=photos.get(i);
                Toast.makeText(PhotoListActivity.this,i+":"+photo.getFileName(),Toast.LENGTH_SHORT).show();
            }
        });
        photoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Photo p=photos.get(i);
                AlertDialog.Builder myDialog=new AlertDialog.Builder(PhotoListActivity.this);
                myDialog.setTitle("提示");
                myDialog.setIcon(R.mipmap.icon_message);
                myDialog.setMessage("确定删除"+ p.getFileName()+"?");
                myDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        photos.remove(p);//删除集合对象
                        photoAdapter.notifyDataSetChanged();//适配器更新数据
                    }
                });
                myDialog.setNegativeButton("取消",null);
                myDialog.show();

                return true;//返回false会认为长按后放开的那一下为点击
            }
        });
    }
        public String getImageFile(){
        //用时间生成照片名称
        String path=getFilesDir().getPath();//得到APP私有存储路径
        DateFormat sdf=new SimpleDateFormat("yyyyMMdd_hhmmss");
        String mDate=sdf.format(new Date());
        String mFilePath=path+"/"+mDate+".jpg";
        return mFilePath;
    }
    public void takeCamer(){
        //打开相机
        Intent intent=new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        String personalPathAndName=getImageFile();//得到app私有文件存储路径，生成包含图片名称的路径
        File file=new File(personalPathAndName);//生成文件
        Uri uri= FileProvider.getUriForFile(this,"com.cqjtu.xiakucao.androiddemojava.provider",file);//生成文件的uri
        photoPath=uri.toString();
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,REQUEST_CAMERA);
    }
    public void getPhoto(){
        //跳转到手机相册
        Intent intent=new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,IMAGE_REQUEST_CODE);
    }
    public void addPic(){//添加图片
        CharSequence[] items={"拍照","图库"};
        AlertDialog.Builder myDialog=new AlertDialog.Builder(this);
        myDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        takeCamer();
                        break;
                    case 1:
                        getPhoto();
                        break;
                }
            }
        });
        myDialog.show();
    }
    public Bitmap imageZip(Uri uri){
        //压缩uri指向的图片，返回位图
        Bitmap bitmap=null;
        InputStream is=null;
        try {
            is=getContentResolver().openInputStream(uri);
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inJustDecodeBounds=false;
            options.inSampleSize=5;
            bitmap=BitmapFactory.decodeStream(is,null,options);
            return bitmap;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        }

    }
    public void initPhoto() {//目前需求是，存储图片到指定路径，然后生成bitmap显示
        //photoDAO.delete(5);

        photos.clear();
        for(Photo p:photoDAO.findPhoto()){
            String uriS=p.getFilePath();
            System.out.println(uriS);
            //Bitmap b=imageZip(Uri.parse(uriS));
            //p.setBitmap(b);
            photos.add(p);
        }
        System.out.println(photos);
        photoAdapter=new PhotoAdapter(this,photos);
        photoList.setAdapter(photoAdapter);



//        photos.clear();
//        Photo p1=new Photo();
//        p1.setInfo("玲芽之旅");
//        p1.setFileName("草太");
//        Bitmap b1=BitmapFactory.decodeResource(getResources(),R.mipmap.caotai);//图片变成位图
//        p1.setBitmap(b1);
//        photos.add(p1);
//        Photo p2=new Photo();
//        p2.setInfo("玲芽之旅");
//        p2.setFileName("玲芽");
//        Bitmap b2=BitmapFactory.decodeResource(getResources(),R.mipmap.lingya);
//        p2.setBitmap(b2);
//        photos.add(p2);
//        photoAdapter=new PhotoAdapter(this,photos);
//        photoList.setAdapter(photoAdapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        Photo photo =new Photo();
        //Toast.makeText(PhotoListActivity.this,filePath,Toast.LENGTH_SHORT).show();
        //System.out.println(filePath);
        PhotoDAO photoDAO=new PhotoDAO(this);
        Uri selectedImageURI=null;

        if (requestCode == REQUEST_CAMERA &&resultCode == RESULT_OK) //请求码是获得照片
        {
            photo.setFilePath(photoPath);
            selectedImageURI = data.getData();//获取系统返回的照片的Uri
            photoPath = selectedImageURI.toString();
        }
        else if (requestCode == IMAGE_REQUEST_CODE &&resultCode == RESULT_OK)
        {
            selectedImageURI = data.getData();//获取系统返回的照片的Uri
            photoPath = selectedImageURI.toString();
            Toast.makeText(PhotoListActivity.this,photoPath,Toast.LENGTH_SHORT).show();
            //System.out.println(photoPath);
            photo.setFilePath(photoPath);//uri
        }
            //从URI中获得图片
                View infoInputView = View.inflate(this, R.layout.dialog_get_photo, null);
                final EditText name_edit = infoInputView.findViewById(R.id.photo_name);
                final EditText info_edit = infoInputView.findViewById(R.id.photo_describe);
                //File file = new File(filePath);
//                Uri uri = FileProvider.getUriForFile(PhotoListActivity.this.getApplicationContext(),
//                        "com.cqjtu.xiakucao.androiddemojava.provider", file);
                try {
                    final Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImageURI));

                info_edit.setHint("信息");
                AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
                myDialog.setTitle("描述一下");
                myDialog.setIcon(R.mipmap.icon_message);
                myDialog.setView(infoInputView);
                myDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //ImageFileAccess imageFileAccess=new ImageFileAccess(PhotoListActivity.this);
                        //System.out.println(photoPath);
                        //imageFileAccess.saveImageFile(filePath,bitmap);
                        String name = name_edit.getText().toString();
                        String info = info_edit.getText().toString();
                        Photo photo = new Photo();
                        photo.setFileName(name);
                        photo.setBitmap(bitmap);
                        photo.setInfo(info);

                        photo.setFilePath(photoPath);
                        photos.add(photo);
                        photoAdapter.notifyDataSetChanged();
                        System.out.println(photo.getFilePath());
                        long i = photoDAO.insert(photo);//保存图片信息到数据库
                        //利用数据库数据刷新
                        photos = photoDAO.findPhoto();
                        initPhoto();
                        //Toast.makeText(PhotoListActivity.this,"")
                    }
                });
                myDialog.setNegativeButton("取消", null);
                myDialog.show();

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }


}
