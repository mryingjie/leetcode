package com.github.myyingjie.leetcode.algorithm.sort;

import java.util.*;

/**
 * created by Yingjie Zheng at 2020-03-12 10:48
 * 堆排序  优先级队列  从10万个数中找前10大的数
 * <p>
 * 最小二叉堆的实现 https://mp.weixin.qq.com/s/cq2EhVtOTzTVpNpLDXfeJg
 * 堆排序 https://mp.weixin.qq.com/s/8Bid1naBLtEjPoP-R4HkBg
 * 优先级队列 https://mp.weixin.qq.com/s/4hXBw7sZ-NKs_asOQxS7gA
 */
public class 堆排序 {


    public static void main(String[] args) {

        int[] arr= {1,3,2,6,5,7,8,9,10,0,13};
        buildMinHeap(arr);
        System.out.println(Arrays.toString(arr));


        int[] arr2 = {7,1,3,10,5,2,8,9,6};
        upAdjust(arr2);
        System.out.println(Arrays.toString(arr2));

        headSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void headSort(int[] arr){
        //首先构建二叉堆
        buildMinHeap(arr);

        //依次删除堆顶元素  其实是将堆顶元素跟对应的位置交换  最大堆实现从小到大排序  最小堆实现从大到小的排序
        //循环次数数组长度减一

        for (int i = arr.length-1; i>0; i--) {
            //交换堆顶和当前索引的位置
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            // 调整二叉堆
            downAdjust(arr, 0, i-1);
        }

    }

    /**
     * 构建最小堆
     * @param arr 待调整的堆
     */
    public static void buildMinHeap(int[] arr){
        // 从最后一个非叶子节点  刚好是数组长度的一半的位置-1 依次下沉调整
        for(int i = arr.length / 2 ; i > 0 ;i--){
            downAdjust(arr,i-1,arr.length -1);
        }
    }

    /**
     * 下沉调整
     * @param arr 待调整的堆
     * @param parentIndex 要下沉的父节点
     * @param length 堆的有效大小
     */
    private static void downAdjust(int[] arr, int parentIndex, int length) {

        //拿到左孩子的索引  一定存在
        int leftChildIndex = (parentIndex << 1) + 1;

        //右孩子的索引可能不存在
        int rightChildIndex = leftChildIndex + 1;

        //判断左右孩子最小的一个是否小于父节点的值
        int parentValue = arr[parentIndex];

        while (leftChildIndex <= length){
            //判断是否有右孩子
            if(rightChildIndex < length){
                //有右孩子 找到最小的那个 用左节点的引用保存
                leftChildIndex = arr[leftChildIndex] < arr[rightChildIndex]? leftChildIndex :  rightChildIndex;
            }
            //没有右孩子 则左孩子一定是最小的

            //判断最小的和父节点的值 如果父节点的值小于任何一个子节点的值 无需调整直接跳出
            if(parentValue < arr[leftChildIndex]){
                break;
            }

            //将父节点的值变为较小的那个子节点的值
            arr[parentIndex] = arr[leftChildIndex];

            //更换父节点的索引 进入下一次循环
            parentIndex = leftChildIndex;

            leftChildIndex = (parentIndex << 1) + 1;
            rightChildIndex = leftChildIndex + 1;
        }
        //因为前边没有进行交换只是单向赋值  最后要将父节点的值再设置回去
        arr[parentIndex] = parentValue;
    }

    /**
     * 上浮调整  当插入一个数据时调用 插入的数据一定在数组的最后位置
     * @param arr 待调整的堆
     */
    public static void upAdjust(int[] arr){
        int childIndex = arr.length - 1;
        //父节点
        int parentIndex = (childIndex - 1) >> 1 ;

        int childValue = arr[childIndex];

        while (childIndex > 0 && childValue < arr[parentIndex] ){
            // 节点上浮 将父节点的值赋值给子节点
            arr[childIndex] = arr[parentIndex];

            //更新父子节点的索引进行下一次比较
            childIndex = parentIndex;
            parentIndex= (childIndex - 1) >> 1 ;
        }
        arr[childIndex] = childValue;
    }



}

