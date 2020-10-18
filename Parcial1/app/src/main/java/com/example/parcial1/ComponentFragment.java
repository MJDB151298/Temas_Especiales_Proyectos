package com.example.parcial1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComponentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComponentFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ComponentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComponentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ComponentFragment newInstance(String param1, String param2) {
        ComponentFragment fragment = new ComponentFragment();
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
        View view = inflater.inflate(R.layout.fragment_component, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        ListView listView = getView().findViewById(R.id.listview_components);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String component = parent.getAdapter().getItem(position).toString();
                Bundle bundle = new Bundle();
                bundle.putString("COMPONENT", getComponentDefinition(component));
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                DefinitionFragment definitionFragment = new DefinitionFragment();
                definitionFragment.setArguments(bundle);
                if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
                    fragmentTransaction.replace(R.id.horizontal_container, definitionFragment);
                else
                    fragmentTransaction.replace(R.id.fragment_container, definitionFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
    }

    public String getComponentDefinition(String component){
        if(component.equalsIgnoreCase("Intent"))
            return "Intent: Es un objeto de mensajeria que puede utilizar para solicitiar una accion de otro componente de la aplicacion.";
        if(component.equalsIgnoreCase("ListView"))
            return "ListView: Es una vista que agrupa varios elementos y los muestra en una lista de desplazamiento vertical.";
        if(component.equalsIgnoreCase("Adapter"))
            return "Adapter: Un adaptador crea un puente en tre los componentes de la interfaz de usuario y la fuente de datos que completan los datos en el componente de la interfaz de usuario.";
        if(component.equalsIgnoreCase("Fragment"))
            return "Fragment: Representa un comportamiento o una parte de la interfaz de usuario en una FragmentActivity";
        return "";
    }
}