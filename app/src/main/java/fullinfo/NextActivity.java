package fullinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.nowandroid.parseryandex.R;

public class NextActivity extends AppCompatActivity {

    public WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        TextView textView = (TextView) findViewById(R.id.textView);
        webView = (WebView) findViewById(R.id.webViewActivity);


        //js
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient());

        Intent intent = getIntent();
        Integer in = intent.getIntExtra("position", 0);
        String link = intent.getStringExtra("link");

        textView.setText(String.valueOf(in));
        webView.loadUrl(link);
        //webView.loadUrl("https://news.yandex.ru/yandsearch?cl4url=chezasite.com%2Fnews%2Fxiaomi-mi-5-pro-preorder-92013.html");

    }
}
