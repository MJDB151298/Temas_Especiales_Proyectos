package com.example.parcial2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.*;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.parcial2.database.DBManagerProducts;
import com.example.parcial2.entitites.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddProductFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddProductFragment newInstance(String param1, String param2) {
        AddProductFragment fragment = new AddProductFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);


        Bundle bundle = getArguments();
        if(bundle != null && bundle.containsKey("PRODUCT_NAME")){
            TextView productNameTextView = view.findViewById(R.id.productNameTextView);
            productNameTextView.setText(bundle.getString("PRODUCT_NAME"));
        }

        if(bundle != null && bundle.containsKey("PRODUCT_PRICE")){
            TextView productPriceText = view.findViewById(R.id.productPriceTextView);
            productPriceText.setText(bundle.getString("PRODUCT_PRICE"));
        }

        //Llenando el spinner con las categorias
        List<String> categoryList = Category.getCategories(this.getContext());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this.getContext(), android.R.layout.simple_spinner_item, categoryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner categorySpinner = (Spinner) view.findViewById(R.id.categorySpinner);
        categorySpinner.setAdapter(adapter);

        if(bundle != null && bundle.containsKey("CATEGORY_NAME")){
            int position = adapter.getPosition(bundle.getString("CATEGORY_NAME"));
            categorySpinner.setSelection(position);
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        final TextView productNameTextView = getView().findViewById(R.id.productNameTextView);
        final TextView productPriceText = getView().findViewById(R.id.productPriceTextView);
        final Button saveProductButton = getView().findViewById(R.id.saveProductButton);
        Button addCategoryButton = getView().findViewById(R.id.addCategoryButton);
        final Spinner categorySpinner = getView().findViewById(R.id.categorySpinner);
        saveProductButton.setEnabled(false);



        productNameTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(productNameTextView.getText().toString().length() > 0 && productPriceText.getText().toString().length() > 0){
                    saveProductButton.setEnabled(true);
                }
                else{
                    saveProductButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        productPriceText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(productNameTextView.getText().toString().length() > 0 && productPriceText.getText().toString().length() > 0){
                    saveProductButton.setEnabled(true);
                }
                else{
                    saveProductButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        saveProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = productNameTextView.getText().toString();
                int productPrice = 0;
                try{
                    productPrice = Integer.parseInt(productPriceText.getText().toString());
                }catch(Exception e){
                    System.out.println("OOPS");
                }

                Category category = Category.getCategoryByName(v.getContext(), categorySpinner.getSelectedItem().toString());
                System.out.println(category.getId() + " - " + category.getName());
                DBManagerProducts dbManagerProducts = new DBManagerProducts(v.getContext()).open();
                dbManagerProducts.insert(productName, productPrice, category.getId());
                dbManagerProducts.close();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(fragmentManager.findFragmentById(R.id.fragment_container));
                ProductListFragment productListFragment = new ProductListFragment();
                fragmentTransaction.add(R.id.fragment_container, productListFragment);
                fragmentTransaction.commit();
            }
        });

        addCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = productNameTextView.getText().toString();
                int productPrice = 0;
                try{
                    productPrice = Integer.parseInt(productPriceText.getText().toString());
                }catch(Exception e){
                    System.out.println("OOPS");
                }

                Bundle bundle = new Bundle();
                bundle.putString("PRODUCT_NAME", productName);
                bundle.putString("PRODUCT_PRICE", Integer.toString(productPrice));

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //fragmentTransaction.remove(fragmentManager.findFragmentById(R.id.fragment_container));
                AddCategoryFragment addCategoryFragment = new AddCategoryFragment();
                addCategoryFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, addCategoryFragment, "ADD_CATEGORY").addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}