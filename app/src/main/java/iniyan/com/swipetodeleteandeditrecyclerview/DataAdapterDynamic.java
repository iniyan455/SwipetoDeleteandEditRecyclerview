package iniyan.com.swipetodeleteandeditrecyclerview;

        import android.app.Activity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.LinearLayout;
        import android.widget.ProgressBar;
        import android.widget.TextView;

        import java.util.ArrayList;



public class DataAdapterDynamic extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Item> items;
    private final  int View_Type_ITEM=0,View_Type_Loading=1;
    ILoadMore loadMore;
    boolean isLoading;
    Activity activity;
    //ArrayList<Item> items;
    int VisibileThreshold=5;
    int LastVisibleItem,totalVisibileItemCount;

    public DataAdapterDynamic(RecyclerView recyclerView,Activity activity,ArrayList<Item> countries) {
        this.items = countries;
        this.activity = activity;





        final LinearLayoutManager linearLayoutManager=(LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalVisibileItemCount=linearLayoutManager.getItemCount();
                LastVisibleItem=linearLayoutManager.findLastVisibleItemPosition();
                if(!isLoading && totalVisibileItemCount <=(LastVisibleItem+VisibileThreshold)){

                    if(loadMore !=null){
                        loadMore.onLoadMore();
                        isLoading=true;
                    }




                }

            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position)==null ? View_Type_Loading : View_Type_ITEM ;
    }


    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }

    @Override
    public DataAdapterDynamic.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int ViewType) {

        if(ViewType ==View_Type_ITEM){

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout_adapter, viewGroup, false);
            return new ViewHolder(view);
        }
        else if(ViewType == View_Type_Loading) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_loading, viewGroup, false);
            return new ViewHolder(view);

        }
        return  null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if(holder instanceof ViewHolder){



            Item item= items.get(position);
            //holder.tv_country.setText(countries.get(position));
            ViewHolder viewHolder= (ViewHolder) holder;
            viewHolder.tv_country.setText(items.get(position).getCountry());
            viewHolder.tv_length.setText(String.valueOf(items.get(position).getLength()));


        }
        else  if(holder instanceof LoadingViewHolder){



            LoadingViewHolder loadHolder= (LoadingViewHolder) holder;
            loadHolder.progressBar.setIndeterminate(true);


        }



    }

    @Override
    public int getItemCount() {
        return items.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_country,tv_length;
        public ViewHolder(View view) {
            super(view);

            tv_country = (TextView)view.findViewById(R.id.tv_country);
            tv_length = (TextView)view.findViewById(R.id.length);


        }
    }



    public class LoadingViewHolder extends RecyclerView.ViewHolder {


        public ProgressBar progressBar;
        public  LoadingViewHolder(View itemView){

            super(itemView);
            progressBar =(ProgressBar)itemView.findViewById(R.id.progress);
        }
    }

    public void setLoading() {
        isLoading = false;
    }
}