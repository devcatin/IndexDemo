package com.erik.contactdemo.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.erik.contactdemo.views.IndexView;
import com.erik.contactdemo.adapter.MyAdapter;
import com.erik.contactdemo.bean.Person;
import com.erik.contactdemo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private IndexView indexView;
    private ListView listView;
    private List<Person> list;
    private MyAdapter adapter;
    private Handler   handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv);
        indexView = findViewById(R.id.words);
        listView = findViewById(R.id.list);

        initData();
        adapter = new MyAdapter(this, list);
        listView.setAdapter(adapter);

        //手指按下字母改变监听回调
        indexView.setOnWordsChangeListener(new IndexView.onWordsChangeListener() {
            @Override
            public void wordsChange(String words) {
                updateWord(words);
                updateListView(words);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //当滑动列表的时候，更新右侧字母列表的选中状态
                indexView.setTouchIndex(list.get(firstVisibleItem).getHeaderWord());
            }
        });
    }

    /**
     * @param words 首字母
     */
    private void updateListView(String words) {
        for (int i = 0; i < list.size(); i++) {
            String headerWord = list.get(i).getHeaderWord();
            //将手指按下的字母与列表中相同字母开头的项找出来
            if (words.equals(headerWord)) {
                //将列表选中哪一个
                listView.setSelection(i);
                //找到开头的一个即可
                return;
            }
        }
    }

    /**
     * 更新中央的字母提示
     *
     * @param words 首字母
     */
    private void updateWord(String words) {
        textView.setText(words);
        textView.setVisibility(View.VISIBLE);
        //清空之前的所有消息
        handler.removeCallbacksAndMessages(null);
        //500ms后让tv隐藏
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setVisibility(View.GONE);
            }
        }, 500);
    }

    /**
     * 初始化联系人列表信息
     */
    private void initData() {
        list = new ArrayList<>();
        list.add(new Person("Dave"));
        list.add(new Person("阿钟"));
        list.add(new Person("发财"));
        list.add(new Person("胡继群"));
        list.add(new Person("隔壁老王"));
        list.add(new Person("姜宇航"));
        list.add(new Person("单丹"));
        list.add(new Person("重庆"));
        list.add(new Person("分析"));
        list.add(new Person("查理一世"));
        list.add(new Person("仇人"));
        list.add(new Person("重耳"));
        list.add(new Person("八戒"));
        list.add(new Person("麒麟"));
        list.add(new Person("沙盒"));
        list.add(new Person("I"));
        list.add(new Person("J"));
        list.add(new Person("G"));
        list.add(new Person("H"));

        //对集合排序
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person lhs, Person rhs) {
                //根据拼音进行排序
                return lhs.getPinyin().compareTo(rhs.getPinyin());
            }
        });
    }

}
