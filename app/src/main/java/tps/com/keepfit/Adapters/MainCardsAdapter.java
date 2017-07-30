package tps.com.keepfit.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import tps.com.keepfit.DataModel.CardsDataModel_;
import tps.com.keepfit.R;

/**
 * Created by aayman on 7/30/2017.
 */

public class MainCardsAdapter extends RecyclerView.Adapter<MainCardsAdapter.MainCardsHolder> {

    Context mContext;
    List<CardsDataModel_> mCardListData;
    Picasso mPicasso;

    public MainCardsAdapter(Context context, Picasso picasso) {
        mContext = context;
        mPicasso = picasso;
    }

    public void swapAdapterData(List<CardsDataModel_> cardListData) {
        mCardListData = cardListData;
        notifyDataSetChanged();
    }

    @Override
    public MainCardsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_card_view, parent, false);

        return new MainCardsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainCardsHolder holder, int position) {
        /*mPicasso.with(mContext)
                .load((int) mCardListData.get(position).getCardImage())
                .error(R.drawable.jumping)
                .into(holder.cardImage);*/
        if ((int) mCardListData.get(position).getCardImage() != -1)
            holder.cardImage.setImageResource((int) mCardListData.get(position).getCardImage());
        holder.cardDuration.setText(holder.duration + " " + mCardListData.get(position).getCardDuration());
        holder.cardName.setText(mCardListData.get(position).getCardName());

        final String fullUrl = holder.baseURL + mCardListData.get(position).getCardVideoURL();
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "" + fullUrl, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCardListData.size();
    }

    class MainCardsHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_card)
        ImageView cardImage;

        @BindView(R.id.item_card_tv_burned_calories)
        TextView burnedCalories;

        @BindView(R.id.item_card_tv_card_name)
        TextView cardName;
        @BindView(R.id.item_card_tv_duration)
        TextView cardDuration;

        @BindString(R.string.labelDuration)
        String duration;
        @BindString(R.string.burned_calories)
        String burnedCaloriesLabel;
        @BindString(R.string.baseURL)
        String baseURL;

        View mView;

        public MainCardsHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }
    }
}
