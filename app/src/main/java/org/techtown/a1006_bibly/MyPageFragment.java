package org.techtown.a1006_bibly;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

public class MyPageFragment extends Fragment {

//    private ImageButton imageButton;
//    private TextView tv_ID;
//    private Button all_book, rating, comment, read_date;
//
//    public MyPageFragment() {
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_4, container, false);

//        imageButton = (ImageButton) view.findViewById(R.id.profile_img);
//
//        //imageButton.setBackground(new ShapeDrawable(new OvalShape()));
//        if(Build.VERSION.SDK_INT>=21){
//            imageButton.setClipToOutline(true);
//        }
//
//        tv_ID =(TextView) view.findViewById(R.id.profile_ID);
//        tv_ID.setText("Wonny");

        //button event
//        all_book = (Button) view.findViewById(R.id.all_book);
//        rating = (Button) view.findViewById(R.id.rating);
//        comment = (Button) view.findViewById(R.id.comment);
//        read_date = (Button) view.findViewById(R.id.read_date);

//        View.OnClickListener listener = new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                switch(v.getId()){
//                    case R.id.profile_img:
//                        Intent intent0 = new Intent(getActivity(), My_Profile.class);
//                        startActivity(intent0);
//                        break;
//                    case R.id.all_book:
//                        Intent intent1 = new Intent(getActivity() , My_AllBook.class);
//                        startActivity(intent1);
//                        break;
//                    case R.id.rating:
//                        Intent intent2 = new Intent(getActivity() , My_Rating.class);
//                        startActivity(intent2);
//                        break;
//                    case R.id.comment:
//                        Intent intent3 = new Intent(getActivity() , My_Comment.class);
//                        startActivity(intent3);
//                        break;
//                    case R.id.read_date:
//                        Intent intent4 = new Intent(getActivity() , My_ReadDate.class);
//                        startActivity(intent4);
//                        break;
//                }
//            }
//        };
//        imageButton.setOnClickListener(listener);
//        all_book.setOnClickListener(listener);
//        rating.setOnClickListener(listener);
//        comment.setOnClickListener(listener);
//        read_date.setOnClickListener(listener);

        return view;
    }//OnCreateView

//    //카메라에서 사진 가져오기
//
//    private void selectPhoto(){
////        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
////        startActivityForResult(intent,CAMERA_CODE);
//        String state = Environment.getExternalStorageState();
//        if(Environment.MEDIA_MOUNTED.equals(state)){
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            if(intent.resolveActivity(getPackageManager())!=null){
//                File photoFile = null;
//                try{
//                    photoFile = createImageFile();
//                }catch (IOException ex){
//
//                }if(photoFile!=null){
//                    photoUri=FileProvider.getUriForFile(this,getPackageName(),photoFile);
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//                    startActivityForResult(intent,CAMERA_CODE);
//                }
//            }
//        }
//
//    }
//
//    //카메라로 찍은 사진을 실제 파일로 저장하는 코드  * path이름 일치
//    private File createImageFile() throws IOException {
//        File dir = new File(Environment.getExternalStorageDirectory()+"/path/");
//        if(!dir.exists()){
//            dir.mkdirs();
//        }
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        mlmageCaptureName = timeStamp+".png";
//
//        File storageDir = new File(Environment.getExternalStorageDirectory().getAbsoluteFile()+"/path/"+mlmageCaptureName);
//        currentPhotoPath = storageDir.getAbsolutePath();
//
//        return storageDir;
//    }
//
//    private void getPictureForPhoto(){
//        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath);
//        ExifInterface exif = null;
//        try{
//            exif = new ExifInterface(currentPhotoPath);
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        int exifOrientation;
//        int exifDegree;
//
//        if(exif != null){
//            exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
//            exifDegree = exifOrientationToDegrees(exifOrientation);
//        }else{
//            exifDegree=0;
//        }
//        ivImage.setImageBitmap(rotate(bitmap,exifDegree)); //이미지 뷰에 비트맵 넣기
//    }
//
//    private void selectGallery(){
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setType("image/*");
//        startActivityForResult(intent,GALLERY_CODE);
//    }
//
//    @Override
//    //protected로 가능하면 변경
//    public void onActivityResult(int requestCode, int resultCode, Intent data){
//        super.onActivityResult(requestCode,resultCode, data);
//
//        if(resultCode==RESULT_OK){
//            switch(requestCode){
//                case GALLERY_CODE :
//                    sendPicture(data.getData());
//                    break;
//                case CAMERA_CODE :
//                    getPictureForPhoto();
//                    break;
//                default:
//                    break;
//            }
//        }
//    }
//
//    private void sendPicture(Uri imgUri){
//        String imagePath = getRealPathFromURI(imgUri); //path경로
//        ExifInterface exif = null;
//        try{
//            exif = new ExifInterface(imagePath);
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//        int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,ExifInterface.ORIENTATION_NORMAL);
//        int exifDegree = exifOrientationToDegrees(exifOrientation);
//
//        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
//        ivImage.setImageBitmap(rotate(bitmap,exifDegree));
//    }
//
//    //사진 회전 값 가져오기 <- 미처리시 사진을 찍은 방향대로 이미지뷰에 처리되지 않음.
//    private int exifOrientationToDegrees(int exifOrientation){
//        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90){
//            return 90;
//        }else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180){
//            return 180;
//        }else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270){
//            return 270;
//        }
//        return 0;
//    }
//
//    private Bitmap rotate(Bitmap src, float degree){
//        //Matrix 객체 생성
//        Matrix matrix = new Matrix();
//        //회전 각도
//        matrix.postRotate(degree);
//
//        return Bitmap.createBitmap(src,0,0,src.getWidth(),src.getHeight(),matrix,true);
//    }
//
//    //사진의 절대 경로 구하기
//    private String getRealPathFromURI(Uri contentUri){
//        int column_index=0;
//        String[] proj = {MediaStore.Images.Media.DATA};
//        Cursor cursor =  getContentResolver().query(contentUri,proj, null, null, null);
//        if(cursor.moveToFirst()){
//            column_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        }
//        return cursor.getString(column_index);
//    }
//

}
