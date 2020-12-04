package com.example.ejercicioretrofit.fragments;

import android.os.Bundle;
import android.util.Log;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ejercicioretrofit.R;
import com.example.ejercicioretrofit.adapters.CommentAdapter;
import com.example.ejercicioretrofit.models.Comment;
import com.example.ejercicioretrofit.retrofit.APIService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CommentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CommentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommentFragment newInstance(String param1, String param2) {
        CommentFragment fragment = new CommentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        Bundle bundle = getArguments();

        RecyclerView commentRecyclerView = view.findViewById(R.id.commentRecyclerView);;
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        System.out.println(bundle.getInt("POST_ID"));
        Call<List<Comment>> listCall = retrofit.create(APIService.class).getCommentsFromPost(bundle.getInt("POST_ID"));

        listCall.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                List<Comment> comments  = response.body();
                System.out.println("Hay una cantidad de: " + comments.size() + " comentarios");
                CommentAdapter adapter = new CommentAdapter(comments, getContext());
                commentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                commentRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.e("Error ", t.getMessage());
            }
        });


        return view;
    }
}