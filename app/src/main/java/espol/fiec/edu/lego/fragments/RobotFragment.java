package espol.fiec.edu.lego.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

//import com.melnykov.fab.FloatingActionButton;
//import com.melnykov.fab.ScrollDirectionListener;
//import com.software.shell.fab.ActionButton;

import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionButton;

import java.util.List;

import espol.fiec.edu.lego.FirstActivity;
import espol.fiec.edu.lego.MenuActivity;
import espol.fiec.edu.lego.R;
import espol.fiec.edu.lego.adapters.RobotAdapter;
import espol.fiec.edu.lego.domain.Robot;
import espol.fiec.edu.lego.interfaces.RecyclerViewOnClickListenerHack;


/**
 * Created by mm on 10/03/2016.
 */
public class RobotFragment extends Fragment implements RecyclerViewOnClickListenerHack, View.OnClickListener {
    private static final String TAG = "LOG";
    private RecyclerView mRecyclerView;
    private List<Robot> mList;
    //private FloatingActionButton fab;
    //private ActionButton fab;
    private FloatingActionMenu fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_robot, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0){
                    fab.hideMenuButton(true);
                }
                else{
                    fab.showMenuButton(true);
                }

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                //GridLayoutManager llm = (GridLayoutManager) mRecyclerView.getLayoutManager();

                /*StaggeredGridLayoutManager llm = (StaggeredGridLayoutManager) mRecyclerView.getLayoutManager();
                int[] aux = llm.findFirstCompletelyVisibleItemPositions(null);
                int max = -1;
                for (int i = 0; i < aux.length; i++){
                    max = aux[i] > max ? aux[i] : max;
                }*/

                RobotAdapter adapter = (RobotAdapter) mRecyclerView.getAdapter();

                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    //if (mList.size() == max + 1) {
                    List<Robot> listAux = ((FirstActivity) getActivity()).getSetCarList(2);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), mRecyclerView, this));

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        //llm.setReverseLayout(true);
        mRecyclerView.setLayoutManager(llm);

        /*GridLayoutManager llm = new GridLayoutManager(getActivity(), 3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(llm);*/

        /*StaggeredGridLayoutManager llm = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        llm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        mRecyclerView.setLayoutManager(llm);*/

        mList =  ((FirstActivity)getActivity()).getSetCarList(2);
        RobotAdapter adapter = new RobotAdapter(getActivity(), mList);
        //adapter.setRecyclerViewOnClickListenerHack(this);
        mRecyclerView.setAdapter(adapter);

        fab = (FloatingActionMenu) getActivity().findViewById(R.id.fab);
        fab.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                Toast.makeText(getActivity(), "Is menu opened? "+(opened ? "true" : "false" ), Toast.LENGTH_SHORT).show();
            }
        });
        fab.setClosedOnTouchOutside(true);

        FloatingActionButton fab1 = (FloatingActionButton) getActivity().findViewById(R.id.fab1);
        FloatingActionButton fab2 = (FloatingActionButton) getActivity().findViewById(R.id.fab2);
        //FloatingActionButton fab3 = (FloatingActionButton) getActivity().findViewById(R.id.fab3);
        //FloatingActionButton fab4 = (FloatingActionButton) getActivity().findViewById(R.id.fab4);
        //FloatingActionButton fab5 = (FloatingActionButton) getActivity().findViewById(R.id.fab5);

        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        //fab3.setOnClickListener(this);
        //fab4.setOnClickListener(this);
        //fab5.setOnClickListener(this);

        /*fab = (ActionButton) getActivity().findViewById(R.id.fab);

        fab.setButtonColor(getActivity().getResources().getColor(R.color.colorFAB));
        fab.setButtonColor( getActivity().getResources().getColor(R.color.colorFABPressed));

        fab.setShowAnimation(ActionButton.Animations.SCALE_UP);
        fab.setHideAnimation(ActionButton.Animations.SCALE_DOWN);

        fab.setImageResource(R.drawable.ic_plus);

        float scale = getActivity().getResources().getDisplayMetrics().density;
        int shadow = (int)(3 * scale + 0.5);
        fab.setShadowRadius(shadow);

        fab.setOnClickListener(this);
        fab.playShowAnimation();

        /* fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.attachToRecyclerView(mRecyclerView, new ScrollDirectionListener() {
            @Override
            public void onScrollDown() {

            }

            @Override
            public void onScrollUp() {

            }
        }, new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                //GridLayoutManager llm = (GridLayoutManager) mRecyclerView.getLayoutManager();

                /*StaggeredGridLayoutManager llm = (StaggeredGridLayoutManager) mRecyclerView.getLayoutManager();
                int[] aux = llm.findFirstCompletelyVisibleItemPositions(null);
                int max = -1;
                for (int i = 0; i < aux.length; i++){
                    max = aux[i] > max ? aux[i] : max;
                }*/

                /*RobotAdapter adapter = (RobotAdapter) mRecyclerView.getAdapter();

                if (mList.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    //if (mList.size() == max + 1) {
                    List<Robot> listAux = ((FirstActivity) getActivity()).getSetCarList(2);

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), mList.size());
                    }
                }
            }
        });
        fab.setOnClickListener(this);*/

        return view;
    }

    @Override
    public void onClickListener(View view, int position) {
        Toast.makeText(getActivity(),"onClickListener(): " + position, Toast.LENGTH_SHORT).show();

     /*   RobotAdapter adapter = (RobotAdapter) mRecyclerView.getAdapter();
        adapter.removeListItem(position);*/
    }

    @Override
    public void onLongPressClickListener(View view, int position) {
        Toast.makeText(getActivity(),"onLongPressClickListener(): " + position, Toast.LENGTH_SHORT).show();

       /* RobotAdapter adapter = (RobotAdapter) mRecyclerView.getAdapter();
        adapter.removeListItem(position);*/
    }

    @Override
    public void onClick(View v) {
        //String aux = "";
        Intent it = null;

        switch (v.getId()){
            case R.id.fab1:
                //aux = "Fab 1 clicked";
                it = new Intent(Intent.ACTION_VIEW);
                it.setData(Uri.parse("https://www.facebook.com/LEGO"));
                startActivity(it);
                break;
            case R.id.fab2:
                //aux = "Fab 2 clicked";
                startActivity(new Intent(this.getContext(), MenuActivity.class ));
                break;
            /*case R.id.fab3:
                aux = "Fab 3 clicked";
                break;
            case R.id.fab4:
                aux = "Fab 4 clicked";
                break;
            case R.id.fab5:
                aux = "Fab 5 clicked";
                break;*/

        }

        //Toast.makeText(getActivity(), aux, Toast.LENGTH_SHORT).show();
    }

    private static class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
        private Context mContext;
        private GestureDetector mGestureDetector;
        private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

        public RecyclerViewTouchListener(Context c, final RecyclerView rv, RecyclerViewOnClickListenerHack rvoclh){
            mContext = c;
            mRecyclerViewOnClickListenerHack = rvoclh;

            mGestureDetector = new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener(){
                @Override
                public void onLongPress(MotionEvent e) {
                    super.onLongPress(e);

                    View cv = rv.findChildViewUnder(e.getX(),e.getY());

                    if(cv != null && mRecyclerViewOnClickListenerHack != null){
                        mRecyclerViewOnClickListenerHack.onLongPressClickListener(cv, rv.getChildPosition(cv));
                    }
                }

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    View cv = rv.findChildViewUnder(e.getX(),e.getY());

                    if(cv != null && mRecyclerViewOnClickListenerHack != null){
                        mRecyclerViewOnClickListenerHack.onClickListener(cv, rv.getChildPosition(cv));
                    }
                    return (true);
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetector.onTouchEvent(e);
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {}

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
    }
}
