package com.example.semana13;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {
    FloatingActionButton flAgregar;
    RecyclerView recycler;
    UserAdapter adapter;
    List<User> usuarios = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chat, container, false);

        recycler = root.findViewById(R.id.recyclerUsuarios);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new UserAdapter(usuarios);
        recycler.setAdapter(adapter);

        flAgregar = root.findViewById(R.id.flAgregar);
        flAgregar.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), RegistroActivity.class));
        });

        cargarUsuarios();
        return root;
    }

    private void cargarUsuarios() {
        FirebaseDatabase.getInstance().getReference("usuarios")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        usuarios.clear();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            User u = ds.getValue(User.class);
                            if (u != null) usuarios.add(u);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}
                });
    }
}
