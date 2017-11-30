package com.rba.emoji;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.DynamicDrawableSpan;
import android.util.AttributeSet;

/**
 * Created by Ricardo Bravo on 29/11/2017.
 */

public class EmojiTextView extends AppCompatTextView {
    private int mEmojiconSize;
    private int mEmojiconAlignment;
    private int mEmojiconTextSize;
    private int mTextStart = 0;
    private int mTextLength = -1;
    private boolean mUseSystemDefault = false;

    public EmojiTextView(Context context) {
        super(context);
        init(null);
    }

    public EmojiTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public EmojiTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mEmojiconTextSize = (int) getTextSize();
        if (attrs == null) {
            mEmojiconSize = (int) getTextSize();
        } else {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Emoji);
            mEmojiconSize = (int) a.getDimension(R.styleable.Emoji_emojiSize, getTextSize());
            mEmojiconAlignment = a.getInt(R.styleable.Emoji_emojiAlignment, DynamicDrawableSpan.ALIGN_BASELINE);
            mTextStart = a.getInteger(R.styleable.Emoji_emojiTextStart, 0);
            mTextLength = a.getInteger(R.styleable.Emoji_emojiTextLength, -1);
            mUseSystemDefault = a.getBoolean(R.styleable.Emoji_emojiUseSystemDefault, false);
            a.recycle();
        }
        setText(getText());
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        CharSequence newText = text;
        if (!TextUtils.isEmpty(newText)) {
            SpannableStringBuilder builder = new SpannableStringBuilder(newText);
            EmojiHandler.addEmojis(getContext(), builder, mEmojiconSize, mEmojiconAlignment,
                    mEmojiconTextSize, mTextStart, mTextLength, mUseSystemDefault);
            newText = builder;
        }
        super.setText(newText, type);
    }

    public void setEmojiconSize(int pixels) {
        mEmojiconSize = pixels;
        super.setText(getText());
    }

    public void setUseSystemDefault(boolean useSystemDefault) {
        mUseSystemDefault = useSystemDefault;
    }
}
