package com.moorle.angeltestlib.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.moorle.angeltestlib.R;
import com.moorle.angeltestlib.model.AngelModel;
import com.moorle.angeltestlib.model.Item;
import com.moorle.angeltestlib.util.IoUtils;

import java.util.List;

public class ChoiceQuestionFragment extends BaseFragment implements View.OnClickListener {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String STATE_SELECTED_ITEM_ID = "selected_nav_drawer_item_id";

    private LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;

    private List<Item> mData;
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ChoiceQuestionFragment newInstance(int sectionNumber) {
        ChoiceQuestionFragment fragment = new ChoiceQuestionFragment();
        Bundle args = new Bundle();
        args.putInt(STATE_SELECTED_ITEM_ID, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ChoiceQuestionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        mData = AngelModel.getInstance().getData();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.question_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new MyAdapter(this, mData));
        return rootView;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_choice_question;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(STATE_SELECTED_ITEM_ID));
    }

    @Override
    public void onClick(View v) {
        int itemPosition = mRecyclerView.getChildPosition(v);
        Intent intent = new Intent(getActivity(), QuestionDetailActivity.class);
        intent.putExtra("currentIndex", itemPosition);
        startActivity(intent);
    }

    private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private Fragment mFragment;
        private List<Item> mData;

        public MyAdapter(Fragment fragment, List<Item> data) {
            mFragment = fragment;
            mData = data;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            // 加载Item的布局.布局中用到的真正的CardView.
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
            // ViewHolder参数一定要是Item的Root节点.
            view.setOnClickListener((ChoiceQuestionFragment)mFragment);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.text.setText(mData.get(i).title);
            viewHolder.seq.setText("" + (i + 1) + "/" + mData.size());
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView text;
            TextView seq;

            public ViewHolder(View itemView) {
                // super这个参数一定要注意,必须为Item的根节点.否则会出现莫名的FC.
                super(itemView);
                text = (TextView) itemView.findViewById(R.id.text);
                seq  = (TextView) itemView.findViewById(R.id.seq);
            }
        }
    }

}