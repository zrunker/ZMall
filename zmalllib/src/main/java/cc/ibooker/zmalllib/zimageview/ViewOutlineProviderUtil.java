package cc.ibooker.zmalllib.zimageview;

import android.graphics.Outline;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewOutlineProvider;

/**
 * Android5.0特性 轮廓提供者
 * <p>
 * Created by 邹峰立 on 2018/2/4 0004.
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ViewOutlineProviderUtil {
    private enum TYPE {
        ALPHA, OVAL_ONE, OVAL_TWO, ROUNDRECT_ONE, ROUNDRECT_TWO, RECT_ONE, RECT_TWO
    }

    /**
     * 透明度
     *
     * @param view  目标View
     * @param alpha 透明度0~1
     */
    public void setAlpha(View view, float alpha) {
        view.setOutlineProvider(new MViewOutlineProvider(TYPE.ALPHA, alpha));
        view.setClipToOutline(true);
    }

    /**
     * 设置圆1
     *
     * @param view 目标View
     * @param rect 矩形图
     */
    public void setOval(View view, Rect rect) {
        view.setOutlineProvider(new MViewOutlineProvider(TYPE.OVAL_ONE, rect));
        view.setClipToOutline(true);
    }

    /**
     * 设置圆2
     *
     * @param view   目标View
     * @param left   目标View左侧距离
     * @param top    目标View上侧距离
     * @param right  目标View右侧距离
     * @param bottom 目标View下侧距离
     */
    public void setOval(View view, int left, int top, int right, int bottom) {
        view.setOutlineProvider(new MViewOutlineProvider(TYPE.OVAL_TWO, left, top, right, bottom));
        view.setClipToOutline(true);
    }

    /**
     * 设置圆角矩形1
     *
     * @param view   目标View
     * @param rect   矩形图
     * @param radius 半径
     */
    public void setRoundRect(View view, Rect rect, float radius) {
        view.setOutlineProvider(new MViewOutlineProvider(TYPE.ROUNDRECT_ONE, radius, rect));
        view.setClipToOutline(true);
    }

    /**
     * 设置圆角矩形2
     *
     * @param view   目标View
     * @param left   目标View左侧距离
     * @param top    目标View上侧距离
     * @param right  目标View右侧距离
     * @param bottom 目标View下侧距离
     * @param radius 半径
     */
    public void setRoundRect(View view, int left, int top, int right, int bottom, float radius) {
        view.setOutlineProvider(new MViewOutlineProvider(TYPE.ROUNDRECT_TWO, radius, left, top, right, bottom));
        view.setClipToOutline(true);
    }

    /**
     * 设置矩形1
     *
     * @param view 目标View
     * @param rect 矩形图
     */
    public void setRect(View view, Rect rect) {
        view.setOutlineProvider(new MViewOutlineProvider(TYPE.RECT_ONE, rect));
        view.setClipToOutline(true);
    }

    /**
     * 设置矩形2
     *
     * @param view   目标View
     * @param left   目标View左侧距离
     * @param top    目标View上侧距离
     * @param right  目标View右侧距离
     * @param bottom 目标View下侧距离
     */
    public void setRect(View view, int left, int top, int right, int bottom) {
        view.setOutlineProvider(new MViewOutlineProvider(TYPE.RECT_TWO, left, top, right, bottom));
        view.setClipToOutline(true);
    }

    /**
     * 自定义内部类实现轮廓提供者
     */
    private class MViewOutlineProvider extends ViewOutlineProvider {
        private TYPE type;
        private float alpha, radius;
        private int left, top, right, bottom;
        private Rect rect;

        MViewOutlineProvider(TYPE type, float alpha) {
            this.type = type;
            this.alpha = alpha;
        }

        MViewOutlineProvider(TYPE type, Rect rect) {
            this.type = type;
            this.rect = rect;
        }

        MViewOutlineProvider(TYPE type, int left, int top, int right, int bottom) {
            this.type = type;
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }

        MViewOutlineProvider(TYPE type, float radius, Rect rect) {
            this.type = type;
            this.radius = radius;
            this.rect = rect;
        }

        MViewOutlineProvider(TYPE type, float radius, int left, int top, int right, int bottom) {
            this.type = type;
            this.radius = radius;
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }

        @Override
        public void getOutline(View view, Outline outline) {
//        int margin = Math.min(view.getWidth(), view.getHeight()) / 10;
            switch (type) {
                case ALPHA:
                    outline.setAlpha(alpha);
                    break;
                case OVAL_ONE:
                    outline.setOval(rect);
                    break;
                case OVAL_TWO:
                    outline.setOval(left, top, view.getWidth() - right, view.getHeight() - bottom);
                    break;
                case ROUNDRECT_ONE:
                    outline.setRoundRect(rect, radius);
                    break;
                case ROUNDRECT_TWO:
                    outline.setRoundRect(left, top, view.getWidth() - right, view.getHeight() - bottom, radius);
                    break;
                case RECT_ONE:
                    outline.setRect(rect);
                    break;
                case RECT_TWO:
                    outline.setRect(left, top, view.getWidth() - right, view.getHeight() - bottom);
                    break;
            }
        }
    }
}
