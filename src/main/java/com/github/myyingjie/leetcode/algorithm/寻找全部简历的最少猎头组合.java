package com.github.myyingjie.leetcode.algorithm;

import java.util.*;

/**
 * created by Yingjie Zheng at 2020-03-14 16:58
 *
 *
 * 猎头简历问题
 * 已知猎头跟简历的对应关系，猎头手中的简历可能重叠
 * 找到能获取全部简历的最少的猎头
 * eg:
 * A 1 2 3 4
 * B 2 3 5
 * C 4 5 6
 * D 5 6 7 8
 * E 1 4 6
 * result: [A, D]
 */
public class 寻找全部简历的最少猎头组合 {

    public static void main(String[] args) {

        HashMap<String, List<Integer>> map = new HashMap<>();
        map.put("A",Arrays.asList(1,2,3,4));
        map.put("B",Arrays.asList(2,3,5));
        map.put("C",Arrays.asList(4,5,6));
        map.put("D",Arrays.asList(5,6,7,8));
        map.put("E",Arrays.asList(1,4,6));

        System.out.println(query(map));

    }

    public static List<String> query(Map<String, List<Integer>> lt2jl) {
        // 结果集
        List<String> result = new ArrayList<>();
        if(lt2jl ==null || lt2jl.size() == 0){
            return result;
        }
        Set<Map.Entry<String, List<Integer>>> entries = lt2jl.entrySet();
        if(lt2jl.size() == 1){
            entries.forEach(stringListEntry -> result.add(stringListEntry.getKey()));
            return result;
        }

        // 所有简历
        Set<Integer> set = new HashSet<>();

        for (Map.Entry<String, List<Integer>> entry : entries) {
            set.addAll(entry.getValue());
        }

        List<Map.Entry<String, List<Integer>>> entriesList = new ArrayList<>(entries);

        entriesList.sort(Comparator.comparing(o -> o.getValue().size()));

        Map.Entry<String, List<Integer>> maxEntry = entriesList.get(entriesList.size() - 1);
        result.add(maxEntry.getKey());

        List<Integer> value = maxEntry.getValue();
        for (Integer integer : value) {
            set.remove(integer);
        }

        Set<Integer> maxSet = new HashSet<>(value);
        entries.remove(maxEntry);

        while (true) {

            for (Map.Entry<String, List<Integer>> listEntry : entries) {
                Iterator<Integer> iterator = listEntry.getValue().iterator();
                if (iterator.hasNext()) {
                    Integer next = iterator.next();
                    if (maxSet.contains(next)) {
                        iterator.remove();
                    }
                }
            }

            entriesList = new ArrayList<>(entries);
            entriesList.sort(Comparator.comparing(o -> o.getValue().size()));
            maxEntry = entriesList.get(entriesList.size() - 1);
            value = maxEntry.getValue();
            maxSet = new HashSet<>(value);
            result.add(maxEntry.getKey());
            for (Integer integer : value) {
                set.remove(integer);
            }
            entries.remove(maxEntry);
            if (set.size() == 0) {
                break;
            }
        }

        return result;

    }


}
