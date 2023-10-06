package com.arfdn.myshop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.arfdn.myshop.databinding.FragmentAddressJavaBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddressFragmentJava#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddressFragmentJava extends Fragment {

    private FragmentAddressJavaBinding _binding;
    private FragmentAddressJavaBinding getBinding() {
        return _binding;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _binding = FragmentAddressJavaBinding.inflate(inflater, container, false);
        return getBinding().getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final String[] provinces = getResources().getStringArray(R.array.provinces);
        ArrayAdapter<String> adapterProvinces = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, provinces);
        adapterProvinces.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getBinding().spinnerProvinces.setAdapter(adapterProvinces);

        getBinding().btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(AddressFragmentJava.this)
                        .getPreviousBackStackEntry()
                        .getSavedStateHandle()
                        .set("address", getBinding().spinnerProvinces.getSelectedItem().toString());
                NavHostFragment.findNavController(AddressFragmentJava.this).navigateUp();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}