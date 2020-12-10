package com.example.library.apicalls;

import com.example.library.request.AddUserBookRequest;
import com.example.library.request.AddUserRequest;
import com.example.library.request.BookRequest;
import com.example.library.request.LoginRequest;
import com.example.library.request.SearchRequest;
import com.example.library.response.AuthorResponse;
import com.example.library.response.BookResponse;
import com.example.library.response.CommonResponse;
import com.example.library.response.PublisherResponse;
import com.example.library.response.UserBookResponse;
import com.example.library.response.UserListResponse;
import com.example.library.response.UserResponse;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

public interface RestService {

    @POST("/user/login")
    Observable<Response<UserResponse>> userlogin(@Body LoginRequest loginRequest);

    @POST("/user/add")
    Observable<Response<CommonResponse>> addUser(@Body AddUserRequest addUserRequest);

    @POST("/user/delete/{userid}")
    Observable<Response<CommonResponse>> deleteUser(@Path("userid") Integer userid);

    @POST("/user/book/add")
    Observable<Response<CommonResponse>> lendBook(@Body AddUserBookRequest addUserBookRequest);

    @POST("/book/search")
    Observable<Response<BookResponse>> searchBook(@Body SearchRequest loginRequest);

    @POST("/book/add")
    Observable<Response<CommonResponse>> addBook(@Body BookRequest bookRequest);

    @GET("/user/get/normal")
    Observable<Response<UserListResponse>> getNormalUser();

    @GET("/publisher/get/all")
    Observable<Response<PublisherResponse>> allPublisher();

    @GET("/author/get/all")
    Observable<Response<AuthorResponse>> allAuthor();

    @POST("/publisher/add/{name}")
    Observable<Response<CommonResponse>> addPublisher(@Path("name") String name);

    @POST("/author/add/{name}")
    Observable<Response<CommonResponse>> addAuthor(@Path("name") String name);

    @POST("/user/book/return/{userbookid}")
    Observable<Response<CommonResponse>> returnBook(@Path("userbookid") Integer userbookid);

    @GET("/user/book/get/{userid}/{status}")
    Observable<Response<UserBookResponse>> getUserBook(@Path("userid") Integer userid, @Path("status") Integer status);

}
