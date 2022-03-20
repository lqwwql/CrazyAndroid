package com.main.crazyandroid;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/9/2.
 */

public class ExpandedListViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expanded_listview_layout);
        ExpandableListAdapter aba = new ExpandableListAdapter() {
            int logos[] = {R.drawable.ptt_1,R.drawable.ptt_2,R.drawable.ptt_3};
            private String armTypes[] = {"复仇者","联盟","部落"};
            private String arms[][] = {
                    {"蜘蛛侠","黑寡妇","罗伯特"},
                    {"队长","幻视","蚁人"},
                    {"打击","阿哥啊","各安格"}};

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {
            }
            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {
            }
            @Override
            public int getGroupCount() {
                return armTypes.length;
            }
            @Override
            public int getChildrenCount(int groupPosition) {
                return arms[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return armTypes[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return arms[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                LinearLayout ll = new LinearLayout(ExpandedListViewActivity.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                ImageView logo = new ImageView(ExpandedListViewActivity.this);
                logo.setImageResource(logos[groupPosition]);
                ll.addView(logo);
                TextView armType = new TextView(ExpandedListViewActivity.this);
                armType.setText(armTypes[groupPosition]);
                ll.addView(armType);
                return ll;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                TextView child = getTextView();
                child.setText(getChild(groupPosition,childPosition).toString());
                return child;
            }

            private TextView getTextView(){
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,64);
                TextView child = new TextView(ExpandedListViewActivity.this);
                child.setLayoutParams(lp);
                child.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
                child.setPadding(36,0,0,0);
                child.setTextSize(20);
                return child;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }

            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public void onGroupExpanded(int groupPosition) {

            }

            @Override
            public void onGroupCollapsed(int groupPosition) {

            }

            @Override
            public long getCombinedChildId(long groupId, long childId) {
                return 0;
            }

            @Override
            public long getCombinedGroupId(long groupId) {
                return 0;
            }
        };

        ExpandableListView expend_list = (ExpandableListView) findViewById(R.id.expand_list);
        expend_list.setAdapter(aba);
    }
}
