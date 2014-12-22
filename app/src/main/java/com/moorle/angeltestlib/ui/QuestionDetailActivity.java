package com.moorle.angeltestlib.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.moorle.angeltestlib.R;
import com.moorle.angeltestlib.model.AngelModel;
import com.moorle.angeltestlib.model.Item;

import java.util.List;

public class QuestionDetailActivity extends BaseActivity {

    private TextView mAnswer;
    private TextView mTitle;
    private TextView mOptions;
    private TextView mSeq;

    private List<Item> mData;

    private int mCurrentIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAnswer = (TextView) findViewById(R.id.answer);
        mTitle = (TextView) findViewById(R.id.title);
        mOptions = (TextView) findViewById(R.id.options);
        mSeq = (TextView) findViewById(R.id.seq);
        setActionBar();

        mData = AngelModel.getInstance().getData();
        mCurrentIndex = getIntent().getIntExtra("currentIndex", 0);
        setupView();
    }

    private void setupView() {
        Item item = mData.get(mCurrentIndex);
        mSeq.setText((mCurrentIndex  + 1 + "/" + mData.size()));
        mTitle.setText(item.title);
        mOptions.setText(item.optionsToString());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_question_detail;
    }

    private void setActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAnswer(View v) {
        Item item = mData.get(mCurrentIndex);
        if(mAnswer.getVisibility() != View.VISIBLE) {
            mAnswer.setText(item.answer);
            mAnswer.setVisibility(View.VISIBLE);
        }
    }

    public void onPre(View v) {
        if(mAnswer.getVisibility() == View.VISIBLE) {
            mAnswer.setVisibility(View.GONE);
        }
        if(mCurrentIndex == 0) {
           return;
        }

        mCurrentIndex = mCurrentIndex - 1;

        Item item = mData.get(mCurrentIndex);

        mSeq.setText((mCurrentIndex  + 1 + "/" + mData.size()));
        mTitle.setText(item.title);
        mOptions.setText(item.optionsToString());
    }

    public void onNext(View v) {
        if (mAnswer.getVisibility() == View.VISIBLE) {
            mAnswer.setVisibility(View.GONE);
        }
        if(mCurrentIndex == mData.size() - 1) {
            return;
        }

        mCurrentIndex = mCurrentIndex + 1;

        Item item = mData.get(mCurrentIndex);

        mSeq.setText((mCurrentIndex  + 1 + "/" + mData.size()));
        mTitle.setText(item.title);
        mOptions.setText(item.optionsToString());
    }
}
