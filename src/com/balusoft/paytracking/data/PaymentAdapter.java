package com.balusoft.paytracking.data;


import com.balusoft.paytracking.R;
import com.balusoft.paytracking.model.Payment;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PaymentAdapter extends ArrayAdapter<Payment> {

	public PaymentAdapter(Context context, int layoutResourceId,Payment[] data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	Context context;
	int layoutResourceId;
	Payment[] data = null;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = convertView;
		PaymentHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new PaymentHolder();

			holder.txtSubject = (TextView) row.findViewById(R.id.txtSubject);
			holder.txtAmount=(TextView)row.findViewById(R.id.txtAmount);

			row.setTag(holder);
		} else {
			holder = (PaymentHolder) row.getTag();
		}

		Payment payment = data[position];
		holder.txtSubject.setText(payment.getSubject());
		holder.txtAmount.setText(""+payment.getAmount());
		
		return row;
	}

	static class PaymentHolder {
		TextView txtSubject;
		TextView txtAmount;
	}

}
