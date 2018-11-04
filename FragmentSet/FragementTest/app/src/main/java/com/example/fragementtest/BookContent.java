package com.example.fragementtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookContent {
    // 定义一个内部类，作为系统的业务对象
    public static class Book {
        public Integer id;
        public String title;
        public String desc;
        public Book(Integer id,String title,String desc){
            this.id = id;
            this.title = title;
            this.desc = desc;
        }
        @Override
        public String toString(){
            return title;
        }
    }
    // 使用List集合记录系统所包含的Book对象
    public static List<Book> ITEMS = new ArrayList<Book>();
    // 使用Map集合记录系统所包含的Book对象
    public static Map<Integer,Book> ITEM_MAP = new HashMap<>();
    static {
        // 使用静态初始化代码，将Book对象添加到List集合、Map集合中
        addItem(new Book(1,"月亮与六便士",
                "讲述了一个画家毅然舍弃幸福生活，追寻实现绘画艺术梦的故事"));
        addItem(new Book(2,"无声告白",
                "讲述了生活在美国的中美混合家庭，意外失去长女，在追忆与寻找长女的过程中，一家人不同的心路历程"));
        addItem(new Book(3,"岛上书店",
                "讲述了一个偏僻小岛上一家唯一书店的老板的人生故事，以及相伴随的书店的命运故事"));
        addItem(new Book(4,"菊与刀",
                "讲述了二战期间，美国作者受命调查日本，并逐渐揭开日本文化中的矛盾的极端文化"));
    }
    private static void addItem(Book book){
        ITEMS.add(book);
        ITEM_MAP.put(book.id,book);
    }
}
