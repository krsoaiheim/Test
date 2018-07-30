package krsoaiheim.test.view;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import krsoaiheim.test.R;
import krsoaiheim.test.loaders.AuthLoader;

public class AuthActivity extends AppCompatActivity {
  private EditText loginEdit, passwordEdit;
  private Button loginBtn;
  private String login, password;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_auth);
    loginEdit = findViewById(R.id.login);
    passwordEdit = findViewById(R.id.password);
    loginBtn = findViewById(R.id.login_btn);
    loginBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        login = loginEdit.getText().toString();
        password = passwordEdit.getText().toString();
        if (!login.isEmpty() && !password.isEmpty()) {
          Bundle args = new Bundle();
          args.putString("login", login);
          args.putString("password", password);
          getLoaderManager().restartLoader(0, args, new AuthLoaderCallback());
        }
      }
    });
  }


  private void openList(String code) {
    Intent intent = new Intent(this, ListActivity.class);
    intent.putExtra("code", code);
    startActivity(intent);
    finish();
  }


  class AuthLoaderCallback implements LoaderManager.LoaderCallbacks<String> {
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
      return new AuthLoader(AuthActivity.this, args.getString("login"), args.getString("password"));
    }


    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
      if (data.equals("-1")) {
        Toast.makeText(AuthActivity.this, getString(R.string.auth_err), Toast.LENGTH_SHORT).show();
      } else {
        openList(data);
      }
    }


    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
    }
  }
}