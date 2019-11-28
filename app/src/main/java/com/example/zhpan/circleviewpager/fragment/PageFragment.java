package com.example.zhpan.circleviewpager.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.zhpan.circleviewpager.R;
import com.example.zhpan.circleviewpager.viewholder.ImageResourceViewHolder;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.annotation.APageStyle;
import com.zhpan.bannerview.constants.PageStyle;
import com.zhpan.bannerview.utils.BannerUtils;
import com.zhpan.idea.utils.ToastUtils;

/**
 * Created by zhpan on 2018/7/24.
 */
public class PageFragment extends BaseFragment {

    private BannerViewPager<Integer, ImageResourceViewHolder> mViewPager;
    private RadioGroup mRadioGroupPageStyle;
    private RadioButton radioButton;

    @Override
    protected int getLayout() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected void initView(Bundle savedInstanceState, View view) {
        mViewPager = view.findViewById(R.id.banner_view);
        mRadioGroupPageStyle = view.findViewById(R.id.rg_page_style);
        radioButton = view.findViewById(R.id.rb_multi_page);
        mViewPager
                .setHolderCreator(() -> new ImageResourceViewHolder(BannerUtils.dp2px(5)))
                .setIndicatorColor(getColor(R.color.red_normal_color), getColor(R.color.red_checked_color))
                .setOnPageClickListener(position -> ToastUtils.show("position:" + position))
                .setInterval(5000);
        initRadioGroup();
    }


    public static PageFragment getInstance() {
        return new PageFragment();
    }


    private void initRadioGroup() {
        mRadioGroupPageStyle.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_multi_page:
                    setupBanner(PageStyle.MULTI_PAGE);
                    break;
                case R.id.rb_multi_page_scale:
                    setupBanner(PageStyle.MULTI_PAGE_SCALE);
                    break;
                case R.id.rb_multi_page_overlap:
                    setupBanner(PageStyle.MULTI_PAGE_OVERLAP);
                    break;
                case R.id.rb_qq_music_style:
                    setNetEaseMusicStyle();
                    break;
            }
        });
        radioButton.performClick();
    }

    private void setupBanner(@APageStyle int pageStyle) {
        mViewPager
                .setPageMargin(BannerUtils.dp2px(10))
                .setRevealWidth(BannerUtils.dp2px(10))
                .setPageStyle(pageStyle)
                .create(getMDrawableList());
    }

    //  仿QQ音乐的Banner
    private void setNetEaseMusicStyle() {
        mViewPager
                .setPageMargin(BannerUtils.dp2px(15))
                .setRevealWidth(BannerUtils.dp2px(0))
                .setPageStyle(PageStyle.MULTI_PAGE)
                .setHolderCreator(() -> new ImageResourceViewHolder(BannerUtils.dp2px(5)))
                .setIndicatorColor(getColor(R.color.red_normal_color), getColor(R.color.red_checked_color))
                .setOnPageClickListener(position -> ToastUtils.show("position:" + position))
                .setInterval(5000).create(getMDrawableList());
    }
}
