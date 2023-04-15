package com.ecclesiav2;

import static com.github.mikephil.charting.utils.ColorTemplate.rgb;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;

public class ResultsDrinkFragment extends Fragment {
    private PieChart pieChart;
    public static final int[] PIE_COLORS = {
            rgb("#648FFF"), rgb("#FFB000"), rgb("#FE6100"), rgb("#DC267F"), rgb("#9A9FB3")
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_results_drink, container, false);

        Election election = getArguments().getParcelable("election");
        TextView elecQuestion = v.findViewById(R.id.elecQuestion);
        elecQuestion.setText(election.getQuestion());
        TextView elecSelected = v.findViewById(R.id.selectedOption);

        if (election.getNeedReCast()==0){
            elecSelected.setText("You voted for " + election.getOptions().get(election.getSelectedIndex()));
        } else {
            elecSelected.setText("Your vote could not be recorded");
        }

        pieChart = v.findViewById(R.id.chart);
        setupPieChart();
        loadPieChart();

        return v;
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(false);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawEntryLabels(false);
        pieChart.getLegend().setEnabled(false);
    }

    private void loadPieChart(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(0.25f, "Water"));
        entries.add(new PieEntry(0.4f, "Coke"));
        entries.add(new PieEntry(0.25f, "Sprite"));
        entries.add(new PieEntry(0.1f, "Fanta"));

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: PIE_COLORS){
            colors.add(color);
        }

        PieDataSet dataset= new PieDataSet(entries, "Drinks");
        dataset.setColors(colors);

        PieData data = new PieData(dataset);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.WHITE);

        pieChart.setData(data);
        pieChart.invalidate();
    }
}