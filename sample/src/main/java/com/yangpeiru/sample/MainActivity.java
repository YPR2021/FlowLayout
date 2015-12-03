package com.yangpeiru.sample;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yangpeiru.flowlibrary.FlowLayout;

import java.util.Random;

public class MainActivity
        extends AppCompatActivity
{
    private String[] mDatas = new String[]{"QQ",
                                           "视频",
                                           "放开那三国",
                                           "电子书",
                                           "酒店",
                                           "单机",
                                           "小说",
                                           "斗地主",
                                           "优酷",
                                           "网游",
                                           "WIFI万能钥匙",
                                           "播放器",
                                           "捕鱼达人2",
                                           "机票",
                                           "游戏",
                                           "熊出没之熊大快跑",
                                           "美图秀秀",
                                           "浏览器",
                                           "单机游戏",
                                           "我的世界",
                                           "电影电视",
                                           "QQ空间",
                                           "旅游",
                                           "免费游戏",
                                           "2048",
                                           "刀塔传奇",
                                           "壁纸",
                                           "节奏大师",
                                           "锁屏",
                                           "装机必备",
                                           "天天动听",
                                           "备份",
                                           "网盘",
                                           "海淘网",
                                           "大众点评",
                                           "爱奇艺视频",
                                           "腾讯手机管家",
                                           "百度地图",
                                           "猎豹清理大师",
                                           "谷歌地图",
                                           "hao123上网导航",
                                           "京东",
                                           "youni有你",
                                           "万年历-农历黄历",
                                           "支付宝钱包",
                                           "QQ",
                                           "视频",
                                           "放开那三国",
                                           "电子书",
                                           "酒店",
                                           "单机",
                                           "小说",
                                           "斗地主",
                                           "优酷",
                                           "网游",
                                           "WIFI万能钥匙",
                                           "播放器",
                                           "捕鱼达人2",
                                           "机票",
                                           "游戏",
                                           "熊出没之熊大快跑",
                                           "美图秀秀",
                                           "浏览器",
                                           "单机游戏",
                                           "我的世界",
                                           "电影电视",
                                           "QQ空间",
                                           "旅游",
                                           "免费游戏",
                                           "2048",
                                           "刀塔传奇",
                                           "壁纸",
                                           "节奏大师",
                                           "锁屏",
                                           "装机必备",
                                           "天天动听",
                                           "备份",
                                           "网盘",
                                           "海淘网",
                                           "大众点评",
                                           "爱奇艺视频",
                                           "腾讯手机管家",
                                           "百度地图",
                                           "猎豹清理大师",
                                           "谷歌地图",
                                           "hao123上网导航",
                                           "京东",
                                           "youni有你",
                                           "万年历-农历黄历",
                                           "支付宝钱包",
                                           "QQ",
                                           "视频",
                                           "放开那三国",
                                           "电子书",
                                           "酒店",
                                           "单机",
                                           "小说",
                                           "斗地主",
                                           "优酷",
                                           "网游",
                                           "WIFI万能钥匙",
                                           "播放器",
                                           "捕鱼达人2",
                                           "机票",
                                           "游戏",
                                           "熊出没之熊大快跑",
                                           "美图秀秀",
                                           "浏览器",
                                           "单机游戏",
                                           "我的世界",
                                           "电影电视",
                                           "QQ空间",
                                           "旅游",
                                           "免费游戏",
                                           "2048",
                                           "刀塔传奇",
                                           "壁纸",
                                           "节奏大师",
                                           "锁屏",
                                           "装机必备",
                                           "天天动听",
                                           "备份",
                                           "网盘",
                                           "海淘网",
                                           "大众点评",
                                           "爱奇艺视频",
                                           "腾讯手机管家",
                                           "百度地图",
                                           "猎豹清理大师",
                                           "谷歌地图",
                                           "hao123上网导航",
                                           "京东",
                                           "youni有你",
                                           "万年历-农历黄历",
                                           "支付宝钱包",
                                           "QQ",
                                           "视频",
                                           "放开那三国",
                                           "电子书",
                                           "酒店",
                                           "单机",
                                           "小说",
                                           "斗地主",
                                           "优酷",
                                           "网游",
                                           "WIFI万能钥匙",
                                           "播放器",
                                           "捕鱼达人2",
                                           "机票",
                                           "游戏",
                                           "熊出没之熊大快跑",
                                           "美图秀秀",
                                           "浏览器",
                                           "单机游戏",
                                           "我的世界",
                                           "电影电视",
                                           "QQ空间",
                                           "旅游",
                                           "免费游戏",
                                           "2048",
                                           "刀塔传奇",
                                           "壁纸",
                                           "节奏大师",
                                           "锁屏",
                                           "装机必备",
                                           "天天动听",
                                           "备份",
                                           "网盘",
                                           "海淘网",
                                           "大众点评",
                                           "爱奇艺视频",
                                           "腾讯手机管家",
                                           "百度地图",
                                           "猎豹清理大师",
                                           "谷歌地图",
                                           "hao123上网导航",
                                           "京东",
                                           "youni有你",
                                           "万年历-农历黄历",
                                           "支付宝钱包"};

    private FlowLayout mFlowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFlowLayout = (FlowLayout) findViewById(R.id.flowlayout);
        mFlowLayout.setPadding(10, 10, 10, 10);
        mFlowLayout.setSpace(10, 10);

        //        //for循环设置每个条目
        //        for (int i = 0; i < mDatas.length; i++) {
        //            TextView view = new TextView(this);
        //            view.setText(mDatas[i]);
        //            view.setTextSize(16);
        //            view.setGravity(Gravity.CENTER);
        //            view.setTextColor(Color.WHITE);
        //            view.setBackgroundColor(Color.GRAY);
        //            view.setPadding(5, 5, 5, 5);

        //            //TODO 每addView一次就会触发重绘,就会触发ViewGroup的onMeasure方法,至少两次
        //            //TODO google把onMeasure定义了:只有当测绘完成后才会测绘,在测绘期间请求的测绘都忽略.
        //            mFlowLayout.addView(view);
        //        }

        Random random = new Random();
        for (int i = 0; i < mDatas.length; i++) {
            final String data = mDatas[i];
            //获得每个数据,设置
            TextView textView = new TextView(MainActivity.this);
            textView.setText(data);
            textView.setTextColor(Color.WHITE);
            textView.setTextSize(16);
            textView.setPadding(5, 5, 5, 5);
            textView.setGravity(Gravity.CENTER);

            /**
             * 设置圆角和随机色
             */
            int alpha = 255;//0代表纯透明,255代表不透明
            //三原色
            int red   = random.nextInt(200) + 30;
            int green = random.nextInt(200) + 30;
            int blue  = random.nextInt(200) + 30;

            //普通的背景
            int              argb     = Color.argb(alpha, red, green, blue);
            GradientDrawable normalBg = new GradientDrawable();
            normalBg.setShape(GradientDrawable.RECTANGLE);//设置形状
            normalBg.setCornerRadius(8);//设置四个边角的值
            normalBg.setColor(argb);

            //被按压时的背景
            GradientDrawable pressedBg = new GradientDrawable();
            pressedBg.setShape(GradientDrawable.RECTANGLE);//设置形状
            pressedBg.setCornerRadius(8);//设置四个边角的值
            pressedBg.setColor(Color.GRAY);//这个不能设置argb,因为颜色都是一样的,显示不了效果
            //            pressedBg.setColor(Color.GRAY);//设置成灰色

            //TODO 看源码知道怎么设置状态选择
            StateListDrawable selector = new StateListDrawable();
            selector.addState(new int[]{android.R.attr.state_pressed}, pressedBg);
            selector.addState(new int[]{}, normalBg);

            textView.setBackground(selector);//设置背景,传入画好的形状

            //父类添加子类
            mFlowLayout.addView(textView);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "" + data, Toast.LENGTH_SHORT)
                         .show();
                }
            });
        }
    }
}
