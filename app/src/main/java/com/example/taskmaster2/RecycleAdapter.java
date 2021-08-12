package com.example.taskmaster2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

  private ArrayList<Task> tasksList = new ArrayList<>();

  public ArrayList<Task> getTasksList() {
    return tasksList;
  }

  public RecycleAdapter() {
  }

  public RecycleAdapter(ArrayList<Task> tasksList) {
    this.tasksList = tasksList;
  }




   public static class MyViewHolder extends RecyclerView.ViewHolder{
      private TextView title;
      private TextView body;
      private TextView state;
      private ImageView imageView;

     public MyViewHolder(View view) {
       super(view);
       title= view.findViewById(R.id.title1);
       body= view.findViewById(R.id.body);
       state= view.findViewById(R.id.taskState);
       imageView=view.findViewById(R.id.img);
     }
   }



  @NonNull

  @Override
  public RecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
  View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout,parent,false);
  return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull  RecycleAdapter.MyViewHolder holder, int position) {
    String title =tasksList.get(position).getTitle();
    holder.title.setText(title);


    String state =tasksList.get(position).getState();
    holder.state.setText(state);

    String body =tasksList.get(position).getBody();
    holder.body.setText(body);
  }

  @Override
  public int getItemCount() {
    return tasksList.size();
  }
}
