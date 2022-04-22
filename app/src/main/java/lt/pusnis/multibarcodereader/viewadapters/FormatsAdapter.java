package lt.pusnis.multibarcodereader.viewadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import lt.pusnis.multibarcodereader.R;
import lt.pusnis.multibarcodereader.model.MbrFormats;

public class FormatsAdapter extends RecyclerView.Adapter<FormatsAdapter.ViewHolder> {
    List<MbrFormats> list;
    Context context;

    public FormatsAdapter(List<MbrFormats> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void addList(List<MbrFormats> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FormatsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new ViewHolder(LayoutInflater.from(parent.getContext()))
//                .inflate(R.layout.recyclerview_item,parent,false);

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.codeFormat.setText(new StringBuilder().append(list.get(position).getId()));
        holder.codeType.setText(new StringBuilder().append(list.get(position).getId()));
        holder.codeValue.setText(list.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

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
