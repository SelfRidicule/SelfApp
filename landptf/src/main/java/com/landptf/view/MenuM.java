package com.landptf.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.landptf.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by landptf on 2017/01/15.
 * 菜单，可用于底部导航菜单，以及内容区的菜单列表
 */
public class MenuM extends LinearLayout {
    private static final String TAG = MenuM.class.getSimpleName();

    private Context mContext;
    private List<MenuItemM> menuList;
    private List<RelativeLayout> rlList;
    private OnItemClickListener mOnItemClickListener;
    private int count = 0;

    public MenuM(Context context) {
        this(context, null, 0);
    }

    public MenuM(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuM(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        setOrientation(LinearLayout.HORIZONTAL);
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.menuM, defStyle, 0);
        if (a != null) {
            //初始化菜单数量
            count = a.getInteger(R.styleable.menuM_count, 0);
            if (count > 0) {
                initControl();
            }
            //设置背景色
            ColorStateList colorList = a.getColorStateList(R.styleable.menuM_backColor);
            if (colorList != null) {
                int backColor = colorList.getColorForState(getDrawableState(), 0);
                if (backColor != 0) {
                    setBackColor(backColor);
                }
            }
            //设置文字的颜色
            ColorStateList textColorList = a.getColorStateList(R.styleable.menuM_textColor);
            if (textColorList != null) {
                int textColor = textColorList.getColorForState(getDrawableState(), 0);
                if (textColor != 0) {
                    setTextColor(textColor);
                }
            }
            //记录View被按下时文字的颜色
            ColorStateList textColorPressList = a.getColorStateList(R.styleable.menuM_textColorPress);
            if (textColorPressList != null) {
                int textColorPress = textColorPressList.getColorForState(getDrawableState(), 0);
                if (textColorPress != 0) {
                    setTextColorPress(textColorPress);
                }
            }
            //设置文本字体大小
            float textSize = a.getFloat(R.styleable.menuM_textSize, 0);
            if (textSize != 0) {
                setTextSize(textSize);
            }
            a.recycle();
        }
    }

