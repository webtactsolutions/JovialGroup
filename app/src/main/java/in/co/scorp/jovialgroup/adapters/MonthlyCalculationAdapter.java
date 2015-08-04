package in.co.scorp.jovialgroup.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import in.co.scorp.jovialgroup.R;
import in.co.scorp.jovialgroup.models.LoanMonthly;

/**
 * Created by root on 7/28/15.
 */
public class MonthlyCalculationAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<LoanMonthly> list;

    public MonthlyCalculationAdapter(Context context, ArrayList<LoanMonthly> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(this.context).inflate(R.layout.single_loan_payment,null);

        TextView tvMonth, tvYear, tvInterest, tvPrincipleAmount, tvBalance;

        tvBalance = (TextView) view.findViewById(R.id.tv_monthly_balance);
        tvInterest = (TextView) view.findViewById(R.id.tv_monthly_interest);
        tvPrincipleAmount = (TextView) view.findViewById(R.id.tv_monthly_principle_amount);
//        tvMonth = (TextView) view.findViewById(R.id.tv_monthly_month);
//        tvYear = (TextView) view.findViewById(R.id.tv_monthly_year);

        if(list.get(i).getBalance().equalsIgnoreCase("0")){
            tvBalance.setText("Completed");
        }else {
            tvBalance.setText(list.get(i).getBalance());
        }
        tvInterest.setText(list.get(i).getInterest());
//        tvMonth.setText(list.get(i).getMonth());
//        tvYear.setText(list.get(i).getYear());
        tvPrincipleAmount.setText(list.get(i).getPrincipleAmount());

        return view;
    }
}
