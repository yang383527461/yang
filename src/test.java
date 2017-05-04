import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * Created by Administrator on 2017/4/8.
 */
public class test {
    private  ArrayList<String> list=new ArrayList<String>();
    private  ArrayList<String> list2=new ArrayList<String>();
    private  ArrayList<String> list3=new ArrayList<String>();
    private  String str2="";
    Scanner op=new Scanner(System.in);
    String[] re1=new String[2];
    String[] re2=new String[2];
    int r1,r2,r3,r4;

    String huajian(String text){

        if(text.indexOf("/")!=-1) {
            re1 = text.split("/");
            r1 = Integer.parseInt(re1[0]);
            r2 = Integer.parseInt(re1[1]);

            if (r1 == 0)
                return "0";
            else {
                for (int i = 1; i <= Math.abs(r1); i++) {
                    if (r1 % i == 0 && r2 % i == 0) {
                        r1 = r1 / i;
                        r2 = r2 / i;
                        i = 1;
                    }
                    if (r2 == 1)
                        return r1 + "";
                }
                return r1+"/"+r2;
            }

        }
        else
            return text;

    }
    String huajian1(String text){             //化简

        if(text.indexOf("/")!=-1) {
            re1 = text.split("/");
            r1 = Integer.parseInt(re1[0]);
            r2 = Integer.parseInt(re1[1]);
            if (r1 == 0)
                return "0";

            else {
                for (int i = 1; i <= r1; i++) {
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
/*    int judge(String form3){                //判断是否生成相同的算式
        int i;
        String[] aa=new String[2];
        String a,b;
        if(list.size()==1)
            return 0;
        else {
            for (i = 0; i < list.size() - 1; i++) {
                if (form3.equals(list.get(i)))
                    return 1;
                if (form3.indexOf("+") != -1) {
                    aa = form3.split("\\+");
                    a = aa[1] + aa[0];
                    if (a.equals(list.get(i)))
                        return 1;

                }
            }
        }
        return 0;
    }*/
boolean isNumeric1(String str) {     //判断是否为数字或分数
    for (int i = str.length(); --i >= 0; ) {
        if (!Character.isDigit(str.charAt(i))) {
            return false;
        }
        if (str.indexOf(" ") != -1)
            return false;
        if (str.indexOf("/") != -1 || str.indexOf("-") != -1) {
            if (str.indexOf("/") != -1) {
                String[] o = new String[2];
                o = str.split("/");
                if (o[1].equals("0"))
                    return false;
            }

        }
    }
    return true;
}

    void output(){
        int i=1,j=0;
        int n=20;
        String out="";
        int p=0,khn=0;
        int q1=(int)((Math.random()*3)+3);    //算式长度
        //int q2=(int)((Math.random()*2));     //生成括号 ,1生成括号
        String[] fh={"+","-","×","÷"};
        Random random=new Random();

        while(i<=q1){
            int index=random.nextInt(fh.length);  //随机生成运算符
            int a=(int)(Math.random()*2);//生成随机数用来判断整数和分数
            int q2=(int)((Math.random()*10));     //生成括号 ,1生成括号
            int q3=(int)((Math.random()*2));

            if(a==0){
                int p1=(int)((Math.random()*n)+1);
                if(q2>1&&i<q1-2) {
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
                if(q2>1&&i<q1-2) {
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

     /*   if(q2>1){
            int a1=(int)((Math.random()*(list2.size()-1))+2); //生成第一个括号的位置(2,list2.size())
            int a2=(int)((Math.random()*(list2.size()-1))+2); //生成第二个括号的位置

            if((list2.size() - a1)%2==0) {
                list2.add(list2.size() - a1, "(");
                list2.add(list2.size() - a1 + 3, ")");
          }
            else{
                a1=a1+1;

                list2.add(list2.size() - a1,"(");
                list2.add(list2.size() - a1 + 3,")");
*//*                for (j = 0; j < list2.size(); j++) {
                    System.out.print(list2.get(j));
                }
                System.out.println();*//*
            }
        }
        */
        for (j = 0; j < list2.size(); j++) {
            out=out+list2.get(j);
        }
        System.out.println(out);
        list2.clear();
    }
    void opear1(ArrayList list){       //多项式计算
        int a=0,b=0;
        int r1=0,r2=0;
        String str1;
        if(list.contains("(")){
            for(int i=0;i<list.size();i++){
                if(list.get(i).equals(")")) {
                    b = i;
                    for(int j=b;j>=0;j--){
                        if(list.get(j).equals("(")) {
                            a = j;
                            break;
                        }
                    }
                    break;
                }

            }
            if (b-a!=4){
        //        list.remove(b);
        //        list.remove(a);
                int c=a;
                for(;a<b-1;a++){
                    list3.add(String.valueOf(list.get(a+1)));
                }
                for(;b>=c;b--){
                    list.remove(b);
                }
                list.add(c,opear2(list3));
                opear1(list);
            }else{
                str1=opear(String.valueOf(list.get(a+1)),String.valueOf(list.get(b-1)),String.valueOf(list.get(a+2)));
                for(;b>=a;b--){
                    list.remove(b);
                }
                list.add(a,str1);
                opear1(list);

            }
            return;
        }
        if(list.contains(" × ")||list.contains(" ÷ ")) {
            if(list.contains(" × ")) {
                r1 = list.indexOf(" × ");
            }
            if(list.contains(" ÷ ")) {
                r2 = list.indexOf(" ÷ ");
            }
            if(r1!=0&&r2!=0) {
                a=r1<r2?r1:r2;
            }else{
                if(r1>0)
                    a=r1;
                else
                    a=r2;
            }

            str1 = opear(String.valueOf(list.get(a - 1)), String.valueOf(list.get(a + 1)), String.valueOf(list.get(a)));
            if (list.size() == 3) {
                str2 = str1;
            } else {
                list.remove(a + 1);
                list.remove(a);
                list.remove(a - 1);
                list.add(a - 1, str1);
                opear1(list);
                return;
            }
        }
        if(list.contains(" + ")||list.contains(" - ")) {
            if(list.contains(" + ")) {
                r1 = list.indexOf(" + ");
            }
            if(list.contains(" - ")) {
                r2 = list.indexOf(" - ");
            }
            if(r1!=0&&r2!=0) {
                a=r1<r2?r1:r2;
            }else{
                if(r1>0)
                    a=r1;
                else
                    a=r2;
            }
            str1=opear(String.valueOf(list.get(a-1)),String.valueOf(list.get(a+1)),String.valueOf(list.get(a)));
            if(list.size()==3) {
                str2= str1;
            }
            else {
                list.remove(a + 1);
                list.remove(a);
                list.remove(a - 1);
                list.add(a - 1, str1);
                opear1(list);
                return;
            }
        }
        list.clear();
       // System.out.println(str2);

    }
   String opear2(ArrayList list){
        opear1(list);
        return str2;
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
    boolean isNumeric(String str) {     //判断是否为数字或分数
        String[] o=new String[2];
        for (int i = 0;i<str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                if(String.valueOf(str.charAt(i)).equals("/")||String.valueOf(str.charAt(i)).equals("-"))
                {
                    o=str.split("/");
                    if((String.valueOf(str.charAt(i)).equals("/")&&o[1].equals("0"))
                            ||String.valueOf(str.charAt(0)).equals("/")
                            ||(!String.valueOf(str.charAt(0)).equals("-"))&&String.valueOf(str.charAt(i)).equals("-"))
                    {
                        return false;
                    }
                }
                else if(!String.valueOf(str.charAt(i)).equals("/")&&!String.valueOf(str.charAt(i)).equals("-"))
                    return false;
            }
            if(str.indexOf(" ")!=-1)
                return false;
        }
        return true;
    }

    void brackets(ArrayList list){
       // int brack1=(int)(Math.random()*2);
        int a=(int)(Math.random()*2);  //随机生成括号数量

        String str = "";
        for(int i=0;i<=a;i++){
            int brack1=(int)((Math.random()*(list.size()-1)));  //随机生成括号的位置
            int p;
            if(!list.contains("(")) {
                if (brack1 % 2 == 0) {
                    list.add(brack1, "(");
                    list.add(brack1 + 4, ")");
                } else {
                    brack1 = brack1 - 1;
                    list.add(brack1, "(");
                    list.add(brack1 + 4, ")");
                }
            }else{
                for(int j=brack1;j<brack1+4;j++)
                    str += list.get(j);
                for(int k=brack1+4;k>brack1;k--)
                    list.remove(k);
                list.add(brack1,str);

            }
       }
        System.out.println(list);
    }

    public static void main(String[] args) {
/*        String[] aa=new String[2];
        String a="ttt/o";
        aa=a.split("/");
        System.out.println(aa[0]);
        System.out.println(aa[1]);
        System.out.println(huajian(32,4));
        String aa="213/54";
        if(aa.indexOf("/")!=-1)  */
/*        test aa=new test();
        System.out.println(aa.huajian("1/4"));*/
/*
       Scanner aa=new Scanner(System.in);
       String sum=aa.nextLine();
        System.out.println(sum.length());
        System.out.println(sum.indexOf(" "));
*/
/*        int n1=1,n2=1,n3=3;
        ArrayList<String> list=new ArrayList<String>();
        list.add(String.valueOf(n1));
        list.add(String.valueOf(n2));
        if(list.get(0).equals(list.get(1)))
            System.out.println("111");
        else
            System.out.println("222");*/
/*        String[] aa=new String[2];
        String a="6+9",b;
        aa=a.split("\\+");
        System.out.println(aa[0]);*/
/*        test aa=new test();
        String form3="789";
        aa.list.add(form3);
        System.out.println(aa.list);
        if(aa.judge(form3)==0)
            System.out.println("111");
        else
            System.out.println("222");*/
/*        test aa=new test();
        String b;
        Scanner s=new Scanner(System.in);
        while(true) {
            b = s.nextLine();
            if (aa.isNumeric(b) == false)
                System.out.println("不是");
            else
                System.out.println("是");
        }
    */
/*    test aa=new test();
    aa.output();*/
/*       ArrayList<String> list3=new ArrayList<String>();
        test aa=new test();
        list3.add("1");
        list3.add("2");
        list3.add("3");
        list3.add("4");
        aa.opear(list3);
        System.out.println(list3.contains("5"));*/

   /*     test aa=new test();
        aa.output();*/
/*    String aaa="678";
        System.out.println(Integer.parseInt(aaa));"+","-","×","÷"}*/
        int r1=0,r2=4;
       // (5 ÷ (7/3 × 1 ÷ 8 + 4))
        test aa=new test();
        ArrayList<String> list=new ArrayList<String>();
    //   4/3 ÷ (2 - 10/9 + 5/3 ÷ 6)
        list.add("4/3");
        list.add(" ÷ ");

        list.add("(");
        list.add("2");
        list.add(" - ");
        list.add("10/9");
        list.add(" + ");
        list.add("5/3");
        list.add(" ÷ ");
        list.add("6");
        list.add(")");















        System.out.println(list);
        aa.opear1(list);
   //     aa.brackets(list);
        System.out.println(aa.str2);
/*
?     while(r1<10){
         aa.output();
         r1++;
     }
*/


/*        int i=list.indexOf("9");
        System.out.println(i);*/
   /**    String str="4/-3";
        System.out.println(aa.huajian1(str));*/
    }
}
