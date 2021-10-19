package com.sum.sample.application.main;

import com.sum.sample.R;

public class IconProvider {
    private static int[] iconArr = new int[]{R.mipmap.icon_1, R.mipmap.icon_2, R.mipmap.icon_3, R.mipmap.icon_4, R.mipmap.icon_5, R.mipmap.icon_6,
            R.mipmap.icon_7, R.mipmap.icon_8, R.mipmap.icon_9, R.mipmap.icon_10, R.mipmap.icon_11, R.mipmap.icon_12, R.mipmap.icon_13,
            R.mipmap.icon_14, R.mipmap.icon_15, R.mipmap.icon_16, R.mipmap.icon_17, R.mipmap.icon_18, R.mipmap.icon_19, R.mipmap.icon_20};

    private static int[] flowerArr = new int[]{R.mipmap.icon_flower_1, R.mipmap.icon_flower_2, R.mipmap.icon_flower_3};

    public static int getIcon(int position) {
        int result = position % 20;
        return iconArr[result];
    }

    public static int getFlower(int position) {
        int result = position % 3;
        return flowerArr[result];
    }

}
