package com.example.retrofitplayers;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;


import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment2 newInstance(String param1, String param2) {
        Fragment2 fragment = new Fragment2();
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
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        EditText teamIdEt = view.findViewById(R.id.editTextTeamId);
        EditText nameEt = view.findViewById(R.id.editTextName);
        EditText positionEt = view.findViewById(R.id.editTextPosition);
        EditText jerseyNumberEt = view.findViewById(R.id.editTextJerseyNumber);

        Button insertButton = view.findViewById(R.id.insertButton);
        Button listButton = view.findViewById(R.id.listButton);

        final APIService service = RetrofitClient.getRetrofitInstance().create(APIService.class);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(teamIdEt.getText().toString()) || TextUtils.isEmpty(nameEt.getText().toString())
                        || TextUtils.isEmpty(positionEt.getText().toString())
                        || TextUtils.isEmpty(jerseyNumberEt.getText().toString())) {
                    Toast.makeText(getActivity(), "Kérem adja meg a hiányos mezőket is!", Toast.LENGTH_SHORT).show();
                } else {

                    int teamId = Integer.parseInt(teamIdEt.getText().toString());
                    String name = nameEt.getText().toString().trim();
                    String position = positionEt.getText().toString().trim();
                    int jerseyNumber = Integer.parseInt(jerseyNumberEt.getText().toString());

                    Call<Player> call = service.createPlayer(new Player(teamId, name, position, jerseyNumber));
                    call.enqueue(new Callback<Player>() {
                        @Override
                        public void onResponse(Call<Player> call, Response<Player> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getActivity(), "A beszúrás sikeres!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getActivity(), "A beszúrás sikertelen!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Player> call, Throwable t) {
                            Toast.makeText(getActivity(), "A beszúrás sikertelen!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ListActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}