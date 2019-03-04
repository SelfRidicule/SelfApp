package com.landptf.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.landptf.R;

/**
 * Created by landptf on 2016/11/07.
 * 菜单按钮，例如底部菜单的item或者消息控件
 */
public class MenuItemM extends FrameLayout {

    private static final String TAG = MenuItemM.class.getSimpleName();

    /**
     * 定义控件
     */
    private ButtonExtendM bemMenu;
    private ImageView ivMore;
    private ImageView ivNew;
    private ButtonM btmUnReadCount;

    private OnClickListener onClickListener = null;

    public interface OnClickListener {
        void onClick(View v);
    }

    /**
     * 设置View的Click事件
     *
     * @param l
     */
    public void setOnClickListener(OnClickListener l) {
        this.onClickListener = l;
        //拦截ButtonExtendM控件的点击事件，使其指向this.onclick
        bemMenu.setOnClickListener(new ButtonExtendM.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v);
            }
        });
    }

    public MenuItemM(Context context) {
        this(context, null, 0);
    }

    public MenuItemM(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuItemM(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        //加载布局
        LayoutInflater.from(context).inflate(R.layout.view_menu_item_m, this, true);
        //初始化控件
        bemMenu = findViewById(R.id.bem_menu);
        ivMore = findViewById(R.id.iv_more);
        ivNew = findViewById(R.id.iv_new);
        btmUnReadCount = findViewById(R.id.btm_unread_count);
        btmUnReadCount.setGravity(Gravity.CENTER);
        TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.menuItemM, defStyle, 0);
        if (a != null) {
            //设置背景色
            ColorStateList colorList = a.getColorStateList(R.styleable.menuItemM_backColor);
            if (colorList != null) {
                int backColor = colorList.getColorForState(getDrawableState(), 0);
                if (backColor != 0) {
                    setBackColor(backColor);
                }
            }
            //设置icon
            Drawable iconDrawable = a.getDrawable(R.styleable.menuItemM_iconDrawable);
            if (iconDrawable != null) {
                setIconDrawable(iconDrawable);
            }
            //记录View被按下时的icon的图片
            Drawable iconDrawablePress = a.getDrawable(R.styleable.menuItemM_iconDrawablePress);
            if (iconDrawablePress != null) {
                setIconDrawablePress(iconDrawablePress);
            }
            //设置文字的颜色
            ColorStateList textColorList = a.getColorStateList(R.styleable.menuItemM_textColor);
            if (textColorList != null) {
                int textColor = textColorList.getColorForState(getDrawableState(), 0);
                if (textColor != 0) {
                    setTextColor(textColor);
                }
            }
            //记录View被按下时文字的颜色
            ColorStateList textColorPressList = a.getColorStateList(R.styleable.menuItemM_textColorPress);
            if (textColorPressList != null) {
                int textColorPress = textColorPressList.getColorForState(getDrawableState(), 0);
                if (textColorPress != 0) {
                    setTextColorPress(textColorPress);
                }
            }
            //设置显示的文本内容
            String text = a.getString(R.styleable.menuItemM_text);
            if (text != null) {
                setText(text);
            }
            //设置文本字体大小
            float textSize = a.getFloat(R.styleable.menuItemM_textSize, 0);
            if (textSize != 0) {
                setTextSize(textSize);
            }
            //设置更多提示是否显示
            int visibleMore = a.getInt(R.styleable.menuItemM_visibleMore, -1);
            if (visibleMore != -1){
                setVisibilityMore(visibleMore);
            }
            //设置new提示是否显示
            int visibleNew = a.getInt(R.styleable.menuItemM_visibleNew, -1);
            if (visibleNew != -1){
                setVisibilityNew(visibleNew);
            }
            //设置消息未读数量
            int unReadCount = a.getInt(R.styleable.menuItemM_unReadCount, -1);
            if (unReadCount != -1){
                setUnReadCount(unReadCount);
            }
            a.recycle();
        }

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    onClickListener.onClick(v);
                }
            }
        });
    }

    /**
     * 设置为被选中状态
     *   @param state in MotionEvent.ACTION_DOWN or MotionEvent.ACTION_UP
     */
    public void setPressState(int state){
        bemMenu.setPressState(state);
    }

    /**
     * 设置View的背景色
     *
     * @param backColor
     */
    public void setBackColor(int backColor) {
        bemMenu.setBackColor(backColor);
    }

    /**
     * 设置icon的图片
     *
     * @param iconDrawable
     */
    public void setIconDrawable(Drawable iconDrawable) {
        bemMenu.setIconDrawable(iconDrawable);
    }

    /**
     * 设置View被按下时的icon的图片
     *
     * @param iconDrawablePress
     */
    public void setIconDrawablePress(Drawable iconDrawablePress) {
        bemMenu.setIconDrawablePress(iconDrawablePress);
    }

    /**
     * 设置文字的颜色
     *
     * @param textColor
     */
    public void setTextColor(int textColor) {
        if (textColor == 0) return;
        bemMenu.setTextColor(textColor);
    }

    /**
     * 设置View被按下时文字的颜色
     *
     * @param textColorPress
     */
    public void setTextColorPress(int textColorPress) {
        if (textColorPress == 0) return;
        bemMenu.setTextColorPress(textColorPress);
    }

    /**
     * 设置显示的文本内容
     *
     * @param text
     */
    public void setText(CharSequence text) {
        bemMenu.setText(text);
    }

    /**
     * 获取显示的文本
     *
     * @return
     */
    public String getText() {
        return bemMenu.getText();
    }

    /**
     * 设置文本字体大小
     *
     * @param size
     */
    public void setTextSize(float size) {
        bemMenu.setTextSize(size);
    }

    /**
     * 设置更多提示是否显示
     *   如果显示则先重置new和未读数量图标
     * @param visibleMore
     */
    public void setVisibilityMore(int visibleMore) {
        if (visibleMore == VISIBLE) {
            resetTip();
        }
        ivMore.setVisibility(visibleMore);
    }

    /**
     * 设置New提示是否显示
     *   如果显示则先重置更多和未读数量图标
     * @param visibleNew
     */
    public void setVisibilityNew(int visibleNew) {
        if (visibleNew == VISIBLE) {
            resetTip();
        }
        ivNew.setVisibility(visibleNew);
    }

    /**
     * 设置未读数量
     *   如果小于等于0，表示隐藏
     *   如果大于99，则将其隐藏，同时显示更多的提示
     *   如果在0-99区间，则隐藏更多和new图标
     * @param unReadCount
     */
    public void setUnReadCount(int unReadCount){
        if (unReadCount <= 0){
            btmUnReadCount.setVisibility(GONE);
            //如果先设置100（此时会显示ivMore），再设置0，因此此处应将ivMore同时置为GONE
            if (ivMore.getVisibility() == VISIBLE){
                ivMore.setVisibility(GONE);
            }
            return;
        }
        if (unReadCount > 99){
            setVisibilityMore(VISIBLE);
            return;
        }
        resetTip();
        btmUnReadCount.setVisibility(VISIBLE);
        btmUnReadCount.setText(unReadCount + "");
    }

    /**
     * 重置提示信息
     */
    private void resetTip(){
        setVisibilityMore(GONE);
        setVisibilityNew(GONE);
        setUnReadCount(0);
    }

}
