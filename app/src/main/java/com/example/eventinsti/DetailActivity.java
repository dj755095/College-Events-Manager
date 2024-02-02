package com.example.eventinsti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ortiz.touchview.TouchImageView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetailActivity extends AppCompatActivity {
    TextView detailDesc, detailTitle, detailLink, detailInstitute;
    ImageView detailStatus;
    TouchImageView detailImage;
    ImageView like;
    String imageUrl = "";
    String eventUrl = "";
    boolean click = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        like = findViewById(R.id.like);

        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        detailLink = findViewById(R.id.detailLink);
        detailInstitute = findViewById(R.id.detailInstitute);
        detailStatus = findViewById(R.id.detailStatus);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));

            //detailLink.setText(bundle.getString("Link"));
            eventUrl =  bundle.getString("Link");

            detailInstitute.setText(bundle.getString("Username"));

            String statusText = bundle.getString("Status");
            if(statusText.equals("Approved")){
                detailStatus.setImageResource(R.drawable.baseline_verified_24);
            }else{
              detailStatus.setImageResource(R.drawable.baseline_not_verified);
            }
            //detailStatus.setText(bundle.getString("Status"));

            imageUrl = bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }

        detailLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = eventUrl;
                if (isValidUrl(url)) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid URL", Toast.LENGTH_SHORT).show();
                }
            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!click){
                    like.setImageResource(R.drawable.baseline_thumb_up_24);
                    click = true;
                }else{
                    like.setImageResource(R.drawable.baseline_thumb_up_dislike);
                    click = false;
                }
            }
        });
    }
    private boolean isValidUrl(String url) {
        // Define a simple regex pattern for a valid URL
        String urlPattern = "^((http|https)://)?([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}(/[a-zA-Z0-9-_.~%]*)*$";

        // Create a Pattern object
        Pattern pattern = Pattern.compile(urlPattern);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(url);

        // Check if the URL matches the pattern
        return matcher.matches();
    }
}