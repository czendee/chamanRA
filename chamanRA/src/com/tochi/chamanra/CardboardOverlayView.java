package com.tochi.chamanra;

/* 
  * Copyright 2014 Google Inc. All Rights Reserved. 
  
  * Licensed under the Apache License, Version 2.0 (the "License"); 
  * you may not use this file except in compliance with the License. 
  * You may obtain a copy of the License at 
  * 
  *     http://www.apache.org/licenses/LICENSE-2.0 
  * 
  * Unless required by applicable law or agreed to in writing, software 
  * distributed under the License is distributed on an "AS IS" BASIS, 
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
  * See the License for the specific language governing permissions and 
  * limitations under the License. 
  */ 
 
 import android.content.Context; 
import android.graphics.BitmapFactory;
 import android.graphics.Color; 
 import android.graphics.Typeface; 
 import android.util.AttributeSet; 
 import android.util.TypedValue; 
 import android.view.Gravity; 
 import android.view.View; 
 import android.view.ViewGroup; 
 import android.view.animation.AlphaAnimation; 
 import android.view.animation.Animation; 
 import android.widget.ImageView; 
 import android.widget.LinearLayout; 
import android.widget.TextView; 
 
 
 /** 
  * Contains two sub-views to provide a simple stereo HUD. 
  */ 
 public class CardboardOverlayView extends LinearLayout { 
     private static final String TAG = CardboardOverlayView.class.getSimpleName(); 
     private final CardboardOverlayEyeView mLeftView; 
     private final CardboardOverlayEyeView mRightView; 
     private AlphaAnimation mTextFadeAnimation; 
 
 
     public CardboardOverlayView(Context context, AttributeSet attrs) { 
         super(context, attrs); 
         setOrientation(HORIZONTAL); 
 
 
         LayoutParams params = new LayoutParams( 
             LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1.0f); 
         params.setMargins(0, 0, 0, 0); 
 
 
         mLeftView = new CardboardOverlayEyeView(context, attrs); 
         mLeftView.setLayoutParams(params); 
         addView(mLeftView); 
 
 
         mRightView = new CardboardOverlayEyeView(context, attrs); 
         mRightView.setLayoutParams(params); 
         addView(mRightView); 
 
 
         // Set some reasonable defaults. 
//        setDepthOffset(0.016f); 
         setDepthOffset(0.20f);
         
         setColor(Color.rgb(150, 255, 180)); 
         setVisibility(View.VISIBLE); 
 
 
         mTextFadeAnimation = new AlphaAnimation(1.0f, 0.0f); 
         mTextFadeAnimation.setDuration(10000); 
     } 
 
 
     public void show3DToast(String message) { 
         setText(message); 
         setTextAlpha(1f); 
         mTextFadeAnimation.setAnimationListener(new EndAnimationListener() { 
             @Override 
             public void onAnimationEnd(Animation animation) { 
                 setTextAlpha(0f); 
             } 
         }); 
         startAnimation(mTextFadeAnimation); 
//         setIcon(); may 4 2016 CZ , para evitar se despliegue encima de la imagen
         setLine(); 
     } 
     
     public void showIcon() { 
         setIcon(); 
         mTextFadeAnimation.setAnimationListener(new EndAnimationListener() { 
             @Override 
             public void onAnimationEnd(Animation animation) { 
                 setTextAlpha(0f); 
             } 
         }); 
         startAnimation(mTextFadeAnimation); 
     }
 
     // start: may 4 2016 CZ
     // display imagen 
//     public void showImage(int mScore, Context context) {
//     setImg(mScore, context);    

         public void showImage(int mScore) {
             setImg(mScore);        	 
     }
     // end: may 4 2016 CZ
     // display imagen  
     
     private abstract class EndAnimationListener implements Animation.AnimationListener { 
         @Override public void onAnimationRepeat(Animation animation) {} 
         @Override public void onAnimationStart(Animation animation) {} 
     } 
 
 
     private void setDepthOffset(float offset) { 
         mLeftView.setOffset(offset); 
         mRightView.setOffset(-offset); 
     } 
 
 
     private void setText(String text) { 
         mLeftView.setText(text); 
         mRightView.setText(text); 
     } 
 
 
     private void setTextAlpha(float alpha) { 
         mLeftView.setTextViewAlpha(alpha); 
         mRightView.setTextViewAlpha(alpha); 
     } 
 
 
     private void setColor(int color) { 
         mLeftView.setColor(color); 
         mRightView.setColor(color); 
     } 
 
     
     private void setIcon() { 
         mLeftView.setIcon(); 
         mRightView.setIcon(); 
     } 
     private void setLine() { 
         mLeftView.setLine(); 
         mRightView.setLine(); 
     } 
     
     // start: may 4 2016 CZ
     // set imagen 

//     private void setImg(int mScore, Context context) {
     private void setImg(int mScore) {




         switch (mScore) {

         case 0:

                mLeftView.imageView.setLayoutParams(new LayoutParams(

                             LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

                mLeftView.imageView.setBackgroundResource(R.drawable.ic_launcher);

                mRightView.imageView.setLayoutParams(new LayoutParams(

                             LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

                mRightView.imageView.setBackgroundResource(R.drawable.ic_launcher);

                break;
         case 1:

             mLeftView.imageView.setLayoutParams(new LayoutParams(

                          LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

             mLeftView.imageView.setBackgroundResource(R.drawable.personaje32hectorized);

             mRightView.imageView.setLayoutParams(new LayoutParams(

                          LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

             mRightView.imageView.setBackgroundResource(R.drawable.personaje32hectorized);

             break;

         default:

//                Intent intent = new Intent(context, VrMagenetActivity.class);

//                context.startActivity(intent);

         }

  }
     // start: may 4 2016 CZ
     // set imagen

     
     /** 
      * A simple view group containing some horizontally centered text underneath a horizontally 
      * centered image. 
      * 
      * This is a helper class for CardboardOverlayView. 
      */ 
     private class CardboardOverlayEyeView extends ViewGroup { 
         private final ImageView imageView; 
         private final TextView textView;
         private final ImageView iconView;
         private final DrawView lineView; 
         private float offset; 
 
 
         public CardboardOverlayEyeView(Context context, AttributeSet attrs) { 
             super(context, attrs); 
             imageView = new ImageView(context, attrs); 
             imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE); 

             imageView.setAdjustViewBounds(true);  // Preserve aspect ratio. 
             
             addView(imageView); 
 

 
             textView = new TextView(context, attrs); 
             textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14.0f); 
             textView.setTypeface(textView.getTypeface(), Typeface.BOLD); 
             textView.setGravity(Gravity.CENTER); 
             textView.setShadowLayer(3.0f, 0.0f, 0.0f, Color.DKGRAY); 
             addView(textView); 
             
             iconView = new ImageView(context, attrs); 
             iconView.setScaleType(ImageView.ScaleType.CENTER_INSIDE); 
             iconView.setAdjustViewBounds(true);  // Preserve aspect ratio. 
             addView(iconView); 
             
             lineView = new DrawView(context); 
             addView(lineView);             
         } 
 
 
         public void setColor(int color) { 
             imageView.setColorFilter(color); 
             textView.setTextColor(color);
             iconView.setColorFilter(color);
         } 
 
 
         public void setText(String text) { 
             textView.setText(text); 
         } 
 
 
         public void setTextViewAlpha(float alpha) { 
             textView.setAlpha(alpha); 
         } 
 
 
         public void setOffset(float offset) { 
             this.offset = offset; 
         } 
 
         public void setIcon() { 
             iconView.setImageBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_launcher) ); 


         } 


         public void setLine() { 
             //


         } 
         
         @Override 
         protected void onLayout(boolean changed, int left, int top, int right, int bottom) { 
             // Width and height of this ViewGroup. 
             final int width = right - left; 
             final int height = bottom - top; 
 
 
             // The size of the image, given as a fraction of the dimension as a ViewGroup. We multiply 
             // both width and heading with this number to compute the image's bounding box. Inside the 
             // box, the image is the horizontally and vertically centered. 
//cz may 4 2016             final float imageSize = 0.12f; 
             
             final float imageSize = 0.3f;
 
 
             // The fraction of this ViewGroup's height by which we shift the image off the ViewGroup's 
             // center. Positive values shift downwards, negative values shift upwards. 
//             final float verticalImageOffset = -0.07f; 
             final float verticalImageOffset = -0.35f; //mas arriba
 
 
             // Vertical position of the text, specified in fractions of this ViewGroup's height. 
             final float verticalTextPos = 0.52f; 
 
             // Layout ImageView 
             float imageMargin = (1.0f - imageSize) / 2.0f; 
             float leftMargin = (int) (width * (imageMargin + offset)); 
             float topMargin = (int) (height * (imageMargin + verticalImageOffset)); 
             imageView.layout( 
                 (int) leftMargin, (int) topMargin, 
                 (int) (leftMargin + width * imageSize), (int) (topMargin + height * imageSize)); 
 
 
             // Layout TextView 
             leftMargin = offset * width; 
             topMargin = height * verticalTextPos; 
             textView.layout( 
                 (int) leftMargin, (int) topMargin, 
                 (int) (leftMargin + width), (int) (topMargin + height * (1.0f - verticalTextPos)));
             
             //ICON
             // The size of the icon, given as a fraction of the dimension as a ViewGroup. We multiply 
             // both width and heading with this number to compute the image's bounding box. Inside the 
             // box, the image is the horizontally and vertically centered. 
             final float iconSize = 0.10f; 
 
 
             // The fraction of this ViewGroup's height by which we shift the image off the ViewGroup's 
             // center. Positive values shift downwards, negative values shift upwards. 
             final float verticalIconOffset = 0.55f; 
  
             // Layout IconView 
             float iconMargin = (1.0f - iconSize) / 2.0f; 
             float leftMarginIcon = (int) (width * (iconMargin + offset)); 
             float topMarginIcon = (int) (height * (iconMargin + verticalIconOffset)); 
             iconView.layout( 
                 (int) leftMarginIcon, (int) topMargin, 
                 (int) (leftMarginIcon + width * iconSize), (int) (topMarginIcon + height * iconSize));
             
             //Line
             // The size of the icon, given as a fraction of the dimension as a ViewGroup. We multiply 
             // both width and heading with this number to compute the image's bounding box. Inside the 
             // box, the image is the horizontally and vertically centered. 
             final float lineSize = 0.20f; 
 
 
             // The fraction of this ViewGroup's height by which we shift the image off the ViewGroup's 
             // center. Positive values shift downwards, negative values shift upwards. 
             final float verticalLineOffset = 0.65f; 
  
             // Layout IconView 
             float lineMargin = (1.0f - lineSize) / 2.0f; 
             float leftMarginLine = (int) (width * (lineMargin + offset)); 
             float topMarginLine = (int) (height * (lineMargin + verticalLineOffset)); 
             lineView.layout( 
                 (int) leftMarginLine, (int) topMargin, 
                 (int) (leftMarginLine + width * lineSize), (int) (topMarginLine + height * lineSize));
             
         } 
     } 
 } 
