/**
 * Created by Administrator on 2017/4/7.
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import static java.lang.System.exit;

public class java_opeartion {
    private int i=0,j=0;
    private  ArrayList<String> list1=new ArrayList<String>();  //存算式用于判断是否生成相同算式
    private  ArrayList<String> list2=new ArrayList<String>();  //生成算式
   // private  ArrayList<String> list3=new ArrayList<String>();
    private Stack stack = new Stack();
    private  Stack sufStack = new Stack();   //计算后缀表达式
    private ArrayList sufList = new ArrayList();    //后缀表达式
    private String str2="";

    Scanner op=new Scanner(System.in);
    String[] re1=new String[2];
    String[] re2=new String[2];
    int r1,r2,r3,r4;

    String huajian(String text){             //化简

        if(text.indexOf("/")!=-1) {
            re1 = text.split("/");
            r1 = Integer.parseInt(re1[0]);
            r2 = Integer.parseInt(re1[1]);
            if (r1 == 0)
                return "0";
          else {
                for (int i = 1; i <=  Math.abs(r1); i++) {
                    if (r1 % i == 0 && r2 % i == 0) {
                        r1 = r1 / i;
                        r2 = r2 / i;
                        i = 1;
                    }
                    if (r2 == 1)
                        return r1 + "";
                    if(r2 == -1)
                        return "-"+r1;
                }
                if(re1[1].contains("-")) {
                    re2=re1[1].split("-");

                    return "-" + r1 + "/" + re2[1];
                }else {
                    return r1 + "/" + r2;
                }
            }

        }
        else
            return text;



    }

    void output(){      //输出
        int i=1,j=0;
        int n=10;
        String out="";
        int p=0,khn=0;
        int q1=(int)((Math.random()*2)+5);    //算式长度
        //int q2=(int)((Math.random()*2));     //生成括号 ,1生成括号
        String[] fh={"+","-","×","÷"};
        Random random=new Random();
        while(true){
            while(i<=q1){
                int index=random.nextInt(fh.length);  //随机生成运算符
                int a=(int)(Math.random()*2);    //生成随机数用来判断整数和分数
                int q2=(int)((Math.random()*10)+1);     //生成括号 ,>1生成括号
                int q3=(int)((Math.random()*2));

                if(a==0){
                    int p1=(int)((Math.random()*n)+1);
                    if(q2>1&&i<q1-2&&i!=1) {
                        list2.add("(");
                        p=i;
                        khn++;
                    }
                    list2.add(String.valueOf(p1));
                    if(list2.contains("(")&&q3==1&&p!=i&&khn!=0) {
                        list2.add(")");
                        khn--;
                    }
                    if(i!=q1){
                        list2.add(" "+fh[index]+" ");
                    }
                    if(i==q1&&khn!=0){
                        for(int x=0;x<khn;x++){
                            list2.add(")");
                        }
                    }
                }
                if(a==1){
                    int p2=(int)((Math.random()*n)+1);
                    int p3=(int)((Math.random()*p2)+1);
                    if(q2>1&&i<q1-2&&i!=1) {
                        list2.add("(");
                        p=i;
                        khn++;
                    }
                    list2.add(huajian(p2+"/"+p3));
                    if(list2.contains("(")&&q3==1&&p!=i&&khn!=0) {
                        list2.add(")");
                        khn--;
                    }
                    if(i!=q1){
                        list2.add(" "+fh[index]+" ");
                    }
                   if(i==q1&&khn!=0){
                         for(int x=0;x<khn;x++){
                            list2.add(")");
                        }
                    }
                }
                i++;
            }
            for (j = 0; j < list2.size(); j++) {
                out = out + list2.get(j);
            }
            list1.add(out);
            int x = judge(out);    //判断重复算式
            if (x == 0)
                break;
    }
        System.out.println(out);
        intStack(list2);
        sufOpear(sufList);
        check(str2);
        list2.clear();
    }

    String opear(String x,String y,String z) {          //  计算
        int n1, n2, n3, n4, n5, n6;
        String result = "";
        String[] aa = new String[2];
        String[] bb = new String[2];
        String[] cc = new String[2];


        if (x.indexOf("/") == -1 && y.indexOf("/") == -1) {
            int a = Integer.parseInt(x);
            int b = Integer.parseInt(y);
            if (z.equals(" + "))
                result = String.valueOf(a + b);
            if (z.equals(" - "))
                result = String.valueOf(a - b);
            if (z.equals(" × "))
                result = String.valueOf(a * b);
            if (z.equals(" ÷ "))
                result = huajian(a + "/" + b);
        }
        if (x.indexOf("/") == -1 && y.indexOf("/") != -1) {
            int a = Integer.parseInt(x);

            aa = y.split("/");
            n1 = Integer.parseInt(aa[0]);
            n2 = Integer.parseInt(aa[1]);

            if (z.equals(" + "))
                result = huajian((a * n2 + n1) + "/" + n2);
            if (z.equals(" - "))
                result = huajian((a * n2 - n1) + "/" + n2);
            if (z.equals(" × "))
                result = huajian((a * n1) + "/" + n2);
            if (z.equals(" ÷ "))
                result = huajian((a * n2) + "/" + n1);
        }
        if (x.indexOf("/") != -1 && y.indexOf("/") == -1) {
            int a = Integer.parseInt(y);

            aa = x.split("/");
            n1 = Integer.parseInt(aa[0]);
            n2 = Integer.parseInt(aa[1]);

            if (z.equals(" + "))
                result = huajian((a * n2 + n1) + "/" + n2);
            if (z.equals(" - "))
                result = huajian((n1 - a * n2) + "/" + n2);
            if (z.equals(" × "))
                result = huajian((a * n1) + "/" + n2);
            if (z.equals(" ÷ "))
                result = huajian(n1 + "/" + (a * n2));
        }
        if (x.indexOf("/") != -1 && y.indexOf("/") != -1) {
            aa = x.split("/");
            bb = y.split("/");
            n1 = Integer.parseInt(aa[0]);
            n2 = Integer.parseInt(aa[1]);
            n3 = Integer.parseInt(bb[0]);
            n4 = Integer.parseInt(bb[1]);

            if (z.equals(" + "))
                result = huajian((n1 * n4 + n3 * n2) + "/" + (n2 * n4));
            if (z.equals(" - "))
                result = huajian((n1 * n4 - n3 * n2) + "/" + (n2 * n4));
            if (z.equals(" × "))
                result = huajian((n1 * n3) + "/" + (n2 * n4));
            if (z.equals(" ÷ "))
                result = huajian((n1 * n4) + "/" + (n2 * n3));
        }

        return result;

    }
    void intStack(ArrayList list){              //转换成后缀表达式
        for (int i=0;i<list.size();i++){
            if(isNumber(String.valueOf(list.get(i)))) {
                //System.out.print(list.get(i));
                sufList.add(list.get(i));
            }else if(list.get(i).toString().equals("(")){
                stack.push(list.get(i));
            }else if(list.get(i).toString().equals(")")){
                while(!stack.empty()){
                    String stacktop = stack.pop().toString();
                    if(!stacktop.equals("(")){
                        //System.out.print(stacktop);
                        sufList.add(stacktop);
                    }else {
                        break;
                    }
                }
            }else {
                if(!stack.empty()) {
                    while(!stack.empty()) {
                        String stacktop = stack.pop().toString();
                        if (mark(String.valueOf(list.get(i))) > mark(stacktop)||stacktop.equals("(")) {  //取出运算级大的符号
                            stack.push(stacktop);
                            break;
                        }
                        else {
                            //System.out.print(stacktop);
                            sufList.add(stacktop);
                        }
                    }
                    stack.push(list.get(i));
                } else {
                    stack.push(list.get(i));
                }
            }
        }
        // System.out.println(stack);
        while(!stack.empty()){
            String stacktop = stack.pop().toString();
            // System.out.print(stacktop);
            sufList.add(stacktop);
        }
    }
    void sufOpear(ArrayList list){      //后缀表达式求解
        for(int i=0;i<list.size();i++){
            if(isNumber(list.get(i).toString())){
                sufStack.push(list.get(i));
            }else{
                String stacktop = sufStack.pop().toString();
                String stacksecond = sufStack.pop().toString();
                sufStack.push(opear(stacksecond,stacktop,list.get(i).toString()));
            }
        }
    }
    int mark(String str){        //判断运算符的优先级
        switch (str){
            case " + ":
            case " - ":
                return 1;
            case " × ":
            case " ÷ ":
                return 2;
            case "(":
            case ")":
                return 3;
        }
        return 0;
    }
    boolean isNumber(String str){                       //正值表达式判断是否为数字和分数
        String test = "^-?[0-9]+(/[1-9][0-9]*)?$";
        boolean  right = str.matches(test);
        return right;
    }

    void check(String str2){        //检查结果是否正确
        System.out.print("=");
        String result = sufStack.pop().toString();
       // System.out.println(result);
        while(true) {
            String sum = op.nextLine();
            i++;
            if (isNumber(sum) == false) {
                if (sum.equals("exit"))                       //输入exit 结束程序
                    exit(0);
                else
                    System.out.print("请输入规范!请重新输入答案=");

            } else {
                System.out.print("结果为：" + result);
                if (result.equals(sum)) {
                    System.out.print("	结果正确    ");
                    j++;
                } else
                    System.out.print("	结果错误    ");
                System.out.println("准确率" + j + "/" + i);

                break;
            }
        }
    }
    int judge(String form3){                //判断是否生成相同的算式
        int i;
        String[] aa=new String[2];
        String a,b;
        if(list1.size()==1)
            return 0;
        else {
            for (i = 0; i < list1.size() - 1; i++) {
                if (form3.equals(list1.get(i)))
                    return 1;
                if (form3.indexOf("+") != -1) {
                    aa = form3.split("\\+");
                    a = aa[1] + aa[0];
                    if (a.equals(list1.get(i)))
                        return 1;
                }
            }
        }
        return 0;
    }
    public static void main(String[] args) {
        java_opeartion aa = new java_opeartion();
        System.out.println("开始进入四则运算，输入exit退出");
        while(true)
        {
            aa.output();
        }

    }
}
