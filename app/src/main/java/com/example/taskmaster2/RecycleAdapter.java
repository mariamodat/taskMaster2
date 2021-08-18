package com.example.taskmaster2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Task;
import com.google.android.material.internal.ViewOverlayImpl;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

  private List<Task> tasksList =new ArrayList<>();
  private OnClickListener listener;
  public List<Task> getTasksList() {
    return tasksList;
  }

  public RecycleAdapter() {
  }

  public RecycleAdapter(List<Task> tasksList , OnClickListener listener) {
    this.tasksList = tasksList;
    this.listener=listener;
  }


public interface OnClickListener {
     void onTaskClicked(int position );
     void onTaskDelete( int position );
}

   public static class MyViewHolder extends RecyclerView.ViewHolder{
      private TextView title;
      private TextView body;
      private TextView state;
      private ImageView imageView;
      private Button deleteBtn ;

     public MyViewHolder(View view , OnClickListener listener) {
       super(view);
       title= view.findViewById(R.id.title1);
       body= view.findViewById(R.id.body);
       state= view.findViewById(R.id.taskState);
       imageView=view.findViewById(R.id.img);
       deleteBtn=view.findViewById(R.id.delete);


       imageView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
           listener.onTaskClicked(getBindingAdapterPosition());
         }
       });
       deleteBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
           listener.onTaskDelete(getBindingAdapterPosition());
         }
       });
     }
   }



  @NonNull

  @Override
  public RecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
  View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout,parent,false);
  return new MyViewHolder(view , listener);
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
