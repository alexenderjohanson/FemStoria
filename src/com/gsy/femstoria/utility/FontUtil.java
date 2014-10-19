package com.gsy.femstoria.utility;

import android.content.Context;
import android.graphics.Typeface;

public class FontUtil {

    private static Typeface mThinFont;
    private static Typeface mNormalFont;
    private static Typeface mExtraNormalFont;

    public static enum FontType {

        THIN {
            public String toString() {
                return "titilliuml001.ttf";
            }
        },

        NORMAL {
            public String toString() {
                return "titilliuml002.ttf";
            }
        },
        
        EXTRA_NORMAL {
            public String toString() {
                return "titilliuml003.ttf";
            }
        }
    }

    /**
     * @return Typeface Instance with the font passed as parameter
     */
    public static Typeface getTypeface(Context context, String typefaceName) {
        Typeface typeFace = null;

        try {

            if (typefaceName.equals(FontType.THIN.toString())) {

                if (mThinFont == null) {
                    mThinFont = Typeface.createFromAsset(
                            context.getAssets(), "fonts/" + typefaceName);
                }

                typeFace = mThinFont;

            } else if(typefaceName.equals(FontType.NORMAL.toString())){
            	if (mNormalFont == null) {
            		mNormalFont = Typeface.createFromAsset(
                            context.getAssets(), "fonts/" + typefaceName);
                }

                typeFace = mNormalFont;
                
            } else if(typefaceName.equals(FontType.EXTRA_NORMAL.toString())){
            	if (mExtraNormalFont == null) {
            		mExtraNormalFont = Typeface.createFromAsset(
                            context.getAssets(), "fonts/" + typefaceName);
                }

                typeFace = mExtraNormalFont;
            }
        } catch (Exception ex) {
            typeFace = Typeface.DEFAULT;
        }

        return typeFace;
    }

    /**
     * @return Typeface Instance with the font passed as parameter
     */
    public static Typeface getTypeface(Context context, FontType typefaceName) {
        return getTypeface(context, typefaceName.toString());
    }
}