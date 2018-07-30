package krsoaiheim.test.loaders;

import android.content.AsyncTaskLoader;
import android.support.annotation.Nullable;

import krsoaiheim.test.model.DataManager;
import krsoaiheim.test.model.ModelInteractor;
import krsoaiheim.test.view.AuthActivity;

public class AuthLoader extends AsyncTaskLoader<String> {
  private String login, password;


  public AuthLoader(AuthActivity authActivity, String login, String password) {
    super(authActivity);
    this.login = login;
    this.password = password;
  }


  @Override
  protected void onStartLoading() {
    super.onStartLoading();
    forceLoad();
  }


  @Nullable
  @Override
  public String loadInBackground() {
    ModelInteractor interactor = new DataManager();
    try {
      return interactor.auth(login, password);
    } catch (Exception e) {
      return null;
    }
  }
}
