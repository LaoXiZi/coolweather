package com.sq.coolweather.util;

import android.text.TextUtils;

import com.sq.coolweather.db.City;
import com.sq.coolweather.db.County;
import com.sq.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *  * Author：Elt on 2017/6/29 09:45
 */

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvince = new JSONArray(response);
                for (int i = 0; i < allProvince.length(); i++) {
                    JSONObject allProvinceJSONObject = allProvince.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(allProvinceJSONObject.getString("name"));
                    province.setProvinceCode(allProvinceJSONObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * 解析和处理服务器返回的市级数据
     */
    public static boolean handleCityResponse(String response,int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCity = new JSONArray(response);
                for (int i = 0; i < allCity.length(); i++) {
                    JSONObject allCityJSONObject = allCity.getJSONObject(i);
                    City city = new City();
                    city.setCityName(allCityJSONObject.getString("name"));
                    city.setCityCode(allCityJSONObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response,int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounty = new JSONArray(response);
                for (int i = 0; i < allCounty.length(); i++) {
                    JSONObject allCountyJSONObject = allCounty.getJSONObject(i);
                    County county = new County();
                    county.setCityId(allCountyJSONObject.getInt("id"));
                    county.setCountyName(allCountyJSONObject.getString("name"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
