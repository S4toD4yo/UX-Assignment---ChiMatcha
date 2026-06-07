package com.example.chimatcha;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

public class HomepageActivity extends AppCompatActivity {

    private LinearLayout navHome;
    private LinearLayout navMenu;
    private LinearLayout navBranches;
    private RecyclerView rvCarousel;
    private ImageView btnPrevious;
    private ImageView btnNext;

    private LinearLayout layoutDots;

    private MaterialCardView btnSignOut;
    private MaterialCardView btnVoucher;
    private MaterialCardView btnOrder;

    private TextView txtWelcome;
    private TextView txtVoucher;
    private TextView txtPoints;

    private LinearLayoutManager layoutManager;

    private int currentPosition = 0;

    private final int[] banners = {

            R.drawable.matcha_latte,
            R.drawable.iced_matcha,
            R.drawable.matcha_cake,
            R.drawable.matcha_mango_latte,
            R.drawable.matcha_tiramisu
    };

    private TextView txtExploreApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_homepage);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.homepage),
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

        setupExploreAppsTitle();

        setupHeader();

        setupCarousel();

        setupDots();

        setupCarouselButtons();

        setupQuickActions();

        setupButtonAnimations();

        setupBottomNavigation();
    }

    private void initializeViews() {

        rvCarousel =
                findViewById(R.id.rvCarousel);

        btnPrevious =
                findViewById(R.id.btnPrevious);

        btnNext =
                findViewById(R.id.btnNext);

        layoutDots =
                findViewById(R.id.layoutDots);

        btnSignOut =
                findViewById(R.id.btnSignOut);

        btnVoucher =
                findViewById(R.id.btnVoucher);

        btnOrder =
                findViewById(R.id.btnOrder);

        txtWelcome =
                findViewById(R.id.txtWelcome);

        txtVoucher =
                findViewById(R.id.txtVoucher);

        txtPoints =
                findViewById(R.id.txtPoints);

        txtExploreApps =
                findViewById(R.id.txtExploreApps);

        navHome =
                findViewById(R.id.navHome);

        navMenu =
                findViewById(R.id.navMenu);

        navBranches =
                findViewById(R.id.navBranches);
    }

    private void setupHeader() {

        String username =
                getIntent().getStringExtra(
                        "USERNAME"
                );

        if (username == null ||
                username.isEmpty()) {

            username = "Guest";
        }

        txtWelcome.setText(
                "Welcome, " + username
        );

        txtVoucher.setText(
                "0"
        );

        txtPoints.setText(
                "0"
        );

        btnSignOut.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            HomepageActivity.this,
                            MainActivity.class
                    );

            startActivity(intent);
        });
    }

    private void setupCarousel() {

        layoutManager =
                new LinearLayoutManager(
                        this,
                        LinearLayoutManager.HORIZONTAL,
                        false
                );

        rvCarousel.setLayoutManager(
                layoutManager
        );

        CarouselAdapter adapter =
                new CarouselAdapter(
                        banners
                );

        rvCarousel.setAdapter(
                adapter
        );

        PagerSnapHelper snapHelper =
                new PagerSnapHelper();

        snapHelper.attachToRecyclerView(
                rvCarousel
        );

        rvCarousel.addOnScrollListener(
                new RecyclerView.OnScrollListener() {

                    @Override
                    public void onScrolled(
                            RecyclerView recyclerView,
                            int dx,
                            int dy
                    ) {

                        super.onScrolled(
                                recyclerView,
                                dx,
                                dy
                        );

                        currentPosition =
                                layoutManager
                                        .findFirstVisibleItemPosition();

                        updateDots();
                    }
                }
        );
    }

    private void setupDots() {

        layoutDots.removeAllViews();

        for (int i = 0; i < banners.length; i++) {

            ImageView dot =
                    new ImageView(this);

            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(
                            10,
                            10
                    );

            params.setMargins(
                    4,
                    0,
                    4,
                    0
            );

            dot.setLayoutParams(
                    params
            );

            if (i == 0) {

                dot.setBackgroundResource(
                        R.drawable.dot_active
                );

            } else {

                dot.setBackgroundResource(
                        R.drawable.dot_inactive
                );
            }

            layoutDots.addView(dot);
        }
    }

    private void updateDots() {

        for (int i = 0;
             i < layoutDots.getChildCount();
             i++) {

            ImageView dot =
                    (ImageView)
                            layoutDots.getChildAt(i);

            if (i == currentPosition) {

                dot.setBackgroundResource(
                        R.drawable.dot_active
                );

            } else {

                dot.setBackgroundResource(
                        R.drawable.dot_inactive
                );
            }
        }
    }

    private void setupCarouselButtons() {

        btnPrevious.setOnClickListener(v -> {

            if (currentPosition > 0) {

                currentPosition--;

                rvCarousel.smoothScrollToPosition(
                        currentPosition
                );

                updateDots();
            }
        });

        btnNext.setOnClickListener(v -> {

            if (currentPosition <
                    banners.length - 1) {

                currentPosition++;

                rvCarousel.smoothScrollToPosition(
                        currentPosition
                );

                updateDots();
            }
        });
    }

    private void setupQuickActions() {

        btnVoucher.setOnClickListener(v -> {

            // VoucherActivity
        });

        btnOrder.setOnClickListener(v -> {

            // MenuActivity
        });
    }

    private void setupButtonAnimations() {

        btnVoucher.setOnTouchListener((v, event) -> {

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

        btnOrder.setOnTouchListener((v, event) -> {

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

        btnSignOut.setOnTouchListener((v, event) -> {

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
    }

    private void setupExploreAppsTitle() {

        if (txtExploreApps == null) {
            return;
        }

        String text =
                "Explore our Apps";

        SpannableString spannable =
                new SpannableString(text);

        int start =
                text.indexOf("Apps");

        int end =
                text.length();

        spannable.setSpan(
                new ForegroundColorSpan(
                        getResources().getColor(
                                R.color.primary
                        )
                ),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        txtExploreApps.setText(
                spannable
        );
    }

    private void setupBottomNavigation() {

        navHome.setOnClickListener(v -> {

        });

        navMenu.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            HomepageActivity.this,
                            MenuActivity.class
                    );

            startActivity(intent);
        });

        navBranches.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            HomepageActivity.this,
                            BranchesActivity.class
                    );

            startActivity(intent);
        });
    }
}