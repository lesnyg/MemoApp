package com.example.user.memoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MemoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private int REQUEST_CODE_NEW_MEMO = 1000;
    private int REQUEST_CODE_UPDATE_MEMO = 1001;
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
                Intent intent = new Intent(MemoActivity.this, Memo2Activity.class);
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

        if (resultCode == RESULT_OK) {
            String title = data.getStringExtra("title");
            String content = data.getStringExtra("content");

            if (requestCode == REQUEST_CODE_NEW_MEMO) {
                //새메모
                mMemoList.add(new Memo(title, content));
            } else if (requestCode == REQUEST_CODE_UPDATE_MEMO) {
                long id = data.getLongExtra("id", -1);
                //수정
                Memo memo = mMemoList.get((int) id);
                memo.setTitle(title);
                memo.setContent(content);
            }
            mAdapter.notifyDataSetChanged();

            Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "취소되었습니다.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Memo memo = mMemoList.get(position);

        Intent intent = new Intent(this,Memo2Activity.class);
        intent.putExtra("id",id);
       // intent.putExtra("memo",memo);

        startActivityForResult(intent,REQUEST_CODE_UPDATE_MEMO);
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.context_menu,menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item;
//        switch (item.getItemId()){
//            case R.id.edit:
//                editNote
//        }
//
//        return super.onContextItemSelected(item);
//    }
}

