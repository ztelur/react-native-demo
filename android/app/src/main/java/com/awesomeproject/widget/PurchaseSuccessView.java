package com.awesomeproject.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by chen.li@shanbay.com on 10/19/15. 支付动画 view
 */
public class PurchaseSuccessView extends View {
	private static final int SWEEP_ANIMATOR_DURATION = 700;
	private static final int DEFAULT_ANIMATION_TIMES = 1;

	private boolean mFirstSweepAnimation = true;
	private ValueAnimator mSuccessAnimation;
	private ValueAnimator mProcessAnimation;
	private ValueAnimator mEndAnimator;
	private ValueAnimator mSweepAppearingAnimator;
	private ValueAnimator mSweepDisappearingAnimator;
	private RectF mCircleBounds;
	private float mSuccessValue;
	private float mCurrentSweepAngle;

	private float mCenterX;
	private float mCenterY;
	private float mRadius;

	private int mMinSweepAngle = 20;
	private int mMaxSweepAngle = 300;

	private Paint mDrawingPaint;
	private int STATE_START = 1;
	private int STATE_SUCCESS = 2;
	private int STATE_END = 3;
	private int mCurrentState = 1;
	private float mCurrentEndRatio = 1f;
	private float mCurrentRotationAngleOffset = 0;
	private float mCurrentRotationAngle = 0;
	private boolean mModeAppearing;
	private int mAnimationTimes = 0;
	private boolean mCancleAnimation = false;

	private OnSuccessAnimationListener mOnSuccessAnimationListener;

	public PurchaseSuccessView(Context context) {
		super(context);
	}

