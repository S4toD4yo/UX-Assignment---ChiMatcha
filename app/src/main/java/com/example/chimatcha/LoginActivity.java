package com.example.chimatcha;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPassword;

    private ImageView btnTogglePassword;

    private MaterialButton btnSignIn;

    private MaterialCardView btnBack;

    private TextView txtForgotPassword;
    private TextView txtSignUp;

    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.login),
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

        setupBackButton();
        setupPasswordToggle();
        setupButtonAnimation();
        setupForgotPassword();
        setupSignUpLink();
        setupLoginButton();
    }

    private void initializeViews() {

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnTogglePassword =
                findViewById(R.id.btnTogglePassword);

        btnSignIn =
                findViewById(R.id.btnSignIn);

        btnBack =
                findViewById(R.id.btnBack);

        txtForgotPassword =
                findViewById(R.id.txtForgotPassword);

        txtSignUp =
                findViewById(R.id.txtSignUp);
    }

    private void setupBackButton() {

        btnBack.setOnClickListener(v -> finish());

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
    }

    private void setupPasswordToggle() {

        btnTogglePassword.setOnClickListener(v -> {

            if (isPasswordVisible) {

                etPassword.setInputType(
                        InputType.TYPE_CLASS_TEXT
                                | InputType.TYPE_TEXT_VARIATION_PASSWORD
                );

                btnTogglePassword.setImageResource(
                        R.drawable.ic_visibility_off
                );

                isPasswordVisible = false;

            } else {

                etPassword.setInputType(
                        InputType.TYPE_CLASS_TEXT
                                | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                );

                btnTogglePassword.setImageResource(
                        R.drawable.ic_visibility
                );

                isPasswordVisible = true;
            }

            etPassword.setSelection(
                    etPassword.getText().length()
            );
        });
    }

    private void setupButtonAnimation() {

        btnSignIn.setOnTouchListener((v, event) -> {

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

    private void setupForgotPassword() {

        txtForgotPassword.setOnClickListener(v -> {

            // Nanti bisa diarahkan ke ForgotPasswordActivity
        });
    }

    private void setupSignUpLink() {

        String text =
                "Don't have an account? Sign Up now";

        SpannableString spannable =
                new SpannableString(text);

        int start =
                text.indexOf("Sign Up");

        int end =
                text.indexOf(" now");

        ClickableSpan clickableSpan =
                new ClickableSpan() {

                    @Override
                    public void onClick(View widget) {

                        Intent intent =
                                new Intent(
                                        LoginActivity.this,
                                        MainActivity.class
                                );

                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void updateDrawState(
                            TextPaint ds
                    ) {

                        ds.setColor(
                                getResources().getColor(
                                        R.color.primary
                                )
                        );

                        ds.setUnderlineText(true);
                    }
                };

        spannable.setSpan(
                clickableSpan,
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        spannable.setSpan(
                new StyleSpan(Typeface.BOLD),
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

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

        txtSignUp.setText(spannable);

        txtSignUp.setMovementMethod(
                LinkMovementMethod.getInstance()
        );
    }

    private void setupLoginButton() {

        btnSignIn.setOnClickListener(v -> {

            String email =
                    etEmail.getText().toString().trim();

            String password =
                    etPassword.getText().toString().trim();

            if (email.isEmpty()) {

                etEmail.setError(
                        "Email is required"
                );

                etEmail.requestFocus();
                return;
            }

            if (password.isEmpty()) {

                etPassword.setError(
                        "Password is required"
                );

                etPassword.requestFocus();
                return;
            }

            Intent intent =
                    new Intent(
                            LoginActivity.this,
                            HomepageActivity.class
                    );

            startActivity(intent);
            finish();
        });
    }
}
