package cc.ibooker.zmalllib.zbitmap;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Bitmap缓存类-这里采用内存缓存
 * Created by 邹峰立 on 2017/7/6.
 */
public class BitmapCache {
    // 内存缓存，key可以是网络路径，可以是本地路径
    private LruCache<String, Bitmap> mLruCache;

    public BitmapCache() {
        // 使用Runtime类获取最大可用内存缓存（计量单位为Byte）
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        // 设置为可用内存的1/4（按Byte计算）
        int cacheSize = maxMemory / 4;
        mLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                // 在每次存入缓存时进行调用
                return value.getByteCount();
            }
        };
    }

    /**
     * 添加缓存
     *
     * @param key   键
     * @param value 值
     */
    public void addBitmapToCache(String key, Bitmap value) {
        if (value != null && getBitmapFromCache(key) == null)
            mLruCache.put(key, value);
    }

    /**
     * 更新缓存
     *
     * @param key   键
     * @param value 值
     */
    public void updateBitmapToCache(String key, Bitmap value) {
        if (value != null)
            mLruCache.put(key, value);
    }

    /**
     * 取出Bitmap
     *
     * @param key 键
     */
    public Bitmap getBitmapFromCache(String key) {
        return mLruCache.get(key);
    }

    /**
     * 移除Bitmap
     *
     * @param key 键
     */
    public void removeBitmapFromCache(String key) {
        mLruCache.remove(key);
    }

    /**
     * 清空缓存
     */
    public void clearCache() {
        mLruCache.evictAll();
        System.gc();
    }

}

