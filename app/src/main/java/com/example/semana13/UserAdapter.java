package com.example.semana13;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private final List<User> usuarios;

    public UserAdapter(List<User> usuarios) {
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv.setText(usuarios.get(position).nombre);
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ViewHolder(View v) {
            super(v);
            tv = v.findViewById(android.R.id.text1);
        }
    }
}
