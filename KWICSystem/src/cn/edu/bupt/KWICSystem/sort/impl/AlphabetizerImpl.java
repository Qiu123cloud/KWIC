package cn.edu.bupt.KWICSystem.sort.impl;

import cn.edu.bupt.KWICSystem.shift.CircularShifter;
import cn.edu.bupt.KWICSystem.sort.Alphabetizer;
//按字母顺序对循环移位进行排序
public class AlphabetizerImpl implements Alphabetizer {



    private int lineSort[];

    private CircularShifter shift;
    @Override
    public String[] getLine(int lines) {
        return shift.getLine(lineSort[lines]);
    }

    @Override
    public String getLineAsString(int lines) {
        return shift.getLineAsString(lineSort[lines]);
    }

    @Override
    public int getLineCount() {
        return shift.getLineCount();
    }

    @Override
    public void alpha(CircularShifter shif) {
        this.shift = shif;

        // 初始化字符串数组
        lineSort = new int[this.shift.getLineCount()];
        for(int i = 0; i < lineSort.length; i++)
            lineSort[i] = i;
        //heapsort();//堆排
        quickSort(lineSort,0,lineSort.length-1);//快排

    }
    private void quickSort(int[] lineSort,int left,int right)            //快速排序算法
    {
        int f;
        String  t;
        int rtemp,ltemp;

        ltemp=left;
        rtemp=right;

        f=(left+right+1)/2;                        //分界值
        while(ltemp<rtemp)
        {
            while(shift.getLineAsString(lineSort[ltemp]).compareTo(shift.getLineAsString(lineSort[f])) <0)
            {
                ++ltemp;
            }
            while(shift.getLineAsString(lineSort[rtemp]).compareTo(shift.getLineAsString(lineSort[f])) >0)
            {
                --rtemp;
            }
            if(ltemp<=rtemp)
            {
                int tmp = lineSort[ltemp];
                lineSort[ltemp] = lineSort[rtemp];
                lineSort[rtemp] = tmp;

                --rtemp;
                ++ltemp;
            }
        }
        if(ltemp==rtemp)
        {
            ltemp++;
        }
        if(left<rtemp)
        {
            quickSort(lineSort,left,ltemp-1);            //递归调用
        }
        if(ltemp<right)
        {
            quickSort(lineSort,rtemp+1,right);            //递归调用
        }
    }
    private void heapsort()
    {
        // 创建堆
        for(int i = (lineSort.length / 2 - 1); i >= 0; i--)
            siftDown(i, lineSort.length);

        // 移除根并重新创建堆
        for(int i = (lineSort.length - 1); i >= 1; i--){

            // 删除根
            int tmp = lineSort[0];
            lineSort[0] = lineSort[i];
            lineSort[i] = tmp;

            // 重新创建堆
            siftDown(0, i);
        }
    }
    private void siftDown(int root, int bottom){
        int max_child = root * 2 + 1;

        while(max_child < bottom){
            if((max_child + 1) < bottom)
                if(shift.getLineAsString(lineSort[max_child + 1]).compareTo(shift.getLineAsString(lineSort[max_child])) > 0)
                    max_child++;

            if(shift.getLineAsString(lineSort[root]).compareTo(shift.getLineAsString(lineSort[max_child])) < 0){
                int tmp = lineSort[root];
                lineSort[root] = lineSort[max_child];
                lineSort[max_child] = tmp;
                root = max_child;
                max_child = root * 2 + 1;
            }else
                break;
        }
    }



}
