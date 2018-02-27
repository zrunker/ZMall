package cc.ibooker.zmalllib.zbitmap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Bitmap方法类
 * 1、当生成一个4M的bitmap就会出现oom问题。
 * 2、图片压缩是一个比较耗时的操作，应放在子线程当中
 * <p>
 * Created by 邹峰立 on 2017/7/6.
 */
public class BitmapFun {
    public enum BitmapFunCompressFormat {
        PNG, JPEG, WEBP
    }

    public enum BitmapFunConfig {
        RGB_565, ALPHA_8, ARGB_4444, ARGB_8888
    }

    /**
     * 由图片路径转换成Bitmap
     *
     * @param imgPath         图片路径
     * @param bitmapFunConfig BitmapConfig
     */
    public static Bitmap imgPathToBitmap1(String imgPath, BitmapFunConfig bitmapFunConfig) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        // 分配内存空间
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        // 不进行压缩
        options.inSampleSize = 1;
        options.inPreferredConfig = Bitmap.Config.RGB_565;

        if (bitmapFunConfig != null) {
            if (bitmapFunConfig == BitmapFunConfig.ALPHA_8)
                options.inPreferredConfig = Bitmap.Config.ALPHA_8;
            else if (bitmapFunConfig == BitmapFunConfig.ARGB_4444)
                options.inPreferredConfig = Bitmap.Config.ARGB_4444;
            else if (bitmapFunConfig == BitmapFunConfig.ARGB_8888)
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        }

