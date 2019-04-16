package com.example.subrata.firstapplication.retrofitExample;


import com.example.subrata.firstapplication.Item;
import com.example.subrata.firstapplication.PostList;
import com.google.gson.Gson;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class BloggerAPI {

    private static final String key = "AIzaSyD5U-FP_8dkxBxwLQXPdrOWkxcz9IpUyYg";
    private static final String url = "https://www.googleapis.com/blogger/v3/blogs/3982416678794696268/posts/";

    public static PostService postService = null;

    public static PostService getService()
    {
        if(postService == null)
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            postService = retrofit.create(PostService.class);

        }
        return postService;
    }

    public interface PostService {
        //get all the posts
        @GET("?key=" + key)
        Call<PostList> getPostList();

        //Get a particular post by post id
//        @GET("{postId}/?key="+key)
//        Call<Item> getPostById(@Path("postId") String id);
    }
}
