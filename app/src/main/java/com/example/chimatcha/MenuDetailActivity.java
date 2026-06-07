package com.example.chimatcha;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MenuDetailActivity extends AppCompatActivity {

    private MaterialCardView btnBack;

    private ImageView imgMenu;

    private TextView txtMenuName;
    private TextView txtMenuPrice;
    private TextView txtMenuDescription;

    private Spinner spinnerIceLevel;
    private Spinner spinnerSweetness;

    private ImageView btnMinus;
    private ImageView btnPlus;

    private TextView txtQuantity;

    private MaterialButton btnAddToCart;

    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(
                R.layout.activity_menu_detail
        );

        initializeViews();

        setupMenuData();

        setupBackButton();

        setupIceLevel();

        setupSweetness();

        setupQuantity();

        setupButtonAnimations();

        setupAddToCartButton();
    }

    private void initializeViews() {

        btnBack =
                findViewById(R.id.btnBack);

        imgMenu =
                findViewById(R.id.imgMenu);

        txtMenuName =
                findViewById(R.id.txtMenuName);

        txtMenuPrice =
                findViewById(R.id.txtMenuPrice);

        txtMenuDescription =
                findViewById(R.id.txtMenuDescription);

        spinnerIceLevel =
                findViewById(R.id.spinnerIceLevel);

        spinnerSweetness =
                findViewById(R.id.spinnerSweetness);

        btnMinus =
                findViewById(R.id.btnMinus);

        btnPlus =
                findViewById(R.id.btnPlus);

        txtQuantity =
                findViewById(R.id.txtQuantity);

        btnAddToCart =
                findViewById(R.id.btnAddToCart);
    }

    private void setupMenuData() {

        String name =
                getIntent().getStringExtra(
                        "MENU_NAME"
                );

        String price =
                getIntent().getStringExtra(
                        "MENU_PRICE"
                );

        String description =
                getIntent().getStringExtra(
                        "MENU_DESCRIPTION"
                );

        int image =
                getIntent().getIntExtra(
                        "MENU_IMAGE",
                        R.drawable.comingsoon
                );

        if (name == null) {

            name = "Unknown Menu";
        }

        if (price == null) {

            price = "Rp 0";
        }

        if (description == null) {

            description =
                    "No description available.";
        }

        txtMenuName.setText(name);

        txtMenuPrice.setText(price);

        txtMenuDescription.setText(description);

        imgMenu.setImageResource(image);
    }

    private void setupIceLevel() {

        String[] iceLevels = {

                "Normal",
                "Less Ice",
                "No Ice"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        iceLevels
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerIceLevel.setAdapter(adapter);
    }

    private void setupSweetness() {

        String[] sweetnessLevels = {

                "Normal",
                "Less Sugar",
                "No Sugar"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        sweetnessLevels
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerSweetness.setAdapter(adapter);
    }

    private void setupQuantity() {

        txtQuantity.setText(
                String.valueOf(quantity)
        );

        btnMinus.setOnClickListener(v -> {

            if (quantity > 0) {

                quantity--;

                txtQuantity.setText(
                        String.valueOf(quantity)
                );
            }
        });

        btnPlus.setOnClickListener(v -> {

            quantity++;

            txtQuantity.setText(
                    String.valueOf(quantity)
            );
        });
    }

    private void setupBackButton() {

        btnBack.setOnClickListener(v -> {

            finish();
        });
    }

    private void setupButtonAnimations() {

        btnBack.setOnTouchListener((v, event) -> {

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    v.setAlpha(0.5f);
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:

                    v.setAlpha(1f);
                    break;
            }

            return false;
        });

        btnMinus.setOnTouchListener((v, event) -> {

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    v.setAlpha(0.5f);
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:

                    v.setAlpha(1f);
                    break;
            }

            return false;
        });

        btnPlus.setOnTouchListener((v, event) -> {

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    v.setAlpha(0.5f);
                    break;

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:

                    v.setAlpha(1f);
                    break;
            }

            return false;
        });

        btnAddToCart.setOnTouchListener((v, event) -> {

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    btnAddToCart.setBackgroundTintList(
                            ColorStateList.valueOf(
                                    getColor(
                                            R.color.primary_dark
                                    )
                            )
                    );
                    break;

                case MotionEvent.ACTION_UP:

                case MotionEvent.ACTION_CANCEL:

                    btnAddToCart.setBackgroundTintList(
                            ColorStateList.valueOf(
                                    getColor(
                                            R.color.primary
                                    )
                            )
                    );
                    break;
            }

            return false;
        });
    }

    private void setupAddToCartButton() {

        btnAddToCart.setOnClickListener(v -> {

            if (quantity <= 0) {

                new MaterialAlertDialogBuilder(
                        MenuDetailActivity.this
                )
                        .setTitle("Error")
                        .setMessage(
                                "Quantity must be greater than 0."
                        )
                        .setPositiveButton(
                                "OK",
                                (dialog, which) ->
                                        dialog.dismiss()
                        )
                        .show();

                return;
            }

            new MaterialAlertDialogBuilder(
                    MenuDetailActivity.this
            )
                    .setTitle("Success")
                    .setMessage(
                            "A confirmation email has been sent to your email."
                    )
                    .setCancelable(false)
                    .setPositiveButton(
                            "OK",
                            (dialog, which) -> {

                                Intent intent =
                                        new Intent(
                                                MenuDetailActivity.this,
                                                MenuActivity.class
                                        );

                                intent.addFlags(
                                        Intent.FLAG_ACTIVITY_CLEAR_TOP
                                );

                                startActivity(intent);

                                finish();
                            }
                    )
                    .show();
        });
    }
}