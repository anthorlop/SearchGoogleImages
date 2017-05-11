package com.lombrinus.apps.googlecseimageexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.lombrinus.projects.mods.gimageparser.gimages.GImageParser;
import es.lombrinus.projects.mods.gimageparser.gimages.GImageParserListener;
import es.lombrinus.projects.mods.gimageparser.gimages.Item;

public class MainActivity extends AppCompatActivity implements MainAdapter.OnItemClickListener {

    private static final String API_KEY = "AIzaSyA_N7dlmLkXyEVUDl7kH55k1O5e4tDL_bk";
    private static final String SEARCH_CX = "015333751053696321047:y29iytmsn-k";

    EditText mEditText;
    GridView mGrid;
    private MainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.editText);
        findViewById(R.id.searchBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEditText.getText().toString().length() > 3) {
                    search();
                }
            }
        });

        mGrid = (GridView) findViewById(R.id.gridView);

    }

    private void search() {

        String texto = mEditText.getText().toString();

        GImageParser.get(texto, API_KEY, SEARCH_CX, 10, new GImageParserListener() {
            @Override
            public void onError() {

                Toast.makeText(MainActivity.this, R.string.error_getting_images, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(List<Item> itemList) {

                mAdapter = new MainAdapter(MainActivity.this, itemList, MainActivity.this);

                mGrid.setAdapter(mAdapter);

            }
        });

    }

    @Override
    public void onItemClick(Item item) {
        ImageDialogFragment.newInstance(item.getLink()).show((this).getSupportFragmentManager(), "IMAGE");
    }
}
