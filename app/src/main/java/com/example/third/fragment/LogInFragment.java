package com.example.third.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.third.MainActivity;
import com.example.third.R;
import com.example.third.databinding.LogInFragmentBinding;

public class LogInFragment extends Fragment {
   FragmentManager fragmentManager;
   LogInFragmentBinding binding;
   MainActivity mainActivity;
    public LogInFragment() {
        super(R.layout.log_in_fragment);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getParentFragmentManager();
        fragmentManager.setFragmentResultListener("switch to LogInFragment", this, (requestKey, result) -> {
            String results = result.getString("resultText").toString();
            String hello = "Здравствуйте, " + results + "!";
            binding.textView6.setText(hello);
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LogInFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.button2.setOnClickListener(view1 -> {
            Bundle result = new Bundle();
            result.putString("resultText", binding.editTextLogin.getText().toString());
            fragmentManager.setFragmentResult("switch to StartFragment", result);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_fragment, new StartFragment());
            fragmentTransaction.addToBackStack(null).commit();
        });
    }
}
