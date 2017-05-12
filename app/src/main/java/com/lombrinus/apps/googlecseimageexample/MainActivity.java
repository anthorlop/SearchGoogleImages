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

    private static final String API_KEY = "";
    private static final String SEARCH_CX = "";

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

        if (API_KEY.isEmpty()) {
            Toast.makeText(MainActivity.this, "API KEY no informado (Ver README.md)", Toast.LENGTH_SHORT).show();
            return;
        } else if (SEARCH_CX.isEmpty()) {
            Toast.makeText(MainActivity.this, "SEARCH ID no informado (Ver README.md)", Toast.LENGTH_SHORT).show();
            return;
        }

        String texto = mEditText.getText().toString();

        GImageParser.get(texto, API_KEY, SEARCH_CX, new GImageParserListener() {
            @Override
            public void onError(String error) {

                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
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
