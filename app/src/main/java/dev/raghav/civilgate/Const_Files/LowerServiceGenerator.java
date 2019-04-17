package dev.raghav.civilgate.Const_Files;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LowerServiceGenerator extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    Context context;
    public LowerServiceGenerator(FragmentManager fm , Context c) {
        super(fm);
        this.context = c;
    }
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }
    public void addFragment(Fragment fragment, String title) {
        Toast.makeText(context, ""+title, Toast.LENGTH_SHORT).show();
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
     //   Toast.makeText(context, "positiobn is"+position, Toast.LENGTH_SHORT).show();
        return mFragmentTitleList.get(position);
    }
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
      //  Toast.makeText(context, "object is"+object.toString(), Toast.LENGTH_SHORT).show();
        return super.getItemPosition(object);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
//    private Context mContext;
//
//    public ServiceGenerator(Context context) {
//        mContext = context;
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup collection, int position) {
//        Tests_Name levelsObject = Tests_Name.values()[position];
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        ViewGroup layout = (ViewGroup) inflater.inflate(levelsObject.getLayoutResId(), collection, false);
//        collection.addView(layout);
//        return layout;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup collection, int position, Object view) {
//        collection.removeView((View) view);
//    }
//
//    @Override
//    public int getCount() {
//        return Tests_Name.values().length;
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        Tests_Name customPagerEnum = Tests_Name.values()[position];
//        return mContext.getString(customPagerEnum.getTitleResId());
//    }

}