    /**
     * 由于MenuItemM是有ButtonExtendM扩展而来，为了适应上下左右不同的样式
     * 需要在MenuItemM外层嵌套一层RelativeLayout，暂时没有找到更好的替代方案
     */
    private void initControl() {
        rlList = new ArrayList<>(count);
        menuList = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            RelativeLayout rlPanel = new RelativeLayout(mContext);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            rlPanel.setLayoutParams(lp);
            final MenuItemM menuItem = new MenuItemM(mContext);
            RelativeLayout.LayoutParams lpR = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lpR.addRule(RelativeLayout.CENTER_IN_PARENT);
            menuItem.setLayoutParams(lpR);
            menuItem.setOnClickListener(new MenuItemM.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //此处需要根据view获取position
                    MenuM.this.onClick(getPosition(menuItem));
                }
            });
            rlPanel.addView(menuItem);
            menuList.add(menuItem);
            rlList.add(rlPanel);
            addView(rlPanel);
        }
    }

    /**
     * 设置View的背景色
     *
     * @param backColor
     */
    public void setBackColor(int backColor) {
        if (backColor == 0) return;
        if (!checkCount()) {
            return;
        }
        for (RelativeLayout item : rlList) {
            item.setBackgroundColor(backColor);
        }
        for (MenuItemM item : menuList) {
            item.setBackColor(backColor);
        }
    }

    /**
     * 设置文字的颜色
     *
     * @param textColor
     */
    public void setTextColor(int textColor) {
        if (textColor == 0) return;
        if (!checkCount()) {
            return;
        }
        for (MenuItemM item : menuList) {
            item.setTextColor(textColor);
        }
    }

    /**
     * 设置View被按下时文字的颜色
     *
     * @param textColorPress
     */
    public void setTextColorPress(int textColorPress) {
        if (textColorPress == 0) return;
        if (!checkCount()) {
            return;
        }
        for (MenuItemM item : menuList) {
            item.setTextColorPress(textColorPress);
        }
    }

    /**
     * 设置icon的图片
     *
     * @param iconDrawable
     */
    public void setIconDrawable(Drawable[] iconDrawable) {
        if (count != iconDrawable.length) {
            Log.e(TAG, "the iconDrawable length do not equals count");
            return;
        }
        for (int i = 0; i < count; i++) {
            if (iconDrawable[i] != null) {
                menuList.get(i).setIconDrawable(iconDrawable[i]);
            }
        }
    }

    /**
     * 设置icon的图片
     *
     * @param iconDrawable
     */
    public void setIconDrawable(List<Drawable> iconDrawable) {
        if (count != iconDrawable.size()) {
            Log.e(TAG, "the iconDrawable length do not equals count");
            return;
        }
        for (int i = 0; i < count; i++) {
            if (iconDrawable.get(i) != null) {
                menuList.get(i).setIconDrawable(iconDrawable.get(i));
            }
        }
    }

    /**
     * 设置View被按下时的icon的图片
     *
     * @param iconDrawablePress
     */
    public void setIconDrawablePress(Drawable[] iconDrawablePress) {
        if (count != iconDrawablePress.length) {
            Log.e(TAG, "the iconDrawablePress length do not equals count");
            return;
        }
        for (int i = 0; i < count; i++) {
            if (iconDrawablePress[i] != null) {
                menuList.get(i).setIconDrawablePress(iconDrawablePress[i]);
            }
        }
    }

    /**
     * 设置View被按下时的icon的图片
     *
     * @param iconDrawablePress
     */
    public void setIconDrawablePress(List<Drawable> iconDrawablePress) {
        if (count != iconDrawablePress.size()) {
            Log.e(TAG, "the iconDrawablePress length do not equals count");
            return;
        }
        for (int i = 0; i < count; i++) {
            if (iconDrawablePress.get(i) != null) {
                menuList.get(i).setIconDrawablePress(iconDrawablePress.get(i));
            }
        }
    }

    /**
     * 设置显示的文本内容
     *
     * @param text
     */
    public void setText(CharSequence[] text) {
        for (int i = 0; i < count; i++) {
            menuList.get(i).setText(text[i]);
        }
    }

    /**
     * 设置显示的文本内容
     *
     * @param text
     */
    public void setText(List<CharSequence> text) {
        if (count != text.size()) {
            Log.e(TAG, "the text length do not equals count");
            return;
        }
        for (int i = 0; i < count; i++) {
            menuList.get(i).setText(text.get(i));
        }
    }

    /**
     * 获取显示的文本
     *
     * @return
     */
    public String getText(int index) {
        if (!checkIndex(index)) {
            return "";
        }
        return menuList.get(index).getText();
    }

    /**
     * 设置文本字体大小
     *
     * @param size
     */
    public void setTextSize(float size) {
        if (!checkCount()) {
            return;
        }
        for (MenuItemM item : menuList) {
            item.setTextSize(size);
        }
    }

    /**
     * 设置更多提示是否显示
     * 如果显示则先重置new和未读数量图标
     *
     * @param visibleMore
     */
    public void setVisibilityMore(int index, int visibleMore) {
        if (!checkIndex(index)) {
            return;
        }
        menuList.get(index).setVisibilityMore(visibleMore);
    }

    /**
     * 设置New提示是否显示
     * 如果显示则先重置更多和未读数量图标
     *
     * @param visibleNew
     */
    public void setVisibilityNew(int index, int visibleNew) {
        if (!checkIndex(index)) {
            return;
        }
        menuList.get(index).setVisibilityNew(visibleNew);
    }

    /**
     * 设置未读数量
     * 如果小于等于0，表示隐藏
     * 如果大于99，则将其隐藏，同时显示更多的提示
     * 如果在0-99区间，则隐藏更多和new图标
     *
     * @param unReadCount
     */
    public void setUnReadCount(int index, int unReadCount) {
        if (!checkIndex(index)) {
            return;
        }
        menuList.get(index).setUnReadCount(unReadCount);
    }

    /**
     * 设置为被选中状态
     *
     * @param index
     * @param state in MotionEvent.ACTION_DOWN or MotionEvent.ACTION_UP
     */
    public void setPressState(int index, int state) {
        if (!checkIndex(index)) {
            return;
        }
        menuList.get(index).setPressState(state);
    }

    /**
     * 设置菜单点击事件
     *
     * @param listener
     */
    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    private void onClick(int position) {
        for (int i = 0; i < count; i++) {
            if (i == position) {
                setPressState(i, MotionEvent.ACTION_DOWN);
            } else {
                setPressState(i, MotionEvent.ACTION_UP);
            }
        }
        mOnItemClickListener.onItemClick(position);
    }

    /**
     * 获取点击菜单项的位置
     * @param item
     * @return
     */
    private int getPosition(MenuItemM item) {
        for (int i = 0; i < count; i++) {
            if (item == menuList.get(i)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 检查是否设置了Count参数
     *
     * @return
     */
    private boolean checkCount() {
        if (count == 0) {
            Log.e(TAG, "You must set the count first");
            return false;
        }
        return true;
    }

    /**
     * 校验输入参数是否合法
     *
     * @param index
     * @return
     */
    private boolean checkIndex(int index) {
        if (!checkCount()) {
            return false;
        }
        if (index < 0 || index >= count) {
            Log.e(TAG, "the index is wrong");
            return false;
        }
        return true;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
