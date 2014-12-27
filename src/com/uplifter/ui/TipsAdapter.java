package com.uplifter.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.uplifter.R;
import com.uplifter.model.TipModel;

public class TipsAdapter extends ArrayAdapter<TipModel> {
    private static final int [] COLOURS = {R.color.green, R.color.blue, R.color.yellow, R.color.orange, R.color.purple};

    private final Context _context;
    private final TipModel[] _tips;

    public TipsAdapter(final Context context, final TipModel[] tips) {
        super(context, R.layout.tip_layout, tips);
        _context = context;
        _tips = tips;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.tip_layout, parent, false);
        rowView.setBackgroundColor(_context.getResources().getColor(COLOURS[position % COLOURS.length]));
        ((TextView) rowView.findViewById(R.id.title)).setText(_tips[position].getTitle());
        ((TextView) rowView.findViewById(R.id.body)).setText(_tips[position].getTip());
        return rowView;
    }
}
