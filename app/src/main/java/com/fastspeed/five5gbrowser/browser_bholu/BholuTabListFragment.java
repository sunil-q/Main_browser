package com.fastspeed.five5gbrowser.browser_bholu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.fastspeed.five5gbrowser.R;
import com.fastspeed.five5gbrowser.databinding.FragmentTabListBholuBinding;

import java.util.List;
import java.util.Objects;


public class BholuTabListFragment extends Fragment {

    public static final String TAB_OPENBholu = "OPEN";
    public static final String TAB_ADDBholu = "ADD";
    public static final String CLEARALLBholu = "CLEAR";
    private static final String TAGBholu = "web:tablist";

    ListFragmentListnerBholu listFragmentListnerBholu;
    FragmentTabListBholuBinding bindingBholu;
    private BholuTabAdapter tabAdapterBholu;
    private List<BholuBrowserFragment> listBholu;

    public BholuTabListFragment(List<BholuBrowserFragment> list, ListFragmentListnerBholu listFragmentListner) {

        this.listBholu = list;
        this.listFragmentListnerBholu = listFragmentListner;
    }

    public ListFragmentListnerBholu getListFragmentListnerBholu() {
        return listFragmentListnerBholu;
    }

    public void setListFragmentListnerBholu(ListFragmentListnerBholu listFragmentListnerBholu) {
        this.listFragmentListnerBholu = listFragmentListnerBholu;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bindingBholu = DataBindingUtil.inflate(inflater, R.layout.fragment_tab_list_bholu, container, false);
        return bindingBholu.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for (int i = 0; i < listBholu.size(); i++) {
            Log.d(TAGBholu, "onCreate: " + listBholu.get(i));
        }

        tabAdapterBholu = new BholuTabAdapter(listBholu, (websiteModel, pos, work) -> {
            if (work.equals("CLOSE")) {
                listBholu.remove(pos);
                tabAdapterBholu.notifyItemRemoved(pos);
                listFragmentListnerBholu.onClickRemoveBholu(websiteModel, pos);
                Objects.requireNonNull(getActivity()).onBackPressed();
            }
            if (work.equals("OPEN")) {
                listFragmentListnerBholu.onListClickBholu(websiteModel, pos);
                Objects.requireNonNull(getActivity()).onBackPressed();
            }
        });
        bindingBholu.rvtabsBholu.setAdapter(tabAdapterBholu);

        bindingBholu.imgaddBholu.setOnClickListener(v -> {
            listFragmentListnerBholu.onClickAddBholu();
            Objects.requireNonNull(getActivity()).onBackPressed();
        });
        bindingBholu.imgbookmarkBholu.setOnClickListener(v ->
                listFragmentListnerBholu.onClickBookmarkBholu());
        bindingBholu.imghistryBholu.setOnClickListener(v ->
                listFragmentListnerBholu.onClickHistoryBholu());

        bindingBholu.imgbackBholu.setOnClickListener(v -> getActivity().onBackPressed());
    }

    public interface ListFragmentListnerBholu {
        void onListClickBholu(BholuBrowserFragment browserFragment, int pos);

        void onClickRemoveBholu(BholuBrowserFragment browserFragment, int pos);

        void onClickAddBholu();

        void closeAllBholu();

        void onClickBookmarkBholu();

        void onClickHistoryBholu();
    }
}
