package com.example.library.apicalls;

import android.app.Activity;
import android.util.Log;

import com.example.library.R;
import com.example.library.activity.AddBookActivity;
import com.example.library.activity.AddBookActivity_;
import com.example.library.activity.BaseActivity;
import com.example.library.activity.LandingActivity;
import com.example.library.activity.LandingActivity_;
import com.example.library.activity.LoginActivity;
import com.example.library.activity.UserLandingActivity;
import com.example.library.libraryenum.EStatus;
import com.example.library.request.AddUserBookRequest;
import com.example.library.request.AddUserRequest;
import com.example.library.request.LoginRequest;
import com.example.library.request.SearchRequest;
import com.example.library.response.CommonResponse;
import com.example.library.response.PublisherResponse;
import com.example.library.response.UserBookResponse;
import com.example.library.response.UserListResponse;
import com.example.library.response.UserResponse;
import com.example.library.utils.LibraryUtils;

import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserApiCall {
    private static final String TAG = UserApiCall.class.getName();

    public static void login(LoginRequest loginRequest, final Activity activity) {
        Log.e(TAG, "login" + loginRequest.toString());

        final BaseActivity baseActivity = (BaseActivity) activity;
        baseActivity.showProgressDialog(baseActivity.getResources().getString(R.string.fetching));

        RestTools.init();
        RestTools.get().userlogin(loginRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<UserResponse>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "In onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
              Log.e(TAG, "In onError()" + e.toString());
                baseActivity.hideProgressDialog();
                LoginActivity loginActivity = (LoginActivity) activity;
                loginActivity.setLoginResponse(new UserResponse());


            }

            @Override
            public void onNext(Response<UserResponse> response) {

                    Log.d(TAG, "onNext");

                    Log.d(TAG, "onNext" + response.toString());
                baseActivity.hideProgressDialog();
                LoginActivity loginActivity = (LoginActivity) activity;
                loginActivity.setLoginResponse(response.body());

            }
        });
    }

    public static void addUser(AddUserRequest addUserRequest, final Activity activity) {
        Log.e(TAG, "addUser" + addUserRequest.toString());

        final BaseActivity baseActivity = (BaseActivity) activity;
        baseActivity.showProgressDialog(baseActivity.getResources().getString(R.string.fetching));

        RestTools.init();
        RestTools.get().addUser(addUserRequest)
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

    public static void deleteUser(Integer userId, final Activity activity) {
        Log.e(TAG, "deleteUser" + userId.toString());

        final BaseActivity baseActivity = (BaseActivity) activity;
        baseActivity.showProgressDialog(baseActivity.getResources().getString(R.string.fetching));

        RestTools.init();
        RestTools.get().deleteUser(userId)
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
                getNormalUser(activity);

            }

            @Override
            public void onNext(Response<CommonResponse> response) {

                    Log.d(TAG, "onNext");

                    Log.d(TAG, "onNext" + response.toString());
                baseActivity.hideProgressDialog();
                getNormalUser(activity);

            }
        });
    }

    public static void getNormalUser(final Activity activity) {
        Log.e(TAG, "getNormalUser");

        final BaseActivity baseActivity = (BaseActivity) activity;
        baseActivity.showProgressDialog(baseActivity.getResources().getString(R.string.fetching));

        RestTools.init();
        RestTools.get().getNormalUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<UserListResponse>>() {
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
                    landingActivity.setUserResponse(new UserListResponse());
                    return;
                }



            }

            @Override
            public void onNext(Response<UserListResponse> response) {

                Log.d(TAG, "onNext");

                Log.d(TAG, "onNext" + response.toString());
                baseActivity.hideProgressDialog();
                if (activity.getClass().getSimpleName().equals(LandingActivity_.class.getSimpleName())) {
                    LandingActivity landingActivity = (LandingActivity) activity;
                    landingActivity.setUserResponse(response.body());
                    return;
                }


            }
        });
    }

    public static void getUserBook(Integer userId,Integer status,final Activity activity) {
        Log.e(TAG, "getNormalUser");

        final BaseActivity baseActivity = (BaseActivity) activity;
        baseActivity.showProgressDialog(baseActivity.getResources().getString(R.string.fetching));

        RestTools.init();
        RestTools.get().getUserBook(userId,status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response<UserBookResponse>>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "In onCompleted()");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "In onError()" + e.toString());
                baseActivity.hideProgressDialog();
                if (EStatus.ACTIVE.getCode().equals(status)) {
                    UserLandingActivity landingActivity = (UserLandingActivity) activity;
                    landingActivity.setCurrentBook(new UserBookResponse());
                    return;
                }
                if (EStatus.INACTIVE.getCode().equals(status)) {
                    UserLandingActivity landingActivity = (UserLandingActivity) activity;
                    landingActivity.setReturnedBook(new UserBookResponse());
                    return;
                }



            }

            @Override
            public void onNext(Response<UserBookResponse> response) {

                Log.d(TAG, "onNext");

                Log.d(TAG, "onNext" + response.toString());
                baseActivity.hideProgressDialog();
                if (EStatus.ACTIVE.getCode().equals(status)) {
                    UserLandingActivity landingActivity = (UserLandingActivity) activity;
                    landingActivity.setCurrentBook(response.body());
                    return;
                }
                if (EStatus.INACTIVE.getCode().equals(status)) {
                    UserLandingActivity landingActivity = (UserLandingActivity) activity;
                    landingActivity.setReturnedBook(response.body());
                    return;
                }


            }
        });
    }

    public static void lendBook(AddUserBookRequest addUserBookRequest, final Activity activity) {
        Log.e(TAG, "lendBook" + addUserBookRequest.toString());

        final BaseActivity baseActivity = (BaseActivity) activity;
        baseActivity.showProgressDialog(baseActivity.getResources().getString(R.string.fetching));

        RestTools.init();
        RestTools.get().lendBook(addUserBookRequest)
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
                LibraryUtils.showToast("Can't Lend Book. Please Try Again Later");

            }

            @Override
            public void onNext(Response<CommonResponse> response) {

                Log.d(TAG, "onNext");

                Log.d(TAG, "onNext" + response.toString());
                baseActivity.hideProgressDialog();
                LibraryUtils.showToast("Book Lended Sucessfully");
                SearchRequest searchRequest = new SearchRequest();
                searchRequest.setSearchText("");
                BookApiCall.search(searchRequest,activity);

            }
        });
    }

  public static void returnBook(Integer userBookId, final Activity activity) {
        Log.e(TAG, "returnBook" + userBookId.toString());

        final BaseActivity baseActivity = (BaseActivity) activity;
        baseActivity.showProgressDialog(baseActivity.getResources().getString(R.string.fetching));

        RestTools.init();
        RestTools.get().returnBook(userBookId)
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
                LibraryUtils.showToast("Can't Return Lended Book. Please Try Again Later");

            }

            @Override
            public void onNext(Response<CommonResponse> response) {

                Log.d(TAG, "onNext");

                Log.d(TAG, "onNext" + response.toString());
                baseActivity.hideProgressDialog();
                getUserBook(LibraryUtils.loginedUser.getUserId(),EStatus.ACTIVE.getCode(),activity);

            }
        });
    }

}
