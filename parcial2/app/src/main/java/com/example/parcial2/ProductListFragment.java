package com.example.parcial2;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.parcial2.entitites.Product;
import com.example.parcial2.helpers.ButtonHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static int RESULT_LOAD_IMAGE = 1;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView productImage;

    public ProductListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductListFragment newInstance(String param1, String param2) {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragmentº
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        //Obteniendo la informacion necesaria a traves de la base de datos
        List<Product> productList = Product.getProducts(this.getContext());
        System.out.println("El size de productos es: " + productList.size());

        //Mostrando el float action button
        ButtonHelper.SwitchCallCreateProductButton((FloatingActionButton) getActivity().findViewById(R.id.callCreateProductButton), false);

        RecyclerView recyclerView = view.findViewById(R.id.productRecyclerView);
        ProductListAdapter productListAdapter = new ProductListAdapter(this.getContext(), productList, this.getActivity());
        recyclerView.setAdapter(productListAdapter);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        Toolbar toolbar = getActivity().findViewById(R.id.toolBar);
        Menu menu = toolbar.getMenu();
        MenuItem addNewProductMenuButton = menu.findItem(R.id.addNewProductMenuButton);
        addNewProductMenuButton.setVisible(true);
        addNewProductMenuButton.setEnabled(true);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                AddProductFragment addProductFragment = new AddProductFragment();
                fragmentTransaction.replace(R.id.fragment_container, addProductFragment).addToBackStack(null);
                fragmentTransaction.commit();
                return true;
            }
        });
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        //Mostrando el float action button
        ButtonHelper.SwitchCallCreateProductButton((FloatingActionButton) getActivity().findViewById(R.id.callCreateProductButton), false);
    }

}