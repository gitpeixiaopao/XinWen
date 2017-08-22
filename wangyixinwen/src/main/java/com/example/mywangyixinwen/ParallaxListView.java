package com.example.mywangyixinwen;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by peiyan on 2017/8/16.
 * 继承式控件：
 * 1.继承ListView，覆写构造方法
 * 2.覆写overScrollBy方法，重点关注deltaY,isTouchEvent方法
 * 3.暴露一个方法，去得到外界ImageView，并测量ImageView控件的高度
 * 4.覆写onTouchEvent方法
 */
public class ParallaxListView extends ListView {
    private ImageView header;
    private int intrinsicHeight;
    private int orignalHeight;

    public ParallaxListView(Context context) {
        this(context,null);
    }
    public ParallaxListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public ParallaxListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void setHeader(ImageView header) {
        this.header = header;
        //获取图片的原始高度
        intrinsicHeight = header.getDrawable().getIntrinsicHeight();
        //获取imageView的原始高度
        orignalHeight = header.getHeight();

    }
    /**
     * 重点:滑动到ListView两端的时候被调用（上部和底部）
     * @param deltaX
     * @param deltaY 竖直方向滑动的瞬时变化量，顶部下拉为-，底部上拉为+；
     * @param scrollX
     * @param scrollY
     * @param scrollRangeX
     * @param scrollRangeY
     * @param maxOverScrollX
     * @param maxOverScrollY
     * @param isTouchEvent  是否是用户触摸拉动，true表示用户手指拉动，false是惯性；
     * @return
     *
     *
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
                                   int scrollY, int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        //通过Log来验证参数的作用
        //deltaY=-270;  scrollY=0滚动; scrollRangeY=0滚动范围; maxOverScrollY=0最大滚动; isTouchEvent=true是触摸事件;

        //顶部下拉，用户触摸的操作才执行视差效果
        if (deltaY<0 && isTouchEvent==true){
            //deltaY是一个负值，我们要改为绝对值 Math.abs(deltaY)，累计给我们的header高度
            int newHeight = header.getHeight() + Math.abs(deltaY);
      //避免图片无限放大，图片最大不能超过图片本身的高度
            //在上面的方法里拿到图片的原始高度
            if (newHeight<=intrinsicHeight){
                //把新的高度值赋值给控件，改变控件的高度
                header.getLayoutParams().height=newHeight;
                header.requestLayout();
            }


        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }
//触摸事件，让滑动的图片重新回到原来的样子
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_UP:
                int currentHeight = header.getHeight();
              //属性动画，改变高度的值,吧我们当前头布局的高度，改为原始的高度
                final ValueAnimator animator = ValueAnimator.ofInt(currentHeight, orignalHeight);
               //动画更新的监听
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                   @Override
                   public void onAnimationUpdate(ValueAnimator valueAnimator) {
                       //获取动画执行过程中的分度值
                       float fraction = animator.getAnimatedFraction();
                       //获取中间的值，并赋值给控件新高度，可以使控件平稳回弹的效果
                       Integer animatedValue = (Integer) animator.getAnimatedValue();
                    //让新的高度值生效
                       header.getLayoutParams().height=animatedValue;
                       header.requestLayout();
                   }
               });
                //动画的回弹效果。值越大,回弹效果越明显
                animator.setInterpolator(new OvershootInterpolator(2));
                //设置动画执行时间
                animator.setDuration(3000);
                //执行动画
                    animator.start();
        }
        return super.onTouchEvent(ev);
    }


}
