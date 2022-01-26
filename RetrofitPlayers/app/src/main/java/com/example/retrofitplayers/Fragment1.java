package com.example.retrofitplayers;

import android.app.Fragment;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        EditText editId = view.findViewById(R.id.id);

        Button sendButton = view.findViewById(R.id.sendButton);

        TextView name = view.findViewById(R.id.name);
        TextView team = view.findViewById(R.id.team);
        TextView position = view.findViewById(R.id.position);
        TextView jerseyNumber = view.findViewById(R.id.jerseyNumber);

        final APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                team.setText("");
                position.setText("");
                jerseyNumber.setText("");

                if (editId.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Kérem adjon meg egy számot azonosítóként!", Toast.LENGTH_SHORT).show();
                } else {
                    Call<Player> call = service.getPlayer(Integer.parseInt(editId.getText().toString()));
                    call.enqueue(new Callback<Player>() {
                        @Override
                        public void onResponse(Call<Player> call, Response<Player> response) {
                            Player player = response.body();

                            if (player.getName() == null) {
                                Toast.makeText(getActivity(), "Hiba nincs ilyen játékos", Toast.LENGTH_SHORT).show();
                            } else {
                                name.setText(player.getName());
                                team.setText(player.getTeamName());
                                position.setText(player.getPosition());
                                jerseyNumber.setText(String.valueOf(player.getJerseyNumber()));
                            }
                        }

                        @Override
                        public void onFailure(Call<Player> call, Throwable t) {
                            Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }
}