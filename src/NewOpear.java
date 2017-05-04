import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Administrator on 2017/4/24.
 */
public class NewOpear {
    private Stack stack = new Stack();
    private  Stack sufStack = new Stack();
    private ArrayList listss = new ArrayList();   //存算式（测试）
    private ArrayList sufList = new ArrayList();
    String[] re1=new String[2];
    String[] re2=new String[2];
    int r1,r2,r3,r4;

    void intStack(ArrayList list){
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

    void sufOpear(ArrayList list){
        for(int i=0;i<list.size();i++){
            if(isNumber(list.get(i).toString())){
                sufStack.push(list.get(i));
            }else{
                String stacktop = sufStack.pop().toString();
                String stacksecond = sufStack.pop().toString();
                sufStack.push(opear(stacksecond,stacktop,list.get(i).toString()));
            }
        }
        System.out.println(sufStack);
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
    int mark(String str){
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

    public static void main(String[] args) {
        NewOpear newopear = new NewOpear();
        Scanner scanner = new Scanner(System.in);
        newopear.listss.add("1");newopear.listss.add(" + ");
        newopear.listss.add("2");newopear.listss.add(" × ");newopear.listss.add("(");
        newopear.listss.add("3");newopear.listss.add(" + ");
        newopear.listss.add("4");newopear.listss.add(" × ");
        newopear.listss.add("5");newopear.listss.add(" + ");
        newopear.listss.add("6");newopear.listss.add(")");newopear.listss.add(" × ");
        newopear.listss.add("7");

        while(true) {
            newopear.intStack(newopear.listss);
            System.out.println(newopear.sufList);
            newopear.sufOpear(newopear.sufList);

            String str = scanner.next();
            System.out.println(newopear.isNumber(str));
        }
    }
}
