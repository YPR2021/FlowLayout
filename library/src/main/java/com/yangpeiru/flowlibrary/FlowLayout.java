package com.yangpeiru.flowlibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @项目名: FlowLayout
 * @包名: com.yangpeiru.flowlibrary
 * @文件名: FlowLayout
 * @创建者: 杨沛儒
 * @创建时间: 2015/11/27 18:12
 * @描述: TODO:
 */


public class FlowLayout
        extends ViewGroup
{

    private List<Line> mLines         = new ArrayList<>();//布局管理里的行
    private int        mVerticalSpace = 15;//垂直的间隙,用于计算top的总高度

    private Line mCurrentLine;//记录当前行
    private int mHorizontalSpace = 15;//水平的间隙,用于创建新行的时候

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSpace(int horizontalSpace, int verticalSpace) {
        this.mHorizontalSpace = horizontalSpace;
        this.mVerticalSpace = verticalSpace;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d("Log", "开始测绘...");
        //TODO 每addView一次就会触发重绘,就会触发ViewGroup的onMeasure方法,至少两次
        //TODO google把onMeasure定义了:只有当测绘完成后才会调用测绘,在测绘期间请求的测绘都忽略.
        //TODO addView多次就会运行多次onMeasure方法,会一直添加行,所以每次调用都要清空行
        mLines.clear();
        mCurrentLine = null;

        int widthSelf = MeasureSpec.getSize(widthMeasureSpec);//拿到条目期望宽
        //child的最大宽度计算:用父类的期望宽-paddingleft-paddingright
        int childMaxWidth = widthSelf - getPaddingRight() - getPaddingLeft();

        /**
         * 测量孩子
         * 1.拿到孩子的总个数
         * 2.变量拿到每个孩子,每个孩子给一个希望值
         * 3.添加进集合
         */
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);

            //如果孩子不显示,就continue
            if (child.getVisibility() == View.GONE) {
                continue;
            }
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            //测一个孩子加一个数据
            //判断如果一行都没有,就new一行
            if (mCurrentLine == null) {

                mCurrentLine = new Line(childMaxWidth, mHorizontalSpace);
                //将line添加进布局中
                mLines.add(mCurrentLine);
                //将view添加到line中
                mCurrentLine.addChild(child);
            } else {
                if (mCurrentLine.canAdd(child)) {
                    mCurrentLine.addChild(child);
                } else {
                    //因为子控件宽度需要用到,所有把child的最大宽度放到方法外
                    //换行
                    mCurrentLine = new Line(childMaxWidth, mHorizontalSpace);
                    //将line添加进布局中
                    mLines.add(mCurrentLine);
                    //将view添加到line中
                    mCurrentLine.addChild(child);
                }
            }
        }

        /**
         * 测量父类自己的宽高
         * 1.宽就是期望宽
         * 2.高=条目高+条目间的间隔,要判断是不是在第一条,第一条是没有间隔的
         *
         */
        //因为子控件测量需要用到widthSelf,所有放到最上面
        int heightSelf = getPaddingTop() + getPaddingBottom();
        for (int i = 0; i < mLines.size(); i++) {
            Line line = mLines.get(i);//拿到每行
            heightSelf += line.mLineMaxHeight;

            //当size为2的时候才走这个方法
            if (i != mLines.size() - 1) {
                heightSelf += mVerticalSpace;
            }
        }
        setMeasuredDimension(widthSelf, heightSelf);
//                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        //测量line.用行来布局,每一行自己管理自己的布局,所以遍历行,创建布局方法(layou()),每行都调用一下
        int top = getPaddingTop();
        for (int i = 0; i < mLines.size(); i++) {
            Line line = mLines.get(i);
            line.layout(getPaddingLeft(), top);

            top += mVerticalSpace + line.mLineMaxHeight;//为下一行记录top的值
        }
    }


    private class Line {

        //创建一行中子控件的集合
        private List<View> mViews = new ArrayList<>();

        private int mLineUsedWidth;//一行中已经使用的宽度
        private int mLineMaxWidth;//一行中最大的宽度
        private int mLineMaxHeight;//一行中最大的高度
        private int mSpace;//控件键的间隙距离

        public Line(int maxWidth, int space) {
            this.mLineMaxWidth = maxWidth;
            this.mSpace = space;
        }

        /**
         * 用来判断是否可以添加子控件的方法
         * @param view
         * @return
         */
        private boolean canAdd(View view) {
            //如果一个子控件都没有,一定要加进去,不然哪个条目都加不进去
            if (mViews.size() == 0) {
                return true;
            }

            int childWidth = view.getMeasuredWidth();
            if (mLineUsedWidth + mSpace + childWidth <= mLineMaxWidth) {
                return true;
            }
            return false;
        }

        /**
         * 添加子控件的方法,在这之前先判断是否可以添加,用canAdd方法
         * @param view
         */
        private void addChild(View view) {
            int childWidth  = view.getMeasuredWidth();//子控件的宽
            int childHeight = view.getMeasuredHeight();
            //判断这行中有没有子控件
            if (mViews.size() == 0) {
                mLineUsedWidth = childWidth;//没有就赋值给使用的宽
                mLineMaxHeight = childHeight;
            } else {
                mLineUsedWidth += childWidth + mSpace;

                //行的高度决定在子控件中最高的高度
                mLineMaxHeight = mLineMaxHeight > childHeight
                                 ? mLineMaxHeight
                                 : childHeight;
            }
            mViews.add(view);
        }


        /**
         * 给行布局的方法
         * @param left
         * @param top
         */
        public void layout(int left, int top) {

            //判断是否有多余的空间
            int surplusWidth = mLineMaxWidth - mLineUsedWidth;
            //计算每个子控件获得的平均值
            int avgWidth = (int) (surplusWidth * 1f / mViews.size() + 0.5f);

            for (int i = 0; i < mViews.size(); i++) {
                View child = mViews.get(i);//获取每一行的子控件
                //有子控件就有期望值
                int childHeight = child.getMeasuredHeight();
                int childWidth  = child.getMeasuredWidth();

                //先不去布局,先期望子控件的宽高,有剩余的多给
                if (avgWidth > 0) {
                    //获得期望值,精确的
                    int childWidthSpec = MeasureSpec.makeMeasureSpec(childWidth+avgWidth,
                                                                     MeasureSpec.EXACTLY);
                    int childHeightSpec = MeasureSpec.makeMeasureSpec(childHeight,
                                                                      MeasureSpec.EXACTLY);
                    //期望一下
                    child.measure(childWidthSpec, childHeightSpec);

                    //获得新的宽高
                    childWidth = child.getMeasuredWidth();
                    childHeight = child.getMeasuredHeight();
                }

                int l = left;
//                int t = top;
                int t = (int) (top + (mLineMaxHeight - childHeight) / 2f+0.5f);//这个是让孩子都居中,用于每个孩子大小不一致时
                int r = l + childWidth;
                int b = t + childHeight;
                child.layout(l, t, r, b);

                left += childWidth + mSpace;//为下一个child记录的left值
            }
        }
    }


}
