package espol.fiec.edu.lego;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Vector;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegistroUsuario extends AppCompatActivity {

    @Bind(R.id.input_name)      EditText _nameText;
    @Bind(R.id.input_email)     EditText _emailText;
    @Bind(R.id.input_password)  EditText _passwordText;
    @Bind(R.id.btn_signup)      Button _signupButton;
    @Bind(R.id.link_login)      TextView _loginLink;

    private static final String TAG = "RegistroUsuario";
    private RegistroUsuarioTask mAuthTask = null;
    private WebServicesConfiguration wsConf = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        ButterKnife.bind(this);
        wsConf = (WebServicesConfiguration) getApplicationContext();
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(RegistroUsuario.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        final String name = _nameText.getText().toString();
        final String email = _emailText.getText().toString();
        final String password = _passwordText.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess(name, email, password);
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public void onSignupSuccess(String name, String email, String password) {
        _signupButton.setEnabled(true);
        mAuthTask = new RegistroUsuarioTask(name, email, password);
        mAuthTask.execute((Void) null);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    public class RegistroUsuarioTask extends AsyncTask<Void, Void, Boolean> {
        private final String mName;
        private final String mEmail;
        private final String mPassword;
        private boolean isOK = false;

        RegistroUsuarioTask(String name, String email, String password) {
            mName = name;
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                //Configuración del web service a consumir
                HttpTransportSE httpTransport = new HttpTransportSE(wsConf.getURL());
                SoapObject request = new SoapObject(wsConf.getNAMESPACE(), wsConf.getMETHOD_GET_INSERT_USER());
                //Agregando parametros del método
                request.addProperty("name", mName);
                request.addProperty("email", mEmail);
                request.addProperty("password", mPassword);
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                httpTransport.call(wsConf.getSOAP_ACTION() + wsConf.getMETHOD_GET_INSERT_USER(), envelope);
                SoapObject response = (SoapObject) envelope.bodyIn;
                String responseLocal = (String)response.getProperty(0);
                if (responseLocal.equals("1")) {
                    isOK = true;
                } else {
                    isOK = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("Respuesta","excepción");
                Log.i("Respuesta",e.toString());
                return false;
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if(isOK){
                AlertDialog.Builder builder = new AlertDialog.Builder(RegistroUsuario.this);
                builder.setTitle("Posii");
                builder.show();
            }else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegistroUsuario.this);
                builder.setTitle("Error");
                builder.show();
            }
        }

    }

}
