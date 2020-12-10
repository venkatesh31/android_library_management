package com.example.library.activity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.cloudinary.EagerTransformation;
import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.library.R;
import com.example.library.apicalls.BookApiCall;
import com.example.library.libraryenum.EBookCategory;
import com.example.library.request.BookRequest;
import com.example.library.response.Author;
import com.example.library.response.AuthorResponse;
import com.example.library.response.Book;
import com.example.library.response.Publisher;
import com.example.library.response.PublisherResponse;
import com.example.library.utils.LibraryUtils;
import com.google.android.material.textfield.TextInputEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressLint("NonConstantResourceId")
@Fullscreen
@EActivity(R.layout.activity_add_book)
public class AddBookActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = AddBookActivity.class.getName();

    @ViewById(R.id.bookName)
    TextInputEditText bookName;

    @ViewById(R.id.bookDesc)
    TextInputEditText bookDesc;

    @ViewById(R.id.imageView)
    ImageView imageView;


    @ViewById(R.id.categorySpinner)
    Spinner categorySpinner;

    @ViewById(R.id.publicationSpinner)
    Spinner publicationSpinner;

    @ViewById(R.id.authorSpinner)
    Spinner authorSpinner;

    @ViewById(R.id.saveButton)
    Button saveButton;

    Publisher selectedPublisher;
    Author selectedAuthor;
    String selectedCategory;

    String filePath = null;
    String fileUrl;

    Book book;

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @AfterViews
    public void init() {
        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle(getString(R.string.add_book));
        book = (Book) getIntent().getSerializableExtra("bookItem");
        getCategoryList(categorySpinner, this);
        BookApiCall.allPublisher(this);
        setClickListeners();
        setPreviousData();
    }

    void setPreviousData(){
        if(book==null){
            return;
        }
        bookName.setText(book.getName());
        bookDesc.setText(book.getDescription());
        if(LibraryUtils.isValidString(book.getImageUrl())){
            Glide.with(Application.getContext()).asBitmap().load(book.getImageUrl()).into(imageView);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            filePath = getImagePathFromURI(this,uri);
            Bitmap thumbnail = BitmapFactory.decodeFile(filePath);
            Log.w("path image gallery..", filePath + "");
            imageView.setImageBitmap(thumbnail);
            String fieName = filePath.substring(filePath.lastIndexOf("/")+1);
            Log.d("fieName",fieName);
            String generateUrl=  MediaManager.get().url().generate(fieName);
            Log.d("generateUrl",generateUrl);
           String result= MediaManager.get().upload(filePath).callback(new UploadCallback() {
               @Override
               public void onStart(String requestId) {
                   // your code here
               }
               @Override
               public void onProgress(String requestId, long bytes, long totalBytes) {
                   // example code starts here
                   Double progress = (double) bytes/totalBytes;
                   // post progress to app UI (e.g. progress bar, notification)
                   // example code ends here
               }
               @Override
               public void onSuccess(String requestId, Map resultData) {
                   // your code here
                   Log.d("result-----", resultData.toString());
                   Log.d("url-----", resultData.get("url").toString());
                   fileUrl = resultData.get("url").toString();
               }
               @Override
               public void onError(String requestId, ErrorInfo error) {
                   // your code here
               }
               @Override
               public void onReschedule(String requestId, ErrorInfo error) {
                   // your code here
               }})
                   .dispatch();

           Log.d("result1111111",result);
        }
    }

    public static String getImagePathFromURI(Context context, Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        String path = null;
        if (cursor != null) {
            cursor.moveToFirst();
            String document_id = cursor.getString(0);
            document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
            cursor.close();

            cursor = context.getContentResolver().query(
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
            if (cursor != null) {
                cursor.moveToFirst();
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                cursor.close();
            }
        }
        Log.d(TAG,"getImagePathFromURI " + path);
        return path;
    }

    private void setClickListeners() {
        saveButton.setOnClickListener(this);
        imageView.setOnClickListener(this);
    }

    public static void galleryImage(Activity activity) {

        Log.d(TAG,"GALLER CLICKED");
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            return;
        }
        Intent intent =null;
        intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        if (intent == null) {
            LibraryUtils.showToast("Error in intent");
            return;
        }
        try {
            activity.startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    1);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            LibraryUtils.showToast("Not Provided File Manager");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveButton:
                BookApiCall.addBook(addBookRequest(),this);
                break;
                case R.id.imageView:
                    galleryImage(this);
                break;
        }
    }


    private BookRequest addBookRequest() {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setBookId(book!=null?book.getBookId():0);
        bookRequest.setCategory(selectedCategory);
        bookRequest.setAuthorId(selectedAuthor.getAuthorId());
        bookRequest.setPublisherId(selectedPublisher.getPublisherId());
        bookRequest.setName(bookName.getText().toString());
        bookRequest.setDescription(bookDesc.getText().toString());
        bookRequest.setImageUrl(fileUrl);
        Log.d(TAG, bookRequest.toString());
        return bookRequest;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }



    public void setAuthorResponse(AuthorResponse authorResponse) {
        if (authorResponse == null || authorResponse.getData() == null || authorResponse.getData().isEmpty()) {
            return;
        }

        getAuthorList(authorSpinner, authorResponse.getData(), this);
    }

    public void getAuthorList(final Spinner spinner, List<Author> authorList, final Activity activity) {
        List<String> transacationStringList = new ArrayList<String>();

        for (int i = 0; i < authorList.size(); i++) {
            transacationStringList.add(authorList.get(i) == null ? "" : authorList.get(i).getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, transacationStringList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();
        spinner.setAdapter(dataAdapter);

        if(book!=null && book.getAuthor()!=null){

            int spinnerPosition = dataAdapter.getPosition(book.getAuthor().getName());
            spinner.setSelection(spinnerPosition);

        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedAuthor = authorList.get(spinner.getSelectedItemPosition());
                //showIcon();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void setPublisherResponse(PublisherResponse publisherResponse) {
        if (publisherResponse == null || publisherResponse.getData() == null || publisherResponse.getData().isEmpty()) {
            return;
        }
        BookApiCall.allAuthor(this);
        getPublicationList(publicationSpinner, publisherResponse.getData(), this);
    }

    public void getPublicationList(final Spinner spinner, List<Publisher> publisherList, final Activity activity) {
        List<String> transacationStringList = new ArrayList<String>();

        for (int i = 0; i < publisherList.size(); i++) {
            transacationStringList.add(publisherList.get(i) == null ? "" : publisherList.get(i).getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, transacationStringList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();
        spinner.setAdapter(dataAdapter);
        if(book!=null && book.getPublisher()!=null){

            int spinnerPosition = dataAdapter.getPosition(book.getPublisher().getName());
            spinner.setSelection(spinnerPosition);

        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPublisher = publisherList.get(spinner.getSelectedItemPosition());
                //showIcon();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void getCategoryList(final Spinner spinner, final Activity activity) {

        final List<String> categoryStringList = new ArrayList<String>();

        EBookCategory[] eSkillCategories = EBookCategory.values();

        for (EBookCategory eSkillCategory : eSkillCategories) {
            categoryStringList.add(eSkillCategory.getCode());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, categoryStringList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.notifyDataSetChanged();
        spinner.setAdapter(dataAdapter);
        if(book!=null && book.getCategory()!=null){

            int spinnerPosition = dataAdapter.getPosition(book.getCategory());
            spinner.setSelection(spinnerPosition);

        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = eSkillCategories[(spinner.getSelectedItemPosition())].getCode();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
