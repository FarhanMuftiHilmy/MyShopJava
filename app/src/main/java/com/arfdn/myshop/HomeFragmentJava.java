package com.arfdn.myshop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arfdn.myshop.databinding.FragmentHomeJavaBinding;

public class HomeFragmentJava extends Fragment {

    private FragmentHomeJavaBinding _binding;
    private FragmentHomeJavaBinding getBinding() {
        return _binding;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _binding = FragmentHomeJavaBinding.inflate(inflater, container, false);
        return getBinding().getRoot();
    }

        @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentHomeJavaBinding binding = getBinding();
        NavDirections action = HomeFragmentJavaDirections.actionHomeFragmentToCheckOutFragment("Samsung M22", null);
        binding.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavHostFragment.findNavController(HomeFragmentJava.this)
                        .navigate(action);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}