package com.example.third.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.third.MainActivity;
import com.example.third.R;
import com.example.third.databinding.StartFragmentBinding;

public class StartFragment extends Fragment {
    FragmentManager fragmentManager;
    StartFragmentBinding binding;
    MainActivity mainActivity;
    public StartFragment() {
        super(R.layout.start_fragment);
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
        fragmentManager.setFragmentResultListener("switch to StartFragment", this, (requestKey, result) -> {
            String results = result.getString("resultText");
            Toast.makeText(getContext(), results + ", приложение пока не готово", Toast.LENGTH_LONG).show();
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = StartFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.button.setOnClickListener(view1 -> {
            Bundle result = new Bundle();
            result.putString("resultText", binding.editTextTextPersonName.getText().toString());
            fragmentManager.setFragmentResult("switch to LogInFragment", result);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_fragment, new LogInFragment());
            fragmentTransaction.addToBackStack(null).commit();
        });
    }
}
