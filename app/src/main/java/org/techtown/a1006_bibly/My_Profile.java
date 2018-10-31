package org.techtown.a1006_bibly;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class My_Profile extends AppCompatActivity {

    //requestCode 선택한 사진에 대한 요청 값 구분 용도
    private final int CAMERA_CODE=1111;
    private final int GALLERY_CODE=1112;
    private final int IMAGE_CROP = 1113;

    private Uri photoUri , albumUri;
    private String currentPhotoPath;//파일경로
    String mlmageCaptureName;//이미지 이름

    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__profile);

        imageButton = (ImageButton) findViewById(R.id.profile_img);

        imageButton.setBackground(new ShapeDrawable(new OvalShape()));
        if(Build.VERSION.SDK_INT>=21){
            imageButton.setClipToOutline(true);
        }

        imageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                show();
            }
        });
    }

    void show(){

        final CharSequence[] items = new CharSequence[]{"사진 촬영","앨범에서 선택"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("프로필 사진 설정방법을 선택하세요");

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                //String selectedText = items[i].toString();
                switch(i)
                {
                    case 0 : //사진촬영
                        //카메라권한 체크
                        int permissionCheck = ContextCompat.checkSelfPermission(My_Profile.this,Manifest.permission.CAMERA);

                        if(permissionCheck==PackageManager.PERMISSION_DENIED){
                            //권한 x
                            ActivityCompat.requestPermissions(My_Profile.this, new String[]{Manifest.permission.CAMERA},0);
                            Toast.makeText(getApplicationContext(),"카메라 권한 없음", Toast.LENGTH_SHORT).show();
                        }else{
                            //권한 o
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent,1);
                        }
                        break;
                    case 1 : //앨범에서 선택
                        selectGallery();
                        break;
                }
                dialog.dismiss();
            }
        });
        builder.show();

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if(requestCode==0){
            if(grantResults[0]==0){
                Toast.makeText(this,"카메라 권한 승인됨",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"카메라 권한이 거절됨",Toast.LENGTH_SHORT).show();
            }
        }

    }



    //카메라에서 사진 가져오기
    private void selectPhoto(){
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent,CAMERA_CODE);
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!=null){
                File photoFile = null;
                try{
                    photoFile = createImageFile();
                }catch (IOException ex){

                }if(photoFile!=null){
                    photoUri=FileProvider.getUriForFile(this,getPackageName(),photoFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(intent,CAMERA_CODE);
                }
            }
        }

    }

    //카메라로 찍은 사진을 실제 파일로 저장하는 코드  * path이름 일치
    private File createImageFile() throws IOException {
        File dir = new File(Environment.getExternalStorageDirectory()+"/Pictures/BookRecommend"); //file_path와 동일하게 설정
        if(!dir.exists()){
            dir.mkdirs();
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        mlmageCaptureName = timeStamp+".png";

        File storageDir = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/path/"+mlmageCaptureName);
        currentPhotoPath = storageDir.getAbsolutePath();

        return storageDir;
    }

    private void getPictureForPhoto(){
        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
        ExifInterface exif = null;
        try{
            exif = new ExifInterface(currentPhotoPath);
        }catch (IOException e){
            e.printStackTrace();
        }
        int exifOrientation;
        int exifDegree;

        if(exif != null){
            exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            exifDegree = exifOrientationToDegrees(exifOrientation);
        }else{
            exifDegree=0;
        }
       // ivImage.setImageBitmap(rotate(bitmap,exifDegree)); //이미지 뷰에 비트맵 넣기
        imageButton.setImageBitmap(rotate(bitmap,exifDegree));
    }

    private void selectGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent,GALLERY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode, data);

        if(resultCode==RESULT_OK){
            switch(requestCode){
                case GALLERY_CODE :
                    sendPicture(data.getData());
                    break;
                case CAMERA_CODE :
                    getPictureForPhoto();
                    break;
                default:
                    break;
            }
        }
    }

    private void sendPicture(Uri imgUri){
        String imagePath = getRealPathFromURI(imgUri); //path경로
        ExifInterface exif = null;
        try{
            exif = new ExifInterface(imagePath);
        }catch(IOException e){
            e.printStackTrace();
        }
        int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL);
        int exifDegree = exifOrientationToDegrees(exifOrientation);

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
      //  ivImage.setImageBitmap(rotate(bitmap,exifDegree));
        imageButton.setImageBitmap(rotate(bitmap,exifDegree));
    }

    //사진 회전 값 가져오기 <- 미처리시 사진을 찍은 방향대로 이미지뷰에 처리되지 않음.
    private int exifOrientationToDegrees(int exifOrientation){
        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90){
            return 90;
        }else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180){
            return 180;
        }else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270){
            return 270;
        }
        return 0;
    }

    private Bitmap rotate(Bitmap src, float degree){
        //Matrix 객체 생성
        Matrix matrix = new Matrix();
        //회전 각도
        matrix.postRotate(degree);

        return Bitmap.createBitmap(src,0,0,src.getWidth(),src.getHeight(),matrix,true);
    }

    //사진의 절대 경로 구하기
    private String getRealPathFromURI(Uri contentUri){
        int column_index=0;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor =  getContentResolver().query(contentUri,proj, null, null, null);
        if(cursor.moveToFirst()){
            column_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        }
        return cursor.getString(column_index);
    }

}
