package com.example.user.memoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MemoActivity extends AppCompatActivity {

    private int REQUEST_CODE_NEW_MEMO=1000;

    private List<Memo> mMemoList;
    private MemoAdapter mAdapter;
    private ListView mMemoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        mMemoListView = findViewById(R.id.memo_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemoActivity.this,Memo2Activity.class);
                startActivityForResult(intent, REQUEST_CODE_NEW_MEMO);
            }
        });

        //데이터
        mMemoList = new ArrayList<>();

        //어댑터
        mAdapter = new MemoAdapter(mMemoList);

        mMemoListView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_NEW_MEMO){
            if(resultCode == RESULT_OK){
                String title = data.getStringExtra("title");
                String content = data.getStringExtra("content");

                mMemoList.add(new Memo(title, content));
                mAdapter.notifyDataSetChanged();

                Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
