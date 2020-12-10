package com.example.library.apicalls;

import android.app.Activity;
import android.util.Log;

import com.example.library.R;
import com.example.library.activity.AddBookActivity;
import com.example.library.activity.AddBookActivity_;
import com.example.library.activity.BaseActivity;
import com.example.library.activity.LandingActivity;
import com.example.library.activity.LandingActivity_;
import com.example.library.activity.UserLandingActivity;
import com.example.library.activity.UserLandingActivity_;
import com.example.library.request.BookRequest;
import com.example.library.request.SearchRequest;
import com.example.library.response.AuthorResponse;
import com.example.library.response.BookResponse;
import com.example.library.response.CommonResponse;
import com.example.library.response.PublisherResponse;

import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class BookApiCall {
    private static final String TAG = BookApiCall.class.getName();

    public static void addBook(BookRequest bookRequest, final Activity activity) {
        Log.e(TAG, "addBook" + bookRequest.toString());

        final BaseActivity baseActivity = (BaseActivity) activity;
        baseActivity.showProgressDialog(baseActivity.getResources().getString(R.string.fetching));

        RestTools.init();
        RestTools.get().addBook(bookRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<CommonResponse>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "In onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "In onError()" + e.toString());
                baseActivity.hideProgressDialog();
                activity.finish();

            }

            @Override
            public void onNext(Response<CommonResponse> response) {

                Log.d(TAG, "onNext");

                Log.d(TAG, "onNext" + response.toString());
                baseActivity.hideProgressDialog();
                activity.finish();

            }
        });
    }

    public static void search(SearchRequest searchrequest, final Activity activity) {
        Log.e(TAG, "searchrequest" + searchrequest.toString());

        final BaseActivity baseActivity = (BaseActivity) activity;
        baseActivity.showProgressDialog(baseActivity.getResources().getString(R.string.fetching));

        RestTools.init();
        RestTools.get().searchBook(searchrequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<BookResponse>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "In onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "In onError()" + e.toString());
                baseActivity.hideProgressDialog();

                if (activity.getClass().getSimpleName().equals(LandingActivity_.class.getSimpleName())) {
                    LandingActivity landingActivity = (LandingActivity) activity;
                    landingActivity.setBookResponse(new BookResponse());
                    return;
                }
                if (activity.getClass().getSimpleName().equals(UserLandingActivity_.class.getSimpleName())) {
                    UserLandingActivity userLandingActivity = (UserLandingActivity) activity;
                    userLandingActivity.setBookResponse(new BookResponse());
                    return;
                }
            }

            @Override
            public void onNext(Response<BookResponse> response) {

                Log.d(TAG, "onNext");

                Log.d(TAG, "onNext" + response.toString());
                baseActivity.hideProgressDialog();

                if (activity.getClass().getSimpleName().equals(LandingActivity_.class.getSimpleName())) {
                    LandingActivity landingActivity = (LandingActivity) activity;
                    landingActivity.setBookResponse(response.body());
                    return;
                }
                if (activity.getClass().getSimpleName().equals(UserLandingActivity_.class.getSimpleName())) {
                    UserLandingActivity userLandingActivity = (UserLandingActivity) activity;
                    userLandingActivity.setBookResponse(response.body());
                    return;
                }

            }
        });
    }


    public static void allPublisher(final Activity activity) {
        Log.e(TAG, "allPublisher");

        final BaseActivity baseActivity = (BaseActivity) activity;
        baseActivity.showProgressDialog(baseActivity.getResources().getString(R.string.fetching));

        RestTools.init();
        RestTools.get().allPublisher()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<PublisherResponse>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "In onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "In onError()" + e.toString());
                baseActivity.hideProgressDialog();
                if (activity.getClass().getSimpleName().equals(LandingActivity_.class.getSimpleName())) {
                    LandingActivity landingActivity = (LandingActivity) activity;
                    landingActivity.setPublisherResponse(new PublisherResponse());
                    return;
                }
                if (activity.getClass().getSimpleName().equals(AddBookActivity_.class.getSimpleName())) {
                    AddBookActivity addBookActivity = (AddBookActivity) activity;
                    addBookActivity.setPublisherResponse(new PublisherResponse());
                    return;
                }


            }

            @Override
            public void onNext(Response<PublisherResponse> response) {

                Log.d(TAG, "onNext");

                Log.d(TAG, "onNext" + response.toString());
                baseActivity.hideProgressDialog();
                if (activity.getClass().getSimpleName().equals(LandingActivity_.class.getSimpleName())) {
                    LandingActivity landingActivity = (LandingActivity) activity;
                    landingActivity.setPublisherResponse(response.body());
                    return;
                }
                if (activity.getClass().getSimpleName().equals(AddBookActivity_.class.getSimpleName())) {
                    AddBookActivity landingActivity = (AddBookActivity) activity;
                    landingActivity.setPublisherResponse(response.body());
                    return;
                }


            }
        });
    }

    public static void allAuthor(final Activity activity) {
        Log.e(TAG, "allAuthor");

        final BaseActivity baseActivity = (BaseActivity) activity;
        baseActivity.showProgressDialog(baseActivity.getResources().getString(R.string.fetching));

        RestTools.init();
        RestTools.get().allAuthor()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<AuthorResponse>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "In onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "In onError()" + e.toString());
                baseActivity.hideProgressDialog();
                if (activity.getClass().getSimpleName().equals(LandingActivity_.class.getSimpleName())) {
                    LandingActivity landingActivity = (LandingActivity) activity;
                    landingActivity.setAuthorResponse(new AuthorResponse());
                    return;
                }
                if (activity.getClass().getSimpleName().equals(AddBookActivity_.class.getSimpleName())) {
                    AddBookActivity addBookActivity = (AddBookActivity) activity;
                    addBookActivity.setAuthorResponse(new AuthorResponse());
                    return;
                }


            }

            @Override
            public void onNext(Response<AuthorResponse> response) {

                Log.d(TAG, "onNext");

                Log.d(TAG, "onNext" + response.toString());
                baseActivity.hideProgressDialog();
                if (activity.getClass().getSimpleName().equals(LandingActivity_.class.getSimpleName())) {
                    LandingActivity landingActivity = (LandingActivity) activity;
                    landingActivity.setAuthorResponse(response.body());
                    return;
                }
                if (activity.getClass().getSimpleName().equals(AddBookActivity_.class.getSimpleName())) {
                    AddBookActivity addBookActivity = (AddBookActivity) activity;
                    addBookActivity.setAuthorResponse(response.body());
                    return;
                }


            }
        });
    }

    public static void addAuthor(String text, final Activity activity) {
        Log.e(TAG, "addAuthor");

        final BaseActivity baseActivity = (BaseActivity) activity;
        baseActivity.showProgressDialog(baseActivity.getResources().getString(R.string.fetching));

        RestTools.init();
        RestTools.get().addAuthor(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<CommonResponse>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "In onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "In onError()" + e.toString());
                baseActivity.hideProgressDialog();
                activity.finish();


            }

            @Override
            public void onNext(Response<CommonResponse> response) {

                Log.d(TAG, "onNext");

                Log.d(TAG, "onNext" + response.toString());
                baseActivity.hideProgressDialog();
                activity.finish();

            }
        });
    }

    public static void addPublication(String text, final Activity activity) {
        Log.e(TAG, "addPublication");

        final BaseActivity baseActivity = (BaseActivity) activity;
        baseActivity.showProgressDialog(baseActivity.getResources().getString(R.string.fetching));

        RestTools.init();
        RestTools.get().addPublisher(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<CommonResponse>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "In onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "In onError()" + e.toString());
                baseActivity.hideProgressDialog();
                activity.finish();


            }

            @Override
            public void onNext(Response<CommonResponse> response) {

                Log.d(TAG, "onNext");

                Log.d(TAG, "onNext" + response.toString());
                baseActivity.hideProgressDialog();
                activity.finish();

            }
        });
    }


}
