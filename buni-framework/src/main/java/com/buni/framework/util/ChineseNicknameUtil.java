package com.buni.framework.util;

import java.util.Random;

public class ChineseNicknameUtil {

    private static final Random random = new Random();

    // 形容词词库
    private static final String[] ADJECTIVES = {
            "快乐的", "忧郁的", "疯狂的", "安静的", "神秘的",
            "阳光的", "温柔的", "暴躁的", "可爱的", "高冷的",
            "机智的", "迷糊的", "贪吃的", "爱睡的", "勤奋的",
            "孤独的", "热情的", "冷漠的", "幽默的", "严肃的"
    };

    // 名词词库
    private static final String[] NOUNS = {
            "小猫", "小狗", "小熊", "小兔", "小鹿",
            "老虎", "狮子", "大象", "熊猫", "猴子",
            "星星", "月亮", "太阳", "云朵", "雪花",
            "大树", "小草", "花朵", "叶子", "果实",
            "书生", "剑客", "游侠", "诗人", "隐士",
            "奶茶", "咖啡", "蛋糕", "糖果", "饼干",
            "清风", "明月", "流云", "孤烟", "寒江",
            "落花", "飞雪", "长亭", "古道", "天涯",
            "红尘", "紫陌", "青衫", "白衣", "墨客",
            "琴心", "剑魄", "诗魂", "酒仙", "茶禅"
    };


    /**
     * 生成昵称
     *
     * @return
     */
    public static String generateCuteNickname() {
        return ADJECTIVES[random.nextInt(ADJECTIVES.length)] + NOUNS[random.nextInt(NOUNS.length)];
    }


}
