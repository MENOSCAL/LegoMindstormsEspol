package espol.fiec.edu.lego.adapters;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import espol.fiec.edu.lego.R;
import espol.fiec.edu.lego.asynctasks.ImageLoad;
import espol.fiec.edu.lego.asynctasks.ImageLoadTask;
import espol.fiec.edu.lego.domain.Robot;
import espol.fiec.edu.lego.extras.ImageHelper;
import espol.fiec.edu.lego.interfaces.RecyclerViewOnClickListenerHack;

/**
 * Created by mm on 11/03/2016.
 */
public class RobotAdapter extends RecyclerView.Adapter<RobotAdapter.MyViewHolder> {
    private Context mContext;
    private List<Robot> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
    private float scale;
    private int width;
    private int height;

    public RobotAdapter(Context c, List<Robot> l){
        mContext = c;
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        scale = mContext.getResources().getDisplayMetrics().density;
        width = mContext.getResources().getDisplayMetrics().widthPixels - (int)(14 * scale + 0.5f);
        height = (width / 16) * 9;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Log.i("LOG","onCreateViewHolder");
        View v = mLayoutInflater.inflate(R.layout.item_robot_card, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //Log.i("LOG","onBindViewHolder");
        holder.tvModel.setText(mList.get(position).getTitle());
        holder.tvBrand.setText(mList.get(position).getBrand());
/*
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            //holder.ivCar.setImageResource(mList.get(position).getPhoto());
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), mList.get(position).getPhoto());
            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);

            bitmap = ImageHelper.getRoundedCornerBitmap(mContext, bitmap, 10, width, height, false, false, true, true);
            holder.ivRobot.setImageBitmap(bitmap);

        }else{
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), mList.get(position).getPhoto());
            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);

            bitmap = ImageHelper.getRoundedCornerBitmap(mContext, bitmap, 10, width, height, false, false, true, true);
            holder.ivRobot.setImageBitmap(bitmap);
        }
*/

        //new ImageLoad("http://www.corporacionsmartest.com/lego_mindstorm/bloques/"+mList.get(position).getUrl()+".jpg" , holder.ivRobot, mContext, width, height).execute();
 //esto de abajo es la que funciona
 //       new ImageLoad(mList.get(position).getUrl() , holder.ivRobot, mContext, width, height).execute();

        /*try {
            YoYo.with(Techniques.Tada)
                    .duration(700)
                    .playOn(holder.itemView);
        }catch (Exception e){

        }*/

        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.app);
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height, false);

        bitmap = ImageHelper.getRoundedCornerBitmap(mContext, bitmap, 10, width, height, false, false, true, true);
        holder.ivRobot.setImageBitmap(bitmap);


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        mRecyclerViewOnClickListenerHack = r;
    }

    public void addListItem(Robot c, int position){
        mList.add(c);
        notifyItemInserted(position);
    }

    public void removeListItem(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView ivRobot;
        public TextView tvModel;
        public TextView tvBrand;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivRobot =(ImageView)itemView.findViewById(R.id.iv_robot);
            tvModel =(TextView)itemView.findViewById(R.id.tv_model);
            tvBrand =(TextView)itemView.findViewById(R.id.tv_brand);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListenerHack != null){
                mRecyclerViewOnClickListenerHack.onClickListener(v, getPosition());
            }
        }
    }
}
