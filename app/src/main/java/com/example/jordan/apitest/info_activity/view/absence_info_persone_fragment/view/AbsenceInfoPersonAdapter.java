package com.example.jordan.apitest.info_activity.view.absence_info_persone_fragment.view;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jordan.apitest.AbsenceTypeForId;
import com.example.jordan.apitest.R;
import com.example.jordan.apitest.enteties.models.Absence;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.text.ParseException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Богдан on 23.02.2016.
 */
public class AbsenceInfoPersonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<Absence> list;

    private AbsenceRecycleViewCallback mAbsenceRecycleViewCallback;


    public AbsenceInfoPersonAdapter(List<Absence> list, AbsenceRecycleViewCallback mAbsenceRecycleViewCallback){
        this.list = list;
        this.mAbsenceRecycleViewCallback = mAbsenceRecycleViewCallback;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        if (viewType == TYPE_HEADER) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_item_absence_info, parent, false);

            HeaderViewHolder vh = new HeaderViewHolder(v);

            return vh;
        }

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_absence_info, parent, false);
        AbsenceViewHolder pvh = new AbsenceViewHolder(v);
        pvh.setAbsenceRecycleViewCallback(mAbsenceRecycleViewCallback);
        return pvh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof AbsenceViewHolder) {
            AbsenceViewHolder viewHolder = (AbsenceViewHolder) holder;
                try {
                    Absence absence = getItem(position - 1);
                    fullDataAbsenceInfo(viewHolder, absence);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
            if (holder instanceof HeaderViewHolder) {
                HeaderViewHolder vh = (HeaderViewHolder) holder;
            }
        }
    }


    @Override
    public int getItemCount() {

        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(isPositionHeader (position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader (int position) {
        return position == 0;
    }

    private Absence getItem (int position) {
        return list.get (position);
    }

    public void fullDataAbsenceInfo(AbsenceViewHolder viewHolder, Absence absence) throws ParseException {
            viewHolder.typeName.setText(AbsenceTypeForId.readAbsenceTypeId(absence.getAbsenceTypeId()));
            viewHolder.status.setText(AbsenceTypeForId.readStatusId(absence.getStatusId()));
            viewHolder.startTime.setText(dateFormat(absence.getStartDateTime()));
            viewHolder.endTime.setText(dateFormat(absence.getEndDateTime()));
    }

    public String dateFormat(String time) throws ParseException {
        DateTimeZone timeZone = DateTimeZone.forID("Europe/Amsterdam");
        String t = time.substring(0,22);
        DateTime date = new DateTime(t, timeZone);
        return date.toString("dd-MM-yyyy HH:mm");
    }

    public class AbsenceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AbsenceRecycleViewCallback mAbsenceRecycleViewCallback;

        @Nullable
        @Bind(R.id.typeNameTextView)
        protected TextView typeName;

        @Nullable
        @Bind(R.id.statusTextView)
        protected TextView status;

        @Nullable
        @Bind(R.id.startTimeTextView)
        protected TextView startTime;

        @Nullable
        @Bind(R.id.tvEndTime)
        protected TextView endTime;


        @Bind(R.id.deleteAbsence)
        protected ImageButton deleteAbsence;

        @Bind(R.id.bUpdate)
        ImageButton updateButton;

        @Bind(R.id.progressBar)
        protected ProgressBar progressBar;

        public AbsenceViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
            deleteAbsence.setOnClickListener(this);
            updateButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.deleteAbsence:
                    mAbsenceRecycleViewCallback.onDeleteClick(getAdapterPosition(), progressBar );
                  //  progressBar.setVisibility(View.VISIBLE);
                    break;
                case R.id.bUpdate:
                    mAbsenceRecycleViewCallback.onUpdateClick(getAdapterPosition(),progressBar );
                    break;
                default:
                    break;
            }
        }

        public void setAbsenceRecycleViewCallback(AbsenceRecycleViewCallback mAbsenceRecycleViewCallback) {
            this.mAbsenceRecycleViewCallback = mAbsenceRecycleViewCallback;
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {



        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }
}
