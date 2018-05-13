package com.evgkit.simpleandroidapp.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.evgkit.simpleandroidapp.R;
import com.evgkit.simpleandroidapp.network.Gif;
import com.evgkit.simpleandroidapp.network.App;
import com.evgkit.simpleandroidapp.network.ImageAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkActivity extends AppCompatActivity {

    private ImageAdapter adapter;
    private RecyclerView recyclerView;
    private TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        recyclerView = findViewById(R.id.recyclerViewNetwork);
        recyclerView.setLayoutManager(
                getResources().getBoolean(R.bool.isLandscape) ?
                        new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL) :
                        new GridLayoutManager(this, 2)
        );
        adapter = new ImageAdapter();
        recyclerView.setAdapter(adapter);

        statusText = findViewById(R.id.textViewNetwork);

        final EditText searchQueryEdit = findViewById(R.id.editTextNetworkSearch);
        final Button submitBtn = findViewById(R.id.buttonNetworkSearch);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                searchImages(searchQueryEdit.getText().toString());
                hideKeyboard();
            }
        });
    }

    private void searchImages(String query) {
        showMessage(getString(R.string.searching));

        getApp().getApi().getSearch(query).enqueue(new Callback<List<Gif>>() {
            @Override public void onResponse(Call<List<Gif>> call, Response<List<Gif>> response) {
                if (response.code() == 200) {
                    final List<Gif> gifs = response.body();
                    showContent(gifs);
                } else {
                    showMessage(getString(R.string.error_with_code, response.code()));
                }
            }

            @Override public void onFailure(Call<List<Gif>> call, Throwable t) {
                showMessage(getString(R.string.something_went_wrong));
            }
        });
    }

    private void showMessage(String message) {
        statusText.setText(message);
        recyclerView.setVisibility(View.INVISIBLE);
        statusText.setVisibility(View.VISIBLE);
    }

    private void showContent(List<Gif> gifs) {
        adapter.replaceWith(gifs);
        recyclerView.setVisibility(View.VISIBLE);
        statusText.setVisibility(View.INVISIBLE);
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private App getApp() {
        return (App) getApplication();
    }
}
