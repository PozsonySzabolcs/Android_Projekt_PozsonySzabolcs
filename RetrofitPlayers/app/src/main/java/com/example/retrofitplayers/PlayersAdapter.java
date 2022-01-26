package com.example.retrofitplayers;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.VH> {
    private Activity mContext;
    private List<Player> mPlayers;

    public PlayersAdapter(Activity mContext, List<Player> mPlayers) {
        this.mContext = mContext;
        this.mPlayers = mPlayers;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new VH(itemView, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Player player = mPlayers.get(position);
        holder.rootView.setTag(player);
        holder.name.setText(player.getName());
        holder.team.setText(player.getTeamName());
        holder.position.setText(player.getPosition());
        holder.jerseyNumber.setText(String.valueOf(player.getJerseyNumber()));
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }

    public class VH extends RecyclerView.ViewHolder {
        final View rootView;
        final TextView name;
        final TextView team;
        final TextView position;
        final TextView jerseyNumber;

        public VH(@NonNull View itemView, final Context context) {
            super(itemView);
            rootView = itemView;
            name = itemView.findViewById(R.id.name1);
            team = itemView.findViewById(R.id.team1);
            position = itemView.findViewById(R.id.position1);
            jerseyNumber = itemView.findViewById(R.id.jerseyNumber1);
        }
    }
}
