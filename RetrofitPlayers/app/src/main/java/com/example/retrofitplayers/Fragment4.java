package com.example.retrofitplayers;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
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
 * Use the {@link Fragment4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment4 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private boolean getId = false;

    public Fragment4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment4.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment4 newInstance(String param1, String param2) {
        Fragment4 fragment = new Fragment4();
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
        View view = inflater.inflate(R.layout.fragment_4, container, false);

        EditText editId = view.findViewById(R.id.id2);

        EditText teamIdEt = view.findViewById(R.id.editTextTeamId1);
        EditText nameEt = view.findViewById(R.id.editTextName1);
        EditText positionEt = view.findViewById(R.id.editTextPosition1);
        EditText jerseyNumberEt = view.findViewById(R.id.editTextJerseyNumber1);

        Button getButton = view.findViewById(R.id.getButton1);
        Button listButton = view.findViewById(R.id.listButton2);
        Button updateButton = view.findViewById(R.id.updateButton);

        final APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListActivity.class);
                startActivity(intent);
            }
        });

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamIdEt.setText("");
                nameEt.setText("");
                positionEt.setText("");
                jerseyNumberEt.setText("");

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
                                getId = true;
                                teamIdEt.setText(String.valueOf(player.getTeamId()));
                                nameEt.setText(player.getName());
                                positionEt.setText(player.getPosition());
                                jerseyNumberEt.setText(String.valueOf(player.getJerseyNumber()));
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

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getId) {
                    if (TextUtils.isEmpty(editId.getText().toString()) || TextUtils.isEmpty(teamIdEt.getText().toString()) || TextUtils.isEmpty(nameEt.getText().toString())
                            || TextUtils.isEmpty(positionEt.getText().toString())
                            || TextUtils.isEmpty(jerseyNumberEt.getText().toString())) {
                        Toast.makeText(getActivity(), "Kérem adja meg a hiányos mezőket is!", Toast.LENGTH_SHORT).show();
                    } else {
                        int id = Integer.parseInt(editId.getText().toString());
                        int teamId = Integer.parseInt(teamIdEt.getText().toString());
                        String name = nameEt.getText().toString();
                        String position = positionEt.getText().toString();
                        int jerseyNumber = Integer.parseInt(jerseyNumberEt.getText().toString());

                        Call<Player> call = service.updatePlayer(id, new Player(teamId, name, position, jerseyNumber));
                        call.enqueue(new Callback<Player>() {
                            @Override
                            public void onResponse(Call<Player> call, Response<Player> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getActivity(), "A módosítás sikeres!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "A módosítás sikertelen!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Player> call, Throwable t) {
                                Toast.makeText(getActivity(), "A módosítás sikertelen!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    Toast.makeText(getActivity(), "Először kérje le a játékost!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}