package com.example.sergeykulyk.flowable;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @BindView(R.id.tv_name)
    TextView nameTextView;

    @InjectPresenter
    Presenter presenter;
    @BindView(R.id.ed_name)
    EditText edName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter.initDb(this);
    }

    @Override
    public void setName(String name) {
        nameTextView.setText(name);
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        presenter.updateName(edName.getText().toString());
    }
}
