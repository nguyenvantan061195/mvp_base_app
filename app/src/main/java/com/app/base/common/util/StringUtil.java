package com.app.base.common.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.Html;

import com.app.base.global.CoreApplication;
import com.app.base.common.logging.TraceLog;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Created by tannv@imt-soft.com on 9/19/18.
 */
public class StringUtil {
    public static boolean isNullOrEmpty(String aString) {
        return (aString == null) || ("".equals(aString.trim()));
    }

    /**
     * keim tra chuoi null hoac empty
     *
     * @param object
     * @return
     * @author: Created by tannv@imt-soft.com on 9/19/18.
     */
    public static boolean isNullOrEmpty(Object object) {
        boolean isNull = (object == null);
        if (!isNull) {
            if (object instanceof String) {
                isNull = isNullOrEmpty((String) object);
            }
        }
        return isNull;
    }

    /**
     * get string from resource id
     *
     * @param id
     * @return
     */
    public static String getString(int id) {
        return CoreApplication.getInstance().getResources().getString(id);
    }

    /**
     * hash string SHA-256
     *
     * @param s
     * @return
     */
    public static String sha256(String s) {
        StringBuilder hexString = new StringBuilder();
        try {
            byte[] defaultBytes = s.getBytes("UTF-8");
            MessageDigest algorithm;
            algorithm = MessageDigest.getInstance("SHA-256");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();

            for (byte aMessageDigest : messageDigest) {
                String hex = Integer.toHexString(0xFF & aMessageDigest);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            LogUtil.w("sha256", TraceLog.getReportFromThrowable(e));
        }
        return hexString.toString();
    }
    public static String getSimSerialNumber() {
        String number = "";
        if (PermissionUtil.checkPermission((Activity) CoreApplication.getInstance().getApplicationContext(), Manifest.permission.READ_PHONE_STATE)) {
            TelephonyManager tm = (TelephonyManager) CoreApplication.getInstance().getApplicationContext()
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null) {
                number = tm.getSimSerialNumber();
            }
        } else {
            // neu chua duoc cap quyen thi thong bao
            // DialogUtils.showMessageDialog(StringUtil.getString(R.string.TEXT_MISSING_READ_PHONE_STATE_PERMISSION));
        }
        return number;
    }

    /**
     * lay chuoi Html
     *
     * @param text
     * @return
     * @author: Created by tannv@imt-soft.com on 9/19/18.
     */
    public static CharSequence getHTMLText(String text) {
        return Html.fromHtml(text);
    }

    /**
     * Lay ten file tu chuoi duong dan URL Vi du:
     * https://www.192.168.1.171/data/file.zip tra ve chuoi: "file"
     *
     * @param url Duong dan URL co chua file name
     * @return
     * @author: Created by tannv@imt-soft.com on 9/19/18.
     */
    public static String getFileNameFromURLString(String url) {
        String fileNameWithoutExtn = null;
        if (!isNullOrEmpty(url)) {
            String fileName = url.substring(url.lastIndexOf('/') + 1, url.length());
            fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));
        }
        return fileNameWithoutExtn;
    }

    public static List<Integer> convertString2Array(String s) {
        if (!isNullOrEmpty(s)) {
            if (!s.contains(",")) {
                List<Integer> ob = new ArrayList<Integer>();
                ob.add(Integer.valueOf(s));
                return ob;
            } else {
                String[] rs = s.split(",");
                ArrayList<Integer> lst = new ArrayList<Integer>();
                for (String field : rs)
                    lst.add(Integer.parseInt(field));
                return lst;
            }
        } else {
            return null;
        }
    }
}
