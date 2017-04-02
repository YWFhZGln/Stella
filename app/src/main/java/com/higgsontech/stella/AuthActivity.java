package com.higgsontech.stella;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.Security;

;

public class AuthActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{


    public static final String LOG_TAG=AuthActivity.class.getSimpleName();


    private String authUrl="http://auth.uidai.gov.in/1.6/public/9/9/MH4hSkrev2h_Feu0lBRC8NI-iqzT299_qPSSstOFbNFTwWrie29ThDo";
    String x="http://higgsontech.com/hack/signXML.php";

    private LoaderManager loaderManager=null;

    private EditText uid;
    private Button authBtn;
    private TextView result;
    private String mUid;
    static {
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(),1);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AuthUtils authUtils=new AuthUtils(this);


        uid=(EditText)findViewById(R.id.uid);
        mUid=uid.getText().toString();
        result=(TextView)findViewById(R.id.result);


        authBtn=(Button)findViewById(R.id.authButton);
        authBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
                if(networkInfo!=null&&networkInfo.isConnected()){

                    loaderManager=getLoaderManager();
                    loaderManager.initLoader(0,null,AuthActivity.this);
                }
                else {
                   // Toast.makeText(AuthActivity.this,"No internet connection",Toast.LENGTH_SHORT).show();
                    Snackbar.make(v,"No internet connection",Snackbar.LENGTH_SHORT).show();
                }




            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        result.setText("");

    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new AuthLoader(this,authUrl,mUid);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {

        result.setText(data);

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        result.setText("");

    }
}

