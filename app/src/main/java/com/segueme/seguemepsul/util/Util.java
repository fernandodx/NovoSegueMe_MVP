package com.segueme.seguemepsul.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by FernandoDias on 18/01/17.
 */

public class Util {


    public static String formatarDataDiaMes(Date data) {

        DateFormat df2 = new SimpleDateFormat ("dd MMMM", new Locale("pt", "BR"));
        return df2.format(data);

    }

    public static String formatarData(Date data) {

        DateFormat df2 = new SimpleDateFormat ("dd 'de' MMMM 'de' yyyy", new Locale("pt", "BR"));
        return df2.format(data);

    }

    public static String formatarAno(Date data) {

        DateFormat df2 = new SimpleDateFormat ("yyyy", new Locale("pt", "BR"));
        return df2.format(data);

    }

    public static String BitmapToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    public static Bitmap StringBase64ToBitmap(String input) {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static Date createDateWithTime(long time) {

        Calendar data = Calendar.getInstance();
        data.setTimeInMillis(time);

        return data.getTime();

    }


}
