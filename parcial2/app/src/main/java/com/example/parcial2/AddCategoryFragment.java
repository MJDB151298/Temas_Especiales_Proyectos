package com.example.parcial2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.parcial2.database.DBManagerCategory;
import com.example.parcial2.helpers.MenuItemHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddCategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddCategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCategoryFragment newInstance(String param1, String param2) {
        AddCategoryFragment fragment = new AddCategoryFragment();
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
        MenuItemHelper.SwitchMenuItem(getActivity());
        return inflater.inflate(R.layout.fragment_add_category, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        final TextView categoryNameText = getView().findViewById(R.id.categoryNameText);
        final Button saveCategoryButton = getView().findViewById(R.id.saveCategoryButton);

        saveCategoryButton.setEnabled(false);

        categoryNameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(categoryNameText.getText().toString().length() > 0)
                    saveCategoryButton.setEnabled(true);
                else
                    saveCategoryButton.setEnabled(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        saveCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBManagerCategory dbManagerCategory = new DBManagerCategory(v.getContext()).open();
                dbManagerCategory.insert(categoryNameText.getText().toString());
                dbManagerCategory.close();

                Bundle receivedBundle = getArguments();
                Bundle bundle = new Bundle();

                bundle.putString("PRODUCT_NAME", receivedBundle.getString("PRODUCT_NAME"));
                bundle.putString("PRODUCT_PRICE", receivedBundle.getString("PRODUCT_PRICE"));
                bundle.putString("CATEGORY_NAME", categoryNameText.getText().toString());

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(fragmentManager.findFragmentByTag("ADD_CATEGORY"));
                //fragmentTransaction.remove(fragmentManager.findFragmentByTag("ADD_PRODUCT"));

                if(receivedBundle.containsKey("UPDATE_PRODUCT")){
                    UpdateDeleteProductFragment updateDeleteProductFragment = new UpdateDeleteProductFragment();
                    updateDeleteProductFragment.setArguments(bundle);
                    fragmentTransaction.add(R.id.fragment_container, updateDeleteProductFragment);
                }else{
                    AddProductFragment addProductFragment = new AddProductFragment();
                    addProductFragment.setArguments(bundle);
                    fragmentTransaction.add(R.id.fragment_container, addProductFragment);
                }
                fragmentTransaction.commit();
            }
        });
    }
}