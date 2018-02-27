package cc.ibooker.zmalllib.zimageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.io.IOException;

/**
 * 圆角图片
 *
 * @Author 邹峰立
 */
public class CircleImgViewUtil {
    private Context context;
    private Paint paint;

    public CircleImgViewUtil(Context context) {
        this.context = context;
        this.paint = new Paint();
    }

    /**
     * 获得圆角图片的方法
     */
    public Bitmap toRoundCornerBitmap(Bitmap bitmap) {
        try {
            // Bitmap格式转换
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xff424242;
            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);

            // 画圆
            int size = Math.min(bitmap.getWidth(), bitmap.getHeight());
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            // 是图片全部重叠
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setImageBitmap(ImageView imageView, Bitmap bm) {
        bm = toRoundCornerBitmap(bm);
        imageView.setImageBitmap(bm);
    }

    public void setImageResource(ImageView imageView, int resId) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        setImageBitmap(imageView, bitmap);
    }

    public void setImageDrawable(ImageView imageView, Drawable drawable) {
        if (drawable != null) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            setImageBitmap(imageView, bitmap);
        }
    }

    public void setImageIcon(ImageView imageView, Icon icon) {
        if (icon != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Drawable drawable = icon.loadDrawable(context);
                setImageDrawable(imageView, drawable);
            }
        }
    }

    public void setImageURI(ImageView imageView, Uri uri) {
        if (uri != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
                setImageBitmap(imageView, bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
