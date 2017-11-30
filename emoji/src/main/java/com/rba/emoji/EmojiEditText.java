package com.rba.emoji;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.text.style.DynamicDrawableSpan;
import android.util.AttributeSet;

/**
 * Created by Ricardo Bravo on 29/11/2017.
 */

public class EmojiEditText extends AppCompatEditText {

    private int mEmojiconSize;
    private int mEmojiconAlignment;
    private int mEmojiconTextSize;
    private boolean mUseSystemDefault = false;

    public EmojiEditText(Context context) {
        super(context);
        mEmojiconSize = (int) getTextSize();
        mEmojiconTextSize = (int) getTextSize();
    }

    public EmojiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EmojiEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Emoji);
        mEmojiconSize = (int) a.getDimension(R.styleable.Emoji_emojiSize, getTextSize());
        mEmojiconAlignment = a.getInt(R.styleable.Emoji_emojiAlignment, DynamicDrawableSpan.ALIGN_BASELINE);
        mUseSystemDefault = a.getBoolean(R.styleable.Emoji_emojiUseSystemDefault, false);
        a.recycle();
        mEmojiconTextSize = (int) getTextSize();
        setText(getText());
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        updateText();
    }

    public void setEmojiconSize(int pixels) {
        mEmojiconSize = pixels;

        updateText();
    }

    private void updateText() {
        EmojiHandler.addEmojis(getContext(), getText(), mEmojiconSize, mEmojiconAlignment,
                mEmojiconTextSize, mUseSystemDefault);
    }

    public void setUseSystemDefault(boolean useSystemDefault) {
        mUseSystemDefault = useSystemDefault;
    }
}