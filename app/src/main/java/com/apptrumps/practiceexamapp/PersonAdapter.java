package com.apptrumps.practiceexamapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by David on 03-Sep-17.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private int numItems;
    private ArrayList<Person> personList;
    private ListItemClickListener onClickListener;

    public PersonAdapter(ArrayList<Person> personList, ListItemClickListener listener){
        this.personList = personList;
        numItems = personList.size();
        onClickListener = listener;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_person, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.txtName.setText(personList.get(position).getName());
        holder.txtAge.setText(personList.get(position).getAge() + "");
        holder.txtHeight.setText(personList.get(position).getHeight());
        holder.txtPhone.setText(personList.get(position).getPhoneNumber() + "");
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtName;
        TextView txtAge;
        TextView txtHeight;
        TextView txtPhone;

        public PersonViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txt_name);
            txtAge = (TextView) itemView.findViewById(R.id.txt_age);
            txtHeight = (TextView) itemView.findViewById(R.id.txt_height);
            txtPhone = (TextView) itemView.findViewById(R.id.txt_phone);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onLIstItemClick(getAdapterPosition());
        }
    }

    public interface ListItemClickListener{
        void onLIstItemClick(int clickedItem);
    }
}
