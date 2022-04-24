package lt.pusnis.multibarcodereader.viewadapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import lt.pusnis.multibarcodereader.R;

import lt.pusnis.multibarcodereader.model.MbrResults;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.ViewHolder> {
    List<MbrResults> list;
    Context context;

    public ResultsAdapter(List<MbrResults> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void addList(List<MbrResults> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ResultsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.codeFormat.setText(new StringBuilder().append(list.get(position).getCodeFormat().getDescription()));
        holder.codeType.setText(new StringBuilder().append(list.get(position).getCodeType().getDescription()));
        holder.codeValue.setText(list.get(position).getResult());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView codeFormat;
        TextView codeType;
        TextView codeValue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            codeFormat = itemView.findViewById(R.id.codeFormat);
            codeType = itemView.findViewById(R.id.codeType);
            codeValue = itemView.findViewById(R.id.codeValue);
        }
    }
}
