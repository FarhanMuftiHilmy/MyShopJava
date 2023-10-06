package com.arfdn.myshop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arfdn.myshop.databinding.FragmentCheckoutJavaBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckoutFragmentJava#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckoutFragmentJava extends Fragment {

    private FragmentCheckoutJavaBinding _binding;
    private FragmentCheckoutJavaBinding getBinding() {
        return _binding;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _binding = FragmentCheckoutJavaBinding.inflate(inflater, container, false);
        return getBinding().getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final CheckoutFragmentJavaArgs args = CheckoutFragmentJavaArgs.fromBundle(getArguments());

        getBinding().edtAddress.setText(args.getAddress());
        getBinding().txtProductName.setText(args.getProductName());

        NavController navController = Navigation.findNavController(requireView());
        NavBackStackEntry backStackEntry = navController.getCurrentBackStackEntry();

        if (backStackEntry != null) {
            SavedStateHandle handle = backStackEntry.getSavedStateHandle();
            if (handle != null) {
                LiveData<String> liveData = handle.getLiveData("address");
                liveData.observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String res) {
                        getBinding().edtAddress.setText(res);
                    }
                });
            }
        }

        getBinding().edtAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = CheckoutFragmentJavaDirections.actionCheckOutFragmentToAddressFragment();
                navController.navigate(action);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}