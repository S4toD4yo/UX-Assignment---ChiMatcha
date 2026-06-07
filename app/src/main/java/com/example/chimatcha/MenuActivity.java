package com.example.chimatcha;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MenuActivity extends AppCompatActivity {

    private LinearLayout navHome;
    private LinearLayout navMenu;
    private LinearLayout navBranches;

    private LinearLayout itemDrink1;
    private LinearLayout itemDrink2;
    private LinearLayout itemDrink3;

    private LinearLayout itemFood1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_menu);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.itemMenu),
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

        setupMenuItems();
    }

    private void initializeViews() {

        navHome =
                findViewById(R.id.navHome);

        navMenu =
                findViewById(R.id.navMenu);

        navBranches =
                findViewById(R.id.navBranches);

        itemDrink1 =
                findViewById(R.id.itemDrink1);

        itemDrink2 =
                findViewById(R.id.itemDrink2);

        itemDrink3 =
                findViewById(R.id.itemDrink3);

        itemFood1 =
                findViewById(R.id.itemFood1);
    }

    private void setupBottomNavigation() {

        navHome.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MenuActivity.this,
                            HomepageActivity.class
                    );

            startActivity(intent);
            finish();
        });

        navMenu.setOnClickListener(v -> {

            // Current Page
        });

        navBranches.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            MenuActivity.this,
                            BranchesActivity.class
                    );

            startActivity(intent);
            finish();
        });
    }

    private void setupMenuItems() {

        itemDrink1.setOnClickListener(v -> {

            openMenuDetail(
                    "Matcha Latte",
                    "Rp 28.000",
                    R.drawable.menu_matcha_latte,
                    "Premium ceremonial matcha blended with fresh milk."
            );
        });

        itemDrink2.setOnClickListener(v -> {

            openMenuDetail(
                    "Iced Matcha",
                    "Rp 30.000",
                    R.drawable.menu_iced_matcha,
                    "Refreshing iced matcha with rich green tea flavor."
            );
        });

        itemDrink3.setOnClickListener(v -> {

            openMenuDetail(
                    "Matcha Mango Latte",
                    "Rp 35.000",
                    R.drawable.menu_matcha_mango_latte,
                    "Sweet mango combined with creamy matcha."
            );
        });

        itemFood1.setOnClickListener(v -> {

            openMenuDetail(
                    "Matcha Cake",
                    "Rp 32.000",
                    R.drawable.menu_matcha_cake,
                    "Soft sponge cake infused with premium matcha."
            );
        });
    }

    private void openMenuDetail(
            String name,
            String price,
            int image,
            String description
    ) {

        Intent intent =
                new Intent(
                        MenuActivity.this,
                        MenuDetailActivity.class
                );

        intent.putExtra(
                "MENU_NAME",
                name
        );

        intent.putExtra(
                "MENU_PRICE",
                price
        );

        intent.putExtra(
                "MENU_IMAGE",
                image
        );

        intent.putExtra(
                "MENU_DESCRIPTION",
                description
        );

        startActivity(intent);
    }
}