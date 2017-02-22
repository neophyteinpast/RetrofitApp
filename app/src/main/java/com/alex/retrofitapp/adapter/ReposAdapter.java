package com.alex.retrofitapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alex.retrofitapp.R;
import com.alex.retrofitapp.model.Repo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 430 on 15.02.2017.
 */

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder> {

    private List<Repo> mReposList;

    public void setDataSource(List<Repo> list) {
        mReposList = list;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.name)
        TextView textViewName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindView(Repo repo) {
            textViewName.setText(repo.getName());
        }
    }

    @Override
    public ReposAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View reposView = inflater.inflate(R.layout.repos_item_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(reposView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ReposAdapter.ViewHolder holder, int position) {
        Repo repo = mReposList.get(position);
        holder.bindView(repo);
    }

    @Override
    public int getItemCount() {
        return mReposList == null ? 0 : mReposList.size();
    }
}