        return BitmapFactory.decodeFile(imgPath, options);
    }

    /**
     * 将图片路径转换成流之后转化为Bitmap
     *
     * @param imgPath 图片路径
     */
    public static Bitmap imgPathToBitmap2(String imgPath) {
        try {
            FileInputStream fis = new FileInputStream(imgPath);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将Bitmap转换成File，写入SD卡
     *
     * @param bitmap                  Bitmap数据源
     * @param filePath                保存SD卡文件路径
     * @param bitmapFunCompressFormat 图片格式
     * @return Uri
     */
    public static Uri saveBitmapToUri(Bitmap bitmap, String filePath, BitmapFunCompressFormat bitmapFunCompressFormat) {
        boolean isMkdirsSuccess = false;
        // 创建文件夹
        File tmpDir = new File(filePath);
        if (!tmpDir.exists()) {
            isMkdirsSuccess = tmpDir.mkdirs();
        }
        if (isMkdirsSuccess) {
            // 创建文件
            File imgFile = new File(tmpDir.getAbsolutePath() + System.currentTimeMillis());
            try {
                // bitmap写入文件
                FileOutputStream fos = new FileOutputStream(imgFile);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

                if (bitmapFunCompressFormat != null) {
                    if (bitmapFunCompressFormat == BitmapFunCompressFormat.JPEG)
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    else if (bitmapFunCompressFormat == BitmapFunCompressFormat.WEBP)
                        bitmap.compress(Bitmap.CompressFormat.WEBP, 100, fos);
                }

                fos.flush();
                fos.close();
                return Uri.fromFile(imgFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将Bitmap转换成图片文件，写入SD卡
     *
     * @param bitmap                  Bitmap数据源
     * @param filePath                保存SD卡文件路径
     * @param bitmapFunCompressFormat 图片格式
     * @return ImagePath
     */
    public static String saveBitmapToImgPath(Bitmap bitmap, String filePath, BitmapFunCompressFormat bitmapFunCompressFormat) {
        boolean isMkdirsSuccess = false;
        // 创建文件夹
        File tmpDir = new File(filePath);
        if (!tmpDir.exists()) {
            isMkdirsSuccess = tmpDir.mkdirs();
        }
        if (isMkdirsSuccess) {
            // 保存文件路径
            String imgPath = tmpDir.getAbsolutePath() + System.currentTimeMillis() + ".png";
            // 创建文件
            File imgFile = new File(imgPath);
            try {
                // bitmap写入文件
                FileOutputStream fos = new FileOutputStream(imgFile);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

                if (bitmapFunCompressFormat != null) {
                    if (bitmapFunCompressFormat == BitmapFunCompressFormat.JPEG)
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    else if (bitmapFunCompressFormat == BitmapFunCompressFormat.WEBP)
                        bitmap.compress(Bitmap.CompressFormat.WEBP, 100, fos);
                }

                fos.flush();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return imgPath;
        }
        return null;
    }

    /**
     * 根据图片路径压缩图片并转成Bitmap，宽和高同步递减
     *
     * @param imgPath 真实图片路径
     * @param width   图片最终宽
     * @param height  图片最终高
     */
    public static Bitmap imgPathToReSizeBitmap(String imgPath, int width, int height) {
        try {
            FileInputStream in = new FileInputStream(new File(imgPath));
            BitmapFactory.Options options = new BitmapFactory.Options();
            // 生产Bitmap不分配内存空间
            options.inJustDecodeBounds = true;
            // 传递图片，主要为了获取图片宽和高
            BitmapFactory.decodeStream(in, null, options);
            in.close();

            int i = 0;
            Bitmap bitmap;
            while (true) {
                if ((options.outWidth >> i <= width) && (options.outHeight >> i <= height)) {
                    in = new FileInputStream(new File(imgPath));
                    // 新生成的图是原图的几分之几
                    options.inSampleSize = (int) Math.pow(2.0D, i);
                    // 生产Bitmap分配内存空间
                    options.inJustDecodeBounds = false;
                    bitmap = BitmapFactory.decodeStream(in, null, options);
                    break;
                }
                i += 1;
            }
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过uri获取图片并进行比例压缩和进行一次质量压缩，大于1M的时候进行质量压缩
     *
     * @param activity                上下文对象
     * @param uri                     uri数据源
     * @param bitmapFunConfig         BitmapConfig
     * @param bitmapFunCompressFormat 图片格式
     */
    public static Bitmap uriToReSizeBitmap(Activity activity, Uri uri, BitmapFunConfig bitmapFunConfig, BitmapFunCompressFormat bitmapFunCompressFormat) {
        try {
            Bitmap.Config bConfig = Bitmap.Config.ARGB_8888;
            if (bitmapFunConfig != null) {
                if (bitmapFunConfig == BitmapFunConfig.RGB_565)
                    bConfig = Bitmap.Config.RGB_565;
                else if (bitmapFunConfig == BitmapFunConfig.ALPHA_8)
                    bConfig = Bitmap.Config.ALPHA_8;
                else if (bitmapFunConfig == BitmapFunConfig.ARGB_4444)
                    bConfig = Bitmap.Config.ARGB_4444;
            }

            Bitmap.CompressFormat bCompressFormat = Bitmap.CompressFormat.PNG;
            if (bitmapFunCompressFormat != null) {
                if (bitmapFunCompressFormat == BitmapFunCompressFormat.JPEG)
                    bCompressFormat = Bitmap.CompressFormat.JPEG;
                else if (bitmapFunCompressFormat == BitmapFunCompressFormat.WEBP)
                    bCompressFormat = Bitmap.CompressFormat.WEBP;
            }

            InputStream input = activity.getContentResolver().openInputStream(uri);

            BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
            onlyBoundsOptions.inJustDecodeBounds = true;
            onlyBoundsOptions.inDither = true;
            onlyBoundsOptions.inPreferredConfig = bConfig;
            BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
            if (input != null) {
                input.close();
            }

            int originalWidth = onlyBoundsOptions.outWidth;
            int originalHeight = onlyBoundsOptions.outHeight;
            if ((originalWidth == -1) || (originalHeight == -1))
                return null;

            //图片分辨率以480x800为标准
            float hh = 800f;//这里设置高度为800f
            float ww = 480f;//这里设置宽度为480f
            //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
            int be = 1;//be=1表示不缩放
            if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
                be = (int) (originalWidth / ww);
            } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
                be = (int) (originalHeight / hh);
            }
            if (be <= 0)
                be = 1;

            //比例压缩
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inSampleSize = be;//设置缩放比例
            bitmapOptions.inDither = true;
            bitmapOptions.inPreferredConfig = bConfig;
            input = activity.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
            if (input != null) {
                input.close();
            }

            //做一次质量压缩
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(bCompressFormat, 100, baos);
            if (baos.toByteArray().length / 1024 > 1024) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
                baos.reset();// 重置baos即清空baos
                bitmap.compress(bCompressFormat, 50, baos);// 这里压缩50%，把压缩后的数据存放到baos中
            }
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Bitmap按照质量来压缩图片，压缩到<=maxSize
     *
     * @param bitmap                  bitmap数据源
     * @param maxSize                 最大大小（K）
     * @param bitmapFunCompressFormat 图片格式
     */
    public static Bitmap compressBitmapByQuality(Bitmap bitmap, int maxSize, BitmapFunCompressFormat bitmapFunCompressFormat) {
        try {
            Bitmap.CompressFormat bCompressFormat = Bitmap.CompressFormat.PNG;
            if (bitmapFunCompressFormat != null) {
                if (bitmapFunCompressFormat == BitmapFunCompressFormat.JPEG)
                    bCompressFormat = Bitmap.CompressFormat.JPEG;
                else if (bitmapFunCompressFormat == BitmapFunCompressFormat.WEBP)
                    bCompressFormat = Bitmap.CompressFormat.WEBP;
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(bCompressFormat, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            int options = 100;
            while (baos.toByteArray().length / 1024 > maxSize) { //循环判断如果压缩后图片是否大于maxSize kb,大于继续压缩
                if (options > 0) {
                    baos.reset();//重置baos，即清空baos
                    options -= 20;//每次都减少20
                    bitmap.compress(bCompressFormat, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
                } else {
                    break;
                }
            }
            ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
            Bitmap realBitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
            baos.close();
            isBm.close();
            return realBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Bitmap图片按比例大小压缩方法
     *
     * @param bitmap                  Bitmap数据源
     * @param pixelW                  对比宽
     * @param pixelH                  对比高
     * @param bitmapFunConfig         BitmapConfig
     * @param bitmapFunCompressFormat 图片格式
     */
    public static Bitmap compressBitmapByRatio(Bitmap bitmap, float pixelW, float pixelH, BitmapFunConfig bitmapFunConfig, BitmapFunCompressFormat bitmapFunCompressFormat) {
        Bitmap.Config bConfig = Bitmap.Config.RGB_565;
        if (bitmapFunConfig != null) {
            if (bitmapFunConfig == BitmapFunConfig.ARGB_8888)
                bConfig = Bitmap.Config.ARGB_8888;
            else if (bitmapFunConfig == BitmapFunConfig.ALPHA_8)
                bConfig = Bitmap.Config.ALPHA_8;
            else if (bitmapFunConfig == BitmapFunConfig.ARGB_4444)
                bConfig = Bitmap.Config.ARGB_4444;
        }
        Bitmap.CompressFormat bCompressFormat = Bitmap.CompressFormat.PNG;
        if (bitmapFunCompressFormat != null) {
            if (bitmapFunCompressFormat == BitmapFunCompressFormat.JPEG)
                bCompressFormat = Bitmap.CompressFormat.JPEG;
            else if (bitmapFunCompressFormat == BitmapFunCompressFormat.WEBP)
                bCompressFormat = Bitmap.CompressFormat.WEBP;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(bCompressFormat, 100, os);
        if (os.toByteArray().length / 1024 > 2048) {//判断如果图片大于2M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            os.reset();//重置baos即清空baos
            bitmap.compress(bCompressFormat, 50, os);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设true
        newOpts.inJustDecodeBounds = true;
        newOpts.inPreferredConfig = bConfig;
        Bitmap realBitmap;
        BitmapFactory.decodeStream(is, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > pixelW) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / pixelW);
        } else if (w < h && h > pixelH) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / pixelH);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设false
        is = new ByteArrayInputStream(os.toByteArray());
        realBitmap = BitmapFactory.decodeStream(is, null, newOpts);
        return realBitmap;
    }

}