	public PurchaseSuccessView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(20);
	}

	public PurchaseSuccessView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(20);
	}

	public void init(int line) {

		int color = Color.GREEN;
		int lineWidth = line;
		mDrawingPaint = new Paint();
		mDrawingPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
		mDrawingPaint.setStyle(Paint.Style.STROKE);
		mDrawingPaint.setColor(color);
		mDrawingPaint.setStrokeWidth(lineWidth);

		initProcessAnimation();
		initEndAnimation();
		initSuccessAnimation();

	}

	private void initProcessAnimation() {
		mProcessAnimation = ValueAnimator.ofFloat(0, 360);
		mProcessAnimation.setStartDelay(500);
		mProcessAnimation.setRepeatCount(ValueAnimator.INFINITE);
		mProcessAnimation.setRepeatMode(ValueAnimator.RESTART);
		mProcessAnimation.setDuration(1200);
		mProcessAnimation.setInterpolator(new LinearInterpolator());
		mProcessAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				float angle = getAnimatedFraction(valueAnimator) * 360f;
				setCurrentRotationAngle(angle);
			}
		});
		mProcessAnimation.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animator) {

			}

			@Override
			public void onAnimationEnd(Animator animator) {
			}

			@Override
			public void onAnimationCancel(Animator animator) {

			}

			@Override
			public void onAnimationRepeat(Animator animator) {
				mAnimationTimes++;
				if (mAnimationTimes >= DEFAULT_ANIMATION_TIMES && mCancleAnimation) {
					mProcessAnimation.cancel();
					mSweepAppearingAnimator.cancel();
					mSweepDisappearingAnimator.cancel();
					mEndAnimator.start();

				}
			}
		});

		mSweepAppearingAnimator = ValueAnimator.ofFloat(mMinSweepAngle, mMaxSweepAngle);
		mSweepAppearingAnimator.setInterpolator(new DecelerateInterpolator());
		mSweepAppearingAnimator.setDuration((long) (SWEEP_ANIMATOR_DURATION));
		mSweepAppearingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float animatedFraction = getAnimatedFraction(animation);
				float angle;
				if (mFirstSweepAnimation) {
					angle = animatedFraction * mMaxSweepAngle;
				} else {
					angle = mMinSweepAngle + animatedFraction * (mMaxSweepAngle - mMinSweepAngle);
				}
				setCurrentSweepAngle(angle);
			}
		});

		mSweepAppearingAnimator.addListener(new Animator.AnimatorListener() {
			boolean cancelled = false;

			@Override
			public void onAnimationStart(Animator animation) {
				cancelled = false;
				mModeAppearing = true;
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				if (!cancelled) {
					mFirstSweepAnimation = false;
					setDisappearing();
					mSweepDisappearingAnimator.start();
				}
			}

			@Override
			public void onAnimationCancel(Animator animation) {
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
			}
		});

		mSweepDisappearingAnimator = ValueAnimator.ofFloat(mMaxSweepAngle, mMinSweepAngle);
		mSweepDisappearingAnimator.setInterpolator(new DecelerateInterpolator());
		mSweepDisappearingAnimator.setDuration((long) SWEEP_ANIMATOR_DURATION);
		mSweepDisappearingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float animatedFraction = getAnimatedFraction(animation);
				setCurrentSweepAngle(mMaxSweepAngle - animatedFraction * (mMaxSweepAngle - mMinSweepAngle));
			}
		});
		mSweepDisappearingAnimator.addListener(new Animator.AnimatorListener() {

			boolean cancelled;

			@Override
			public void onAnimationStart(Animator animation) {
				cancelled = false;
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				if (!cancelled) {
					setAppearing();
					mSweepAppearingAnimator.start();
				}
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				cancelled = true;
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
			}
		});

	}

	private void initEndAnimation() {
		mEndAnimator = ValueAnimator.ofFloat(0, 1f);
		mEndAnimator.setInterpolator(new LinearInterpolator());
		mEndAnimator.setDuration(400);
		mEndAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				setEndRatio(getAnimatedFraction(animation));

			}
		});
		mEndAnimator.addListener(new Animator.AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {
				mCurrentState = STATE_END;
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				setEndRatio(1f);
				mCurrentState = STATE_SUCCESS;
				mSuccessAnimation.start();
			}

			@Override
			public void onAnimationCancel(Animator animation) {
			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}
		});
	}

	private void initSuccessAnimation() {
		mRadius = 30;
		mSuccessAnimation = ValueAnimator.ofFloat(0, mRadius * 2);
		mSuccessAnimation.setDuration(600);
		mSuccessAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
		mSuccessAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator valueAnimator) {
				mSuccessValue = (float) valueAnimator.getAnimatedValue();
				invalidate();
			}
		});
		mSuccessAnimation.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animator) {
			}

			@Override
			public void onAnimationEnd(Animator animator) {
				invalidate();
				if (mOnSuccessAnimationListener != null) {
					mOnSuccessAnimationListener.onSuccessAnimation();
				}
			}

			@Override
			public void onAnimationCancel(Animator animator) {

			}

			@Override
			public void onAnimationRepeat(Animator animator) {

			}
		});
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (mCurrentState == STATE_START) {
			drawStart(canvas);
		}

		if (mCurrentState == STATE_END) {
			drawEnd(canvas);
		}

		if (mCurrentState == STATE_SUCCESS) {
			drawSuccess(canvas);
		}
	}

	private void drawStart(Canvas canvas) {
		float startAngle = mCurrentRotationAngle - mCurrentRotationAngleOffset;
		float sweepAngle = mCurrentSweepAngle;
		if (!mModeAppearing) {
			startAngle = startAngle + (360 - sweepAngle);
		}
		startAngle %= 360;
		if (mCurrentEndRatio < 1f) {
			float newSweepAngle = sweepAngle * mCurrentEndRatio;
			startAngle = (startAngle + (sweepAngle - newSweepAngle)) % 360;
			sweepAngle = newSweepAngle;
		}
		canvas.drawArc(mCircleBounds, startAngle, sweepAngle, false, mDrawingPaint);
	}

	private void drawEnd(Canvas canvas) {
		float startAngle = mCurrentRotationAngle - mCurrentRotationAngleOffset;
		float sweepAngle = mCurrentSweepAngle;
		if (!mModeAppearing) {
			startAngle = startAngle + (360 - sweepAngle);
		}
		startAngle %= 360;
		float remainAngle = 360 - mCurrentSweepAngle;
		if (mCurrentEndRatio < 1f) {
			float newSweepAngle = remainAngle * mCurrentEndRatio;
			sweepAngle = sweepAngle + newSweepAngle;
		}
		canvas.drawArc(mCircleBounds, startAngle, sweepAngle, false, mDrawingPaint);
	}

	private void drawSuccess(Canvas canvas) {

		canvas.drawArc(mCircleBounds, 0, 360, false, mDrawingPaint);
		float startX1 = (int) (mCenterX - mRadius * 4 / 7);
		float startY1 = (int) (mCenterY + mRadius * 1 / 8);

		float endX1 = (int) (mCenterX - mRadius * 1 / 5);
		float endY1 = (int) (mCenterY + mRadius * 1 / 2);

		float startX2 = (int) (mCenterX - mRadius * 1 / 5 - 2);
		float startY2 = (int) (mCenterY + mRadius * 1 / 2);

		float endX2 = (int) (mCenterX + mRadius * 4 / 7);
		float endY2 = (int) (mCenterY - mRadius * 1 / 3);

		float slope1 = (endY1 - startY1) / (endX1 - startX1);

		if ((startX1 + mSuccessValue * slope1) <= endX1) {
			canvas.drawLine(startX1, startY1, startX1 + mSuccessValue * slope1, startY1 + mSuccessValue * slope1,
					mDrawingPaint);
		} else {
			canvas.drawLine(startX1, startY1, endX1, endY1, mDrawingPaint);

			float percent2 = (endX1 - startX1) / (endX2 - startX1);

			float slope2 = (endX2 - startX2) / (endY2 - startY2);

			float finalX = startX2 - (mSuccessValue - mRadius * percent2) * slope2 > endX2 ? endX2 : startX2
					- (mSuccessValue - mRadius * percent2) * slope2;
			float finalY = startY2 + (mSuccessValue - mRadius * percent2) * slope2 < endY2 ? endY2 : startY2
					+ (mSuccessValue - mRadius * percent2) * slope2;
			canvas.drawLine(startX2, startY2, finalX, finalY, mDrawingPaint);
		}
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		mCenterX = w / 2f;
		mCenterY = h / 2f;

		mCircleBounds = new RectF();
		mCircleBounds.top = h / 2f - mRadius;
		mCircleBounds.left = w / 2f - mRadius;
		mCircleBounds.bottom = h / 2f + mRadius;
		mCircleBounds.right = w / 2f + mRadius;
	}

	public void start() {
		mProcessAnimation.start();
		mSweepAppearingAnimator.start();
		mCurrentState = STATE_START;
	}

	public void stop() {
		mCancleAnimation = true;
	}

	float getAnimatedFraction(ValueAnimator animator) {
		float fraction = animator.getDuration() > 0 ? ((float) animator.getCurrentPlayTime()) / animator.getDuration()
				: 0f;

		fraction = Math.min(fraction, 1f);
		fraction = animator.getInterpolator().getInterpolation(fraction);
		return fraction;
	}

	public void setCurrentSweepAngle(float currentSweepAngle) {
		mCurrentSweepAngle = currentSweepAngle;
		invalidate();
	}

	private void setEndRatio(float ratio) {
		mCurrentEndRatio = ratio;
		invalidate();
	}

	public void setCurrentRotationAngle(float currentRotationAngle) {
		mCurrentRotationAngle = currentRotationAngle;
		invalidate();
	}

	private void setAppearing() {
		mModeAppearing = true;
		mCurrentRotationAngleOffset += mMinSweepAngle;
	}

	private void setDisappearing() {
		mModeAppearing = false;
		mCurrentRotationAngleOffset = mCurrentRotationAngleOffset + (360 - mMaxSweepAngle);
	}

	public void setOnSuccessAnimationListener(OnSuccessAnimationListener listener) {
		mOnSuccessAnimationListener = listener;
	}

	public interface OnSuccessAnimationListener {
		void onSuccessAnimation();
	}

}
