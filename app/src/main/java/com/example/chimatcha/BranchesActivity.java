package com.example.chimatcha;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BranchesActivity extends AppCompatActivity {

    private LinearLayout navHome;
    private LinearLayout navMenu;
    private LinearLayout navBranches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_branches);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.branches),
                (v, insets) -> {

                    Insets systemBars =
                            insets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        initializeViews();

        setupBottomNavigation();
    }

    private void initializeViews() {

        navHome =
                findViewById(R.id.navHome);

        navMenu =
                findViewById(R.id.navMenu);

        navBranches =
                findViewById(R.id.navBranches);
    }

    private void setupBottomNavigation() {

        navHome.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            BranchesActivity.this,
                            HomepageActivity.class
                    );

            startActivity(intent);

            finish();
        });

        navMenu.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            BranchesActivity.this,
                            MenuActivity.class
                    );

            startActivity(intent);

            finish();
        });

        navBranches.setOnClickListener(v -> {

        });
    }
}