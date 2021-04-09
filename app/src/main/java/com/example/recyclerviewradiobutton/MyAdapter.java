package com.example.recyclerviewradiobutton;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ModelClass> arrayList;
    private boolean isNewRadioButtonChecked = false;
    private int lastCheckedPosition = -1;

    public MyAdapter(MainActivity context, ArrayList<ModelClass> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelClass modelClass = arrayList.get(position);
        holder.textView.setText(modelClass.getName());

        if (isNewRadioButtonChecked) {
            holder.radioButton.setChecked(modelClass.isSelected());
        } else {
            if (holder.getAdapterPosition() == 0) {
                holder.radioButton.setChecked(true);
                lastCheckedPosition = 0;
            }
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ConstraintLayout rowItem;
        private RadioButton radioButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textView = itemView.findViewById(R.id.textView);
            this.rowItem = itemView.findViewById(R.id.rowitem);
            this.radioButton = itemView.findViewById(R.id.radioButton);

            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleRadiobuttonChecks(getAdapterPosition());
                }
            });

            rowItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, String.valueOf(arrayList.get(getAdapterPosition())),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void handleRadiobuttonChecks(int adapterPosition) {
        //Navin radiobutton check jhalay so junya button chi state false karaychi, navin button chi
        //state true karaychi ani lastCheckposition update karun current adapterposition thevaychi.

        isNewRadioButtonChecked = true;
        arrayList.get(lastCheckedPosition).setSelected(false);
        arrayList.get(adapterPosition).setSelected(true);
        lastCheckedPosition = adapterPosition;
        notifyDataSetChanged();

    }
}
